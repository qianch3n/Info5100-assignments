/* 
Score 1.9
you are extending from exception which is a broad scope. you have to extend from IndexOutOfBounds() or RuntimeException()
*/
public class Q1 {

    int[] arr;

    public Q1(int[] arr) {
        this.arr = arr;
    }

    public int getNumber(int i) throws MyIndexOutOfBoundException {
        int len = arr.length;
        if (i > len-1 || i < 0) {
            throw new MyIndexOutOfBoundException(0, len-1, i);
        }
        return arr[i];
    }

    public static void main(String[] args) throws MyIndexOutOfBoundException {
        Q1 test = new Q1(new int[]{1,2,3,4,5});
        try {
            test.getNumber(3);
        } catch (MyIndexOutOfBoundException e) {
            System.out.println(e);
        } finally {
            System.out.println("End");
        }
    }
}
