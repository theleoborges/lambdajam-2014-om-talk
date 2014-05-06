# Om demo project - LambdaJam Brisbane 2014

Code used to demonstrate [Om](https://github.com/swannodette/om), a Clojurescript interface to [React](http://facebook.github.io/react/).

## Running the app

1.  From the root directory, compile and start up a REPL session:

        $ lein do cljsbuild once, repl

    The first time will take a few seconds

2.  Once you're in the REPL there is a utility function which will start up the web server, create a new Clojurescript REPL environment and switch to it:

        (browser-repl!)
        
	This project uses [Austin](https://github.com/cemerick/austin) to provide a browser-connected REPL.

3.  At this point you can point your browser to [http://localhost:8080](http://localhost:8080) and have a play with the application. 

    Have a look at `src/cljs/om_lambdajam/core.cljs`. It contains the main app code. At the end of the file you'll find a comment block with the REPL commands used during the talk. Type these in the REPL or come up with some other examples that interact with the live app.
    
    Feel free to start the REPL in an environment like Emacs over nREPL. That way you can evaluate forms straight from your buffer with no copying/pasting.
