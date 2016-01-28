(ns fox-goose-bag-of-corn.puzzle)
;; http://math.stackexchange.com/questions/786881/wolf-cabbage-and-goat-using-dijkstra
;; http://yongouyang.blogspot.com/2013/04/solving-farmer-wolf-goat-cabbage-riddle.html
;; http://www.thesoftwaresimpleton.com/blog/2015/01/28/graph/
;; https://www.cs.unm.edu/~luger/ai-final/code/PROLOG.fwgc.html
;; http://hueypetersen.com/posts/2013/06/25/graph-traversal-with-clojure/

;; from http://rosettacode.org/wiki/Combinations#Clojure
(defn combinations
  [elements n]
  (letfn [(comb-aux
            [n start]
            (cond
              (= 0 n) []
              (= 1 n) (map vector elements)
              (for [x (range start n)
                    xs (comb-aux (dec m) (inc x))]
                (cons x xs)))
          )]
    (comb-aux n 0)))

(def start-pos [[[:fox :goose :corn :farmer] [:boat] []]])

(def end-pos [[] [:boat] [:fox :goose :corn :farmer]])

(defn valid-bank? [bank]
  ;; every bank with the farmer is valid
  ;; a bank with a creature by itself is valid
  ;; fox and corn is valid
  (or
    (contains? bank :farmer)
    (= 1 (count bank))
    (and (contains? bank :fox) (contains? bank :corn))
    )
  )

(defn valid-move? [pos] (let [[left-bank boat right-bank] pos] (and (valid-bank? left-bank) (valid-bank? right-bank))))

(defn generate-next-moves [pos]
  ;; this generates all possible moves after figuring out where you are
  ;; let's remember the rules: the farmer has to end up on the other side of the bank
  ;; the farmer may take 0 or 1 items from his bank to the other bank
  ;; so given that...we need to find the farmer
  ;; and generate possibilities from that
  (let [
        [[left-bank boat right-bank] pos]
        farmer-bank ((if (contains? left-bank :farmer) left-bank right-bank))
        non-farmer-bank ((if (contains? left-bank :farmer) right-bank left-bank))
        ]

    )
  )

(defn possible-next-moves [pos] (filter #(valid-move? %) (generate-moves pos)))

(should-take-move? [pos] (> 0 (count (possible-next-moves pos))))

(defn next-move [pos]
  ()
  )

(comment
  (defn seq-graph-dfs [g s]
    ((fn rec-dfs [explored frontier]
       (lazy-seq
         (if (empty? frontier)
           nil
           (let [v (peek frontier)
                 neighbors (g v)]
             (cons v (rec-dfs
                       (into explored neighbors)
                       (into (pop frontier) (remove explored neighbors))))))))
      #{s} [s]))

  (seq-graph-dfs G :1) ; => (:1 :3 :4 :2)
   )

(defn river-crossing-plan []
  start-pos)
