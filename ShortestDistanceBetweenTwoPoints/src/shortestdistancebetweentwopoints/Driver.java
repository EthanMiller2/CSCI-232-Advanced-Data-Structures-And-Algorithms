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
public class Driver {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        // TODO code application logic here
        DataSet d = new DataSet();
        
        d.getPoints("input.txt");
        d.closest();
     

    }

    

}
