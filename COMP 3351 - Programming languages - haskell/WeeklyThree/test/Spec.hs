module Main where

import Test.Hspec
import WeeklyThree

main :: IO ()
main = hspec $ do
  describe "firstAnswer" $ do
    it "produces Just first element of the given list that matches the given function argument" $
      (firstAnswer (\x -> Just x) [99,72,0,5,100] ) `shouldBe` Just 99

    it "produces Just first element of the given list that matches the given function argument" $
      (firstAnswer (\x -> Just 72) [99,72,0,5,100] ) `shouldBe` Just 72

    it "produces Just first element of the given list that matches the given function argument" $
      (firstAnswer (\x -> Nothing) ["c","f"] ) `shouldBe` (Nothing :: Maybe Char)

  describe "allAnswers" $ do
    it "produces Just list of the given list that matches the given function argument" $
      (allAnswers (\x -> Just [x]) [99,72,0,5,100] ) `shouldBe` Just [99,72,0,5,100]

    it "produces Just list of the given list that matches the given function argument" $
      (allAnswers (\x -> Just [x]) ["c","f"] ) `shouldBe` Just ["c","f"]

    it "produces Just list of the given list that matches the given function argument" $
      (allAnswers (\x -> Nothing) [99,72,0,5,100] ) `shouldBe` (Nothing :: Maybe [Int])

  describe "checkPat" $ do
    it "produces true if and only if all the variables appearing in the pattern are distinct from each other and false otherwise" $
      (checkPat (TuplePat [VariablePat "x", ConstantPat 2]) ) `shouldBe` True

    it "produces true if and only if all the variables appearing in the pattern are distinct from each other and false otherwise" $
      (checkPat (TuplePat [VariablePat "x", WildcardPat]) ) `shouldBe` False

    it "produces true if and only if all the variables appearing in the pattern are distinct from each other and false otherwise" $
      (checkPat (ConstructorPat ("v",VariablePat "v"))) `shouldBe` False


  describe "match" $ do
    it "produces Nothing or Just empty list or Just list of (String, Value) tuple based on whether it found a match from the given rules or not" $
      ( match ( Constructor ("a", Unit), WildcardPat )) `shouldBe` Just []

    it "produces Nothing or Just empty list or Just list of (String, Value) tuple based on whether it found a match from the given rules or not" $
      ( match (Constructor ("v",Unit),(ConstructorPat ("v",VariablePat "v")))) `shouldBe` Just [("v",Unit)]

    it "produces Nothing or Just empty list or Just list of (String, Value) tuple based on whether it found a match from the given rules or not" $
      ( match (Constant 22, VariablePat "i" )) `shouldBe` Just [("i",Constant 22)]
    

  describe "firstMatch" $ do
    it "matches the value with the first applicable pattern" $
      ( firstMatch ( Tuple[Unit, Constructor ("i", Unit)] )  [ConstantPat 2, TuplePat [VariablePat "v", WildcardPat]] ) `shouldBe` (Nothing :: Maybe[(String, Value)])

    it "matches the value with the first applicable pattern" $
      ( firstMatch ( Constructor ("i", Unit) )  [VariablePat "i", UnitPat] ) `shouldBe` Just [("i",Constructor ("i",Unit))]

    it "matches the value with the first applicable pattern" $
      ( firstMatch ( Constructor ("X", Unit) )  [] ) `shouldBe` Nothing