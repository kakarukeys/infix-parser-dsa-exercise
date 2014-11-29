(ns infix-parser.tree-test
  (:require [clojure.test :refer :all]
            [infix-parser.tree :refer :all]))

(deftest test-tree
  (testing "to-branch"
    (is (= (to-branch \1) '(\1)))
    (is (= (to-branch \+ \1 \2) '(\+ \1 \2))))

  (testing "collect-values"
    (is (= (collect-values #(list %2 %3 %1) '(\+ (\1) (\2)))
           '(\1 \2 \+)))))
