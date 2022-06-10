module Main where

import Test.Hspec
import DailyEight

event1 = [Event "concert1" 2 "aug" 2021 23.33 44.22,Event "concert2" 21 "dec" 2022 21.33 40.22,Event "concert3" 30 "jan" 2021 50.03 90.22]

main :: IO ()
main = hspec $ do
  describe "inYear" $ do
    it "produces a list of the events in that year." $
      (inYear 2021 event1 ) `shouldBe` [Event "concert1" 2 "aug" 2021 23.33 44.22,Event "concert3" 30 "jan" 2021 50.03 90.22]
    it "produces a list of the events on that year." $
      (inYear 2022 event1 ) `shouldBe` [Event "concert2" 21 "dec" 2022 21.33 40.22]
    it "produces a list of the events on that year." $
      (inYear 2020 event1 ) `shouldBe` []

    it "produces a list of the events in that Day Range." $
      (inDayRange 1 22 event1 ) `shouldBe` ["concert1","concert2"]
    it "produces a list of the events in that Day Range." $
      (inDayRange 15 25 event1 ) `shouldBe` ["concert2"]
    it "produces a list of the events in that Day Range." $
      (inDayRange 15 20 event1 ) `shouldBe` []

    it "produces a list of the events in that area." $
      (inArea "concert1" 20.0 30.0 40.0 45.0 event1 ) `shouldBe` [Event "concert1" 2 "aug" 2021 23.33 44.22] 
    it "produces a list of the events in that area." $
      (inArea "concert2" 20.0 30.0 40.0 45.0 event1 ) `shouldBe` [Event "concert2" 21 "dec" 2022 21.33 40.22] 
    it "produces a list of the events in that area." $
      (inArea "concert3" 20.0 30.0 40.0 45.0 event1 ) `shouldBe` [] 





      
  