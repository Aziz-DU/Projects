module Main where

import Test.Hspec
import DailyFour

main :: IO ()
main = hspec $ do
  describe "zip3Lists" $ do
    it "produces a list of tuples that consists of the elements from each list consecutively" $
      (zip3Lists [1, 2, 3] ['a', 'b', 'c'] [4, 5, 6]) `shouldBe` [(1, 'a', 4), (2, 'b', 5), (3, 'c', 6)]

    it "produces a list of tuples that consists of the elements from each list consecutively" $
      (zip3Lists [9, 8, 7] ['a', 'x', 'z'] [4, 5, 9]) `shouldBe` [(9, 'a', 4), (8, 'x', 5), (7, 'z', 9)]

    it "produces a list of tuples that consists of the elements from each list consecutively" $
      (zip3Lists [4, 7, 1] ['r', 'b', 'w'] [1, 0, 6]) `shouldBe` [(4, 'r', 1), (7, 'b', 0), (1, 'w', 6)]


  describe "unzipTriples" $ do
    it "produces a tuple of 3 lists each consist of elements in an index of each list" $
      (unzipTriples [(1,2,3),(4, 5, 6),(7, 8, 9)])  `shouldBe`  ([1,4,7], [2, 5, 8], [3, 6, 9])

    it "produces a tuple of 3 lists each consist of elements in an index of each list" $
      (unzipTriples [(1,0,5),(4, 7, 6),(7, 2, 0)])  `shouldBe`  ([1,4,7], [0, 7, 2], [5, 6, 0])

    it "produces a tuple of 3 lists each consist of elements in an index of each list" $
      (unzipTriples [(2,8,3),(2, 5, 7),(7, 1, 0)])  `shouldBe`  ([2,2,7], [8, 5, 1], [3, 7, 0])

  describe "mergeSorted3" $ do
    it "produces a sorted merged list from given 3 lists" $
      (mergeSorted3 [2, 3, 5] [1, 8] [-1, 0, 0, 0]) `shouldBe` [-1, 0, 0, 0, 1, 2, 3, 5, 8]

    it "produces a sorted merged list from given 3 lists" $
      (mergeSorted3 [1, 4, 9] [2, 5, 7] [-7, 0, 10, 40]) `shouldBe` [-7, 0, 1, 2, 4, 5, 7, 9, 10, 40]
  
    it "produces a sorted merged list from given 3 lists" $     
      (mergeSorted3 [13, 22, 57] [10, 18] [-11, 0, 9, 42]) `shouldBe` [-11, 0, 9, 10, 13, 18, 22, 42, 57]
