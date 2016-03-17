/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parser.structure;

import com.parser.structure.Node.NodeDescription;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 
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
    
    public static ParseTree buildTree(NodeDescription nodeDescription) {
        return buildTree(nodeDescription, null);
    }
    
    public static ParseTree buildTree(Node rootNode) {
        ParseTree ptree = new ParseTree();
        ptree.setValue(rootNode);
        return ptree;
    }
    
    public static ParseTree buildTree(NodeDescription nodeDescription,
                                        Object nodeValue) {
        Node n = new Node();
        n.setDescription(nodeDescription);
        n.setValue(nodeValue);
        ParseTree ptree = new ParseTree();
        ptree.setValue(n);
        return ptree;
    }
    
    public static void printTree(ParseTree pTree) {
        printTreeWithDepth(pTree, 0);
    }
    
    public void addChild(Node node) {
        children.add(buildTree(node));
    }
    
    public void addChild(ParseTree node) {
        children.add(node);
    }
    
    public void addChild(NodeDescription nodeDescription,
                            Object nodeValue) {
        Node node = new Node();
        node.setValue(nodeValue);
        node.setDescription(nodeDescription);
        addChild(node);
    }
}
