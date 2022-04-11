package utill;

import collection.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Класс создатель машин(VehicleMaker)
 */
public class VehicleMaker {
//    XmlParser xmlParser = new XmlParser();

    /**
     * Метод, возращающий транспорт из строки
     *
     * @param line строка
     * @return vehicle
     */
//    public Vehicle makeVehicle(String line) {
//        return new Vehicle(
//                xmlParser.parseId(line),
//                xmlParser.parseName(line),
//                xmlParser.parseCoordinates(line),
//                xmlParser.parseCreationDate(line),
//                xmlParser.parseEnginePower(line),
//                xmlParser.parseNumberOfWheels(line),
//                xmlParser.parseFuelConsumption(line),
//                xmlParser.parseFuelType(line),
//                );
//
//    }

    /**
     * Метод, создающий экземпляр класса Vehicle из командной строки
     *
     * @return
     */
    public Vehicle insertVehicle(String path) {
        Scanner scanner = new Scanner(System.in);
        if (path != null) {
            try {
                scanner = new Scanner(new File(path));
                while (true) {
                    if (scanner.hasNextLine()) {
                        if (scanner.nextLine().trim().split(" ")[0].equals("insert")) {
                            break;
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден");
            }
        }
        String line = "";
        Vehicle product = new Vehicle(0, null, null, null, 0, null, null, null, null);

        while (true) {
            System.out.println("Введите значение поля name ");
            if (scanner.hasNextLine()) {
                line = scanner.nextLine().trim();
            }
            if (line.equals(null) || line.equals("")) {
                System.out.println("Ошибка ввода");
            } else {
                product.setName(line);
                break;
            }
        }

        Coordinates coordinates = new Coordinates(0, null);
        System.out.println("Введем координаты");

        while (true) {
            try {
                System.out.println("Введите значение поля для координаты X ");
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                }
                coordinates.setX(Double.parseDouble(line));
                break;
            } catch (Exception e) {
                System.out.println("Ошибка ввода");
            }
        }

        while (true) {
            try {
                System.out.println("Введите значение поля для координаты Y ");
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                }
                if (Float.parseFloat(line) <= 834) {
                    coordinates.setY(Float.valueOf(line));
                    break;
                } else throw new Exception("Wrong input");
            } catch (Exception e) {
                System.out.println("Ошибка ввода");
            }
        }
        product.setCoordinates(coordinates);

        product.setCreationDate();

        while (true) {
            try {
                System.out.println("Введите значение для поля enginePower ");
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                }
                if (Integer.parseInt(line) > 0) {
                    product.setEnginePower(Double.parseDouble(line));
                    break;
                } else throw new Exception("Wrong input");
            } catch (Exception e) {
                System.out.println("Ошибка ввода");
            }
        }

        while (true) {
            try {
                System.out.println("Введите значение для поля numberOfWheels ");
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                }
                if (line.equals(null) || line.equals("")) {
                    throw new Exception("Wrong input");
                } else {
                    product.setNumberOfWheels(Long.parseLong(line));
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода");
            }
        }

        while (true) {
            try {
                System.out.println("Введите значение для поля fuelConsumption ");
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                }
                if (line.equals(null) && line.equals("")) {
                    throw new Exception("Wrong input");
                }
                product.setFuelConsumption(Long.valueOf(line));
                break;
            } catch (Exception e) {
                System.out.println("Ошибка ввода");
            }
        }

        while (true) {
            try {
                System.out.println("Введите значение для поля fuelType ");
                System.out.println("Возможные варианты : " + FuelType.GASOLINE + ", " + FuelType.KEROSENE + ", " + FuelType.DIESEL + ", " + FuelType.ALCOHOL + ", " + FuelType.MANPOWER);
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                }
                if (line.equals("GASOLINE") || line.equals("KEROSENE") || line.equals("DIESEL") || line.equals("ALCOHOL") || line.equals("MANPOWER")) {
                    switch (line) {
                        case ("GASOLINE"):
                            product.setFuelType(FuelType.GASOLINE);
                            break;
                        case ("KEROSENE"):
                            product.setFuelType(FuelType.KEROSENE);
                            break;
                        case ("DIESEL"):
                            product.setFuelType(FuelType.DIESEL);
                            break;
                        case ("ALCOHOL"):
                            product.setFuelType(FuelType.ALCOHOL);
                            break;
                        case ("MANPOWER"):
                            product.setFuelType(FuelType.MANPOWER);
                            break;
                    }
                    break;
                } else throw new Exception("Wrong input");
            } catch (Exception e) {
                System.out.println("Ошибка ввода");
            }
        }
        return product;
    }
}