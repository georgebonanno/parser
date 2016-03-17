/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parser.structure;

/**
 *
 * 
 */
public class Node {
    
    public enum NodeDescription {
        Expression,
        Program,
        Numeral,
        ExpressionLHS,
        Assignment,
        Token
    };
    
    private Object value;
    
    private NodeDescription description;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public NodeDescription getDescription() {
        return description;
    }

    public void setDescription(NodeDescription description) {
        this.description = description;
    }
    
    public static Node buildNode(NodeDescription description,Object value) {
        Node n = new Node();
        n.description = description;
        n.value=value;
        
        return n;
    }

    @Override
    public String toString() {
        String stringDesc;
        if (value == null) {
            stringDesc = "Node{description=" + description + '}';
        } else {
            stringDesc = "Node{" + "value=" + value + ", description=" + description + '}';
        }
        return stringDesc;
    }
    
    
}
