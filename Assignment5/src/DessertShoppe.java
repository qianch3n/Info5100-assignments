public class DessertShoppe {
    public final static String NAME = "M & M Dessert Shoppe";
    public final static double TAX_RATE = 0.0653645;
    public final static int MAX_NAME_WIDTH = 20;
    public final static int MAX_RECEIPT_WIDTH = 40;

    public static String cents2dollarsAndCents(int cents) {
        int dollar = cents / 100;
        cents = cents - dollar * 100;
        String d = dollar == 0 ? "" : Integer.toString(dollar);
        String c = cents < 10 ? "0" + Integer.toString(cents) : Integer.toString(cents);
         
        return d + "." + c;
    }
}