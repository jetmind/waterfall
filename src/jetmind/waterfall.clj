(ns jetmind.waterfall
  (:require [manifold.executor :as ex]
            [manifold.stream.default :as d])
  (:import [java.util LinkedList]
           [org.apache.commons.collections4.queue CircularFifoQueue]))


;; playing dirty with this private, remove kids from the screen
(def ->Stream #'d/->Stream)

(def add! d/add!)


(defn- description [m]
  (merge m {:buffer-type :sliding}))


(defn sliding-stream
  ([buffer-size]
   (sliding-stream buffer-size nil (ex/executor)))

  ([buffer-size xform]
   (sliding-stream buffer-size xform (ex/executor)))

  ([buffer-size xform executor]
   {:pre [(pos? buffer-size)]}

   (let [consumers (LinkedList.)
         producers (LinkedList.)
         messages  (CircularFifoQueue. buffer-size)

         ;; Dirty little hack to trick add! into believing queue isn't full
         ;; https://github.com/ztellman/manifold/blob/0.1.6-alpha3/src/manifold/stream/default.clj#L260-L261
         capacity (inc buffer-size)

         add! (add! producers consumers messages capacity executor)
         add! (if xform (xform add!) add!)]
     (->Stream
       false
       description
       producers
       consumers
       buffer-size
       messages
       executor
       add!))))
