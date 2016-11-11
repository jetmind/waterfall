# waterfall

[Manifold](https://github.com/ztellman/manifold/) utilities.

- Creation of streams with sliding buffer

[![Clojars Project](https://img.shields.io/clojars/v/jetmind/waterfall.svg)](https://clojars.org/jetmind/waterfall)

## Usage

Dependency: `[jetmind/waterfall "0.1.0"]`

```clojure
(require '[jetmind.waterfall :as w])

(def s (w/sliding-stream 10))
```

Works pretty much as you would expect. `put!`'s always succeed (replacing oldest
element if buffer is full).
