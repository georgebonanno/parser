
import com.parser.structure.Token;
import org.junit.Test;
import parsing.Tokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author george
 */
public class OnTokenisation {
    
    @Test
    public void shouldTokenize() {
        Tokenizer tokenizer = new Tokenizer("332+(343+2234)");
        Token token;

        while((token=tokenizer.getNextToken()) != null) {
            System.out.println(token);
        }
    }
}
