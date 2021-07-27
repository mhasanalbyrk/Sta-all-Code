package day2.lab3;

public class Circle extends  Shape {
    private int radius;
    private String color;

    public Circle(int radius, String color){
        this.radius = radius;
        this.color = color;

    }

    public int getRadius() {
        return radius;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
