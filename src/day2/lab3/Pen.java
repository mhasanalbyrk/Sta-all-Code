package day2.lab3;

public class Pen {

//    public void drawRectangle( Rectangle r){
//        System.out.println("Are of rectangle is ->" + r.getHeight() * r.getWidth());
//    }
//
//    public void drawCircle( Circle c){
//        System.out.println("Are of circle is ->" + c.getRadius() * c.getRadius() * Math.PI);
//    }
    public void drawShape(Shape s){



        if (s instanceof Rectangle)
            System.out.println("Are of rectangle is ->" + ((Rectangle)s).getHeight() * ((Rectangle)s).getWidth());

        if (s instanceof Circle)
            System.out.println("Are of circle is ->" + ((Circle)s).getRadius() * ((Circle) s).getRadius() * Math.PI);
    }

    public void changeColorRectangle(String color, Rectangle r){
        r.setColor(color);
        System.out.println("Rectangle  is " + color + " now.");
    }

    public void changeColorCircle(String color, Circle c){
        c.setColor(color);
        System.out.println("Circle  is " + color + " now.");
    }

    public void changeColor(String color, Shape s){
        if (s instanceof Rectangle){
            ((Rectangle)s).setColor(color);
            System.out.println("Rectangle  is " + color + " now.");
        }

        if (s instanceof Circle){
            ((Circle)s).setColor(color);
            System.out.println("Circle  is " + color + " now.");
        }


    }
}
