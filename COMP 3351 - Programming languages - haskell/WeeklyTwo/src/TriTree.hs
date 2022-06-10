---------------------------------------------------
--Abdulaziz alkhelaiwi (Aziz)
--Class: COMP 3351: Programming Languages
--Project info: WeeklyTwo - Tree Structures 
---------------------------------------------------
module TriTree  where

data TriTree a = Empty | 
                 NodeOne a (TriTree a) (TriTree a) (TriTree a) | 
                 NodeTwo a a (TriTree a) (TriTree a) (TriTree a) 
                 deriving (Show,Eq)
--tr = NodeOne 11 (NodeOne 2 Empty Empty (NodeOne 4 Empty Empty Empty)) Empty (NodeTwo 33 72 Empty (NodeTwo 40 50 Empty Empty Empty) Empty)
-- treeMap (*2) tr
-- treeFoldPreOrder (+) 0 tr


-- recursively searching through the tree, takes a value to search for and a tree and returns boolean on whether it's found or not
search :: (Eq a, Ord a) => a -> TriTree a -> Bool
search val Empty = False
search val (NodeOne a leftSub middleSub rightSub) = if val == a
    then True
    else if val < a
        then search val leftSub
    else search val rightSub
search val (NodeTwo a b leftSub middleSub rightSub) = if val == a || val ==b
    then True
    else if val < a 
      then
      if val > b 
        then search val rightSub
      else search val leftSub
    else if val > a && val < b 
      then search val middleSub
    else search val rightSub

-- takes a value and a tree and produces a new tree with value added to it
insert :: (Eq a, Ord a) =>a -> TriTree a -> TriTree a 
insert val Empty = NodeOne val Empty Empty Empty
insert val (NodeOne a left mid right)
    | val == a = NodeOne a left (insert val mid) right
    | val < a = NodeOne a (insert val left) mid right
    | val > a = NodeOne a left mid (insert val right)
insert val (NodeTwo a b left mid right)
    | val > a && val < b= NodeTwo a b left (insert val mid) right
    | val < a && val < b= NodeTwo a b (insert val left) mid right
    | val > a && val > b = NodeTwo a b left mid (insert val right)

-- takes a list of values and a tree and produces a new tree with all the values added to it
insertList :: (Eq a, Ord a) =>[a] -> TriTree a -> TriTree a 
insertList (val:valList) Empty = NodeOne val Empty Empty Empty
insertList [] tr = tr
insertList (xs) tr =  foldl (\acc x  -> insert x acc) tr xs
--insertList (x:xs) tr =  insert x (insertList xs tr) -- alternative method

-- takes two trees and returns true if the trees are identical and false otherwise
identical ::  (Eq a, Ord a)=>TriTree a ->  TriTree a -> Bool
identical Empty Empty = True
identical tr1 Empty = False
identical Empty tr2 = False
identical (NodeOne a1 Empty Empty Empty) (NodeOne a2 Empty Empty Empty) = if a1 == a2 then True else False
identical (NodeTwo a1 b1 Empty Empty Empty) (NodeTwo a2 b2 Empty Empty Empty) = if a1 == a2 && b1 == b2 then True else False
identical (NodeOne a1 leftSub1 middleSub1 rightSub1) (NodeOne a2 leftSub2 middleSub2 rightSub2) =
      if a1 == a2 && (identical leftSub1 leftSub2==True)
                  && (identical middleSub1 middleSub2==True)
                  && (identical rightSub1 rightSub2==True) then True
      else False
identical (NodeTwo a1 b1 leftSub1 middleSub1 rightSub1) (NodeTwo a2 b2 leftSub2 middleSub2 rightSub2) =
      if a1 == a2 && b1 == b2 
                  && (identical leftSub1 leftSub2==True)
                  && (identical middleSub1 middleSub2==True)
                  && (identical rightSub1 rightSub2==True) then True
      else False

--takes a function and a starting value and a tree then produces the sum of the tree while processing the tree in a preorder order 
treeFoldPreOrder :: (Num a) =>(a -> a -> a) -> a ->  TriTree a ->  a
treeFoldPreOrder f i Empty =  0
treeFoldPreOrder f i (NodeOne a leftSub middleSub rightSub) =  (a+((f)(l)((f)(m)(r))))  where 
      l =  treeFoldPreOrder (f) (i) (leftSub) 
      m =  treeFoldPreOrder (f) (i) (middleSub)
      r =  treeFoldPreOrder (f) (i) (rightSub) 
treeFoldPreOrder f i (NodeTwo a b leftSub middleSub rightSub) =  (a+b+((f)(l)((f)(m)(r))))  where 
      l =  treeFoldPreOrder (f) (i) (leftSub) 
      m =  treeFoldPreOrder (f) (i) (middleSub)
      r =  treeFoldPreOrder (f) (i) (rightSub) 

--takes a function and a starting value and a tree then produces the sum of the tree while processing the tree in an inorder order 
treeFoldInOrder :: (Num a) =>(a -> a -> a) -> a ->  TriTree a ->  a
treeFoldInOrder f i Empty =  0
treeFoldInOrder f i (NodeOne a leftSub middleSub rightSub) =  ((l)+a) +(m) +(r)  where 
      l =  treeFoldInOrder (f) (i) (leftSub) 
      m =  treeFoldInOrder (f) (i) (middleSub)
      r =  treeFoldInOrder (f) (i) (rightSub) 
treeFoldInOrder f i (NodeTwo a b leftSub middleSub rightSub) =  ((l)+a) +((m)+b) +(r)  where 
      l =  treeFoldInOrder (f) (i) (leftSub) 
      m =  treeFoldInOrder (f) (i) (middleSub)
      r =  treeFoldInOrder (f) (i) (rightSub) 

--takes a function and a starting value and a tree then produces the sum of the tree while processing the tree in a postorder order 
treeFoldPostOrder :: (Num a) =>(a -> a -> a) -> a ->  TriTree a ->  a
treeFoldPostOrder f i Empty =  0
treeFoldPostOrder f i (NodeOne a leftSub middleSub rightSub) =  ((f)(l)((f)(m)(r)))+a  where 
      l =  treeFoldPostOrder (f) (i) (leftSub) 
      m =  treeFoldPostOrder (f) (i) (middleSub)
      r =  treeFoldPostOrder (f) (i) (rightSub) 
treeFoldPostOrder f i (NodeTwo a b leftSub middleSub rightSub) =  ((f)(l)((f)(m)(r)))+a+b  where 
      l =  treeFoldPostOrder (f) (i) (leftSub) 
      m =  treeFoldPostOrder (f) (i) (middleSub)
      r =  treeFoldPostOrder (f) (i) (rightSub) 

--takes a function and a tree then produces a new tree with the function applied to all the values
treeMap :: (a -> b) -> TriTree a -> TriTree b
treeMap _ Empty = Empty
treeMap f (NodeOne a Empty Empty Empty) = NodeOne (f a) Empty Empty Empty
treeMap f (NodeTwo a b Empty Empty Empty) = NodeTwo (f a)(f b) Empty Empty Empty
treeMap f  (NodeOne a leftSub middleSub rightSub) = NodeOne (f a) (treeMap f leftSub)(treeMap f middleSub)(treeMap f rightSub)  
treeMap f  (NodeTwo a b leftSub middleSub rightSub) = NodeTwo (f a)(f b) (treeMap f leftSub)(treeMap f middleSub)(treeMap f rightSub)  



