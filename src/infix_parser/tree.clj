(ns infix-parser.tree)

; a branch is a list of one or three elements:
; '(node-value) or '(node-value child-branch-1 child-branch-2)

(def to-branch list)

(defn is-leave? [branch] (= (count branch) 1))

; TODO: how to write this using loop recur?
(defn collect-values [permute branch]
  (if (is-leave? branch)
    branch
    (let [[node-value child-branch-1 child-branch-2] branch]
      (apply concat
        (permute
          (list node-value)
          (collect-values permute child-branch-1)
          (collect-values permute child-branch-2))))))
