package utill;

import collection.Vehicle;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVManager {

    public static ArrayList<String> readAllLines(String path) {
        checkAllPermissions(new File(path));
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void checkAllPermissions(File file) {
        if (file.exists()) {
            if ((!file.canWrite()) && (!file.canRead())) {
                System.out.println("Файл нельзя считать и записать");
                System.exit(1);
            } else if ((!file.canWrite()) && file.canRead()) {
                System.out.println("Нельзя записать в файл");
                System.exit(1);
            } else if (file.canWrite() && (!file.canRead())) {
                System.out.println("Файл нельзя считать");
                System.exit(1);
            }
        }
    }
    public static void saveAllLines(HashMap<Integer, Vehicle> collection, String path) throws IOException {
        List<String> list = new ArrayList<>();
        File file = new File(path);
//        file.delete();
        for (Map.Entry<Integer, Vehicle> e : collection.entrySet()) {
            String line = e.getValue().getId() + ";"
                    + e.getValue().getName() + ";"
                    + e.getValue().getCoordinates().getX() + ";"
                    + e.getValue().getCoordinates().getY() + ";"
                    + e.getValue().getCreationDate() + ";"
                    + e.getValue().getEnginePower() + ";"
                    + e.getValue().getNumberOfWheels() + ";"
                    + e.getValue().getFuelConsumption() + ";"
                    + e.getValue().getFuelType() + "\n";


            list.add(line);
        }


        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path));
        for (int i = 0; i < list.size(); i++) {
            try {
                // перевод строки в байты
                byte[] buffer = list.get(i).getBytes();
                bos.write(buffer, 0, buffer.length);
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }
        bos.close();
    }
}
