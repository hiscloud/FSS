/*
 * Using the NEH heuristic to solve the FSS problems
 */

import java.util.Arrays;

/**
 *
 * @author Junyu Lu
 */


public class NEH {

    int r, c;///row and column of the matrix
    int[][] p;///the input matrix
    int[][] comp;///the matrix copied from the input
    int[][] temp;///the matrix storing the largest matrix
    int[][] temp2;///the matrix to run the algorithm, small to large
    int[] total;///the array storing the origin total of the matrix

    /**
     * the constructor taking in an 2-d array
     * @param p 
     */
    public NEH(int[][] p) {
        this.p = p;
        r = p.length;
        c = p[0].length;
    }
/**
 * 
 * @param objectiveF
 * @param output
 * @return 
 */
    public int[] runNEH(int objectiveF, Output output) {
        initNEH();
        int[] out = new int[r + 1];//+1 for the Cmax
        temp[0] = comp[0].clone();
        int result = 0;
        int min = Integer.MAX_VALUE;
        for (int L = 1; L < r; L++) {

            resizeTempVertically(L);// another line appened
            min = Integer.MAX_VALUE;
            int[][] best = new int[0][0];
            for (int m = 0; m <= L; m++) {
                insertion(L, m);//insert the Lth array into mth location,temp2 is filled
                result = getResult(objectiveF);
                if (result < min) {
                    min = result;
                    best = Matrix.copy(temp2);
                }
            }
            temp = best;//update the temp matrix           

        }
        out[0] = min;

        outputMatrix(output,objectiveF);
              
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < r; j++) {
                if (Arrays.equals(temp[i], p[j])) {
                    out[i + 1] = j + 1;
                }
            }
        }

        return out;
    }
/**
 * output the Cmax matrix to the output file using Output class.
 * @param outp
 * @param f 
 */
    private void outputMatrix(Output outp,int f) {
        int[][] resultMX=null;
        switch (f) {
            case 1:
                resultMX = FlowShopScheduling.mxFSS(temp);
                break;
            case 2:
                resultMX = FlowShopScheduling.mxFSSB(temp);
                break;
            case 3:
                resultMX = FlowShopScheduling.mxFSSNW(temp);
                break;
        }
        outp.writeMatrix(resultMX);
    }
    /**
     * initialization
     */
    public void initNEH() {
        comp = new int[r][c];
        for (int i = 0; i < r; i++) {
            comp[i] = p[i].clone();
        }
        temp = new int[1][c];
        total = new int[r];
        total = calculateTotal().clone();
        int[] totalForSort = total.clone();
        sort(comp, totalForSort);

    }
/**
 * calculate the sum of each array in matrix "comp", stored in array "total"
 * @return 
 */
    public int[] calculateTotal() {
        int[] t = new int[r];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {

                t[i] += comp[i][j];
            }
        }
        return t;
    }
/**
 * run temp2 through an objective function
 * @param obj
 * @return 
 */
    private int getResult(int obj) {
        int result = 0;
        switch (obj) {
            case 1:
                result = FlowShopScheduling.FSS(temp2);
                break;
            case 2:
                result = FlowShopScheduling.FSSB(temp2);
                break;
            case 3:
                result = FlowShopScheduling.FSSNW(temp2);
                break;
        }
        return result;
    }

    /**
     * resize the temp array to "size", usually add 1.
     * @param size
     */
    private void resizeTempVertically(int size) {
        int[][] tempWithNewLine = new int[size + 1][temp[0].length];
        for (int i = 0; i < temp.length; i++) {
            tempWithNewLine[i] = temp[i].clone();
        }
        temp = tempWithNewLine;

    }
/**
 * insert one array to a location of a matrix
 * @param L
 * @param m 
 */
    private void insertion(int L, int m) {
        temp2 = Matrix.copy(temp);
        for (int n = L; n > m; n--) {
            temp2[n] = temp[n - 1];// move down
        }
        temp2[m] = comp[L].clone();

    }
/**
 * sort a matrix by another array (the sum value of it stored in total in this case)
 * @param p
 * @param total 
 */
    private void sort(int[][] p, int[] total) {

        for (int i = 0; i < total.length; i++) {
            int max = 0;
            for (int k = i; k < total.length; k++) {
                if (total[k] > max) {
                    //total[]
                    max = total[k];
                    total[k] = total[i];
                    total[i] = max;
                    //p[][]
                    int[] temp = p[i].clone();
                    p[i] = p[k].clone();
                    p[k] = temp;
                }

            }
        }

    }
}
