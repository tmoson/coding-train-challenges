(ns menger-fractal.components.box
  (:require [quil.core :as q]))

(defrecord DrawBox [^hash-map pos ^float r])

(defn new-box
  [^float x ^float y ^float z ^float r]
  (DrawBox. {:x x :y y :z z} r))

(defn show
  [^DrawBox box]
  (q/push-matrix)
  (q/translate (:x (:pos box)) (:y (:pos box)) (:z (:pos box)))
  (q/fill 255)
  (q/box (:r box))
  (q/pop-matrix))

(defn generate
  [^DrawBox box]
  (def acc '())
  (let [new-r (/ (:r box) 3)]
    (doseq [x (range -1 2)]
      (doseq [y (range -1 2)]
        (doseq [z (range -1 2)]
          (when (> (+ (.abs js.Math x) (.abs js.Math y) (.abs js.Math z))  1) ;; I wanted to use clojure's abs, but for some reason it was coming up as undefined
            (def acc (conj acc
                      (new-box (+ (:x (:pos box)) (* x new-r))
                        (+ (:y (:pos box)) (* y new-r))
                        (+ (:z (:pos box)) (* z new-r))
                        new-r)))))))
    acc))

