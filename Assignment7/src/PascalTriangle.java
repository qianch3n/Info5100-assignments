import java.util.ArrayList;
import java.util.List;

public class PascalTriangle { // score 2
    public void printPascalTriangle(int n) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            List<Integer> line = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (j ==0) {
                    line.add(1);
                } else if (j == i - 1) {
                    line.add(1);
                } else {
                    List<Integer> pre = res.get(i - 2);
                    int cur = pre.get(j - 1) + pre.get(j);
                    line.add(cur);
                }
            }
            res.add(line);
        }
        for (List<Integer> l : res) {
            System.out.println(l.toString());
        }

    }

    public static void main(String[] args) {
        PascalTriangle test = new PascalTriangle();
        test.printPascalTriangle(6);
    }
}


