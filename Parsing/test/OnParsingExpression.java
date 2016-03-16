/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.parser.structure.ParseTree;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import parsing.Parser;

/**
 *
 * @author gbonano
 */
public class OnParsingExpression {
    
    public OnParsingExpression() {
    }
    
    @Test
    public void shouldPrintTree() {
        ParseTree pTree = Parser.parseInput("24234");
        if (pTree != null) {
            ParseTree.printTree(pTree);
        } else {
            System.out.println("null");
        }
    }
}
