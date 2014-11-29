(ns infix-parser.prefix-test
  (:require [clojure.test :refer :all]
            [infix-parser.prefix :refer :all]))

(deftest test-prefix
  (testing "to-prefix-expression"
    (is (= (to-prefix-expression '(\+ (\a) (\- (\b) (\c))))
           "+a-bc"))))
