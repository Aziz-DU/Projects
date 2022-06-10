---------------------------------------------------
--Abdulaziz alkhelaiwi (Aziz)
--Class: COMP 3351: Programming Languages
--Project info: DailyTwo - Problem Soliving With Haskell
---------------------------------------------------
module DailyTwo where
import Data.List

-- takes a list and produces a list that contains every third element of that list
-- takes and produces list of integers
everyThird :: [Integer] -> [Integer]
everyThird (x:y:z:xs) = z : everyThird xs
everyThird _ = []

-- takes two lists and produces the dot product from the lists. takes two lists
-- of integers and returns an integer
tupleDotProduct  :: [Integer] -> [Integer] -> Integer
tupleDotProduct [] [] = 0
tupleDotProduct (x:xs) (y:ys) = x * y + (tupleDotProduct xs ys)

-- takes a string and a list of strings and produces the same list of strings with 
--the given string added at the end of all elements. 
appendToEach  :: String -> [String] -> [String]
appendToEach  x [] = []
appendToEach x (ys) = map(++"!!!") ys

