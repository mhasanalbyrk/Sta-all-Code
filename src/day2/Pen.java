package day2;

public class Pen {

    public void drawRectangle( Rectangle r){
        System.out.println("Are of rectangle is ->" + r.getHeight() * r.getWidth());
    }

    public void drawCircle( Circle c){
        System.out.println("Are of circle is ->" + c.getRadius() * c.getRadius() * Math.PI);
    }
    public void changeColorRectangle(String color, Rectangle r){
        r.setColor(color);
        System.out.println("Rectangle  is " + color + " now.");
    }

    public void changeColorCircle(String color, Circle c){
        c.setColor(color);
        System.out.println("Circle  is " + color + " now.");
    }
}
