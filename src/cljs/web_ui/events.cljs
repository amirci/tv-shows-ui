(ns web-ui.events
    (:require [re-frame.core :as re-frame]
              [web-ui.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))


;(re-frame/reg-event-fx    ;; <-- note the `-fx` extension
;  :request-it        ;; <-- the event id
;  (fn                ;; <-- the handler function
;    [{db :db} _]     ;; <-- 1st argument is coeffect, from which we extract db 
;   
;    ;; we return a map of (side) effects
;    {:http-xhrio {:method          :get
;                  :uri             "http://json.my-endpoint.com/blah"
;;;                  :format          (ajax/json-request-format)
;;;                  :response-format (ajax/json-response-format {:keywords? true}) 
;                  :on-success      [:process-response]
;                  :on-failure      [:bad-response]}
;     :db  (assoc db :loading? true)}))
