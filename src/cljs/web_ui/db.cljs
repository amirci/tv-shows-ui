(ns web-ui.db)

(def default-db
  {:shows {:status :loading :error nil :content []}})

(defn status-loading?
  [{:keys [status]}]
  (= status :loading))

(defn status-loading
  []
  {:status :loading :error nil :content []})

(defn status-loaded
  [shows]
  {:status :loaded :error nil :content shows})


(defn status-failure
  [error]
  {:status :failure :error error :content []})
