---------------------------------------------------
--Abdulaziz alkhelaiwi (Aziz)
--Class: COMP 3351: Programming Languages
--Project info: DailyEight - Problem Soliving With Haskell
---------------------------------------------------

module DailyEight where
import Text.Printf

data Event = Event { name :: String
 , day :: Int
 , month :: String
 , year :: Int
 , xlocation :: Double
 , ylocation :: Double
 }  deriving (Eq,Show,Read)

--  takes a number called, year, and a list of event structures and produces a new list of the events on that year.
-- two helper functions were used, one that filters out events that don't match our given rules
-- and one that extracts requested information from the accepted events
inYear ::  (Integral year) => year -> [Event] -> [Event]
inYear yr evs = let onlyAllowedEvs = filter (\event -> checkRequirements event==True) evs 
  in inYear' yr onlyAllowedEvs

inYear' ::   (Integral year) => year -> [Event] -> [Event]
inYear' yr evs = filter (\event-> (fromIntegral (year event))==yr) evs
 
--  takes two days (a start day and an end day) and a list of event structures and produces a new list that includes the names
-- of the events that are happening in the range
-- two helper functions were used, one that filters out events that don't match our given rules
-- and one that extracts requested information from the accepted events
inDayRange :: (Integral day) =>day -> day -> [Event] -> [String]
inDayRange sday eday evs = let onlyAllowedEvs = filter (\event -> checkRequirements event==True) evs 
  in inDayRange' sday eday onlyAllowedEvs

inDayRange' :: (Integral day) =>day -> day -> [Event] -> [String]
inDayRange' sday eday evs = map (name) (filter (\event -> let dRange= (fromIntegral (day event)) 
  in dRange >= sday && dRange <= eday) evs)

-- takes a name, a lower x location, an upper x location, a lower y location, an upper y location and a list of event structures and produces a new list
-- of the events that are happening in the given location range
-- two helper functions were used, one that filters out events that don't match our given rules
-- and one that extracts requested information from the accepted events
inArea :: (Real xlocation,Real ylocation,Show name) => name -> xlocation -> xlocation -> ylocation -> ylocation -> [Event] -> [Event]
inArea n xlocS xlocE ylocS ylocE evs = let onlyAllowedEvs = filter (\event -> checkRequirements event==True) evs  
  in inArea' n xlocS xlocE ylocS ylocE onlyAllowedEvs

inArea' :: (Real xlocation,Real ylocation,Show name) => name -> xlocation -> xlocation -> ylocation -> ylocation -> [Event] -> [Event]
inArea' n xlocS xlocE ylocS ylocE evs = filter (\event -> 
  let x = ( realToFrac  (xlocation event))
      y = ( realToFrac  (ylocation event))
      in (x >=  realToFrac(xlocS) && x <=  realToFrac(xlocE))&&(y >=  realToFrac(ylocS) && y <=  realToFrac(ylocE))&&(read(show(n))==(name event))) evs


-- a helper function that other functions use to filter out events that dont abide by the given rules
checkRequirements ::  Event -> Bool
checkRequirements e = if 
  (d' e >= 1 && d' e <= 31)&&
  (length(show(m' e))==5)&&
  (y' e >= 2021 && y' e <= 2031)&&
  (show(xloc' e) == roundToStr 2 (xloc' e))&&
  (show(yloc' e) == roundToStr 2 (yloc' e)) 
  then True 
  else False

-- helper functions that use pattern matching to extract desired values
d' :: Event -> Int
d' (Event  _ d _ _ _ _) = d

m' :: Event -> String
m' (Event _ _ m _ _ _) = m

y' :: Event -> Int
y' (Event _ _ _ y _ _) = y

xloc' :: Event -> Double
xloc' (Event _ _ _ _ x _) = x

yloc' :: Event -> Double
yloc' (Event _ _ _ _ _ y) = y

-- helper function used to get 2 decimal doubles
roundToStr :: (PrintfArg a, Floating a) => Int -> a -> String
roundToStr n f = printf ("%0." ++ show n ++ "f") f

-- oo= [Event "sss" 2 "ssx" 2021 23.33 44.22,Event "eww" 2 "ssx" 2022 21.33 40.22,Event "mmm" 0 "ssx" 2020 23.3 44.22]