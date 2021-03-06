(ns alphabet-cipher.coder)

(defn ascii-offset [c] (- (int c) (int \a)))

(defn translate [keyword message operation]
  (let
    [keyword-salt (take (count message) (cycle keyword))]
    (apply str (map #(apply operation %) (map vector keyword-salt message)))
    )
  )

(defn encode-char [row col]
  (let
    [row-offset (ascii-offset row)
     col-offset (ascii-offset col)
     wrap-around (fn [c]
                   (if (> c (int \z))
                     (char (- c 26))
                     (char c))
                   )
     ]
    (char (wrap-around (+ row-offset col-offset (int \a))))
    )
  )

(defn encode [keyword message]
  (translate keyword message encode-char)
  )

(defn decode-char [row code]
  (let
    [row-offset (ascii-offset row)
     wrap-around (fn [c]
                   (if (< c (int \a))
                     (char (+ c 26))
                     (char c))
                   )
     ]
    (char (wrap-around (- (int code) row-offset)))
    )
  )

(defn decode [keyword message]
  (translate keyword message decode-char)
  )

(defn decipher [cipher message]
  "decypherme")

