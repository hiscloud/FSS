/*
 * The class including several static methods of FSS problems.
 */


/**
 *
 * @author Junyu Lu
 */
public class FlowShopScheduling {

    /**
     * generate the finishing time matrix in FSS situation
     *
     * @param p
     * @return
     */
    public static int[][] mxFSS(int[][] p) {
        int r = p.length;
        int c = p[0].length;
        int[][] comp = new int[r][c];
        for (int i = 0; i < r; i++) {
            comp[i] = p[i].clone();
        }
        for (int j = 1; j < c; j++) {
            comp[0][j] += comp[0][j - 1];
        }
        for (int i = 1; i < r; i++) {
            comp[i][0] += comp[i - 1][0];
        }
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                comp[i][j] += Double.max(comp[i - 1][j], comp[i][j - 1]);
            }
        }
        return comp;
    }

    /**
     * find the makespan time in FSS situation
     *
     * @param p
     * @return
     */
    public static int FSS(int[][] p) {
        int[][] comp = mxFSS(p);
        return comp[comp.length - 1][comp[0].length - 1];
    }

    /**
     * generate the finishing time matrix in FSSB situation.
     *
     * @param p
     * @return
     */
    public static int[][] mxFSSB(int[][] p) {
        int r = p.length;
        int c = p[0].length;
        int[][] comp = new int[r][c];
        for (int i = 0; i < r; i++) {
            comp[i] = p[i].clone();
        }
        for (int j = 1; j < c; j++) {
            comp[0][j] += comp[0][j - 1];
        }
        for (int i = 1; i < r; i++) {
            comp[i][0] = Integer.max(comp[i - 1][1], comp[i - 1][0] + p[i][0]);
            for (int j = 1; j < c - 1; j++) {
                comp[i][j] = Integer.max(comp[i][j - 1] + p[i][j], comp[i - 1][j + 1]);
            }
            comp[i][c - 1] = comp[i][c - 2] + p[i][c - 1];
        }

        return comp;
    }

    /**
     * caculate the makespan time in FSSB situation
     *
     * @param p
     * @return
     */
    public static int FSSB(int[][] p) {
        int[][] comp = mxFSSB(p);

        return comp[comp.length - 1][comp[0].length - 1];
    }

    /**
     * generate the finishing time matrix in FSSNW situation
     *
     * @param p
     * @return
     */
    public static int[][] mxFSSNW(int[][] p) {
        int r = p.length;
        int c = p[0].length;
        int[][] comp = new int[r][c];
        for (int i = 0; i < r; i++) {
            comp[i] = p[i].clone();
        }
        for (int i = 1; i < r; i++) {
            comp[i][0] += comp[i - 1][0];
        }
        for (int j = 1; j < c; j++) {
            comp[0][j] = Integer.max(comp[0][j - 1] + comp[0][j], comp[1][j - 1]);

            for (int i = 1; i < r - 1; i++) {
                if ((comp[i - 1][j] + p[i][j]) < comp[i + 1][j - 1]) {
                    for (int k = 0; k <= i; k++) {
                        comp[k][j] += comp[i + 1][j - 1] - p[i][j] - comp[i - 1][j];

                    }
                }
                comp[i][j] += comp[i - 1][j];
            }
            comp[r - 1][j] += comp[r - 2][j];
        }
        return comp;
    }

    /**
     * calculate the Total Flow Time in FSSNW situation
     *
     * @param p
     * @return
     */
    public static int FSSNW(int[][] p) {
        int[][] comp = mxFSSNW(p);
        int TFT = 0;
        for (int j = 0; j < comp[0].length; j++) {
            TFT += comp[comp.length - 1][j];
        }
        return TFT;
    }

}
