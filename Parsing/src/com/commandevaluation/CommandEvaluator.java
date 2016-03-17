/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.commandevaluation;

import com.parser.structure.ParseTree;
import com.parser.structure.Token;
import java.util.Arrays;
import java.util.List;
import parsing.Parser;
import parsing.Tokenizer;

/**
 *
 * 
 */
public class CommandEvaluator {
    
    /***
     * evaluates the command against the rules of a simple language 
     * (as described in the Parsing class.
     * The tokens and parseTree for the command are extracted is the 
     * command is obeys the grammar rules. An error is given if otherwise.
     * @param command 
     */
    public static void evaluateCommand(String command) {
        try {
            System.out.println("-------------------------------------------");
            System.out.println("evaluating command: "+command);
            Tokenizer tokenizer = new Tokenizer(command);
            List<Token> tokens = tokenizer.tokenizeAllInput();
            System.out.println("tokens: "+tokens);
            System.out.println("parse tree: ");
            Parser p = new Parser(tokens);
            ParseTree pTree = p.evaluate();

            ParseTree.printTree(pTree);
            System.out.println("-------------------------------------------");
        } catch (Exception e) {
            System.out.println("parse error: "+e.getMessage());
        }
    }
    
    public static void main(String [] args) {
        List<String> commands = Arrays.asList("x=3+(3+343)+3",
                                               "an4=    (42+34)+((3+2))");
        for (String command : commands) {
            evaluateCommand(command);
        }
    }
}
