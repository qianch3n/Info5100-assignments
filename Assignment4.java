import java.util.*;

public class Assignment4 {
    // Q1
    public static String formatString(String str, int k) {
        StringBuilder res = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) != '-') {
                res.append(res.length() % (k + 1) == k ? "-" : "").append(str.charAt(i));
            }
        }
        return res.reverse().toString().toUpperCase();
    }

    // Q2
    static class Tool {
        protected int strength;
        protected char type;

        public void setStrength(int s) {
            strength = s;
        }

        public int getStrength() {
            return strength;
        }

        public void setType(char t) {
            type = t;
        }

        public char getType() {
            return type;
        }

    }

    static class Rock extends Tool {
        Rock(int s) {
            setStrength(s);
            setType('r');
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
                throw new IllegalArgumentException("Wrong tool!");
            }
        }
    }

    static class Paper extends Tool {
        Paper(int s) {
            setStrength(s);
            setType('p');
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
                throw new IllegalArgumentException("Wrong tool!");
            }
        }
    }

    static class Scissors extends Tool {
        Scissors(int s) {
            setStrength(s);
            setType('s');
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
                throw new IllegalArgumentException("Wrong tool!");
            }
        }
    }

    // Q3
    static class IpAddress {
        private int[] octets = new int[4];
        String ipAddress;

        IpAddress(String ip) {
            ipAddress = ip;
            String[] ipArr = ip.split("\\.");
            for(int i = 0; i < ipArr.length; i++) {
                 octets[i] = Integer.parseInt(ipArr[i]);
            }
        }

        String getDottedDecimal() {
            return ipAddress;
        }

        int getOctet(int idx) {
            int i = idx - 1;
            return octets[i];
        }

    }

    // Q4
    static class Student {
        String name;
        String id;
        Student(String name, String id) {
            this.name = name;
            this.id = id;
        }
    }

    static class Course {
        String name;
        int numberOfStudent;
        List<Student> registeredStudent = new ArrayList<>();

        Course(String name) {
            this.name = name;
        }

        public Student[] getStudents() {
            return registeredStudent.toArray(new Student[registeredStudent.size()]);
        }

        public boolean isFull() {
            return registeredStudent.size() == 10;
        }

        public String getTitle() {
            return name;
        }

        public int getNumberOfStudents() {
            return registeredStudent.size();
        }

        public void registerStudent(Student stu) {
            int len = registeredStudent.size();
            if(len < 10) {
                registeredStudent.add(stu);
            } else {
                throw new IllegalArgumentException("This Course is full!");
            }
        }

    }

    // Q5
    private static final String[] SYMBOLS = {
        "M", "CM", "D", "CD", 
        "C", "XC", "L", "XL", 
        "X", "IX", "V", "IV", 
        "I"
    };

    private static final int[] VALUES = {
        1000, 900, 500, 400,
        100, 90, 50, 40,
        10, 9, 5, 4,
        1
    };


    public static String intToRoman(int num) {
        int i = 0;
        StringBuilder res = new StringBuilder();
        while (num > 0) {
            int k = num / VALUES[i];
            for (int j = 0; j < k; j++) {
                num -= VALUES[i];
                res.append(SYMBOLS[i]);
            }
            i++;
        }
        return res.toString();
    }

    // Extra
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        if(len1 == 0 && len2 == 0) return 0;
        int i = 0, j = 0, m = 0;
        int[] merge = new int[len1 + len2];
        while (i < len1 && j < len2) {
            if (nums1[i] < nums2[j]) {
                merge[m] = nums1[i];
                i++;
            } else {
                merge[m] = nums2[j];
                j++;
            }
            m++;
        }
        if (i == len1) {
            while (j < len2) {
                merge[m] = nums2[j];
                j++;
                m++;
            }
        }
        if (j == len2) {
            while (i < len1) {
                merge[m] = nums1[i];
                i++;
                m++;
            }
        }
        
        if (m % 2 == 0) {
            double res = (double) (merge[m / 2] + merge[(m-1)/2]) / 2;
            
            return res;
        } else {
            double res = merge[m / 2];
            return res;
        }
        
    }
    
    public static void main(String[] args) {
        // Q1
        String str = "-ab-CD-EF-123-HEIF";
        System.out.println(formatString(str, 3));
    
        // Q2
        Scissors s = new Scissors(5);
        Paper p = new Paper(7);
        Rock r = new Rock(15);
    
        System.out.println(s.fight(p) + " , "+ p.fight(s) );
        System.out.println(p.fight(r) + " , " + r.fight(p));
        System.out.println(r.fight(s) + " , " + s.fight(r));
    
        // Q3
        IpAddress ip = new IpAddress("216.27.6.136");
        System.out.println(ip.getDottedDecimal());
        System.out.println(ip.getOctet(4));
        System.out.println(ip.getOctet(1));
        System.out.println(ip.getOctet(3));
        System.out.println(ip.getOctet(2));

        // Q4
        Student s1 = new Student("David", "123456");
        Student s2 = new Student("Kevin", "123451");
        Student s3 = new Student("Peter", "123452");
        Student s4 = new Student("Jack", "123453");
        Student s5 = new Student("Tina", "123454");
        Student s6 = new Student("Lucy", "123455");
        Student s7 = new Student("Kate", "123457");
        Student s8 = new Student("John", "123457");
        Student s9 = new Student("Nate", "123414");
        Student s0 = new Student("Alvin", "123237");
        Student s11 = new Student("Paty", "121287");

        Course algorithm = new Course("Algorithm");
        System.out.println(algorithm.getTitle());
        algorithm.registerStudent(s1);
        algorithm.registerStudent(s2);
        algorithm.registerStudent(s3);
        algorithm.registerStudent(s4);
        algorithm.registerStudent(s5);
        algorithm.registerStudent(s6);
        algorithm.registerStudent(s7);
        algorithm.registerStudent(s8);
        algorithm.registerStudent(s9);
        algorithm.registerStudent(s0);
        Student[] arr = algorithm.getStudents();
        for(Student st : arr) {
            System.out.println(st.name);
        }
        System.out.println(algorithm.isFull());

        // Q5
        System.out.println(intToRoman(2999));

        // Extra
        System.out.println(findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));
        System.out.println(findMedianSortedArrays(new int[]{1,2}, new int[]{3}));
        
    }
}



    