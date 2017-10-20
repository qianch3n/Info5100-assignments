public class MyIndexOutOfBoundException extends Exception {
    MyIndexOutOfBoundException(int lowerBound, int upperBound, int index) {
        super("Error Message: Index: " + index + ", but Lower bound: " + lowerBound + ", Upper bound: " + upperBound);
    }
}