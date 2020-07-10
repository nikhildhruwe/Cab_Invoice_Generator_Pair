import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class RideRepository {
    Map<String, ArrayList<Ride>> userRidesMap = null;
    public void addRides(String userId, Ride[] rides){
        userRidesMap.put(userId, new ArrayList<>((Arrays.asList(rides))));
    }
    public Ride[] getRides(String userId){
        return this.userRidesMap.get(userId).toArray(new Ride[0]);
    }
}
