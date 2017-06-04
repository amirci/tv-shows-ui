(ns web-ui.views
  (:require 
    [re-frame.core :as re-frame]
    [tv-lib.core :as lib]))


;; home
(defn- family-icon
  [show]
  (if (lib/family-show? show) 
    :fa-thumbs-o-up 
    :fa-thumbs-o-down))

(defn display-shows
  [shows]
  (for [{:keys [name description poster] :as show} shows]
    [:tr {:key name}
     [:td.poster [:img {:src poster :alt "Poster for the show"}]]
     [:td.name.description 
      [:h4 name]
      [:p description]]
     ;[:td.description description]
     [:td.family
      [:i.fa {:class (family-icon show)}]]]))

(defn shows-panel []
  (let [shows (re-frame/subscribe [:shows])]
    (fn []
      (if (empty? (:content @shows))
        [:div "Sorry, no list available :("]
        [:table.shows-list.table.table-striped
         [:thead
          [:tr
           [:th "Poster"]
           [:th "Name & Description"]
           ;[:th "Description"]
           [:th "Family?"]]]
         [:tbody
          (display-shows (:content @shows))]]))))

(defn home-panel
  []
  (let [ready? (re-frame/subscribe [:initialised?])]
    [:div.home-panel
     [:div.page-header
      [:h1 "TV shows"]
      [:p.lead "List of TV shows obtained from the local API"]]
     (if-not @ready?
       [:div "Loading shows ... please wait... "]
       [shows-panel])]))

;; about

(defn about-panel []
  (fn []
    [:div.about-panel.page-header
     [:h1 "This is the About Page."]
     [:p.lead "Demo done to show Clojurescript + re-frame"]]))


;; main

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn- is-active
  [actual expected]
  (when (= actual expected) "active"))

(defn show-menu
  [panel-name]
  [:nav.navbar.navbar-inverse.navbar-fixed-top
   [:div.container
    [:div.navbar-header
     [:a.navbar-brand {:href "#/"} "Clojurescript Demo"]]
    [:div.collapse.navbar-collapse {:id "navbar"}
     [:ul.nav.navbar-nav
      [:li {:id "home-menu" :class (is-active panel-name :home-panel)} [:a {:href "#/"} "Home"]]
      [:li {:id "about-menu" :class (is-active panel-name :about-panel)} [:a {:href "#/about"} "About"]]]]]])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [:div.something
       [show-menu @active-panel]
       [:div.container
        [show-panel @active-panel]]])))
