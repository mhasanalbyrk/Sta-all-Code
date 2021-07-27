package day2.BusReservation;
enum Destination{
    Istanbul, ADANA, ANKARA
}

public class Main {
    public static void main(String[] args) {
        Bus bus1 = new Bus(Destination.Istanbul, 30);

        Passenger ali = new Passenger("Ali", Destination.Istanbul);
        Passenger veli = new Passenger("Veli", Destination.ANKARA);
        Passenger mehmet = new Passenger("Mehmet", Destination.ADANA);
        bus1.insertPassenger(ali);
        bus1.insertPassenger(veli);
        bus1.insertPassenger(mehmet);


    }



}
