(ns wonderland-number.finder)

(defn wonderland-number []
  (let
    [hasSameDigits? (fn [n1 n2] (let [s1 (set (str n1))
                                      s2 (set (str n2))]
                                  (= s1 s2)))
     isWonderland? (fn [num] (and (hasSameDigits? num (* 2 num)) (hasSameDigits? num (* 3 num)) (hasSameDigits? num (* 4 num)) (hasSameDigits? num (* 5 num)) (hasSameDigits? num (* 6 num))))]
    (first (filter #(isWonderland? %) (range 100000 999999)))
    )
  )
