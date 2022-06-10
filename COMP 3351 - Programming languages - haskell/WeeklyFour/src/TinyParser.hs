module TinyParser where

--    A Parser for things
-- Is a function from strings
--     To lists of pairs
--   Of things and strings

import TinyDefinitions
import TinyLexer

import MonadicParserLibrary
import Control.Applicative 

-- parseString
--   Consume a string containing a Tiny Language program 
--   Produce a structure representing the parse tree of the program 
-- parseString :: String -> [(ParseTree,String)]
parseString :: String -> ParseTree 
parseString program = let [(tree,remainingChars)] = parse expressionParser program
                          in
                             case remainingChars of 
                                  "" -> tree
                                  _ -> error ("Parse Error: " ++ remainingChars)

-- expressionParser
--   Produce a parser for an expression in the Tiny Language
--     The parser will produce a ParseTree representing the 
--         program 


expressionParser :: Parser ParseTree  
expressionParser = do boolExpr <- boolLevelOne   
                      return boolExpr 
                    <|>
                   do mathExpr <- mathLevelOne
                      return mathExpr
                    <|>
                   do leftParenthesis
                      pairKeyword
                      exprOne <- expressionParser
                      exprTwo <- expressionParser
                      rightParenthesis
                      return (ValueNode (PairType exprOne exprTwo))
                    <|> 
                   do leftParenthesis
                      letKeyword
                      i <- ident
                      equalKeyword
                      expr <- expressionParser 
                      inKeyword
                      body <- expressionParser
                      rightParenthesis
                      return (LetNode i expr body)
                    <|> 
                   do leftParenthesis
                      lambdaKeyword
                      i <- ident
                      inKeyword
                      body <- expressionParser
                      rightParenthesis
                      return (LambdaNode i body)

                    <|>
                   do callKeyword
                      leftParenthesis
                      i <- ident
                      body <- expressionParser
                      rightParenthesis
                      return (CallNode i body)

-- Lowest level of precedence of Boolean Expressions 
--    This handles the boolean or operation
boolLevelOne :: Parser ParseTree 
boolLevelOne = do exprOne <- boolLevelTwo
                  do op <- orOp
                     exprTwo <- expressionParser 
                     return (OrNode exprOne exprTwo)
                    <|> 
                     return exprOne 

-- Second level of precedence of Boolean Expressions
--    This handles the boolean and operation 
boolLevelTwo :: Parser ParseTree
boolLevelTwo = do exprOne <- boolLevelThree
                  do op <- andOp
                     exprTwo <- expressionParser
                     return (AndNode exprOne exprTwo)

                   <|>
                     return exprOne


-- Third level of precedence of Boolean Expressions
--     This handles the boolean not operation
boolLevelThree :: Parser ParseTree
boolLevelThree = do op <- notOp
                    expr <- expressionParser
                    return (NotNode expr)
                  <|>
                 do leftParenthesis
                    expr <- expressionParser
                    rightParenthesis
                    return expr
                  <|>
                 do b <- boolConst 
                    return b     

-- Lowest level of precedence of Math Expressions 
--    This handles the math add and subtract operations

mathLevelOne :: Parser ParseTree 
mathLevelOne = do exprOne <- mathLevelTwo
                  do op <- addOp
                     exprTwo <- expressionParser 
                     return (AdditionNode  exprOne exprTwo)
                   <|> do op <- subtractOp
                          exprTwo <- expressionParser
                          return (SubtractionNode  exprOne exprTwo) 
                   <|>
                     return exprOne


-- Lowest level of precedence of Math Expressions 
--    This handles the math multiplication, division and remainder operations
mathLevelTwo :: Parser ParseTree 
mathLevelTwo = do exprOne <- mathLevelThree
                  do op <- multiplyOp
                     exprTwo <- expressionParser 
                     return (MultiplicationNode exprOne exprTwo)
                   <|> do op <- divideOp
                          exprTwo <- expressionParser
                          return (DivisionNode exprOne exprTwo) 
                   <|>
                     return exprOne
    
-- Third level of precedence of Math Expressions
--     This handles parenthesis
mathLevelThree :: Parser ParseTree
mathLevelThree = do leftParenthesis
                    expr <- expressionParser
                    rightParenthesis
                    return expr
                  <|> do num <- integerConst  
                         return num
