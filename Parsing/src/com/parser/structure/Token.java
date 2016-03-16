/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parser.structure;

/**
 *
 * @author george
 */
public class Token {

    public enum TokenDescription {
        Number,
        SpecialCharacter,
        Identifier
    };
    
    private String value;
    private TokenDescription description;

    public static Token createToken(TokenDescription tokenDesc, String tokenValue) {
      Token token = new Token();
      token.description = tokenDesc;
      token.value = tokenValue;
      return token;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TokenDescription getDescription() {
        return description;
    }

    public void setDescription(TokenDescription description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Token{" + " value=" + value + ",description='" + description +"'}";
    }
    
    

}
