/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.parser.structure.ParseTree;
import com.parser.structure.Token;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import parsing.Parser;
import parsing.Parsing;
import parsing.Tokenizer;

/**
 *
 * 
 */
public class OnParsingExpression {
    
    public OnParsingExpression() {
    }
    
    @Test
    public void shouldPrintTree() {
        Tokenizer tokenizer = new Tokenizer("  sa=  1+(3+  2)-2");
        List<Token> tokens = tokenizer.tokenizeAllInput();
        System.out.println("tokenized string: "+tokens);
        Parser p = new Parser(tokens);
        ParseTree pTree = p.evaluate();
        ParseTree.printTree(pTree);
    }
}
