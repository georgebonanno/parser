/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsing;

import com.parser.structure.Node;
import com.parser.structure.Node.NodeDescription;
import com.parser.structure.ParseTree;

/**
 *
 * @author gbonano
 */
public class Parser {
    
    private ParseTree parseTree;
    int lookahead;
    private String input;

    public Parser(String input) {
        this.input = input;
        parseTree = null;
        lookahead = 0;
    }
      
    private ParseTree evaluate(String streamToParse) {
        parseTree = new ParseTree();
        lookahead=0;
        
        parseTree = parseExpression(streamToParse);
        
        return parseTree;
    } 
    
    public static ParseTree parseInput(String message) {
        Parser parser = new Parser(message);
        return parser.evaluate(message);
    }

    private ParseTree parseExpression(String streamToParse) {
        ParseTree parsedStructure=parseNumber(streamToParse);
        
        if (parsedStructure == null) {
            parsedStructure = parseExpressionInBracket(streamToParse);
        }
       
        return parsedStructure;
    }

    private ParseTree parseNumber(String streamToParse) {
        ParseTree parsedNumber = null; 
        String number = "";
        while (lookahead < streamToParse.length() && 
                Character.isDigit(streamToParse.charAt(lookahead))) {
            number += streamToParse.charAt(lookahead);
            lookahead++;
        }
        
        if (!number.isEmpty()) {
            parsedNumber = new ParseTree();
            Node numberNode = 
                    Node.buildNode(NodeDescription.Number, Integer.valueOf(number));
            parsedNumber.setValue(numberNode);
        }
        
        return parsedNumber;
    }

    private ParseTree parseExpressionInBracket(String streamToParse) {
        int start = lookahead;
        ParseTree pTree = null;
        if (charactersAvailable() && nextChar() == '(') {
            lookahead++;
        }
        
        return pTree;
    }

    private boolean charactersAvailable() {
        return lookahead < this.input.length();
    }

    private char nextChar() {
        return this.input.charAt(lookahead);
    }
    
    
}
