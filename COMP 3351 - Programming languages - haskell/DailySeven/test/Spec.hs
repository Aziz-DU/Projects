module Main where

import Test.Hspec
import DailySeven

main :: IO ()
main = hspec $ do
  describe "createOneList" $ do
    it "produces a list with all elements from gives lists merged" $
      (createOneList [ [1,2], [3], [ ], [4, 5] ]) `shouldBe` [1,2,3,4,5]

  describe "findLargest " $ do
    it "produces the highest values int in the list" $
      (findLargest [3, 5,10, 9, 15]) `shouldBe` 15

  describe "allTrue" $ do
    it "produces true if all elements from given list of bools are true and false otherwise" $
      (allTrue [True,True,False,True,True]) `shouldBe` False



