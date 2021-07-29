package day4.Exercise;

public class Main {
    public static void main(String[] args) {
        PostOffice po = new PostOffice();
        Person person = new Person(1, "Istanbul");
        po.addListener(person);

        Mail m = new Mail("Your mail content");
        po.mailArrived(m);


    }
}
