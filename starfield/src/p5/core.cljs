(ns p5.core
    (:require [p5.components.star :as star]))

(enable-console-print!)

;;;; STATE

(def size 800)

(defn stars []
  (loop [num-stars 0
         acc '[]]
    (if (< num-stars 800)
      (recur (inc num-stars) (conj acc (star/new-star size size)))
      acc)))

(defn move [stars]
  (map star/update-star stars))

(defonce *state (atom (stars)))


;;;; P5
(defn setup []
  (js/createCanvas size size)
  ;;(js/rectMode "CENTER")
  (js/fill 255)
  (js/noStroke))


(defn draw []
  (js/background 0)
  (js/translate (/ size 2) (/ size 2))
  (doseq [draw-star @*state]
    (star/show draw-star))
  (swap! *state move))


;;;; INIT

(doto js/window
  (aset "setup" setup)
  (aset "draw" draw))


;;;; FIGWHEEL

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! *state update-in [:__figwheel_counter] inc)
)
