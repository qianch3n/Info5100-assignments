import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MyJson {

    private static ArrayList<Vehicle> readAndGetVehicles(File file) throws FileNotFoundException {
        ArrayList<Vehicle> ret = new ArrayList<>();
        Scanner sc = new Scanner(file);
        int count = 0;
        while (sc.hasNextLine()) {
            if (count == 0){
                count++;
                String line = sc.nextLine();
                continue;
            }
            String line = sc.nextLine();
            String[] items = line.split("~");
            Vehicle v = new Vehicle(items);
            ret.add(v);
            count++;
        }
        return ret;
    }

    public static String getJsonString(ArrayList<Vehicle> vehicles) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n\"gmps-camino\" : [\n");
        for(Vehicle v : vehicles) {
            sb.append("{\n");
            sb.append("\"id\" : \"");
            sb.append(v.id);
            sb.append("\",\n");
            sb.append("\"category\" : \"");
            sb.append(v.category);
            sb.append("\",\n");
            sb.append("\"year\" : \"");
            sb.append(v.year);
            sb.append("\",\n");
            sb.append("\"make\" : \"");
            sb.append(v.make);
            sb.append("\",\n");
            sb.append("\"model\" : \"");
            sb.append(v.model);
            sb.append("\",\n");
            sb.append("\"trim\" : \"");
            sb.append(v.trim);
            sb.append("\",\n");
            sb.append("\"type\" : \"");
            sb.append(v.type);
            sb.append("\",\n");
            sb.append("\"price\" : \"");
            sb.append(v.price);
            sb.append("\",\n");
            sb.append("\"photo\" : \"");
            sb.append(v.photo);
            sb.append("\",\n");
            sb.append("},\n");
        }
        sb.deleteCharAt(sb.length()-2);
        sb.append("]\n}");

        return sb.toString();
    }

    public static void writeToFile(String inputToWrite, String filePath) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
        bw.write(inputToWrite);
        System.out.println("Write complete.");
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        File file = new File("Question3_camino.txt");
        ArrayList<Vehicle> vehicles = readAndGetVehicles(file);
        String s = getJsonString(vehicles);
//        File output = new File("Q3_output.txt");
        writeToFile(s, "Q3_output.txt");
    }


}

class Vehicle{

    String id;
    String webId;
    Category category;
    Year year;
    String make;
    String model;
    String trim;
    String type;
    double price;
    URL photo;

    Vehicle(String[] arr){
        this.id = arr[0];
        this.webId = arr[1];
        this.category = Category.getCategory(arr[2].toLowerCase());
        this.year = Year.parse(arr[3]);
        this.make = arr[4];
        this.model = arr[5];
        this.trim = arr[6];
        this.type = arr[7];
        this.price = Double.parseDouble(arr[8]);
        try {
            this.photo = new URL(arr[9]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}



enum Category{
    NEW , USED, CERTIFIED;

    public static Category getCategory(String cat){
        switch (cat){
            case "used": return USED;
            case "new": return NEW;
            case "certified": return CERTIFIED;
            default: throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        switch (this){
            case NEW: return "NEW";
            case USED: return "USED";
            case CERTIFIED: return "CERTIFIED";
            default: throw new IllegalArgumentException();
        }
    }
}