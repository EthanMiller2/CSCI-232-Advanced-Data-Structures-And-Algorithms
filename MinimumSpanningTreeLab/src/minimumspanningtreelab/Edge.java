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
public class Edge {
    protected Vertex start;
    protected Vertex end;
    protected int weight;
    protected Edge back;
    
    public Edge(Vertex inA, Vertex inB, int inWeight)
    {
        start = inA;
        end = inB;
        weight = inWeight;
        
    }
    
    public Vertex getStartVertex()
    {
        return start;
    }
    
    public Vertex getEndVertex()
    {
        return end;
    }
    
    public int getWeight()
    {
        return weight;
    }
    
    public int compareWeight(Edge x)
    {
        return this.weight - x.weight;
    }
    
    @Override
    public String toString()
    {
        return "(" + getStartVertex() + "," + getEndVertex() + ")"+ " : " + getWeight();
    }
    
}
