(ns doublets.solver
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))

(def words (-> "words.edn"
               (io/resource)
               (slurp)
               (read-string)))

(defn single-step-away-words [word words used-words]
  (let
    [
      only-one-char-diff? (fn [pairs] (= (count (filter #(not= (first %) (second %)) pairs)) 1))
      same-length-words (fn [word words] (filter #(= (count word) (count %)) words))
      non-used-words (fn [words used-words] (filter #(not (contains? used-words %)) words))
      word-pairs (fn [word words] (map vector (repeat word) words))
      pair-chars (fn [word1 word2] (map vector (seq word1) (seq word2)))
    ]
    (map #(second %) (filter #(only-one-char-diff? (pair-chars (first %) (second %))) (word-pairs word (same-length-words word (non-used-words words used-words)))))
    ))

(defn doublets [word1 word2]
  "make me work")
