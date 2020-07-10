public class InvoiceGenerator {
    private static final double MINIMUM_COST_PER_KM = 1;
    private static final double COST_PER_KM = 10;

    public double calculateFare(double distance , int time) {
            double totalFare = distance * MINIMUM_COST_PER_KM * time * COST_PER_KM;
            return totalFare;
    }
}
