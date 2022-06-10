---------------------------------------------------
--Abdulaziz alkhelaiwi (Aziz)
--Class: COMP 3351: Programming Languages
--Project info: DailySeven - Problem Soliving With Haskell
---------------------------------------------------

module DailySeven where

-- takes a list of lists and produces a list with all elements merged
createOneList :: [[a]] -> [a]
createOneList [] = []
createOneList xs = let ll = [] in foldl (++)(ll) xs

-- takes a list of positive ints and produces the highest values int in the list
findLargest :: [Int] -> Int
findLargest [] = 0
findLargest (x:xs)  
    | x < 0 = error "Only positive ints allowed"
    | findLargest xs < x = x
    | findLargest xs > x && findLargest xs > 0 = findLargest xs 

--takes a list of bools and produces true if all are true and false otherwise
allTrue :: [Bool] -> Bool
allTrue [] = False
allTrue xs = foldl (&&) True xs