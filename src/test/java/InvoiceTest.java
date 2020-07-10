import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceTest {
    InvoiceGenerator invoiceGenerator = null;

    @Before
    public void setUp() throws Exception{
        invoiceGenerator = new InvoiceGenerator();
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
            double distance = 2;
            int time = 5;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(25, fare , 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(5, fare , 0.0);
    }

    @Test
    public void giveMultipleRides_shouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(2.0,5),
                        new Ride(0.1,1),
                        };
        InvoiceSummary summary = invoiceGenerator.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2,30.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void giveUserIdWithMultipleRides_shouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(2.0,5),
                new Ride(0.1,1),
                };
        invoiceGenerator.addRide("sai", rides);
        InvoiceSummary summary = invoiceGenerator.getInvoiceSummary("sai");
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2,30.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenPremiumUserId_GenerateTotalFare_ShouldReturnInvoiceSummery() {
        Ride[] rides = {new Ride(RideType.PREMIUM, 35.0, 45), new Ride(RideType.PREMIUM, 10.55, 30), new Ride(RideType.NORMAL, 20, 30)};
        invoiceGenerator.addRide("Nikhil", rides);
        InvoiceSummary invoiceSummery = invoiceGenerator.getInvoiceSummary("Nikhil");
        InvoiceSummary expectedSummery = new InvoiceSummary(3, 760.5);
        Assert.assertEquals(expectedSummery, invoiceSummery);
    }
}