module Main where

import Test.Hspec
import TriTree

tr1 =NodeOne 11 (NodeOne 2 Empty Empty (NodeOne 3 Empty Empty Empty)) Empty (NodeTwo 33 72 Empty (NodeTwo 40 50 Empty Empty Empty) Empty)
tr2 =NodeOne 11 (NodeOne 2 Empty Empty (NodeOne 3 Empty Empty Empty)) Empty (NodeTwo 33 72 Empty (NodeTwo 40 50 Empty Empty Empty) Empty)
tr3 =NodeOne 11 (NodeOne 1 Empty Empty (NodeOne 3 Empty Empty Empty)) Empty (NodeTwo 33 72 Empty (NodeTwo 40 51 Empty Empty Empty) Empty)
tr4 =NodeOne 15 Empty Empty (NodeTwo 90 222 Empty (NodeTwo 4 99 Empty Empty Empty) Empty)
tr5 =NodeTwo 30 90 Empty Empty (NodeTwo 100 130 Empty (NodeTwo 125 127 Empty Empty Empty) Empty)
tr6 =NodeTwo 520 400 Empty Empty Empty 
tr7 =NodeOne 1000 Empty Empty Empty
tr8 =NodeOne 9999 Empty (NodeOne 1 Empty Empty Empty) Empty 

main :: IO ()
main = hspec $ do
  describe "search" $ do
    it "produces true if a value exists in a tree and false otherwise" $
       (search 50 tr1) `shouldBe` True
    it "produces true if a value exists in a tree and false otherwise" $
       (search 50 tr3) `shouldBe` False


  describe "insert" $ do
    it "produces a new tree with the value added to it" $
       (insert 27 (NodeOne 11 (NodeOne 1 Empty Empty Empty) Empty Empty)) `shouldBe` NodeOne 11 (NodeOne 1 Empty Empty Empty) Empty (NodeOne 27 Empty Empty Empty)

  describe "insertList" $ do
    it "produces a new tree with the list values added to it" $
       (insertList [5,27,57] (NodeOne 11 (NodeOne 1 Empty Empty Empty) Empty Empty)) `shouldBe` NodeOne 11 (NodeOne 1 Empty Empty (NodeOne 5 Empty Empty Empty)) Empty (NodeOne 27 Empty Empty (NodeOne 57 Empty Empty Empty))
    it "produces a new tree with the list values added to it" $
       (insertList [5,27,57] (NodeTwo 2 11 (NodeOne 1 Empty Empty Empty) Empty Empty)) `shouldBe` NodeTwo 2 11 (NodeOne 1 Empty Empty Empty) (NodeOne 5 Empty Empty Empty) (NodeOne 27 Empty Empty (NodeOne 57 Empty Empty Empty))

  describe "identical" $ do
    it "produces true if both trees are identical and false otherwise" $
       (identical tr1 tr2) `shouldBe` True
    it "produces true if both trees are identical and false otherwise" $
       (identical tr1 tr3) `shouldBe` False

  describe "treeFoldPreOrder" $ do
    it "applies fold in pre order order" $
       (treeFoldPreOrder (+) 0 tr1) `shouldBe` 211
    it "applies fold in pre order order" $
       (treeFoldPreOrder (+) 0 tr4) `shouldBe` 430


  describe "treeFoldInOrder" $ do
    it "applies fold in an in-order order" $
       (treeFoldInOrder (+) 0 tr5) `shouldBe` 602
    it "applies fold in an in-order order" $
       (treeFoldInOrder (+) 0 tr6) `shouldBe` 920

  describe "treeFoldPostOrder" $ do
    it "applies fold in a post order order" $
       (treeFoldPostOrder (+) 0 tr7) `shouldBe` 1000
    it "applies fold in a post order order" $
       (treeFoldPostOrder (+) 0 tr8) `shouldBe` 10000

  describe "treeMap" $ do
    it "applies a function to all elements in a given tree" $
       (treeMap (/2) tr6) `shouldBe` NodeTwo 260 200 Empty Empty Empty 
    it "applies a function to all elements in a given tree" $
       (treeMap (*2) tr4) `shouldBe` NodeOne 30 Empty Empty (NodeTwo 180 444 Empty (NodeTwo 8 198 Empty Empty Empty) Empty)