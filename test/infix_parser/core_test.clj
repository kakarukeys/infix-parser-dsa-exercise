(ns infix-parser.core-test
  (:require [clojure.test :refer :all]
            [infix-parser.core :refer :all]))

(deftest test-core
  (testing "convert-expression"
    (are [input expected] (= (convert-expression input) expected)
      ""                  ""
      "a"                 "a"
      "a + b"             "+ab"
      "a + b * c"         "+a*bc"
      "a + b - c"         "-+abc"
      "a + b * c / d"     "+a/*bcd"
      "a + b * c - d"     "-+a*bcd"
      "a + b * c^d"       "+a*b^cd"
      "(a + b)"           "+ab"
      "(a + b) * c"       "*+abc"
      "a + (b - c)"       "+a-bc"
      "(a + b) * (c / d)" "*+ab/cd"
      "a + (b * (c - d))" "+a*b-cd"
      "(a + b * c)^d"     "^+a*bcd"
      )))
