// score 9+ extra credit 2; total score 10
import java.util.*;

class Extra {
    public static List<Integer> spiralOrder(int[][] matrix) {// extra credit 2
        List<Integer> res = new ArrayList<>();
        int c = matrix[0].length;
        int r = matrix.length;
        if (r == 0) {
            return res;
        }
        int column = -1, row = 0;

        while (true) {
            for (int i = 0; i < c; i++) {
                res.add(matrix[row][++column]);
            }
            if (--r == 0) break;
            for (int i = 0; i < r; i++) {
                res.add(matrix[++row][column]);
            }
            if (--c == 0) break;
            for (int i = 0; i < c; i++) {
                res.add(matrix[row][--column]);
            }
            if (--r == 0) break;
            for (int i = 0; i < r; i++) {
                res.add(matrix[--row][column]);
            }
            if (--c == 0) break;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] m = {{1,2,3},
        {4,5,6},
        {7,8,9}};
        List<Integer> list = spiralOrder(m);
        for (Integer l : list) {
            System.out.print(l);
        }
        
    }
}
