import java.util.*;

public class Assignment3 {
    // Q1
    class Book {
        int size;
        int price;
        String name;

        // This constructor is duplicated with the third constructor.
        public Book(int size) {
            this.size = size;
        }

        public Book(int size, int price, String name) {
            super();
            this.size = size;
            this.price = price;
            this.name = name;
        }

        public Book(int price) {
            this.price = price;
        }

        // 1. This method should declare the type of return: public void setName
        public setName(String name){
        // 2. According to the method name, the body of this method should be { this.name = name; }
            return name;
        }
    }

    // Q2
    class Clock {
        String time;

        // This method is declared as void, so it can't return any value. 
        // Should be like String getTime()
        void getTime() {
            return time;
        }

        void setTime(String t) {
            time = t;
        }
    }

    // Q3
    public static String removeVowelsFromString(String input) {
        List<Character> str = new ArrayList<>();
        for (char c : input.toCharArray()) {
            if (!checkVowels(c)) {
                str.add(c);
            }
        }
        String res = "";
        for (Character c : str) {
            res += c.toString();
        }
        return res;
    }

    private static boolean checkVowels(char c) {
        char[] vowels = { 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' };
        for (int i = 0; i < vowels.length; i++) {
            if (c == vowels[i]) {
                return true;
            }
        }
        return false;
    }

    // Q4
    public static boolean checkIfTwoStringsAreAnagrams(String s1, String s2) {
        Map<Character, Integer> map = new HashMap<>();

        for(char c : s1.toCharArray()) {
            if(map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        for(char c : s2.toCharArray()) {
            if(map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
            } else {
                return false;
            }
        }
        for(int val : map.values()) {
            if(val != 0 ) {
                return false;
            }
        }
        return true;
    }

    // Q5
    static class Calculator {
        // i.
        public double add(double a, double b) {
            return a + b;
        }
        public double minus(double a, double b) {
            return a - b;
        }
        public double multiply(double a, double b) {
            return a * b;
        }
        public double devide(double a, double b) {
            return a / b;
        }

        // ii.
        public double square(double a) {
            return Math.pow(a, 2);
        }
        public double cube(double a) {
            return Math.pow(a, 3);
        }
        public double sqrt(double a) {
            return Math.sqrt(a);
        }

        // iii.
        public double fahrenheitToCelsius(double f) {
            return (f - 32) / 9 * 5;
        }
        public double CelsiusToFahrenheit(double c) {
            return c / 5 * 9 + 32;
        }
        public double feetToInches(double feet) {
            return feet * 12;
        }
        public double inchesToFeet(double inches) {
            return inches / 12;
        }

        // extra
        double[] quadraticEquation(double a, double b, double c) {
            double[] result = new double[2];
            if(Math.pow(b, 2) - 4 * a * c < 0) {
                return new double[0];
            }
            double temp = Math.sqrt( Math.pow(b, 2) - 4 * a * c );
            result[0] = (b * -1 + temp) / 2 * a;
            result[1] = (b * -1 - temp) / 2 * a;
            return result;
        }

    }

    public static void main(String[] args) {
        // Q3 test
        String str = "Rubby is nice. Amy is not.";
        System.out.println(removeVowelsFromString(str));

        // Q4 test
        System.out.println(checkIfTwoStringsAreAnagrams("", ""));

        // Q5 test
        Calculator cal = new Calculator();
        double cel = cal.fahrenheitToCelsius(73);
        double[] res = cal.quadraticEquation(2,3,2);
        System.out.println(cal.add(1,9));
        System.out.println(cal.cube(5));
        System.out.println(cel);
        System.out.println(Arrays.toString(res));

    }
}
