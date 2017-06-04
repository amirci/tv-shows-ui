(ns web-ui.css
  (:require [garden.def :refer [defstyles]]))

(defstyles screen
  [:body {:padding-top :50px}]
  [:.shows-list
    [:.poster 
     [:img {:width :100px}]]
    [:.name {:white-space :nowrap}]
    [:.description {:width :80%}]])
