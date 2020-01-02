/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimumspanningtreelab;

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
        final int inf = 99999;

        int[][] fw = {
            {0, 50, inf, 80, inf},
            {inf, 0, 60, 90, inf},
            {inf, inf, 0, inf, 40},
            {inf, inf, 20, 0, 70},
            {inf, 50, inf, inf, 0}

        };
        
        int[][]matrix = {
            {0, 30, 18, 0, 20},
            {30, 0, 15, 60, 0},
            {0, 15, 0, 32, 0},
            {0, 60, 32, 0, 12},
            {20, 0, 0, 12, 0}
       
        };

        Graph i = new Graph(fw);
        i.floydWarshals();
        Graph g = new Graph(matrix);
        g.prims();
        Graph h = new Graph(matrix);
        h.kruskals();
        

    }
    
}
