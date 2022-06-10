---------------------------------------------------
--Abdulaziz alkhelaiwi (Aziz)
--Class: COMP 3351: Programming Languages
--Project info: DailySix - Problem Soliving With Haskell
---------------------------------------------------

module DailySix where

-- takes a number and a list of strings, produces a list  of the words whose length is shorter than or equal to the given number.
shorterThan :: Int -> [String] -> [String]
shorterThan _ [] = []
shorterThan i xs = filter (\x -> length x <= i) xs

-- takes a number and a list of ints, produces a list where the multiples of the given number have been removed.
removeMultiples :: Int -> [Int] -> [Int]
removeMultiples _ [] = []
removeMultiples i xs =  filter (\x -> x `mod` i /= 0) xs

-- takes a list of Maybe a's and produces a list where all values of Nothing have been eliminated
onlyJust :: (Eq a) => [Maybe a] -> [Maybe a]
onlyJust [] = []
onlyJust xs =  filter (Nothing /=) xs 

