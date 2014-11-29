(ns infix-parser.operator)

 ; defines the operators and their precedence of operation.

(def operators {\( 0, \+ 1, \- 1, \* 2, \/ 2, \^ 3})

(defn is-operator? [c] (contains? operators c))

(def get-precedence operators)

 ; convenience checkers

(defn left-parenthesis? [c] (= c \())

(defn right-parenthesis? [c] (= c \)))
