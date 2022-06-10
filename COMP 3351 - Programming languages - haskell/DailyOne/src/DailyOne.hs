---------------------------------------------------
--Abdulaziz alkhelaiwi (Aziz)
--Class: COMP 3351: Programming Languages
--Project info: DailyOne - Problem Soliving With Haskell
---------------------------------------------------

module DailyOne where

-- takes 4 parameters of type integer and produces the quadratic value
quadratic  :: Integer -> Integer -> Integer -> Integer -> Integer
quadratic  a b c x = a + b*x + c*x^2

-- takes an integer and a two dimensional vector then produces the vector scaled by the given number
scaleVector  :: Integer -> (Integer,Integer) -> (Integer,Integer) 
scaleVector  a(b, c) = (a*b, a*c)

-- takes two 3-tuples and finds the cartesian distance between them as a float type
-- i chose to use a variable r just to experment with using variables in functions 
tripleDistance  :: (Integer,Integer,Integer) -> (Integer,Integer,Integer) -> Float
tripleDistance  (a, b, c) (x, y, z) = let r = ((x-a)^2 +(y-b)^2 +(z-c)^2)
    in sqrt (fromIntegral r) 
