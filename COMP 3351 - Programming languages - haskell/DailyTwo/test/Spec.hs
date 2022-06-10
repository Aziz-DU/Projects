module Main where

import Test.Hspec
import DailyTwo

main :: IO ()
main = hspec $ do
  describe "everyThird" $ do
    it " produces a list of every third element" $
      (everyThird [1,2,3,4,5,6,7]) `shouldBe` [3,6]

  describe "tupleDotProduct" $ do
    it "produces the dot product from two given lists" $
      (tupleDotProduct [2,3,4] [5,6,7]) `shouldBe` 56

  describe "appendToEach" $ do
    it "produces the given list of strings with the given string added at the end of all elements" $
      (appendToEach "!!!" ["Hi","Bye","Why"]) `shouldBe` ["Hi!!!","Bye!!!","Why!!!"]



