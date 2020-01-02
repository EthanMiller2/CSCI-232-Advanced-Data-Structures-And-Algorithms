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
public class PriorityQueue {
    private Edge first; 
    private boolean empty = false;
    
   
    public void add(Edge x){
        Edge iter;
        Edge iter2;
        if(first == null){
            first = x;
        } else if(x.weight < first.weight){
            x.back = first;
            first = x;
        } else if (x.weight > first.weight){
            iter = first.back;
            iter2 = first;
            while(x.weight > iter.weight){
                if(iter.back == null){
                    iter.back = x;
                    break;
                } else{
                    iter2 = iter;
                    iter = iter.back;
                }
            }
            if(iter.back != x){
                iter2.back = x;
                x.back = iter;
            }
        }
    }
    
    public void remove(){
        if(first.back == null){
            empty = true;
            first = null;
        } else {
            first = first.back;
        }
    }
    
    public boolean isEmpty(){
        return empty;
    }
    
    public Edge peek(){
        return first;
    }
}

