(ns advent-of-code.core
  (:require [ysera.test :refer [is is= is-not]])
  (:require [clojure.string :as str]))

;----------
; Day 1
;----------

(defn get-input []
  (slurp "data/input-dec-01")
  )

(defn sum-conseq-digit-str [digstr sum first-digit]
   (if (= 0 (count (next digstr)))
     (+ sum (if (=(bigint digstr) first-digit) first-digit 0))
     (sum-conseq-digit-str (subs digstr 1) (+ sum (if (= (first digstr) (fnext digstr))(bigint (subs digstr 0 1))0)) first-digit)))

  (defn advent-of-code-2017-12-01-recursive ;for education purposes
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

(defn get-comparison-nbr-I
  {:test (fn []
           (is= "2" (get-comparison-nbr-I "246246" 2))
           (is= "6" (get-comparison-nbr-I "246246" 4))
           (is= "1" (get-comparison-nbr-I "123123" 5))
           )}
  [input-str current-position]
  (let [
        length (count input-str)
        circular-next (mod (+ 1 current-position) length)
        ]
    (subs input-str circular-next (+ 1 circular-next))
    )
  )

(defn get-comparison-nbr-II
  {:test (fn []
           (is= "6" (get-comparison-nbr-II "246246" 2))
           (is= "4" (get-comparison-nbr-II "246246" 4))
           (is= "3" (get-comparison-nbr-II "123123" 5))
           )}
  [input-str current-position]
  (let [
        length (count input-str)
        half-length (/ length 2)
        halfway-pos (mod (+ half-length current-position) length)
        ]
    (subs input-str halfway-pos (+ 1 halfway-pos))
    )
  )

(defn advent-of-code-2017-12-01-2
  {:test (fn []
           (is= 6 (advent-of-code-2017-12-01-2 "1212" get-comparison-nbr-II))
           (is= 0 (advent-of-code-2017-12-01-2 "1221" get-comparison-nbr-II))
           (is= 4 (advent-of-code-2017-12-01-2 "123425" get-comparison-nbr-II))
           (is= 12 (advent-of-code-2017-12-01-2 "123123" get-comparison-nbr-II))
           (is= 4 (advent-of-code-2017-12-01-2 "12131415" get-comparison-nbr-II))

           (is= 3 (advent-of-code-2017-12-01-2 "1122" get-comparison-nbr-I))
           (is= 4 (advent-of-code-2017-12-01-2 "1111" get-comparison-nbr-I))
           (is= 0 (advent-of-code-2017-12-01-2 "1234" get-comparison-nbr-I))
           (is= 9 (advent-of-code-2017-12-01-2 "91212129" get-comparison-nbr-I))

           )}
  [input-string get-comparison-nbr]
  (loop [current-pos 0
         result 0]
    (if (>= (+ 1 current-pos) (count input-string))
      (+ result
         (if (= (subs input-string current-pos) (get-comparison-nbr input-string current-pos))
           (bigint (subs input-string current-pos))
           0))
    (recur (+ current-pos 1)
           (+ result
              (if (= (subs input-string current-pos (+ 1 current-pos)) (get-comparison-nbr input-string current-pos))
                (bigint (subs input-string current-pos (+ 1 current-pos)))
                0))))))

;Part I
(advent-of-code-2017-12-01-loop (get-input))

;Part II
(advent-of-code-2017-12-01-2 (get-input) get-comparison-nbr-I)
(advent-of-code-2017-12-01-2 (get-input) get-comparison-nbr-II)

  ;----------
  ; Day 2
  ;----------

 ; 5 1 9 5
 ; 7 5 3
 ; 2 4 6 8

(defn advent-of-code-2017-12-02
  {:test (fn []
           (is= 18 (advent-of-code-2017-12-02 [[5 1 9 5]
                                               [7 5 3]
                                               [2 4 6 8]])))}
  [input-data]
  (reduce
    (fn [a v]
      (+ a (- (reduce max v) (reduce min v))))
      0
      input-data)
  )

;----------
; Day 3
;----------

;(declare create-matrix)
;(def matrix (create-matrix))

(defn create-matrix []
  ;543
  ;612
  ;789

  {1 [0 0], 2 [1 0], 3 [1 1]}
  )

(key (first (create-matrix)))
(val (first (create-matrix)))
(get (create-matrix) 3)


