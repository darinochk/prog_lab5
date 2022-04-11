package utill;

import collection.Coordinates;
import collection.FuelType;
import collection.Vehicle;
import main.App;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 * Класс Менджер колеекции(CollectionManager). Отвечает за управление коллекцией
 */
public class CollectionManager {
    private HashMap<Integer, Vehicle> collection;
    private LocalDate initDate;
    private VehicleMaker vehicleMaker = new VehicleMaker();


    /**
     * Конструктор
     * Инициализация коллекции
     * Получение даты инициализации коллекции
     */
    public CollectionManager() {
        collection = new HashMap<Integer, Vehicle>();
        initDate = LocalDate.now();
    }

    /**
     * метод, который переводит строку в коллекцию
     * @param path путь к файлу
     */
    public void lineToCollection(String path) {
        try {
            ArrayList<String> lines = CSVManager.readAllLines(path);
            if (lines.size() == 1) {
                lines.clear();
                System.out.println("Файл пустой");
            }
            for (int i = 0; i < lines.size(); i++) {
                String[] line = lines.get(i).trim().split(";");
                FuelType fuelType = null;
                switch (line[8]) {
                    case ("GASOLINE"):
                        fuelType = FuelType.GASOLINE;
                        break;
                    case ("KEROSENE"):
                        fuelType = FuelType.KEROSENE;
                        break;
                    case ("DIESEL"):
                        fuelType = FuelType.DIESEL;
                        break;
                    case ("ALCOHOL"):
                        fuelType = FuelType.ALCOHOL;
                        break;
                    case ("MANPOWER"):
                        fuelType = FuelType.MANPOWER;
                        break;
                }

                Vehicle vehicle = new Vehicle(Integer.parseInt(line[0]),
                                                line[1],
                                                new Coordinates(Double.parseDouble(line[2]), Float.parseFloat(line[3])),
                                                new Date(),
                                                Double.parseDouble(line[5]),
                                                Long.parseLong(line[6]),
                                                Long.parseLong(line[7]),
                                                fuelType);

                collection.put(vehicle.getId(), vehicle);
            }
        }catch (Exception e) {}

    }

    /**
     * Метод, возвращающий тип коллекции
     * @return тип колекции
     */
    public String getCollectionType() {
        return collection.getClass().getSimpleName();
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isLong(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean fuelType(String s) {
        return (s.equals("GASOLINE") || s.equals("KEROSENE") || s.equals("DIESEL") || s.equals("ALCOHOL") || s.equals("MANPOWER"));
    }

    public static boolean isDateFormat(String s) {
        return s.matches("\\d{2}[.]\\d{2}[.]\\d{4}");
    }

    /**
     * Метод, возвращающий дату инициализации коллекции
     * @return initDate
     */
    public LocalDate getInitDate() {
        return initDate;
    }

    /**
     * Метод, возвращающий количество элементов коллеции
     * @return размер коллекции
     */
    public int getNumberOfElements() {
        return collection.size();
    }


    public HashMap<Integer, Vehicle> getCollection() {
        return collection;
    }

    /**
     * Метод, реализующий команду show
     */
    public void show() {
        try {
            if (collection.isEmpty()) System.out.println("Коллекция пуста");
            else {
                for (Map.Entry<Integer, Vehicle> e : collection.entrySet()) {
                    System.out.println(e.getValue());
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Файл пустой");
        }
    }

    /**
     * Метод, реализующий команду insert
     * @param id ключ элемента
     */
    public void insert(int id) {
        collection.put(id, vehicleMaker.insertVehicle(null));
        collection.get(id).setId(id);
        System.out.println("Команда успешно выполнена");
    }

    /**
     * Метод, реализующий команду update
     * @param id ключ эдемента
     */
    public void update(int id) {
        if (!collection.get(id).equals(null)) {
            collection.replace(id, vehicleMaker.insertVehicle(null));
            System.out.println("Команда успешно выполнена");
        }
    }

    /**
     * Метод, реализующий команду remove_key
     * @param id ключ элемента
     */
    public void removeKey(int id) {
        if (!collection.get(id).equals(null)) {
            collection.remove(id);
            System.out.println("Команда успешно выполнена");
        }
    }

    /**
     * Метод, реализующий команду clean
     */
    public void clear() {
        collection.clear();
        System.out.println("Команда успешно выполнена");
    }

    /**
     * Метод, реализующий команду save
     */
    public void save() {
        try {
            CSVManager.saveAllLines(collection, "./src/input_file.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Команда успешно выполнена");
    }

    /**
     * Метод, реализующий команду execute_script
     * @param listOfCommands список команд в скрипт-файле
     */
    public void executeScript(ArrayList<String> listOfCommands, ArrayList<String> listOfPaths) {
        for (int i = 0; i < listOfCommands.size(); i++) {
            String[] input = listOfCommands.get(i).trim().split(" ");
            if (input[0].equals("execute_script")) {
                System.out.println("Рекурсия. Проверьте файл скрипта");
                return;
            } else {
                Parser.parseThenRun(input, App.getCommandManager());
            }
        }
    }

    /**
     * Метод, реализующий команду remove_lower
     */
    public void removeGreater() {
        Vehicle vehicle = vehicleMaker.insertVehicle(null);
        HashSet<Integer> keys = new HashSet<>();
        for (Map.Entry<Integer, Vehicle> e : collection.entrySet()) {
            if (e.getValue().hashCode() - vehicle.hashCode() > 0) keys.add(e.getKey());
        }
        for (Integer s : keys) {
            collection.remove(s);
        }
        System.out.println("Команда успешно выполнена");
    }

    /**
     * Метод, реализующий команду remove_if_greater
     * @param id ключ элемента
     */
    public void replaceIfGreater(int id) {
        Vehicle vehicle = vehicleMaker.insertVehicle(null);
        if (collection.get(id).hashCode() - vehicle.hashCode() < 0) {
            collection.replace(id, vehicle);
        }
        System.out.println("Команда успешно выполнена");
    }

    /**
     * Метод, реализующий команду remove_lower_key
     * @param id ключ элемента
     */
    public void removeGreaterKey(int id) {
        HashSet<Integer> keys = new HashSet<>();
        for (Map.Entry<Integer, Vehicle> e : collection.entrySet()) {
            if (e.getKey() > id) keys.add(e.getKey());
        }

        for (Integer s : keys) {
            collection.remove(s);
        }
        System.out.println("Команда успешно выполнена");
    }

    /**
     * Метод, реализующий команду remove_all_by_owner
     */
    public void removeAnyByFuel() {
        HashSet<Integer> keys = new HashSet();
        Vehicle fuelConsumption = vehicleMaker.insertVehicle(null);

        for (Map.Entry<Integer, Vehicle> e : collection.entrySet()) {
            if (fuelConsumption.equals(e.getValue().getFuelConsumption())) {
                keys.add(e.getKey());
            }
        }

        for (Integer s : keys) {
            collection.remove(s);
        }
        System.out.println("Команда успешно выполнена");
    }

    public void filterByEnginePower() {
        Scanner scanner = new Scanner(System.in);
        double val = 0;
        try {
            val = scanner.nextDouble();
        } catch (Exception e) {
            System.out.println("Ошибка ввода");
        }

        for (Map.Entry<Integer, Vehicle> e : collection.entrySet()) {
            if (e.getValue().getEnginePower() == val)
                System.out.println(e.getValue());
        }
        
    }
}