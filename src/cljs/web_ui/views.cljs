(ns web-ui.views
    (:require [re-frame.core :as re-frame]))


;; home
(defn display-shows
  [shows]
  (for [{:keys [name description]} shows]
    [:li.show {:key name}
     [:div.name name]
     [:div.description description]]))

(defn shows-panel []
  (let [shows (re-frame/subscribe [:shows])]
    (fn []
      [:h1 "List of TV shows"
       (if (empty? (:content @shows))
         [:div "Sorry, no list :("]
         [:ul.shows-list
          (display-shows (:content @shows))])])))

(defn home-panel
  []
  (let [ready? (re-frame/subscribe [:initialised?])]
    [:div.home-panel
     (if-not @ready?
       [:div "Loading shows ... please wait... "]
       [shows-panel])
     [:div [:a {:href "#/about"} "go to About Page"]]]))

;; about

(defn about-panel []
  (fn []
    [:div "This is the About Page."
     [:div [:a {:href "#/"} "go to Home Page"]]]))


;; main

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [show-panel @active-panel])))
