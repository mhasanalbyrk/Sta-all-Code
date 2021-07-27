package day2;

public class Main {
    public static void main(String[] args) {
        Pen pen = new Pen();

        Rectangle r = new Rectangle(5, 6, "red");
        Circle c = new Circle(1, "blue");

        pen.drawCircle(c);
        pen.drawRectangle(r);
        pen.changeColorCircle("red", c);
        pen.changeColorRectangle("blue", r);
    }
}
