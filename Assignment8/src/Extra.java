import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Extra {
    public static void convertJsonToOriginFile(String filePath, String outputFilePath) throws FileNotFoundException, IOException {
        File json = new File(filePath);
        Scanner sc = new Scanner(json);
        sc.nextLine();
        String webId = sc.nextLine().substring(1, 12);
        String[] item = new String[10];
        item[1] = webId;
        int i = 0;
        ArrayList<String> lines = new ArrayList<>();
        lines.add("id~webId~category~year~make~model~trim~type~price~photo");
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.startsWith("{") || line.startsWith("}")|| line.startsWith("]")) continue;
            int start = line.indexOf(':') + 3;
            int end = line.indexOf(',') - 1;
            if (i == 1) i++;
            item[i] = line.substring(start, end);
            if (i == 9) {
                i = -1;
                StringBuilder oriLine = new StringBuilder();
                for (int j = 0; j < 10; j++) {
                    oriLine.append(item[j]);
                    if (j != 9) {
                        oriLine.append("~");
                    }
                }
                lines.add(oriLine.toString());
            }
            i++;
        }
        StringBuilder output = new StringBuilder();
        for(String line : lines) {
            output.append(line);
            output.append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath));
        bw.write(output.toString());
        bw.close();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        convertJsonToOriginFile("Q3_output.txt", "Q3_json_to_origin");
    }
}
