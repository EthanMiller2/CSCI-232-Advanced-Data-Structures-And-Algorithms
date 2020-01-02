/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphoutlab;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author ethanmiller
 */
public class Graph {

    private Vertex vertexList[];
    private int adjacencyMatrix[][];
    private String vertexs;
    private int dim;
    private String[] vals;
    private ArrayList<Vertex> mst;

    public Graph() {
        readLabels("labels.txt");
        readAdjacencyMatrix("adjacency_matrix.txt");
//        printAdjacencyMatrix(adjacencyMatrix);

    }

    private void readAdjacencyMatrix(String fileName) {

        adjacencyMatrix = new int[dim][dim];

        int i = 0, j = 0;

        try {

            FileReader reader = new FileReader(fileName);

            BufferedReader br = new BufferedReader(reader);

            String line;
            while ((line = br.readLine()) != null) {

                j = 0;

                String vals[] = line.split("");

                for (String s : vals) {

                    adjacencyMatrix[i][j] = Integer.parseInt(s);

                    j++;

                }

                i++;

            }

        } catch (FileNotFoundException ex) {
            System.out.println("File Not found");

        } catch (IOException e) {

        }

    }

    private void printAdjacencyMatrix(int[][] array) {

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
    }

    private void readLabels(String fileName) {

        try {
            String line;

            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                vertexs = line;
            }

            char[] vertexChars = vertexs.toCharArray();
            dim = vertexChars.length;
            vertexList = new Vertex[dim];
            bufferedReader.close();

            for (int i = 0; i < vertexChars.length; i++) {
                vertexList[i] = new Vertex(vertexChars[i]);
            }

//            for (int i = 0; i < vertexList.length; i++) {
//                System.out.println(v[i]);
//            }
        } catch (FileNotFoundException ex) {
            System.out.println("File Not found");
        } catch (IOException e) {
            System.out.println("Error");

        }
    }

    public void depthFirstSearch() {

        Stack<Vertex> s = new Stack<Vertex>();
        mst = new ArrayList<>();

        Vertex start = vertexList[0];
        s.push(start);
        vertexList[0].visited = true;
        while (!s.isEmpty()) {
            Vertex temp = (Vertex) s.pop();
            System.out.print(temp);

            ArrayList<Vertex> neighborsStack = getNeighbors(temp);
            for (Vertex v : neighborsStack) {
                if (v != null && !v.visited) {
                    s.push(v);
                    v.visited = true;

                }

            }
        }
    }

    public void breadthFirstSearch() {
        readLabels("labels.txt");
        readAdjacencyMatrix("adjacency_matrix.txt");

        Queue<Vertex> q = new LinkedList<Vertex>();
        Vertex start = vertexList[0];
        q.add(start);
        start.visited = true;

        while (!q.isEmpty()) {
            Vertex temp = q.remove();
            System.out.print(temp);
            ArrayList<Vertex> neighborsQueue = getNeighbors(temp);
            for (Vertex v : neighborsQueue) {
                if (v != null && !v.visited) {
                    q.add(v);
                    v.visited = true;
                }
            }

        }

    }

    public void minimum_spanning_tree() {

        readLabels("labels.txt");
        readAdjacencyMatrix("adjacency_matrix.txt");
        //With undirected and unweighted graphs we already have the minimum spanning tree from Depth First Search
        depthFirstSearch();

    }

    public ArrayList<Vertex> getNeighbors(Vertex v) {
        int nodeIndex = -1;

        ArrayList<Vertex> neighbors = new ArrayList<>();
        for (int i = 0; i < vertexList.length; i++) {
            if (vertexList[i].equals(v)) {
                nodeIndex = i;
                break;
            }
        }

        if (nodeIndex != -1) {
            for (int j = 0; j < adjacencyMatrix[nodeIndex].length; j++) {
                if (adjacencyMatrix[nodeIndex][j] == 1) {
                    neighbors.add(vertexList[j]);
                }
            }
        }
        return neighbors;
    }

}
