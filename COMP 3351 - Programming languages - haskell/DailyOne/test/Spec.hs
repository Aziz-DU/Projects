module Main where

import Test.Hspec
import DailyOne

main :: IO ()
main = hspec $ do
  describe "quadratic" $ do
    it "returns the quadratic value of 4 numbers" $
      (quadratic 2 3 4 5) `shouldBe` 117

  describe "scaleVector" $ do
    it "returns 2-tuple scaled by the value" $
      (scaleVector 5(3,4)) `shouldBe` (15, 20)


  describe "tripleDistance" $ do
    it "returns the cartesian distance between two 3-tuples" $
      (tripleDistance (2,3,4)(5,6,7)) `shouldBe` 5.196152