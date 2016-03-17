/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsing;

import com.parser.structure.Token;
import com.parser.structure.Token.TokenDescription;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author george
 */
public class Tokenizer {
    
    int position;
    private String input;
    private int positionSinceLastToken;
    private Token previousToken = null;
    
    private final static Set<Character> OPERATORS = 
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList('+','-','*','/')));
    
    public Tokenizer(String input) {
        this.input = input;
        position=0;
        positionSinceLastToken=0;
    }
    
    public Token getNextToken() {
        String tokenValue="";
        positionSinceLastToken=position;
        TokenDescription tokenDesc = null;
        while (available() && Character.isWhitespace(currentCharacter())) {
            position++;
        }
        if (available()) {
            if ('-' == (char) currentCharacter() && 
                    !previousTokenIsNumberOrRightBracket()) {
                tokenDesc = TokenDescription.UnaryMinus;
                tokenValue+= currentCharacter();
                position++;
            } else if (Character.isDigit(currentCharacter())) {
                tokenDesc = TokenDescription.Number;
                while (available() && Character.isDigit(currentCharacter())) {
                    tokenValue+= currentCharacter();
                    position++;
                }
            } else if (Character.isAlphabetic(currentCharacter()) ||
                       currentCharacter() == '_') {
                tokenDesc = TokenDescription.Identifier;
                while (available() && 
                        (Character.isDigit(currentCharacter()) ||
                         Character.isAlphabetic(currentCharacter()))) {
                    tokenValue+= currentCharacter();
                    position++;
                }   
            } else if (currentCharacter() == '(') {
                tokenDesc = TokenDescription.LeftBracket;
                tokenValue+= currentCharacter();
                position++;
            } else if (currentCharacter() == ')') {
                tokenDesc = TokenDescription.RightBracket;
                tokenValue+= currentCharacter();
                position++;
            } else if (currentCharacter() == '=') {
                tokenDesc = TokenDescription.Assignment;
                tokenValue+= currentCharacter();
                position++;
            }else if (OPERATORS.contains(currentCharacter())) {
                tokenDesc = TokenDescription.Operator;
                tokenValue+= currentCharacter();
                position++;
            }
            
        }
        Token token;
        if (tokenDesc != null) {
            token=Token.createToken(tokenDesc,tokenValue);
            previousToken = token;
            
        } else {
            token=null;
        }
        
        return token;
    }
    
    public List<Token> tokenizeAllInput() {
        List<Token> tokens = new ArrayList<>();
        for (Token token=getNextToken(); token != null; token=getNextToken()) {
            tokens.add(token);
        }
        return tokens;
    }
    
    public boolean available() {
        return position < input.length();
    }
    
    private char currentCharacter() {
        return this.input.charAt(position);
    }

    private boolean previousTokenIsNumberOrRightBracket() {
        return previousToken != null && 
                    (previousToken.getDescription() == TokenDescription.Number ||
                     previousToken.getDescription() == TokenDescription.RightBracket);
    }
}
