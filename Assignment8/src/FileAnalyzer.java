import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class FileAnalyzer // score 3
{
    public static void main(String[] args) throws IOException
    {
        System.out.println("Filename: ");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        FileCounter counter = new FileCounter();
        FileReader reader = new FileReader(name);
        Scanner fileIn = new Scanner(reader);
        counter.read(fileIn);
        fileIn.close();
        System.out.println("Characters: " + counter.getCharacterCount());
        System.out.println("Words: " + counter.getWordCount());
        System.out.println("Lines : " + counter.getLineCount());
    }
}

class FileCounter {

    private int characterCount, wordCount, lineCount;

    public int getCharacterCount() {
        return characterCount;
    }

    public int getLineCount() {
        return lineCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void charCountPlus(int num) {
        characterCount += num;
    }

    public void wordCountPlus(int num) {
        wordCount += num;
    }

    public void lineCountPlus() {
        lineCount ++;
    }

    public FileCounter() {
    }

    /**
     * Processes an input source and adds its character, word, and line
     * counts to the respective variables.
     *
     * @param in the scanner to process
     */
    public void read(Scanner in) throws IOException {
        while (in.hasNextLine()) {
            String temp = in.nextLine();
            lineCountPlus();
            String[] words = temp.split("\\s+");
            wordCountPlus(words.length);
            for (String w : words) {
                charCountPlus(w.length());
            }
        }

    }
}

