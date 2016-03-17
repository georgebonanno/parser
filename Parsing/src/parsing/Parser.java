/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsing;

import com.parser.structure.Node;
import com.parser.structure.Node.NodeDescription;
import com.parser.structure.ParseTree;
import com.parser.structure.Token;
import com.parser.structure.Token.TokenDescription;
import java.util.List;
import jdk.nashorn.internal.runtime.regexp.joni.constants.TokenType;

/**
 *
 * @author gbonano
 */
public class Parser {
    
    /**
     * contains the mechanism to parse a list of tokens according to the 
     * following rools:
     * 
     *  <program> ::= {<statement>};
     *  <statement> ::= <assignment>|...other statements;
     *  <assignment> ::= <identifier>'='<expr>
     *  <expr> ::=<lhs>{<op><expr>} | '(' <expr> ')';
     *  <lhs> ::= <numeral> | '(' <expr> ')';
     *  <op> ::= '+' | '-' | '*' | '\';
     *  <numeral> ::= <identifier> | <number> | '-' <number>;
     * 
     * To note that <identifier>, <number> , <op>, '(', ')' and 
     * '-' (unary minus) are all considered as tokens (basic language blocks).
     * These tokens are defined below:
     *  <identifier> ::= <alpha>{<alpha>|<digit>}
     *  <alpha> ::= 'A'|'B'...'a'|...'z'|'_';
     *  <digit> ::= '0'|'1'|..'9'
     * 
     *  <number> ::= {<digit>}
     */
    
    private ParseTree parseTree;
    int lookahead = 0;
    private final List<Token> tokenList;
    // indicates the error that occured. null means that no error occured at
    // this stage.
    private String error = null;

    public Parser(List<Token> tokenList) {
        parseTree = new ParseTree();
        this.tokenList = tokenList;
    }
      
    public ParseTree evaluate() {
        parseTree = new ParseTree();
        lookahead=0;
        
        parseTree = parseProgram();
        
        return parseTree;
    } 
    
    public static ParseTree parseInput(List<Token> tokenList) {
        Parser parser = new Parser(tokenList);
        return parser.evaluate();
    }

    private ParseTree parseAssignment() {
        ParseTree assignmentTree = null;
        if (tokensAvailable()) {
            Token token = currentToken();
            if (token.getDescription() == TokenDescription.Identifier) {
                assignmentTree = ParseTree.buildTree(NodeDescription.Assignment);
                nextToken();
                if (tokensAvailable() && 
                        currentToken().getDescription() == TokenDescription.Assignment) {
                    assignmentTree.addChild(NodeDescription.Token, token);
                    nextToken();
                    ParseTree expression = parseExpression();
                    if (expression == null) {
                        assignmentTree = null;
                        error = "expression expected";
                    } else {
                        assignmentTree.addChild(expression);
                    }
                } else {
                    assignmentTree = null;
                    error = "assignment expected after identifier";
                }
            }
        }  
        
        return assignmentTree;
    }

    private ParseTree parseExpression() {
        ParseTree parseExpression = null;
        if (tokensAvailable()) {
            if (currentToken().getDescription() == TokenDescription.Number ||
                   currentToken().getDescription() == TokenDescription.Identifier) {
                parseExpression = ParseTree.buildTree(NodeDescription.Expression);
                parseExpression.addChild(NodeDescription.Token, currentToken());
                currentToken();
                nextToken();
            } else if (currentToken().getDescription() == TokenDescription.LeftBracket) {
                parseExpression = ParseTree.buildTree(NodeDescription.Expression);
                ParseTree subExp = parseExpressionInBrackets();
                parseExpression.addChild(subExp);
            }
            if (tokensAvailable() && 
                    currentToken().getDescription() == TokenDescription.Operator) {
                parseExpression.addChild(NodeDescription.Token,currentToken());
                nextToken();
                ParseTree rhsExpression = parseExpression();
                if (rhsExpression == null && error == null) {
                    error = "expression expected after operator";
                } else {
                    parseExpression.addChild(rhsExpression);
                }
            }
            
        } 
        
        return parseExpression;
    }

    private ParseTree parseProgram() {
        parseTree = ParseTree.buildTree(NodeDescription.Program);
        while (tokensAvailable()) {
            ParseTree pTree = parseStatement();
            if (pTree != null) {
                parseTree.addChild(pTree);
            } else {
                throw new IllegalArgumentException("parse error: "+error);
            }
        }
        
        return parseTree;
    }
    
    private boolean tokensAvailable() {
        return lookahead < tokenList.size();
    }

    private Token currentToken() {
        Token nextToken;
        if (tokensAvailable()) {
            nextToken = tokenList.get(lookahead);
        } else {
            throw new IllegalStateException("available tokens have been parsed");
        }
        return nextToken;
    }
    
    private Token nextToken() {
        Token nextToken;
        lookahead++;
        if (tokensAvailable()) {
            nextToken = tokenList.get(lookahead);
        } else {
            nextToken = null;
        }
        return nextToken;
    }
    
    private boolean parseErrorOccured() {
        return error != null;
    }
   
    private ParseTree parseStatement() {
        ParseTree statement;
        if (((statement = parseAssignment()) == null) && (!parseErrorOccured())) {
            //try to parse other statements
        }
        return statement;
    }

    private ParseTree parseExpressionInBrackets() {
        ParseTree bracketedExp = null;
        if (tokensAvailable()) {
            if (currentToken().getDescription() == TokenDescription.LeftBracket) {
                bracketedExp = ParseTree.buildTree(NodeDescription.Expression);
                bracketedExp.addChild(NodeDescription.Token, currentToken());
                nextToken();
                ParseTree subExp = parseExpression();
                if (subExp == null) {
                    bracketedExp = null;
                    if (error == null) {
                        error = "expression expected after left bracket";
                    } 
                } else {
                    bracketedExp.addChild(subExp);
                    if (tokensAvailable()) {
                        if (currentToken().getDescription() == TokenDescription.RightBracket) {
                            bracketedExp.addChild(NodeDescription.Token, currentToken());
                            nextToken();
                        } else {
                            error = "right bracket expected";
                            bracketedExp = null;
                        }
                    } else {
                        error = "right bracket expected";
                    }
                }
            }
            
        }
        return bracketedExp;    
    }
    
}
