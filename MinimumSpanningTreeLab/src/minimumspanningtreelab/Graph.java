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
public class Graph {

    private final int inf = 99999;
    private Vertex[] vertexs;
    char[] labels = {'a', 'b', 'c', 'd', 'e'};
    private String abc = "abcdefghijklmnopqrstuvwxyz";
    int L = labels.length;
    private int[][] matrix;

    public Graph(int[][] inMatrix) {
        matrix = inMatrix;
        convertToVertexs();
        
    }

    public void convertToVertexs() {
        vertexs = new Vertex[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            vertexs[i] = new Vertex(abc.charAt(i));
        }

    }

    public void prims() {
        PriorityQueue p = new PriorityQueue();
        int num = 0;
        int iter = 0;
        boolean found = false;
        System.out.println("The minimum spanning tree with Prim's algorithm is:");
        //there will always be one less edge than vertexes 
        while(num < (matrix.length - 1)){
            //finds all connected vertexes
            for(int i = 0; i < matrix.length; i++){
               if(matrix[iter][i] != 0){
                   p.add(new Edge(vertexs[iter], vertexs[i], matrix[iter][i]));
               } 
            }
            found = false;
            while(found == false){
                if(p.peek().start.visited == false || p.peek().end.visited == false){
                    //the lowest edge value connected that doesn't create a loop
                    p.peek().start.visited = true;
                    p.peek().end.visited = true;
                    System.out.print(" " + p.peek().start.label + p.peek().end.label);
                    //finds the vertex that the new connection made and goes through loop with it
                    for(int i = 0; i < vertexs.length; i++){
                        if(p.peek().end.label == vertexs[i].label && i != iter){
                            iter = i;
                        }
                    }
                    p.remove();
                    found = true;
                    num++;
                } else if (p.isEmpty() == true){
                    //Shouldn't happen, but will get out of loop and stop null pointer if it does
                    found = true;
                    break;
                }else{
                    p.remove();
                }
            }
        }
        System.out.println();
    }

    public void kruskals() {
        //create priority queue 
        PriorityQueue p = new PriorityQueue();
        //adding all vertices to matrix
        int j;
        for(int i = 0; i < matrix.length; i++){
            j = i;
            while(j > -1){
                if(matrix[i][j] != 0){
                    p.add(new Edge(vertexs[i], vertexs[j], matrix[i][j]));
                }
                j--;
            }
        }
        System.out.println("The minimum spanning tree using Kruskal's Algorithm has edges:");
        Edge iter;
        while(p.isEmpty() == false){
            iter = p.peek();
            //will only add smallest edges that don't create loops, because will only use edges that add a new vertex
            if(iter.start.visited == false || iter.end.visited == false){
                iter.start.visited = true;
                iter.end.visited = true;
                System.out.print(" " + iter.start.label + iter.end.label);
            }
            p.remove();
        }
    }

    public void floydWarshals() {
        int[][] x = matrix;
        int shortest[][] = new int[L][L];
        int i, j, k;

        for (i = 0; i < L; i++) {
            for (j = 0; j < L; j++) {
                shortest[i][j] = x[i][j];

            }
        }

        for (k = 0; k < L; k++) {
            for (i = 0; i < L; i++) {
                for (j = 0; j < L; j++) {
                    if (shortest[i][j] > shortest[i][k] + shortest[k][j]) {
                        shortest[i][j] = shortest[i][k] + shortest[k][j];
                        printAdjacencyMatrix(shortest);
                    }
                }
            }
        }
        System.out.println("The final matrix is as follows: ");
        printAdjacencyMatrix(shortest);
    }

    public void printAdjacencyMatrix(int[][] x) {
        System.out.print("  ");
        for (Vertex vertex : vertexs) {
            System.out.print(vertex + "\t");
        }
        System.out.println("");

        for (int i = 0; i < L; i++) {
            System.out.print(vertexs[i] + " ");
            for (int j = 0; j < L; j++) {

                if (x[i][j] == inf) {
                    System.out.print("∞\t");
                } else {
                    System.out.print(x[i][j] + "\t");
                }
            }
            System.out.println();
        }
        System.out.println("");
    }

}

