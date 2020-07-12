package com.invoicegenerator.services;

import com.invoicegenerator.model.InvoiceSummary;
import com.invoicegenerator.model.Ride;
import com.invoicegenerator.utility.RideRepository;
import com.invoicegenerator.utility.RideType;

public class InvoiceGenerator {
    /**
     * Method to calculate fare.
     * @return maximum between minimum and totalFare.
     */
    public double calculateFare(RideType rideType, double distance, int time) {
        double totalFare = distance * rideType.minimumCostPerKM + time * rideType.costPerTime;
        return Math.max(rideType.minimumCostPerKM, totalFare);
    }

    /**
     * Calculating totalFare for all the rides of user.
     * @param rides
     */
    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.rideType, ride.distance, ride.time);
        }

        return new InvoiceSummary(rides.length, totalFare);
    }

    /**
     * Method to userID and rides
     * @param userId
     * @param rides
     */
    public void addRide(String userId, Ride[] rides) {
        RideRepository.addRides(userId, rides);
    }

    /**
     * Method to get invoice summary of the userID.
     * @param userId
     */
    public InvoiceSummary getInvoiceSummary(String userId) {
        Ride[] rides = RideRepository.getRides(userId);
        return this.calculateFare(rides);
    }
}
