(ns infix-parser.prefix
  (:require [infix-parser.tree :refer [collect-values]]))

(defn in-this-order [operator-list left-branch-values right-branch-values]
  [operator-list left-branch-values right-branch-values])

(defn to-prefix-expression
  "convert a tree to prefix expression"
  [tree]
  (apply str (collect-values in-this-order tree)))
