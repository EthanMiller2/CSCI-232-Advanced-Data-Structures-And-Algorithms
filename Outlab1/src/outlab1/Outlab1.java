/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package outlab1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author ethanmiller
 */
public class Outlab1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

  
        String fileName = "input.txt";
        File file1 = new File("outputA.txt");
        File file2 = new File("outputB.txt");
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(fileName));
            BufferedWriter bw3 = new BufferedWriter(new FileWriter(file1));
            
            String line = null;
            while ((line = br1.readLine()) != null) {
                System.out.println(line);
                bw3.write(line + "\n");
                

            }
            br1.close();
            bw3.close();

            System.out.println("\n******************\n");
            BufferedReader br2 = new BufferedReader(new FileReader(fileName));
            BufferedWriter bw4 = new BufferedWriter(new FileWriter(file2));

            line = null;
            while ((line = br2.readLine()) != null) {
                String [] wordReader = line.split(" ");

                for (int i = 0; i < wordReader.length; i++) {
                    System.out.println(wordReader[i]);
                    bw4.write(wordReader[i] + "\n");
                }
            }

            br2.close();
            bw4.close();

        } catch (IOException x) {

            System.out.println("File Not found");

        }
    }
}
