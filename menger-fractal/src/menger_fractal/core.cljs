(ns menger-fractal.core
  (:require [menger-fractal.components.box :as c-box]
            [quil.core :as q :include-macros true]
            [quil.middleware :as m]))

(defn setup []
  ; Set frame rate to 30 frames per second.
  (q/frame-rate 60)
  ; Set color mode to HSB (HSV) instead of default RGB.
  (q/color-mode :hsb)
  ; setup function returns initial state. It contains
  ; circle color and position.
  {:pt 0.0
   :sponge (list (c-box/new-box 0.0 0.0 0.0 200.0))})

(defn update-state [state]
  ;;(println "Current Sponge: " (:sponge state))
  {:pt (+ (:pt state) 0.01)
   :sponge (:sponge state)})

(defn mouse-pressed
  [state]
  {:pt (:pt state)
   :sponge (flatten (map c-box/generate (:sponge state)))})

(defn draw-state [state]
  (q/background 51)
  (q/stroke 255)
  (q/no-fill)
  (q/lights)
  (q/rotate-x (:pt state))
  (q/rotate-y (* 0.4 (:pt state)))
  (q/rotate-z (* 0.2 (:pt state)))
  (doseq [boxes (:sponge state)]
    (c-box/show boxes)))

; this function is called in index.html
(defn ^:export run-sketch []
  (q/defsketch menger-fractal
    :host "menger-fractal"
    :size [400 400]
    :renderer :p3d
    :mouse-pressed mouse-pressed
    ; setup function called only once, during sketch initialization.
    :setup setup
    ; update-state is called on each iteration before draw-state.
    :update update-state
    :draw draw-state
    ; This sketch uses functional-mode middleware.
    ; Check quil wiki for more info about middlewares and particularly
    ; fun-mode.
    :middleware [m/fun-mode]))

; uncomment this line to reset the sketch:
; (run-sketch)
