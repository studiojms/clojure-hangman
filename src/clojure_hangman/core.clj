(ns clojure-hangman.core
  (:gen-class))

(def lives-total 6)

(defn game-over []
  println "You lose")

(defn you-win []
  println "You Win! Congratulations!")

(defn missing-letters [word letters]
  (remove #(contains? letters (str %)) word))

(defn match-whole-word? [word tries]
  (empty? (missing-letters word tries)))

(defn letter-matches? [letter word]
  (.contains word letter))

(defn game "Hangman Game" [lives word letters]
  (cond (= lives 0) (game-over)
        (match-whole-word? (clojure.string/lower-case word) letters) (you-win)
        :else
        (do (println "Type a new letter: ")
            (let [new-letter (clojure.string/lower-case (read-line)) lowercase-word (clojure.string/lower-case word)]
              (if (letter-matches? new-letter lowercase-word)
                (do
                  (println "Correct!")
                  (recur lives lowercase-word (conj letters new-letter)))
                (do
                  (println "Wrong!")
                  (recur (dec lives) lowercase-word letters)))))))

; (defn fib [x]
;   (loop [a 1 b 1 number 2]
;     (if (= number x) b (recur b (+ a b) (inc number)))))

; (defn filter-values [values]
;   (filter #(or (< % 2) (> % 4)) values))

; (defn sum [n]
;   (loop [count 1 sum-val 0]
;     (if (> count n) sum-val
;         (recur (inc count) (+ sum-val count)))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
