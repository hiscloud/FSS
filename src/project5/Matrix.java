/*
 * The class for matrix modifying.
 */

/**
 *
 * @author Junyu Lu
 */
public class Matrix {
    /**
     * Perform the transverse of a matrix
     * @param mx
     * @return 
     */
    public static int[][]transverse(int[][] mx) {
        int r = mx.length;
        int c = mx[0].length;

        int[][] newMx = new int[c][r];
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                newMx[i][j] = mx[j][i];
            }
        }
       
        return newMx;
    }
    /**
     * create a deep copy of an two dimensional array
     * @param mx
     * @return 
     */
    public static int[][] copy(int[][] mx){
        int r = mx.length;
        int c = mx[0].length;
        int[][] newMx = new int[r][c];
          for (int i = 0; i < r; i++) {
            newMx[i] = mx[i].clone();
        }
          return newMx;
    }
    /**
     * print matrix into standard output
     * @param mx 
     */
       public static void printMx(int[][] mx) {
        for (int i = 0; i < mx.length; i++) {
            for (int j = 0; j < mx[0].length; j++) {
                System.out.print(mx[i][j] + " ");
            }
            System.out.println();
        }
    }
       
       public static int[][] reorder(int[][] mx,int[] order){
           int r = mx.length;
           int c = mx[0].length;
           int[][] newMx = new int[r][c];
           for (int i=0;i<r;i++){
               newMx[i]=mx[order[i]-1].clone();
           }
           return newMx;
       }
}
