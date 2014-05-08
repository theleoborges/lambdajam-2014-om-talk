(ns om-lambdajam.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [clojure.string :as string]
            [om-lambdajam.sample-data :refer [speaker-data]]
            [clojure.browser.repl]))

(enable-console-print!)

(extend-type string
  ICloneable
  (-clone [s] (js/String. s)))

(extend-type js/String
  ICloneable
  (-clone [s] (js/String. s))
  om/IValue
  (-value [s] (str s)))

(defn display [show]
  (if show
    #js {}
    #js {:display "none"}))

(defn handle-change [e text owner]
  (om/transact! text (fn [_] (.. e -target -value))))

(defn commit-change [text owner]
  (om/set-state! owner :editing false))

(defn editable [text owner]
  (reify
    om/IInitState
    (init-state [_]
      {:editing false})
    om/IRenderState
    (render-state [_ {:keys [editing]}]
      (dom/li nil
              (dom/span #js {:style (display (not editing))} (om/value text))
              (dom/input
               #js {:style (display editing)
                    :value (om/value text)
                    :onChange #(handle-change % text owner)
                    :onKeyPress #(when (== (.-keyCode %) 13)
                                   (commit-change text owner))
                    :onBlur (fn [e] (commit-change text owner))})
              (dom/button
               #js {:style (display (not editing))
                    :onClick #(om/set-state! owner :editing true)}
               "Edit")))))

(defn speakers [app]
  (->> (:speakers app)
       (mapv (fn [x]
               (if (:sessions x)
                 (update-in x [:sessions]
                            (fn [cs] (mapv (:sessions app) cs)))
                 x)))))

(def app-state   (atom speaker-data))
(def app-history (atom [@app-state]))

(add-watch app-state :history
           (fn [_ _ _ n]
             (when-not (= (last @app-history) n)
               (swap! app-history conj n))
             (let [c (count @app-history)]
               (prn c " Saved items in app history"))))

(defn undo []
  (when (> (count @app-history) 1)
    (swap! app-history pop)
    (reset! app-state (last @app-history))))

(defn reset-app-state []
  (reset! app-state (first @app-history))
  (reset! app-history [@app-state]))

(defn speaker-details-view [app owner]
  (reify
    om/IRender
    (render [_]
      (if-let [speaker (first (filterv :selected (:speakers app)))]
        (dom/div #js {:id "speaker-details"
                      :style #js {:float "right"
                                  :width "50%"}}
                 (dom/h2 nil "Speaker bio")
                 (dom/h3 #js {:style #js {:margin-bottom "0px"}} (:name speaker))
                 (dom/em nil (om/build editable (:bio-short speaker)))(dom/p nil)
                 (dom/div nil (:bio-long speaker)))
        (dom/div nil)))))

(defn display-speaker-details [speaker owner]
  (om/transact!
   (om/get-shared owner :app-state)
   :speakers
   (fn [speakers]
     (mapv (fn [speaker]
             (dissoc speaker :selected))
           speakers)))
  (om/transact! speaker :selected (fn [_] true)))

(defn speaker-view [speaker owner]
  (reify
    om/IRender
    (render [_]
      (dom/li nil
              (dom/div nil
                       (dom/a
                        #js {:href "#"
                             :onClick #(display-speaker-details speaker owner)}
                        (:name speaker))
                       (dom/em nil (str " (" (:bio-short speaker) ")")))
              (dom/label nil "Sessions")
              (apply dom/ul nil
                     (map #(om/build editable %) (:sessions speaker)))))))

(defn speakers-view [app owner]
  (reify
    om/IRender
    (render [_]
      (dom/div nil
               (dom/div #js {:id "speakers"
                             :style #js {:float "left"}}
                        (dom/h2 nil "Speakers")
                        (dom/button #js {:onClick undo} "Undo")
                        (dom/button #js {:onClick reset-app-state} "Reset")
                        (apply dom/ul nil
                               (om/build-all speaker-view (speakers app)
                                             {:shared {:app-state app}})))
               (om/build speaker-details-view app)))))

(defn sessions-view [app owner]
  (reify
    om/IRender
    (render [_]
      (dom/div #js {:id "sessions"}
               (dom/h2 nil "Sessions")
               (apply dom/ul nil
                      (map #(om/build editable %) (vals (:sessions app))))))))

(om/root speakers-view app-state
         {:target (. js/document (getElementById "speakers"))})

(om/root sessions-view app-state
         {:target (. js/document (getElementById "sessions"))})


;; bREPL interaction examples
(comment
  (js/alert "Hi LambdaJam 2014!")

  (def app-state-copy @app-state)

  (reset! app-state  (update-in @app-state [:speakers]
                                (fn [speakers]
                                  (into [] (drop 3 speakers)))))

  (reset! app-state app-state-copy)

  (defn update-bio-long [speakers speaker-name new-bio]
    (let [speaker-idx  (reduce (fn [acc speaker]
                                 (if (= (:name speaker) speaker-name)
                                   (reduced acc)
                                   (inc acc)))
                               0
                               speakers)]
      (update-in speakers [speaker-idx]
                 (fn [old-speaker]
                   (assoc old-speaker :bio-long new-bio)))))

  (swap! app-state
         (fn [state]
           (update-in state [:speakers]
                      (fn [speakers]
                        (update-bio-long speakers "Leonardo Borges"
                                         "I'm live coding at LambdaJam Brisbane, 2014!")))))

  (reset! app-state app-state-copy)

  (defn counter [app owner]
    (reify
      om/IInitState
      (init-state [_]
        {:clicks 0})
      om/IRenderState
      (render-state [_ state]
        (dom/div #js {:style (display (:display app))}
                 (dom/button #js {:onClick
                                  #(om/set-state! owner :clicks (inc (:clicks state)))}
                             "Click me!")
                 (str "Clicks " (:clicks state))))))

  (def counter-state (atom {:display true}))
  (om/root counter counter-state
           {:target (. js/document (getElementById "hello"))})

  (reset! counter-state {:display false})
  )
