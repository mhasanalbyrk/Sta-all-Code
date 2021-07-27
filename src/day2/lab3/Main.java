package day2.lab3;

public class Main {
    public static void main(String[] args) {
        Pen pen = new Pen();

        Shape r = new Rectangle(5, 6, "red");
        Shape c = new Circle(1, "blue");

        pen.drawShape(r);
        pen.drawShape(c);

        pen.changeColor("red",  c);
        pen.changeColor("blue",  r);

    }
}
