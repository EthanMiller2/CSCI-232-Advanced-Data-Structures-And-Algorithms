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
public class Driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Graph g = new Graph();
        System.out.println("Depth First Search Visits: ");
        g.depthFirstSearch();
        System.out.println("");
        System.out.println("Breadth First Search Visits: ");
        g.breadthFirstSearch();
        System.out.println("");
        System.out.println("Minimum Spanning Tree: ");
        g.minimum_spanning_tree();
        System.out.println("");
    }

        // TODO code application logic here
}
