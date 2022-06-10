---------------------------------------------------
--Abdulaziz alkhelaiwi (Aziz)
--Class: COMP 3351: Programming Languages
--Project info: WeeklyFour - LISP Style Language 
---------------------------------------------------
module TinyEvaluator where

import TinyParser
import TinyLexer
import Env

import TinyDefinitions

-- compileAndRun
--   Consumes a String which is the program
--   Produces the result of lexing, parsing, and evaluating the program
compileAndRun :: String -> ValueType
compileAndRun program = evaluate (parseString program) emptyEnv

-- evaluate
--   Consume a Parse Tree
--   Produce the result value of evaluating the given Parse Tree
evaluate :: ParseTree -> EnvType -> ValueType 
evaluate tree env = case tree of
                      (ValueNode (BoolType val)) -> BoolType val
                      (ValueNode (ClosureType val)) -> ClosureType val
                      (ValueNode (IntegerType val)) -> IntegerType val
                      (ValueNode (PairType exprOne exprTwo)) -> PairType exprOne exprTwo
                      (IdNode var) -> (applyEnv var env)
                      (NotNode val) -> let param = (evaluate val env)
                                     
                                       in 
                                            case param of 
                                               (BoolType True) -> (BoolType False)
                                               (BoolType False) -> (BoolType True)
                      (AndNode valOne valTwo) -> let paramOne = (evaluate valOne env)
                                                     paramTwo = (evaluate valTwo env)
                                                   in
                                                     case paramOne of 
                                                        (BoolType True) -> 
                                                           case paramTwo of
                                                             (BoolType True) -> (BoolType True)
                                                             (BoolType False) -> (BoolType False)
                                                        (BoolType False) -> (BoolType False)
                      (OrNode valOne valTwo) -> let paramOne = (evaluate valOne env)
                                                    paramTwo = (evaluate valTwo env)
                                                  in
                                                    case paramOne of
                                                       (BoolType True) -> (BoolType True)
                                                       (BoolType False) ->
                                                           case paramTwo of
                                                                (BoolType True) -> (BoolType True)
                                                                (BoolType False) -> (BoolType False)
-- TODO: Add evaluation of all the Mathematical Operations

                      (AdditionNode valOne valTwo) -> let paramOne = (evaluate valOne env)
                                                          paramTwo = (evaluate valTwo env)
                                                        in
                                                          case (paramOne, paramTwo) of
                                                               (IntegerType intOne, IntegerType intTwo) -> IntegerType (intOne + intTwo)
                      (SubtractionNode valOne valTwo) -> let paramOne = (evaluate valOne env)
                                                             paramTwo = (evaluate valTwo env)
                                                          in
                                                            case (paramOne, paramTwo) of
                                                                (IntegerType intOne, IntegerType intTwo) -> IntegerType (intOne - intTwo)
                      (MultiplicationNode valOne valTwo) -> let paramOne = (evaluate valOne env)
                                                                paramTwo = (evaluate valTwo env)
                                                              in
                                                                case (paramOne, paramTwo) of
                                                                    (IntegerType intOne, IntegerType intTwo) -> IntegerType (intOne * intTwo)
                      (DivisionNode valOne valTwo) -> let paramOne = (evaluate valOne env)
                                                          paramTwo = (evaluate valTwo env)
                                                        in
                                                          case (paramOne, paramTwo) of
                                                               (IntegerType intOne, IntegerType intTwo) -> IntegerType (intOne `div` intTwo)
                      (RemainderNode valOne valTwo) -> let paramOne = (evaluate valOne env)
                                                           paramTwo = (evaluate valTwo env)
                                                        in
                                                          case (paramOne, paramTwo) of
                                                               (IntegerType intOne, IntegerType intTwo) -> IntegerType (intOne `mod` intTwo)

                      (LetNode id val body) -> let valResult = (evaluate val env)
                                                           in 
                                                             (evaluate body 
                                                               (extendEnv (id,valResult) env))
                      (LambdaNode id body) -> ClosureType (Closure id body env)
                      (CallNode functionName expr) -> 
                           let result = applyEnv functionName env
                              in 
                                case result of
                                    ClosureType (Closure paramName functionBody functionEnv) ->
                                         (evaluate functionBody 
                                              (extendEnv (paramName, (evaluate expr env)) functionEnv))
                                    _ -> error "Illegal function call"
                   
