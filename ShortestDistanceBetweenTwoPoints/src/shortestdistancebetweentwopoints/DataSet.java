/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shortestdistancebetweentwopoints;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ethanmiller
 */
public class DataSet {

    private int pointNum = 0;
    private Point points[];
    private double maxDist = Double.POSITIVE_INFINITY;
    private Point short1;
    private Point short2;

    public DataSet() {

    }

    public void getPoints(String fileName) {
        String lines[];
        ArrayList<Point> p1 = new ArrayList<>();
        int iter = 0;

        try {
            Scanner scan = new Scanner(new File(fileName));

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                lines = line.split(",");
                Point p = new Point(Double.parseDouble(lines[0]), Double.parseDouble(lines[1]));
                p1.add(p);
                pointNum++;

            }

            points = new Point[pointNum];

            for (Point p : p1) {
                points[iter] = p;
                iter++;

            }

        } catch (FileNotFoundException ex) {
            System.out.println("File Not found");

        }
    }

    public void closest() {
        Point Px[] = new Point[pointNum];
        Point Py[] = new Point[pointNum];

        for (int i = 0; i < pointNum; i++) {
            Px[i] = points[i];
            Py[i] = points[i];
        }

        //Sorts array by x and y coordinate
        Px = bubbleSortX(Px);
        Py = bubbleSortY(Py);

        Point strip[] = new Point[pointNum];

        closestHelper(Px, Py, strip, 0, pointNum - 1);

        System.out.println("Final Result: Closest pair: " + short2 + " and " + short1 + ", Distance: " + maxDist);
    }

    public double closestHelper(Point Px[], Point Py[], Point strip[], int low, int high) {


        if (high <= low) {
            return maxDist;
            
        }

        System.out.println("Solving Problem: Point[" + low + "]....Point[" + high + "]");

        int mid = low + (high - low) / 2;
        Point middle = Px[mid];
        System.out.println("\tDividing at Point[" + mid + "]");

        double dl = closestHelper(Px, Py, strip, low, mid);
        double dr = closestHelper(Px, Py, strip, mid + 1, high);
        double d = Math.min(dl, dr);

        int index = 0;
        for (int i = low; i < high; i++) {
            if (Math.abs(Px[i].getX() - middle.getX()) < d) {
                strip[index++] = Py[i];
            }
        }

        //Check the points within distance d
        for (int i = 0; i < index; i++) {
            for (int j = i + 1; (j < index) && (strip[j].getY() - strip[i].getY() < d); j++) {
                double dist = strip[i].getDistance(strip[j]);
                if (dist < d) {
                    d = dist;
                    if (dist < maxDist) {
                        maxDist = d;
                        short1 = strip[i];
                        short2 = strip[j];

                    }

                }

            }
        }
        return d;

    }

    public Point[] bubbleSortX(Point x[]) {

        int n = x.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (x[j].getX() > x[j + 1].getX()) {
                    // swap temp and arr[i]
                    Point temp = x[j];
                    x[j] = x[j + 1];
                    x[j + 1] = temp;

                }
            }
        }
        return x;
    }

    public Point[] bubbleSortY(Point x[]) {
        int n = x.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (x[j].getY() > x[j + 1].getY()) {
                    // swap temp and arr[i]
                    Point temp = x[j];
                    x[j] = x[j + 1];
                    x[j + 1] = temp;

                }
            }
        }
        return x;

    }

}
