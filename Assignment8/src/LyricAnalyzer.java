import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class LyricAnalyzer {
    private HashMap<String, ArrayList<Integer>> map = new HashMap<>();
    private int totalCount;

    public void read(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        int count = 1;
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] words = line.split("\\s+");
            for (int i = 0; i < words.length; i++) {
                int index = 0;
                if (i == words.length - 1) {
                    index = - (i + count);
                } else {
                    index = i + count;
                }
                add(words[i], index);
            }
            count += words.length;
        }
        this.totalCount = count;

    }

    private void add(String lyricWord, int wordPosition) {
        if (!map.containsKey(lyricWord)) {
            map.put(lyricWord, new ArrayList<>(Arrays.asList(wordPosition)));
        } else {
            ArrayList<Integer> temp = map.get(lyricWord);
            temp.add(wordPosition);
            map.put(lyricWord, temp);
        }
    }

    public void displayWords() {
        for (Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            System.out.println(entry.getValue().toString());
        }
    }

    public void writeLyrics(File file) throws IOException {
        String[] lyrics = new String[totalCount];
        for (String key : map.keySet()) {
            ArrayList<Integer> positions = map.get(key);
            for (int p : positions) {
                if (p < 0) {
                    lyrics[-p] = key + "\n";
                } else {
                    lyrics[p] = key + " ";

                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <lyrics.length; i++) {
            sb.append(lyrics[i]);
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(file.getPath()));
        bw.write(sb.toString());
        System.out.println("Lyrics have been written in the output.txt");
        bw.close();

    }

    public int count() {
        return map.size();
    }

    public String mostFrequentWord() {
        String mostFrequent = "";
        int biggestSize = 0;
        for (String key : map.keySet()) {
            if (map.get(key).size() > biggestSize) {
                biggestSize = map.get(key).size();
                mostFrequent = new String(key);
            } else if (map.get(key).size() == biggestSize) {
                if (key.charAt(0) < mostFrequent.charAt(0)) {
                    mostFrequent = new String(key);
                }
            }
        }
        return mostFrequent;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        LyricAnalyzer test = new LyricAnalyzer();
        test.read(new File("test2.txt"));
        test.displayWords();
        test.writeLyrics(new File("Q1_output.txt"));
        System.out.println("count: " + test.count());
        System.out.println("The most frequent word: " + test.mostFrequentWord());
    }

}
