module TinyDefinitions where

  data ParseTree = AdditionNode ParseTree ParseTree      |
                   SubtractionNode ParseTree ParseTree   |
                   MultiplicationNode ParseTree ParseTree|
                   DivisionNode ParseTree ParseTree      |
                   RemainderNode ParseTree ParseTree     |
                   AndNode ParseTree ParseTree           |
                   OrNode ParseTree ParseTree            |
                   NotNode ParseTree                     |
                   ValueNode ValueType                   |
                   IdNode String                         |
                   LetNode String ParseTree ParseTree    |
                   LambdaNode String ParseTree           |
                   CallNode String ParseTree             |
                   EmptyNode
                    deriving (Show)
  
  -- closure structure

  data ClosureStructure = Closure String ParseTree EnvType 
                          deriving (Show)

  data ValueType = BoolType Bool                | 
                   IntegerType Integer          |
                   PairType ParseTree ParseTree |
                   ClosureType ClosureStructure
                     deriving (Show)

  type EnvType = [(String,ValueType)]
