(ns infix-parser.operator-test
  (:require [clojure.test :refer :all]
            [infix-parser.operator :refer :all]))

(deftest test-operator
  (with-redefs [operators {\+ 1, \- 1, \* 2, \/ 2, \^ 3}]
    (testing "is-operator?"
      (is (is-operator? \+))
      (is (not (is-operator? \1)))))

  (testing "get-precedence"
    (is (= (get-precedence \*) 2)))

  (testing "left-parenthesis?"
    (is (left-parenthesis? \())
    (is (not (left-parenthesis? \1))))

  (testing "right-parenthesis?"
    (is (right-parenthesis? \)))
    (is (not (right-parenthesis? \1)))))
