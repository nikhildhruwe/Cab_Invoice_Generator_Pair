public class Ride {

    public double distance;
    public int time;
    public RideType rideType;

    public Ride(double distance, int time) {
        this.distance = distance;
        this.time = time;
    }

    public Ride(RideType rideType, double distance, int time) {
        this.distance = distance;
        this.time = time;
        this.rideType = rideType;

    }
}
