/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphoutlab;

/**
 *
 * @author ethanmiller
 */
public class Vertex {
    protected char label;
    protected boolean visited;

    
    public Vertex(char lab)
    {
        label = lab;
        visited = false;
    }
    
    public String toString()
    {
        return label  + " ";
    }
    
}