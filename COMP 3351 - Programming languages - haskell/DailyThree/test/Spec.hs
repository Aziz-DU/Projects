module Main where

import Test.Hspec
import DailyThree

main :: IO ()
main = hspec $ do
  describe "removeAllExcept" $ do
    it " produces a list of only instances of given charecter" $
      (removeAllExcept 'a' ['v','e','a','m','a']) `shouldBe` ['a','a']

  describe "countOccurrences" $ do
    it "produces the count of a given charecter in a list" $
      (countOccurrences 'a' ['a','b','a','c']) `shouldBe` 2

  describe "substitute" $ do
    it "produces a list of all instances of a given charecter replaced by another givne charecter" $
      (substitute 3 4 [1,2,3,4]) `shouldBe` [1,2,4,4]



