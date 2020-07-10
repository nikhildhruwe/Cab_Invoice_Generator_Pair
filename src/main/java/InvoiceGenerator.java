import java.io.Reader;

public class InvoiceGenerator {
    private static final double MINIMUM_COST_PER_KM = 10;
    private static final double COST_PER_TIME = 1;
    private static final double MINIMUM_FARE = 5.0;


    public double calculateFare(double distance, int time) {
            double totalFare = distance * MINIMUM_COST_PER_KM + time * COST_PER_TIME;
            if(MINIMUM_FARE >= totalFare)
                return MINIMUM_FARE;
            return totalFare;
    }

    public double calculateFare(Ride[] rides) {
        double totalFare = 0;
        for(Ride ride : rides){
           totalFare += this.calculateFare(ride.distance, ride.time);
        }
        return  totalFare;
    }
}
