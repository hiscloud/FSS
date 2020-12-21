/*
 * The output class to store the data into a file
 */


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Junyu Lu
 */
public class Output {

    BufferedWriter bw;
/**
 * the constructor which use the file name for the output
 * @param fileName 
 */
    public Output(String fileName) {
        try {
            bw = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
/**
 * write a string in the file
 * @param s 
 */
    public void write(String s) {

        try {
            bw.write(s + ",");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
/**
 * write an array in the file
 * @param arr 
 */
    public void writeArrayInt(int[] arr) {
        try {
            for (int i = 0; i < arr.length; i++) {
                bw.write(arr[i] + ",");
            }
          
        } catch (IOException e) {
            System.out.println(e);
        }
    }
/**
 * write a matrix in the file
 * @param mx 
 */
    public void writeMatrix(int[][] mx) {
        for (int i = 0; i < mx.length; i++) {
            writeArrayInt(mx[i]);
            next();
        }
    }
/**
 * go to another line of the file
 */
    public void next() {
        try {
            bw.newLine();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void close() {
        try {
            bw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
