(ns web-ui.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require 
    [re-frame.core :as re-frame]
    [web-ui.db :as db]))


(re-frame/reg-sub
 :shows
 (fn [db]
   (:shows db)))

(re-frame/reg-sub
 :active-panel
 (fn [db _]
   (:active-panel db)))

(re-frame/reg-sub
  :initialised?
  (fn  [{shows :shows} _]
    (not (db/status-loading? shows))))
