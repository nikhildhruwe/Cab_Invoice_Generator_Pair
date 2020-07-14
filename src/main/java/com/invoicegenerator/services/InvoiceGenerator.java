package com.invoicegenerator.services;

import com.invoicegenerator.model.InvoiceSummary;
import com.invoicegenerator.model.Ride;
import com.invoicegenerator.utility.RideRepository;
import com.invoicegenerator.utility.RideType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     * @param rideList
     */
    public InvoiceSummary calculateFare(List<Ride> rideList) {
        double totalFare = rideList.stream()
                                 .mapToDouble(ride -> this.calculateFare(ride.rideType, ride.distance, ride.time))
                                 .sum();
        return new InvoiceSummary(rideList.size(), totalFare);
    }

    /**
     * Method to userID and rides
     * @param userId
     * @param rideList
     */
    public void addRide(String userId, ArrayList<Ride> rideList) {
        RideRepository.addRides(userId, rideList);
    }

    /**
     * Method to get invoice summary of the userID.
     * @param userId
     */
    public InvoiceSummary getInvoiceSummary(String userId) {
        List<Ride> rideList = RideRepository.getRides(userId);
        return this.calculateFare(rideList);
    }
}
