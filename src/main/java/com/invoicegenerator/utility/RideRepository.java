package com.invoicegenerator.utility;

import com.invoicegenerator.model.Ride;

import java.util.*;

public class RideRepository {
    static Map<String, ArrayList<Ride>> userRidesMap = new HashMap<>();

    public static void addRides(String userId, ArrayList<Ride> rideList) {
        userRidesMap.put(userId, rideList);
    }

    public static List<Ride> getRides(String userId) {
        return userRidesMap.get(userId);
    }
}
