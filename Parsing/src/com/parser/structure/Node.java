/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parser.structure;

/**
 *
 * @author gbonano
 */
public class Node {
    
    public enum NodeDescription {
        Number,
        Left_Bracket,
        Right_Bracket,
        Expression
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
        return "Node{" + "value=" + value + ", description=" + description + '}';
    }
    
    
}
