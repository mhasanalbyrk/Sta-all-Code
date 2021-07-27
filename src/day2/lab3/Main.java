package day2.lab3;

public class Main {
    public static void main(String[] args) {
        Pen pen = new Pen();

        Shape r = new Rectangle(5, 6, "red");
        Shape c = new Circle(1, "blue");

        if (c instanceof Circle)
        pen.drawCircle((Circle)c);
        if (r instanceof Rectangle)
        pen.drawRectangle((Rectangle) r);

        if (c instanceof Circle)
        pen.changeColorCircle("red", (Circle)c);
        if (r instanceof Rectangle)
        pen.changeColorRectangle("blue", (Rectangle)r);
    }
}
