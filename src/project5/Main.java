/*
 * Main class of Project5
 */


import java.io.File;
import java.util.Scanner;
import java.io.IOException;

/**
 *
 * @author Junyu Lu
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int r = 0;///rows of a matrix
        int c = 0;///columns of a matrix
        int fileMax = 120;///the total number of tailard sets
        //1.reorder matrix and get result
        System.out.println("start");
		
		
        try {
            Scanner inSc = new Scanner(new File("input.txt"));
            fileMax = inSc.nextInt();
			System.out.println(fileMax);
            while (inSc.hasNext()) {
                int fileNum = inSc.nextInt();
                int objectiveF = inSc.nextInt();
                Scanner sc = new Scanner(new File("DataFiles/" + fileNum + ".txt"));
                r = sc.nextInt();
                c = sc.nextInt();
                int[][] mx = new int[r][c];
                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        mx[i][j] = sc.nextInt();
                    }
                }
                mx = Matrix.transverse(mx);

                int[] order = new int[mx.length];
                for (int i = 0; i < mx.length; i++) {
                    order[i] = inSc.nextInt();
                }
                mx = Matrix.reorder(mx, order);

                int result = 0;
                if (objectiveF == 1) {
                    result = FlowShopScheduling.FSS(mx);
                }
                if (objectiveF == 2) {
                    result = FlowShopScheduling.FSSB(mx);
                }
                if (objectiveF == 3) {
                    result = FlowShopScheduling.FSSNW(mx);
                }
                System.out.print("The matrix " + fileNum + ", in the order:");
                for (int i = 0; i < order.length; i++) {
                    System.out.print(order[i] + " ");
                }
                System.out.println(" shows below.");

                System.out.println("Cmax for the matrix " + fileNum + ", with situation " + objectiveF + " " + " in this order is " + result);
                Matrix.printMx(mx);
            }
        } catch (IOException e) {
            System.out.println("No input files found.");
        }

        //2.run the talliard sets
        Output out = new Output("output.csv");
        System.out.println("Now start Tailard Sets, please wait.");
        for (int fileNum = 1; fileNum <= fileMax; fileNum++) {
            try {
                Scanner sc = new Scanner(new File("./DataFiles/" + fileNum + ".txt"));
                r = sc.nextInt();
                c = sc.nextInt();
                int[][] mx = new int[r][c];
                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        mx[i][j] = sc.nextInt();
                    }
                }
                mx = Matrix.transverse(mx);

                NEH neh = new NEH(mx);
                int[] arr;
                int calls = (mx.length + 1) * mx.length / 2;
                for (int i = 1; i <= 3; i++) {
                    double startTime = System.currentTimeMillis();
                    arr = neh.runNEH(i, out);
                    out.writeArrayInt(arr);
                    out.next();
                    out.write(System.currentTimeMillis() - startTime + "," + calls + "," + fileNum + "," + i);
                    out.next();
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        out.close();
		System.out.println("OK");
    }

}
