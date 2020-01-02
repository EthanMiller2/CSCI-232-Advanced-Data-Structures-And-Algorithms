/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shortestdistancebetweentwopoints;

/**
 *
 * @author ethanmiller
 */
public class Point {
    private double x;
    private double y;
    
    public Point(double inX, double inY){
    
            x = inX;
            y = inY;
    }
    
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
    
    public double getDistance(Point p)
    {
        double dx = this.x - p.x;
        double dy = this.y - p.y;
        double dist = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        return dist;   
    }
    
    @Override
    public String toString()
    {
        return "(" + x + "," + y + ")";
    }
    
    
    
    
        
    
}
