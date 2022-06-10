module Main where

import Test.Hspec
import WeeklyOne

main :: IO ()
main = hspec $ do
  describe "removeChar" $ do
    it "produces a new string with the selected charecter removed" $
      (removeChar 'a' ['h','a','p','a','e']) `shouldBe` ['h','p','e']

  describe "removeWhitespace" $ do
    it "produces a string without whitespace charecters" $
      (removeWhitespace " H E  ll o        o ") `shouldBe` "HElloo"

  describe "removePunctuation" $ do
    it "produces a string without Punctuation" $
      (removePunctuation ",,,H..E[]LL(O") `shouldBe` "HELLO"

  describe "charsToAscii" $ do
    it "produces a list of the ASCII values of the charecters" $
      (charsToAscii ['a','.',',',')','{','b','(']) `shouldBe` [97,46,44,41,123,98,40]

  describe "asciiToChars" $ do
    it "produces a new list of characters created from given ASCII values" $
      (asciiToChars [97,46,44,41,123,98,40]) `shouldBe` "a.,){b("

  describe "shiftInts" $ do
    it "Produces a new list of integers with the given int added to all the ints" $
      (shiftInts 1 [2, 4, 6]) `shouldBe` [3,5,7]

  describe "shiftMessage" $ do
    it "produces a string where each character has been shifted by the shift value in the ASCII encoding" $
      (shiftMessage 2 ['H','e','l','l','o']) `shouldBe` "Jgnnq"