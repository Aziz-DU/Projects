module TinyLexer where

import Control.Applicative 
import MonadicParserLibrary 

import TinyDefinitions

-- Build monadic parsers for each of the words in the Tiny
--   Language 

trueConst :: Parser ParseTree 
trueConst = do symbol "true"
               return (ValueNode (BoolType True))

falseConst :: Parser ParseTree 
falseConst = do symbol "false"
                return (ValueNode (BoolType False))

boolConst :: Parser ParseTree 
boolConst = do trueConst 
             <|> falseConst

andOp :: Parser String
andOp = symbol "and"

orOp :: Parser String
orOp = symbol "or"

notOp :: Parser String
notOp = symbol "not"

boolOp :: Parser String
boolOp = do andOp
           <|> orOp
           <|> notOp 

leftParenthesis :: Parser String
leftParenthesis = do symbol "("

rightParenthesis :: Parser String
rightParenthesis = do symbol ")"

addOp :: Parser String
addOp = do symbol "add"

subtractOp :: Parser String
subtractOp = do symbol "subtract"

multiplyOp :: Parser String
multiplyOp = do symbol "multiply"

divideOp :: Parser String
divideOp = do symbol "divide"

remainderOp :: Parser String
remainderOp = do symbol "remainder"

mathOp :: Parser String
mathOp = do addOp
          <|> subtractOp
          <|> multiplyOp
          <|> divideOp
          <|> remainderOp

integerConst :: Parser ParseTree
integerConst = do num <- integer
                  return (ValueNode (IntegerType num))

equalKeyword :: Parser String
equalKeyword = symbol "equals"

callKeyword :: Parser String
callKeyword = symbol "call"

inKeyword :: Parser String
inKeyword = symbol "in"

lambdaKeyword :: Parser String
lambdaKeyword = symbol "lambda"

letKeyword :: Parser String
letKeyword = symbol "let"

pairKeyword :: Parser String
pairKeyword = symbol "pair"

