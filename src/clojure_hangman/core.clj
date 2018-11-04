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
  (defn evaluate-try [lives word letters]
    (println "Type a new letter: ")
    (def new-letter (clojure.string/lower-case (read-line)))
    (if (letter-matches? new-letter word)
      (do
        (println "Correct!")
        (game lives word (conj letters new-letter)))
      (do
        (println "Wrong!")
        (game (dec lives) word letters))))

  (if (= lives 0)
    (game-over)
    (do
      (println "You have" lives "live(s)")
      (if (match-whole-word? (clojure.string/lower-case word) letters)
        (you-win)
        (evaluate-try lives (clojure.string/lower-case word) letters)))))

; (defn fib [val]
;   (if (= val 0) 1 (if (= val 1) 1 (if (>= val 2) (+ (fib (dec val)) (fib (- val 2)))))))

; (defn filter-values [values]
;   (filter #(or (< % 2) (> % 4)) values))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
