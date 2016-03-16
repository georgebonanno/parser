/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parser.structure;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gbonano
 */
public class ParseTree {
    
    private Node value;
    
    private final List<ParseTree> children = new ArrayList<>();

    public Node getValue() {
        return value;
    }

    public List<ParseTree> getChildren() {
        return children;
    }

    public void setValue(Node value) {
        this.value = value;
    }

    private static void printTreeWithDepth(ParseTree parseTree,int depth) {
        if (parseTree != null) {
            String tabs="";
            for(int i=0; i<depth; i++) {
                tabs+="\t";
            }
            System.out.println(tabs+parseTree.getValue());
            for (ParseTree child : parseTree.getChildren()) {
                printTreeWithDepth(child, depth+1);
            }
        }
    }
    
    public static void printTree(ParseTree pTree) {
        printTreeWithDepth(pTree, 0);
    }
    
}
