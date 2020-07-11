package com.invoicegenerator.services;

import com.invoicegenerator.utility.RideRepository;
import com.invoicegenerator.utility.RideType;

public class InvoiceGenerator {
//    private static final double MINIMUM_COST_PER_KM = 10;
//    private static final double COST_PER_TIME = 1;
//    private static final double MINIMUM_FARE = 5.0;
    private static double MINIMUM_COST_PER_KM;
    private static int COST_PER_TIME;
    private static int MINIMUM_FARE;

    private void setValue(RideType rideType) {
        MINIMUM_COST_PER_KM = rideType.minimumCostPerKM;
        COST_PER_TIME = rideType.costPerTime;
        MINIMUM_FARE = rideType.minFare;
    }

    public double calculateFare(RideType rideType, double distance, int time) {
            this.setValue(rideType);
            double totalFare = distance * MINIMUM_COST_PER_KM + time * COST_PER_TIME;
            return Math.max(MINIMUM_FARE, totalFare);
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for(Ride ride : rides){
           totalFare += this.calculateFare(ride.rideType, ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public void addRide(String userId, Ride[] rides){
        RideRepository.addRides(userId, rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId){
        Ride[] rides = RideRepository.getRides(userId);
        return this.calculateFare(rides);
    }
}
