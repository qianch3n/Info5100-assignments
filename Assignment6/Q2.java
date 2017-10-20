import java.io.*;

public class Q2 {

    public static void parse(File file) {
        RandomAccessFile input = null;
        String line = null;
        
        try {
            input = new RandomAccessFile(file, "r");
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            return;
        } catch (IOException e) {
            System.out.println(e);
        }
        finally {
              if (input != null) {
                  try {
                      input.close();
                  } catch(IOException e) {
                      System.out.println(e);
                  }
              }
          }
    } 
    
    public static void main(String[] args) {
        File test = new File("/Users/qianchen/Documents/Test.rtf");
        parse(test);
    }

}