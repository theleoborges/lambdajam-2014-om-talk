# Om demo project - LambdaJam Brisbane 2014

Code samples used at the talk.

## Running the app

1.  From the root directory, compile and start up a REPL session:

        $ lein do cljsbuild once, repl

    This will compile the dummy sample ClojureScript namespace in
    `$AUSTIN/src/cljs`, which happens to require the ClojureScript
    browser-REPL client-side namespace.

2.  Once you're in the REPL there is a utility function which will start up the web server, create a new Clojurescript REPL environment and switch to it:

        (browser-repl!)

3.  At this point you can point your browser to [http://localhost:8080](http://localhost:8080) and have a play with the application. 

    Have a look at `src/cljs/om_lambdajam/core.cljs`. It contains the main app code. At the end of the file you'll find a comment block with the REPL commands used during the talk. Play with these or come up with some other examples that interact with the live app.
