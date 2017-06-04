(ns web-ui.events
  (:require 
    [ajax.core :as ajax]
    [day8.re-frame.http-fx]
    [re-frame.core :as re-frame]
    [web-ui.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))

(re-frame/reg-event-db
  :loading-shows-success
  (fn [db [_ {shows :shows :as response}]]
    (assoc db :shows (db/status-loaded shows))))

(re-frame/reg-event-db
  :loading-shows-failure
  (fn [db [_ _]]
    (assoc db :shows (db/status-failure "Couldn't load the tv shows"))))

(re-frame/reg-event-fx
  :load-tv-shows 
  (fn
    [{db :db} _]
    {:http-xhrio {:method          :get
                  :uri             "http://localhost:3000/api/tv-shows"
                  :format          (ajax/json-request-format)
                  :response-format (ajax/json-response-format {:keywords? true}) 
                  :on-success      [:loading-shows-success]
                  :on-failure      [:loading-shows-failure]}
     :db (assoc db :shows (db/status-loading))}))
