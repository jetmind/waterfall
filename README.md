# waterfall

[Manifold](https://github.com/ztellman/manifold/) utilities.

- Creation of streams with sliding buffer

## Usage

```clojure
(require '[jetmind.waterfall :as w])

(def s (w/sliding-buffer 10))
```

Works pretty much as you would expect. `put!`'s always succeed (replacing oldest
element if buffer is full).
