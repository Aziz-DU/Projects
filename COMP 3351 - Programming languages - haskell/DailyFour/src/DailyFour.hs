---------------------------------------------------
--Abdulaziz alkhelaiwi (Aziz)
--Class: COMP 3351: Programming Languages
--Project info: DailyFour - Problem Soliving With Haskell
---------------------------------------------------

module DailyFour where
import Data.List

-- takes 3 lists and produces a list of tuples
zip3Lists  :: [a] -> [b] -> [c] -> [(a,b,c)]
zip3Lists  (x:xs) (y:ys) (z:zs) = (x,y,z): zip3Lists xs ys zs 
zip3Lists  [] [] [] = []

-- takes a list of triples and produces a tuple of three lists.
unzipTriples  ::  [(a,a,a)] -> ([a],[a],[a]) 
unzipTriples [(x,y,z)] = ([x],[y],[z])
unzipTriples ((x,y,z):xs) = let (q,w,e) = (unzipTriples xs) in  ((x:[] ++ q),(y:[] ++ w),(z:[] ++ e))

-- takes 3 lists which are in sorted order and merges them so 
-- that the final list is sorted in increasing order
mergeSorted3 :: Ord a => [a] -> [a] -> [a] -> [a]
mergeSorted3 [] [] [] = []
mergeSorted3 (x:xs) [] [] = x : mergeSorted3 xs [] []
mergeSorted3 [] (y:ys) [] = y : mergeSorted3 ys [] []
mergeSorted3 [] [] (z:zs) = z : mergeSorted3 zs [] []
mergeSorted3 (x:xs) (y:ys) [] = if x<y 
     then x : mergeSorted3 xs (y:ys) []
     else y : mergeSorted3 (x:xs) ys [] 
mergeSorted3 (x:xs) [] (y:ys) = if x<y 
     then x : mergeSorted3 xs (y:ys) []
     else y : mergeSorted3 (x:xs) ys [] 
mergeSorted3 [] (x:xs) (y:ys) = if x<y 
     then x : mergeSorted3 xs (y:ys) []
     else y : mergeSorted3 (x:xs) ys [] 
mergeSorted3   (x:xs)(y:ys)(z:zs)= if x<y && x<z 
     then x: mergeSorted3 xs (y:ys) (z:zs)
     else if y<x && y<z then y: mergeSorted3 (x:xs) ys (z:zs)
     else (z:mergeSorted3 (x:xs) (y:ys) zs)
