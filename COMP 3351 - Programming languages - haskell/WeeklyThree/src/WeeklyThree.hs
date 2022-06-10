---------------------------------------------------
--Abdulaziz alkhelaiwi (Aziz)
--Class: COMP 3351: Programming Languages
--Project info: WeeklyThree - Patterns in Haskell 
---------------------------------------------------
module WeeklyThree  where
import Data.Maybe
data Pattern = WildcardPat | VariablePat (String) | UnitPat | ConstantPat (Int) |
                  ConstructorPat (String, Pattern) | TuplePat ([Pattern])     
                  deriving (Eq, Show)

data Value = Constant (Int) | Unit | Constructor (String, Value) |
                   Tuple [Value] deriving (Eq, Show)
-- takes a function and a list, the function applies to elements of the list in order recursively until the first time it returns
-- Just v for some v. If it returns nothing for all elements in the list then it returns Nothing.
firstAnswer :: ( a -> Maybe b ) -> [a] -> Maybe b
firstAnswer f [] = Nothing
firstAnswer f (x:xs) = case f x of
    Just x -> Just x
    (_)      -> firstAnswer f xs
--takes a function and a list, the the function applies to elements of the list. If it returns nothing for any element then it'll return
--Nothing, otherwise it'll return Just list
allAnswers :: (a -> Maybe [b]) -> [a] -> Maybe [b]
allAnswers f [] = Nothing
allAnswers f xs =  let l=concat(catMaybes(map f xs)) in  
  if (length(l)>=1) then 
    Just(l)
  else
    Nothing

-- takes a Pattern and returns true if and only if all the variables appearing in the pattern are distinct from each other and false otherwise
checkPat :: Pattern -> Bool 
checkPat p = case p of
  WildcardPat -> True
  (VariablePat s) -> True
  UnitPat -> True
  (ConstantPat i)  -> True
  ConstructorPat (s, pa) -> if (getStr(pa) /= s)&&(getStr(pa) /= "WildcardPat") then True else False
  TuplePat (ps) -> if ((checkStrs(getPS(ps)) == True) && 
                      (("WildcardPat" `notElem` getPS(ps))||
                      (length(getPS(ps))<=1)) && ("Tuple is False" `notElem` getPS(ps)))  then True
                      else False 


--takes a (Value, Pattern) tuple and returns Nothing or Just empty list or Just list of (String, Value) tuple based on whether it found a match from the 
--given rules or not
match :: (Value, Pattern) -> Maybe [(String, Value)]
match (val,pat) = case (val,pat) of
  (_,WildcardPat)     -> Just []
  (v,(VariablePat s)) -> Just [(s,v)]
  (Unit,UnitPat) -> Just []
  (_,(ConstantPat _)) -> Just []
  (Constructor (s1, v),ConstructorPat (s2, p)) -> if (s1==s2) then match(v,p) else Nothing
  ((Tuple vs),(TuplePat ps)) -> let vsps = zip vs ps in if (length vs == length ps) then allAnswers match(vsps) else Nothing
  (_,_) -> Nothing

--takes a Value and a list of Patterns and returns a Just list of bindings for the first pattern in the list that matches or nothing if
--there isn't a match. it uses a helper function that extracts the first match from if it exists

firstMatch :: Value -> [Pattern] ->  Maybe [(String, Value)]
firstMatch val pats = let ms =(firstMatch' val pats) 
                          r =(firstAnswer match ms)
                          in  if (r == Just []) then Nothing else r 
                         
                        
firstMatch' v [] = []
firstMatch' v (x : xs) = (v, x) : (firstMatch' v xs)


-- helper methods for checkPat, one takes a list of patterns and makes a list of strings out of that
-- and one that gets a single string from a single pattern
-- and one that checks the list of strings to see if there are repetitves                       
getPS :: [Pattern] -> [String]
getPS [] = []
getPS (p:ps)  = case p of
  TuplePat (pats)        -> getPS(pats) ++ getPS(ps)
  ConstructorPat (s, pa) -> s : getStr(pa) : getPS(ps)
  _                      ->  getStr(p) : getPS(ps)

getStr :: Pattern -> String
getStr p = case p of
  (VariablePat s) -> s
  UnitPat -> "unit"
  (ConstantPat i)  -> show(i)
  WildcardPat -> "WildcardPat"


checkStrs :: [String] -> Bool
checkStrs [] = True
checkStrs (x:xs) = if (x `elem` xs)
  then False
  else checkStrs(xs)

