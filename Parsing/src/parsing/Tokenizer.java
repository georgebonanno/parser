/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsing;

import com.parser.structure.Token;
import com.parser.structure.Token.TokenDescription;

/**
 *
 * @author george
 */
public class Tokenizer {
    
    int position;
    private String input;
    private int positionSinceLastToken;
    
    public Tokenizer(String input) {
        this.input = input;
        position=0;
        positionSinceLastToken=0;
    }
    
    public Token getNextToken() {
        String tokenValue="";
        positionSinceLastToken=position;
        TokenDescription tokenDesc = null;
        if (available()) {
        if (Character.isDigit(currentCharacter())) {
            tokenDesc = TokenDescription.Number;
            while (available() && Character.isDigit(currentCharacter())) {
                
                tokenValue+= currentCharacter();
                position++;
            }
        } else if (Character.isAlphabetic(currentCharacter()) ||
                   currentCharacter().equals('_')) {
            tokenDesc = TokenDescription.Identifier;
            while (available() && Character.isDigit(currentCharacter())) {
                tokenValue+= currentCharacter();
                position++;
            }   
        } else if (isSpecial(currentCharacter())) {
            tokenDesc = TokenDescription.SpecialCharacter;
            tokenValue+= currentCharacter();
            position++;
        }
        }
        Token token;
        if (tokenDesc != null) {
            token=Token.createToken(tokenDesc,tokenValue);
        } else {
            token=null;
        }
        return token;
    }
    
    public boolean available() {
        return position < input.length();
    }
    
    private Character currentCharacter() {
        return this.input.charAt(position);
    }

    private boolean isSpecial(Character currentCharacter) {
        return currentCharacter == '(' ||
                currentCharacter == '+' ||
                currentCharacter == ')' ||
                currentCharacter == '-';
    }
}
