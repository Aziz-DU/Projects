module Main where

import Test.Hspec
import DailySix

main :: IO ()
main = hspec $ do
  describe "shorterThan " $ do
    it "produces a list  of the words whose length is shorter than or equal to the given number." $
      (shorterThan 5 ["Hi","Hiiiii","helllllo","Yo"]) `shouldBe` ["Hi","Yo"]
    it "produces a list  of the words whose length is shorter than or equal to the given number." $
      (shorterThan 1 ["Hi","Hiiiii","helllllo","Yo","F"]) `shouldBe` ["F"]
    it "produces a list  of the words whose length is shorter than or equal to the given number." $
      (shorterThan 1 ["Hi","Hiiiii","helllllo","Yo"]) `shouldBe` []

  describe "removeMultiples " $ do
    it "produces a list where the multiples of the given number have been removed." $
      (removeMultiples 5 [3,5,10,9,15]) `shouldBe` [3,9]
    it "produces a list where the multiples of the given number have been removed." $
      (removeMultiples 10 [3,5,10,9,15]) `shouldBe` [3,5,9,15]
    it "produces a list where the multiples of the given number have been removed." $
      (removeMultiples 2 [4,5,10,8,15]) `shouldBe` [5,15]


  describe "onlyJust " $ do
    it "produces a list where all values of Nothing have been eliminated" $
      (onlyJust [Nothing, Just 5, Nothing, Just 10]) `shouldBe` [ Just 5, Just 10]
    it "produces a list where all values of Nothing have been eliminated" $
      (onlyJust [Nothing, Just 1]) `shouldBe` [ Just 1]

