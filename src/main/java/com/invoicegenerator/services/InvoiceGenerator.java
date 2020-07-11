package com.invoicegenerator.services;

import com.invoicegenerator.model.InvoiceSummary;
import com.invoicegenerator.model.Ride;
import com.invoicegenerator.utility.RideRepository;
import com.invoicegenerator.utility.RideType;

public class InvoiceGenerator {
    public double calculateFare(RideType rideType, double distance, int time) {
        double totalFare = distance * rideType.minimumCostPerKM + time * rideType.costPerTime;
        return Math.max(rideType.minimumCostPerKM, totalFare);
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.rideType, ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public void addRide(String userId, Ride[] rides) {
        RideRepository.addRides(userId, rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        Ride[] rides = RideRepository.getRides(userId);
        return this.calculateFare(rides);
    }
}
