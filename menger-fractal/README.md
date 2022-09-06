# menger-fractal

A Quil sketch designed to create a menger sponge. Code was made from following [The Coding Train's Menger Sponge Fractal video](https://youtu.be/LG8ZK-rRkXo)

## Usage

Run `lein figwheel` in your terminal. Wait for a while until you see `Successfully compiled "resources/public/js/main.js"`. Open [localhost:3449](http://localhost:3449) in your browser.

You can use this while developing your sketch. Whenever you save your source files the browser will automatically refresh everything, providing you with quick feedback. For more information about Figwheel, check the [Figwheel repository on GitHub](https://github.com/bhauman/lein-figwheel).

## Publishing your sketch

Before you publish your sketch, run `lein do clean, cljsbuild once optimized`. This will compile your code and run Google Closure Compiler with advanced optimizations. Take `resources/public/index.html` and `resources/public/js/main.js` and upload them to server of your choice.

## License

Copyright © 2017 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
