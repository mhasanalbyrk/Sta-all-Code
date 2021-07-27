package day2.BusReservation;

public class Passenger {
    private String name;
    private Destination destination;

    public Passenger(String name, Destination destination){
        this.destination = destination;
        this.name = name;
    }
    public Destination getDestination() {
        return destination;
    }

    public String getName() {
        return name;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public void setName(String name) {
        this.name = name;
    }
}
