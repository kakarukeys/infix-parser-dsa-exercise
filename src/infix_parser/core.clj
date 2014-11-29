(ns infix-parser.core
  (:require [infix-parser.parsing :refer [read-char-seq]]
            [infix-parser.prefix :refer [to-prefix-expression]]))

(defn skip-space [expression]
  (remove #(= \space %) expression))

(defn convert-expression
  "convert infix expression to prefix"
  [expression]
  (let [char-seq (skip-space expression)]
    (if (empty? char-seq)
      ""
      (->> char-seq
        read-char-seq
        to-prefix-expression))))
