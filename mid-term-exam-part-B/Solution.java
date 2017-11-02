/* good work
 * Score 30 + extra credit 10;
 */

import java.util.*;

public class Solution {
    // Q1
    public int[] reverseEvenIndices(int[] nums) { // score 6
        if (nums.length == 0)
            return nums;
        int i = 0;
        int j = nums.length - 1;
        if (j % 2 != 0)
            j--;
        while (i < j) {
            swap(nums, i, j);
            i += 2;
            j -= 2;
        }
        return nums;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }

    // Q2
    public int arrangeCoins(int n) { // score 7
        int start = 0;
        int end = n;
        while (start <= end) {
            int mid = (start + end) >>> 1;
            if ((0.5 * mid * mid + 0.5 * mid) <= n) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start - 1;
    }

    // Q3
    public int minMoves(int[] nums) { // score 7
        if (nums.length == 0)
            return 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int n : nums) {
            min = Math.min(min, n);
            sum += n;
        }
        return sum - nums.length * min;
    }

    // Q4
    public int countNumberOfPossibleWays(int m, int n, int x) { // score 10
        int[] res = new int[n];
        List<int[]> list = new ArrayList<>();
        countNumberOfPossibleWays(m, x, 0, res, list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(Arrays.toString(list.get(i)));
        }
        return list.size();
    }

    private void countNumberOfPossibleWays(int m, int x, int cur, int[] res, List<int[]> list) {
        if (cur == res.length) {
            int sum = 0;
            for (int r : res) {
                sum += r;
            }
            if (sum == x) {
                int[] temp = new int[res.length];
                for (int i = 0; i < res.length; i++ ) {
                    temp[i] = res[i];
                }
                list.add(temp);
            }
        } else {
            for (int i = 1; i <= m; i++) {
                res[cur] = i;
                countNumberOfPossibleWays(m, x, cur + 1, res, list);
            }
        }
    }

    // Q5
    class Cell {
        int x, y;

        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "[" + this.x + ", " + this.y + "]";
        }
    }
    /*
        for input = { {1,0,0,1,0},
                      {1,1,1,1,1},
                      {1,0,0,1,0},
                      {1,1,0,1,1},
                      {0,0,0,0,1}};
                      
         expected output: [[0, 0], [1, 0], [1, 1], [1, 2], [1, 3], [2, 3], [3, 3], [3, 4]],[4,4]
         your output    : [[0, 0], [1, 0], [2, 0], [3, 0], [3, 1], [1, 1], [1, 2], [1, 3], [2, 3], [3, 3], [3, 4], [4, 4]]
     */
    
    public ArrayList<Cell> findPath(int[][] maze) { // extra credit 5
        ArrayList<Cell> res = new ArrayList<>();
        ArrayList<Cell> allPaths = new ArrayList<>();
        findPath(maze, res, allPaths, 0, 0);
        return res;
    }

    private void findPath(int[][] maze, ArrayList<Cell> res, ArrayList<Cell> allPaths, int r, int c) {
        int rows = maze.length, cols = maze[0].length;
        if (r == rows - 1 && c == cols - 1) {
            allPaths.add(new Cell(r, c));
            for (Cell cell : allPaths) {
                res.add(cell);
            }
            return;
        }

        if (maze[r][c] == 0) {
            return;
        } else {
            allPaths.add(new Cell(r, c));
            if (r == rows - 1) {
                findPath(maze, res, allPaths, r, c + 1);

            } else if (c == cols - 1) {
                findPath(maze, res, allPaths, r + 1, c);
            } else {
                findPath(maze, res, allPaths, r + 1, c);
                findPath(maze, res, allPaths, r, c + 1);
            }

        }

    }

    public static void main(String[] args) {
        Solution test = new Solution();

        // Q1
        System.out.println(Arrays.toString(test.reverseEvenIndices(new int[] { 9, 4, 8, 7, 5, 1, 3 })));

        // Q2
        System.out.println(test.arrangeCoins(8));

        // Q3
        System.out.println(test.minMoves(new int[] { 1, 2, 3 }));

        // Q4  Please note, I print out all the combinations only for checking. The return is still a number of ways.
        System.out.println(test.countNumberOfPossibleWays(3, 3, 5));

        // Q5
        int[][] maze = { 
            { 1, 0, 0, 0 }, 
            { 1, 1, 1, 1 }, 
            { 0, 1, 0, 0 }, 
            { 1, 1, 1, 1 } 
        };
        ArrayList<Cell> res = test.findPath(maze);
        for (Cell c : res) {
            System.out.print(c.toString() + ", ");
        }

    }
}
