(ns advent-of-code.core
  (:require [ysera.test :refer [is is= is-not]])
  (:require [clojure.string :as str]))

;----------
; Day 1
;----------

(defn sum-conseq-digit-str [digstr sum first-digit]
   (println digstr sum first-digit)
   (if (= 0 (count (next digstr)))
     (+ sum (if (=(bigint digstr) first-digit) first-digit 0))
     (sum-conseq-digit-str (subs digstr 1) (+ sum (if (= (first digstr) (fnext digstr))(bigint (subs digstr 0 1))0)) first-digit)))

  (defn advent-of-code-2017-12-01-recursive
    {:test (fn []
             (is= 3 (advent-of-code-2017-12-01-recursive "1122"))
             (is= 4 (advent-of-code-2017-12-01-recursive "1111"))
             (is= 0 (advent-of-code-2017-12-01-recursive "1234"))
             (is= 9 (advent-of-code-2017-12-01-recursive "91212129"))
             )}
    [input-string]
    (let [first-item (bigint(subs input-string 0 1))]
      (sum-conseq-digit-str input-string 0 first-item)
      ))

  (defn advent-of-code-2017-12-01-loop
    {:test (fn []
             (is= 3 (advent-of-code-2017-12-01-loop "1122"))
             (is= 4 (advent-of-code-2017-12-01-loop "1111"))
             (is= 0 (advent-of-code-2017-12-01-loop "1234"))
             (is= 9 (advent-of-code-2017-12-01-loop "91212129"))
             )}
    [input-string]
    (loop [substring input-string
           result 0]
      (if (= 0 (count (next substring)))
        (+ result
           (if (= (first substring) (first input-string))
             (bigint (str (first input-string)))
             0))
        (recur (subs substring 1)
               (+ result
                  (if (= (first substring)(fnext substring))
                    (bigint (str (subs substring 0 1)))
                    0))))))

  ;----------
  ; Day 2
  ;----------

 ; 5 1 9 5
 ; 7 5 3
 ; 2 4 6 8
