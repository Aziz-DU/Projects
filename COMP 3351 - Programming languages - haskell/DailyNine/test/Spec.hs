module Main where
import Test.Hspec
import DailyNine

main :: IO ()
main = hspec $ do
  describe "minAndMax" $ do
    it " produces a tuples of the min and max values in the given list of ints" $
      (minAndMax [1,5,11,22,27,57,90,150] ) `shouldBe` (1,150)
    it " produces a tuples of the min and max values in the given list of ints" $
      (minAndMax [5,11,22,27,57,90] ) `shouldBe` (5,90)

  describe "everyK" $ do
    it "produces every kth element of a given list" $
      (everyK 4 [1,5,11,22,27,57,90,150] ) `shouldBe` [22,150]
    it "produces every kth element of a given list" $
      (everyK 6 [1,5,11,22,27,57,90,150] ) `shouldBe` [57]
    it "produces every kth element of a given list" $
      (everyK 5 [1,5,11,22,27] ) `shouldBe` [27]      


  describe "shuffle" $ do
    it "produces a list that contains alternating elements from two given lists" $
      (shuffle [1,5,110,22,270] [27,57,90,500] ) `shouldBe` [1,27,5,57,110,90,22,500,270]
    it "produces a list that contains alternating elements from two given lists" $
      (shuffle [22,270] [90,500] ) `shouldBe` [22,90,270,500]
    it "produces a list that contains alternating elements from two given lists" $
      (shuffle [1] [27] ) `shouldBe` [1,27]