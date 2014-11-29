(ns infix-parser.parsing
  (:require [infix-parser.operator :refer :all]
            [infix-parser.tree :refer [to-branch]]))

 ; functions to convert stacks of variables and operators to trees

(defn form-branch-on-top [{[first-var second-var & rest-vars] :variables [first-op & rest-ops] :operators}]
  {:variables (cons (to-branch first-op second-var first-var) rest-vars)
   :operators (or rest-ops ())})

(defn form-branch-on-top-till [predicate memo]
  (->> memo
       (iterate form-branch-on-top)
       (filter predicate)
       first))

(defn max-precedence-within-limit? [upper-bound {operators :operators}]
  (or (empty? operators)
      (< (get-precedence (first operators)) upper-bound)))

(defn collapse-till-max-precedence-okay [upper-bound memo]
  (form-branch-on-top-till #(max-precedence-within-limit? upper-bound %) memo))

(defn collapse-till-left-parenthesis [memo]
  (form-branch-on-top-till #(left-parenthesis? (first (:operators %))) memo))

(defn collapse-all [memo]
  (form-branch-on-top-till #(empty? (:operators %)) memo))

; functions to parse character sequence and output a tree

(defn read-char [memo c]
  (apply update-in
         (cond
           (right-parenthesis? c) [(collapse-till-left-parenthesis memo) [:operators] rest]
           (left-parenthesis? c)  [memo [:operators] conj c]
           (is-operator? c)       [(collapse-till-max-precedence-okay (get-precedence c) memo) [:operators] conj c]
           :else                  [memo [:variables] conj (to-branch c)])))

(defn read-char-seq [char-seq]
  (->> char-seq
    (reduce read-char {:variables '() :operators '()})
    collapse-all
    :variables
    first))
