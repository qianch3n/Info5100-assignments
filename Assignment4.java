import java.util.*;

public class Assignment4 {
    // Q1
    public static String formatString(String str, int k) {
        StringBuilder res = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            if(str.charAt(i) != '-') {
                res.append(res.length() % (k + 1) == k ? "-" : "").append(str.charAt(i));
            }
        }
        return res.reverse().toString().toUpperCase();
    }

    // Q2
    class Tool {
        private int strength;
        private char type;
        
        public void setStrength(int s) {
            strength = s;
        }
        public int getStrength() {
            return strength;
        }
        public char getType() {
            return type;
        }
        
    }

    class Rock extends Tool {
        Rock(int s) {
            strength = s;
            type = 'r';
        }

        public boolean fight(Tool op) {
            char opType = op.getType();
            int opStr = op.getStrength();
            switch (opType) {
                case 'r':
                    return strength > opStr;
                case 'p':
                    return strength / 2 > opStr;
                case 's':
                    return strength * 2 > opStr;
                default:
                    throw IllegalArgumentException("Wrong tool!") ;
            }
        }
    }
    class Paper extends Tool {
        Paper(int s) {
            strength = s;
            type = 'p';
        }

        public boolean fight(Tool op) {
            char opType = op.getType();
            int opStr = op.getStrength();
            switch (opType) {
                case 'r':
                    return strength * 2 > opStr;
                case 'p':
                    return strength > opStr;
                case 's':
                    return strength / 2 > opStr;
                default:
                    throw IllegalArgumentException("Wrong tool!") ;
            }
        }
    }
    class Scissors extends Tool {
        Scissors(int s) {
            strength = s;
            type = 's';
        }

        public boolean fight(Tool op) {
            char opType = op.getType();
            int opStr = op.getStrength();
            switch (opType) {
                case 'r':
                    return strength / 2 > opStr;
                case 'p':
                    return strength * 2 > opStr;
                case 's':
                    return strength > opStr;
                default:
                    throw IllegalArgumentException("Wrong tool!") ;
            }
        }
    }

    public bool fight(Tool a, Tool b) {

    }

    public static void main(String[] args) {
        String str = "-ab-CD-EF-123-HEIF";
        System.out.println(formatString(str, 3));
    }
}