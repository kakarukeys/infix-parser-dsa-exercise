(ns infix-parser.parsing-test
  (:require [clojure.test :refer :all]
            [infix-parser.parsing :refer :all]))

(deftest test-parsing
  (testing "max-precedence-within-limit?"
    (testing "if no operator in stack -> always true"
      (is (max-precedence-within-limit? 0 {:operators '() :variables '(\a)})))

    (testing "otherwise check precedence of first operator"
      (with-redefs [infix-parser.operator/get-precedence {\+ 0, \* 1}]
        (is (max-precedence-within-limit? 1 {:operators '(\+ \*) :variables '(\a)}))
        (is (not (max-precedence-within-limit? 1 {:operators '(\* \+) :variables '(\a)}))))))

  (testing "collapse-till-max-precedence-okay"
    (with-redefs [max-precedence-within-limit? #(and (= %1 2)
                                                     (= (first (:operators %2)) \-))]
      (is (= (collapse-till-max-precedence-okay 2 {:operators '(\+ \- \*) :variables '(\1 \2 \3 \4)})
             {:operators '(\- \*) :variables '((\+ \2 \1) \3 \4)}))))

  (testing "collapse-till-left-parenthesis")
    (is (= (collapse-till-left-parenthesis {:operators '(\+ \( \*) :variables '(\1 \2 \3)})
           {:operators '(\( \*) :variables '((\+ \2 \1) \3)}))

  (testing "collapse-all"
    (is (= (collapse-all {:operators '(\+ \-) :variables '(\1 \2 \3)})
           {:operators () :variables '((\- \3 (\+ \2 \1)))})))

  (testing "read-char-seq"
    (is (= (read-char-seq (seq "a+(b-c)"))
           '(\+ (\a) (\- (\b) (\c)))))))
