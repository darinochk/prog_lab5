package collection;

import java.util.Date;

/**
 * Класс продукта
 */

public class Vehicle {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double enginePower; //Поле может быть null, Значение поля должно быть больше 0
    private long numberOfWheels; //Значение поля должно быть больше 0
    private long fuelConsumption; //Значение поля должно быть больше 0
    private FuelType fuelType; //Поле не может быть null

    public Vehicle(Integer id, String name, Coordinates coordinates, Date creationDate, Double enginePower, long numberOfWheels, long fuelConsumption, FuelType fuelType) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.enginePower = enginePower;
        this.numberOfWheels = numberOfWheels;
        this.fuelConsumption = fuelConsumption;
        this.fuelType = fuelType;
    }

    public Vehicle(int id, Object name, Object coordinates, Object creationDate, int i, Object o, Object o1, Object fuelType, Object o2) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
        else System.out.println("Ведите имя еще раз");
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates != null) {
            this.coordinates = coordinates;
        }
        else System.out.println("Введите координаты еще раз");
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate() {
        Date creationDate = new Date();
        this.creationDate = creationDate;
    }

    public Double getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(Double enginePower) {
        if (enginePower > 0) {
            this.enginePower = enginePower;
        }
        else System.out.println("Введите цену еще раз");
    }

    public long getNumberOfWheels() {
        return numberOfWheels;
    }

    public void setNumberOfWheels(long numberOfWheels) {
        if (Long.toString(numberOfWheels) != null) {
            this.numberOfWheels = numberOfWheels;
        }
    }

    public long getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(long fuelConsumption) {
        if (Long.toString(fuelConsumption) != null) {
            this.fuelConsumption = fuelConsumption;
        }
        else System.out.println("Укажите потребление топлива ещё раз");
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        if (fuelType != null) {
            this.fuelType = fuelType;
        }
        else System.out.println("Введите тип топлива еще раз");
    }

    @Override
    public String toString() {
        String nameOfFuelType = "";
        switch (fuelType) {
            case GASOLINE:
                nameOfFuelType = "Бензин";
                break;
            case KEROSENE:
                nameOfFuelType = "Керосин";
                break;
            case DIESEL:
                nameOfFuelType = "Дизель";
                break;
            case ALCOHOL:
                nameOfFuelType = "Алкоголь";
                break;
            case MANPOWER:
                nameOfFuelType = "Кадровая сила";
                break;
        }
        return  "Id: " + id + "\n" +
                "Название: " + name + "\n" +
                "Координаты: " + coordinates + "\n" +
                "Дата создания: " + creationDate + "\n" +
                "Мощность двигателя: " + enginePower + "\n" +
                "Количество колёс: " + numberOfWheels + "\n" +
                "Потребление топлива: " + fuelConsumption + "\n" +
                "Тип топлива: " + fuelType + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return enginePower == vehicle.enginePower && id.equals(vehicle.id) && name.equals(vehicle.name) && coordinates.equals(vehicle.coordinates) && creationDate.equals(vehicle.creationDate) && numberOfWheels==vehicle.numberOfWheels && fuelConsumption==vehicle.fuelConsumption && fuelType == vehicle.fuelType;
    }

    @Override
    public int hashCode() {
        return (int) (enginePower * fuelConsumption);
    }
}