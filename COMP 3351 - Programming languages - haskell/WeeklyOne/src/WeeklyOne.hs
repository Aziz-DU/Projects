---------------------------------------------------
--Abdulaziz alkhelaiwi (Aziz)
--Class: COMP 3351: Programming Languages
--Project info: WeeklyOne - Encoding and Decoding 
---------------------------------------------------

module WeeklyOne where
import Data.List
import Data.Char   

whitespace = [' ', '\n', '\r', '\t']

-- takes a charecter and a string and produces a new string with the selected
-- charecter removed 
removeChar :: Eq a => a -> [a] -> [a]
removeChar  x [] = []
removeChar x (y:ys) = if (x/=y) 
    then y : removeChar x ys
    else removeChar x ys

-- takes a string and produces that string without whitespace charecters
removeWhitespace :: String -> String
removeWhitespace  [] = []
removeWhitespace xs = removeWhitespace' whitespace xs
    
removeWhitespace' :: String -> String -> String
removeWhitespace' [] [] = []
removeWhitespace' (x : xs) y = if (null xs)
                         then removeChar x y
                         else removeWhitespace' xs (removeChar x y)


-- takes a string and produces that string without Punctuation charecters  
removePunctuation  :: String -> String
removePunctuation   [] = []
removePunctuation  (x:xs) =  if  x `elem` punct
    then 
        (removePunctuation (removeChar x xs))
    else 
        x : removePunctuation xs 
    where  punct = ['.', ',', '[', ']', '{', '}', '(', ')']

-- takes a string and produces a list of the ASCII values of the charecters  
charsToAscii  :: String -> [Int]
charsToAscii    [] = []
charsToAscii   (x:xs) = let e = fromEnum x in 
    ( e:charsToAscii xs) 

-- takes a list of ASCII values and produces a new list of characters created
-- from the ASCII values
asciiToChars   :: [Int] -> [Char]
asciiToChars     [] = []
asciiToChars    (x:xs) = let e = toEnum x in 
    ( e:asciiToChars xs) 

-- takes an int and a list of ints and Produces a new list of integers with the
-- given int added to all the ints
shiftInts    :: Int -> [Int] -> [Int]
shiftInts       _ [] = []
shiftInts     x (y:ys) 
    | (x+y) >= 128 = x + y - 128 : shiftInts x ys
    | (x+y) < 128 = x + y : shiftInts x ys

-- takes an int and a string and produces a string where each character has 
-- been shifted by the shift value in the ASCII encoding 
shiftMessage     :: Int -> String -> String
shiftMessage        _ [] = []
shiftMessage      x (y:ys) = let e = toEnum ((fromEnum y)+x) in
     (e:shiftMessage x ys) 
