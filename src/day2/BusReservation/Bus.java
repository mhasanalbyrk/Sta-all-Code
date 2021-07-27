package day2.BusReservation;

public class Bus {
    private Destination destination;
    private Passenger[] passengers;
    private int capacity;
    private int numberOfPassengers;

    public Bus(Destination destination, int capacity){
        this.capacity = capacity;
        this.destination = destination;
        this.passengers = new Passenger[capacity];
        numberOfPassengers = 0;
    }

    public void insertPassenger(Passenger passenger){
        if (capacity == numberOfPassengers){
            System.out.println("Bus is full");
            return;
        }
        else if (!passenger.getDestination().equals(this.destination)){
            System.out.println("Destination does not match!!");
            return;
        }

        for (int i = 0; i < capacity; i++) {
            if (passengers[i] == null){
                passengers[i] = passenger;
                System.out.println("Passenger inserted");
                numberOfPassengers++;
                return;

            }
        }
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public void setPassengers(Passenger[] passengers) {
        this.passengers = passengers;
    }

    public Passenger[] getPassengers() {
        return passengers;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
