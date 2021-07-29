package day4.Exercise;

public class Person implements MailReceiver {
    int id;
    String address;

    Person(int id, String address){
        this.id = id;
        this.address = address;
    }

    @Override
    public void onNewMailArrived(Mail m){
        System.out.println("Your mail has arrived-> " + m.toString());
    }
}
