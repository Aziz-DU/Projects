module Main where

import Test.Hspec
import DailyFive

main :: IO ()
main = hspec $ do
  describe "multPairs " $ do
    it "produces a list of the products of each pair." $
      (multPairs  [(3,5),(7,8),(2,6)]) `shouldBe` [15,56,12]

  describe "squareList " $ do
    it "produces a new list of tuple pairs of Integers of the original integer and its square" $
      (squareList  [1,3,2]) `shouldBe` [(1,1), (3, 9), (2, 4)]

  describe "findLowercase " $ do
    it "produces a list of Bool that is ture if the corresponding string starts with lowercase and false otherwise" $
      (findLowercase  ["hi","Bye","why","HI"]) `shouldBe` [True,False,True,False]



