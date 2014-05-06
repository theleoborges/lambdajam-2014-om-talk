(ns om-lambdajam.sample-data)

(def speaker-data
  {:speakers
   [{:name "Mark Hibberd"
     :bio-short "Code"
     :bio-long "Mark Hibberd spends his days building products for NICTA and his nights contributing to open source software. Mark takes software development seriously. Valuing correctness and reliability, he is constantly looking to learn tools and techniques to support these goals. This approach has led to a history of building teams that utilise purely-functional programming techniques to help deliver robust products."
     :sessions [:5]}
    {:name "Edward Kmett"
     :bio-short "Software Engineering Lead at S&P Capital IQ"
     :bio-long "Edward spent most of his adult life trying to build reusable code in imperative languages before realizing he was building castles in sand. He converted to Haskell in 2006 while searching for better building materials. He now chairs the Haskell core libraries committee, collaborates with hundreds of other developers on over 150 projects on github, builds tools for quants and traders using the purely-functional programming-language Ermine for S&P Capital IQ, and is obsessed with finding better tools so that seven years from now he won’t be stuck solving the same problems with the same tools he was stuck using seven years ago."
     :sessions [:4]}
    {:name "Leonardo Borges"
     :bio-short "Clojure Developer, Organiser of clj-syd"
     :bio-long "A programming languages enthusiast, Leonardo loves writing code, contributing to open-source and speaking about subjects he feels strongly about. Currently undertaking the challenges of consulting at ThoughtWorks, his first contact with Functional Programming came from using Scheme while reading SICP.

Not too long after he fell in love with Clojure and has since been in a couple of commercial Clojure project, the latest one being for a financial services company.

He created and runs clj-syd, the Clojure Sydney User Group, and also blogs about other geeky stuff and plays the guitar in his spare time."
     :sessions [:1]}
    {:name "Julian Gamble"
     :bio-short "JVM Developer, Author of Clojure Recipes"
     :bio-long "Julian works in Financial Services and is a family man. In his spare time he writes and presents on all things software related. Julian is the author of the upcoming book Clojure Recipes (http://clojurerecipes.com/)."
     :sessions [:3]}
    {:name "Manuel Chakravarty"
     :bio-short "Associate Professor at UNSW"
     :bio-long "Manuel M. T. Chakravarty is an Associate Professor at the University of New South Wales. His main research interests are in functional programming languages, novel compiler technology, and parallel programming. He graduated from the University of Karlsruhe and received a doctoral degree from the Berlin Institute of Technology. He contributed to Haskell’s foreign function interface, the theory and implementation of type families, and multiple systems for data parallel programming of multicore CPUs and GPUs, including Accelerate, Data Parallel Haskell, and Repa. He receives his inspiration from combining theory with practice."
     :sessions [:6]}]
   :sessions
   {:1 "High Performance Web UI's with Om and React"
    :3 "Applying the paradigms of core.async in Clojure and ClojureScript"
    :4 "Functionally Oblivious and Succinct"
    :5 "The Art of Incremental Stream Processing"
    :6 "Foreign Inline Code in Haskell"
    }})
