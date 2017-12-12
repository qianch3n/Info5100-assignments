/**
 * This thread finds the sum of a subsection of an array.
 */
public class MaxValue extends Thread { // score 1.5
    private int lo, hi;
    private int[] arr;
    private int max = 0; // it should be equal to Integer.MIN_VALUE

    public MaxValue(int[] arr, int lo, int hi) {
        this.lo = lo;
        this.hi = hi;
        this.arr = arr;
    }

    @Override
    public void run() {
        for (int i = lo; i < hi; i++) {
            max = Math.max(max, arr[i]);
        }
    }

    public static int search(int[] arr) throws InterruptedException {
        int len = arr.length;
        int max = 0;

        // Create and start 4 threads.
        MaxValue[] ts = new MaxValue[4];
        for (int i = 0; i < 4; i++) {
            ts[i] = new MaxValue(arr, (i * len) / 4, ((i + 1) * len / 4));
            ts[i].start();
        }

        // Wait for the threads to finish and sum their results.
        for (int i = 0; i < 4; i++) {
            ts[i].join();
            max = Math.max(max, ts[i].max);
        }
        return max;
    }

    public static void main(String[] args) throws InterruptedException {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] =  (int) (100 * Math.random());
        }
        int max = search(arr);
        System.out.println("Max: " + max);
    }
}
