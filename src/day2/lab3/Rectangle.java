package day2.lab3;

public class Rectangle extends  Shape {
    private int width;
    private int height;
    private String color;

    public Rectangle(int width, int height, String color){
        this.color = color;
        this.height = height;
        this.width = width;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getColor() {
        return color;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
