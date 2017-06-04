(ns web-ui.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :shows
 (fn [db]
   (:shows db)))

(re-frame/reg-sub
 :active-panel
 (fn [db _]
   (:active-panel db)))