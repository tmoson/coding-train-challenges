(ns p5.components.star)

(defrecord Star [x y z pz])

(defn new-star
  ([]
    (let [z (/ (.-width js/window) 2)]
      (Star. (rand-nth (range (- (.-width js/window)) (.-width js/window))) (rand-nth (range (- (.-height js/window)) (.-height js/window))) z z)))
  ([width height]
    (let [z (rand width)]
      (Star. (rand-nth (range (- width) width)) (rand-nth (range (- height) height)) z z))))

(defn show [^Star show-star]
  (js/fill 255)
  (js/noStroke)
  (let [sx (js/map (/ (:x show-star) (:z show-star)) 0 1 0 (/ (.-width js/window) 2))
        sy (js/map (/ (:y show-star) (:z show-star)) 0 1 0 (/ (.-height js/window) 2))
        px (js/map (/ (:x show-star) (:pz show-star)) 0 1 0 (/ (.-width js/window) 2))
        py (js/map (/ (:y show-star) (:pz show-star)) 0 1 0 (/ (.-height js/window) 2))
        r (js/map (:z show-star) 0 (/ (.-width js/window) 2) 16 0)]
    (js/ellipse sx sy r r)
    (when (and (> (:z show-star) 0) (> (:pz show-star) 0)) ;; for some reason, when pz or z is 0, it was still drawing
      (js/stroke 255)
      (js/line px py sx sy))))

(defn update-star
  [^Star star]
  (if (> (:z star) 0)
    (if (> (.-mouseX js/window) 0) ;; mouseX being outside of the window caused the draw to fail at first
      (Star. (:x star) (:y star) (int (- (:z star) (js/map (.-mouseX js/window) 0 (.-width js/window) 0 30))) (:z star))
      (Star. (:x star) (:y star) (- (:z star) 10) (:z star)))
    (new-star)))
