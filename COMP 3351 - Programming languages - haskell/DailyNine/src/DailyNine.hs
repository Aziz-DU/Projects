---------------------------------------------------
--Abdulaziz alkhelaiwi (Aziz)
--Class: COMP 3351: Programming Languages
--Project info: DailyNine - Problem Soliving With Haskell
---------------------------------------------------

module DailyNine where

-- takes a list of ints and produces a tuples of the min and max value in that list. 3 comparissons per 2 elements.
-- used a helper function that takes two indivisual ints and a list of ints to extract the min and max tuple.
-- the two ints in the helper function act as the min and max for where we are

minAndMax :: [Int] -> (Int,Int)
minAndMax (x:y:xys)
  | x<y = minAndMax' x y xys
  | x>y = minAndMax' y x xys
minAndMax (x:xs) = (x,x)

minAndMax' :: Int -> Int -> [Int] -> (Int,Int)
minAndMax' curMin curMax [] = (curMin,curMax)
minAndMax' curMin curMax (x:y:xys) 
   | x>y =
    if x > curMax then
      if y < curMin then  
        minAndMax' y x xys
      else
        minAndMax' curMin x xys
    else 
      if y < curMin then  
        minAndMax' y curMax xys
      else
        minAndMax' curMin curMax xys
  | x<y = 
    if y > curMax then
      if x < curMin then  
        minAndMax' x y xys
      else
        minAndMax' curMin y xys
    else 
      if x < curMin then  
        minAndMax' x curMax xys
      else
        minAndMax' curMin curMax xys

minAndMax' curMin curMax (x:xys) = if x > curMax then (curMin,x)
                                   else if x < curMin then (x,curMax)
                                   else (curMin,curMax)


-- takes a number and a list and produces every kth element of the list.
-- helper function that takes two ints to produce the new list, the extra int used here acts like a counter
everyK :: Int -> [a] -> [a]
everyK k xs = everyK' 0 k xs

everyK' :: Int -> Int -> [a] -> [a]
everyK' n k (x:xs) = let m = n+1 in 
  if (m `mod` k == 0) then x:everyK' 0 k xs
  else  everyK' m k xs
everyK' n k [] = []

-- takes two lists of the same type elements and produces one list which contains alternating elements from the given lists
-- helper function that takes an int and two lists, the extra int also acts like a counter
shuffle :: [a] -> [a] -> [a]
shuffle xs ys = shuffle' 0 xs ys 

shuffle' :: Int -> [a] -> [a] -> [a]
shuffle' n (x:xs) (y:ys) = let m = n+1 in
  if (m `mod` 2 /= 0) then x:shuffle' m xs (y:ys)
  else  y:shuffle' m (x:xs) ys
shuffle' n (x:xs) [] = x:shuffle' 0 xs []
shuffle' n [] (y:ys) = y:shuffle' 0 [] ys
shuffle' n [] [] = []


