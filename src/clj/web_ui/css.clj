(ns web-ui.css
  (:require [garden.def :refer [defstyles]]))

(defstyles screen
  [:body {:padding-top :50px}]
  [:.nav
   [:.active {:border-bottom "2px solid lightpink"}]]
  [:.shows-list
    [:.poster 
     [:img {:width :100px}]]
    [:.family {:text-align :center}
     [:i {:font-size :30px}]]
    ;[:.name {:white-space :nowrap}]
    [:.description {:width :80%}]])
