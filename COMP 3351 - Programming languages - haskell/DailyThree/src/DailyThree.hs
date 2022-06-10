---------------------------------------------------
--Abdulaziz alkhelaiwi (Aziz)
--Class: COMP 3351: Programming Languages
--Project info: DailyThree - Problem Soliving With Haskell
---------------------------------------------------

module DailyThree where
import Data.List

-- removeAllExcept 'a' ['v','e','a','m','a']
--takes a charecter and a list of charecters and produces the same list 
--with everything removed except the given charecter 
removeAllExcept :: Eq a => a -> [a] -> [a]
removeAllExcept x [] = []
removeAllExcept  x (y:ys) = if (x==y) 
    then y : removeAllExcept x ys 
    else removeAllExcept x ys

-- takes A charecter and a list of charecters and produces how many instances
-- of the charecter is in the list
countOccurrences   :: Eq a => a -> [a] -> Int
countOccurrences  x [] = 0
countOccurrences  x (y:ys) = if (x==y)
    then  1 + (countOccurrences x ys)
    else  countOccurrences x ys

-- takes two charecters and a list of charecters and produces the same list 
-- with all instances of the first charecter replaced with the second charecter
substitute :: Eq a => a -> a -> [a] -> [a]
substitute  x y [] = []
substitute x y (z:zs) =  if (x==z)
    then y: substitute x y zs
    else z: substitute x y zs

