package collection;

/**
 * Класс координат
 */
public class Coordinates {
    private double x; //Максимальное значение поля: 997
    private Float y; //Максимальное значение поля: 409, Поле не может быть null

    public Coordinates(double x, Float y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        if (x <=997){
        this.x = x;}
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        if ( (y <= 409) && (y != null) ) {
            this.y = y;
        }
    }

    @Override
    public String toString() {
        return  "x=" + x +
                ", y=" + y ;
    }
}