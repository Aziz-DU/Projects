---------------------------------------------------
--Abdulaziz alkhelaiwi (Aziz)
--Class: COMP 3351: Programming Languages
--Project info: DailyFive - Problem Soliving With Haskell
---------------------------------------------------

module DailyFive where
import Data.Char 

-- takes a list of tuple pairs of integers and produces a list of the products of each pair.
multPairs  :: [(Integer,Integer)] ->  [Integer]
multPairs [] = []
multPairs xs = map (\x -> (fst x * snd x)) xs
-- takes a list of Integers as input and produces a new list of tuple pairs of Integers
-- of the original integer and its square
squareList :: [Integer] -> [(Integer,Integer)]
squareList [] = []
squareList xs = map (\x -> (x,x * x)) xs
-- takes a list of String and produces a list of Bool that is ture
-- if the corresponding string starts with lowercase and false otherwise
findLowercase :: [String] -> [Bool] 
findLowercase [] = []
findLowercase (xs) = map (\x -> let fir = head x in if (isLower fir) then True else False) xs

