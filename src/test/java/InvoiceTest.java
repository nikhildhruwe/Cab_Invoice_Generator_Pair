import com.invoicegenerator.services.InvoiceGenerator;
import com.invoicegenerator.model.InvoiceSummary;
import com.invoicegenerator.model.Ride;
import com.invoicegenerator.utility.RideType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class InvoiceTest {
    InvoiceGenerator invoiceGenerator = null;

    @Before
    public void setUp() throws Exception {
        invoiceGenerator = new InvoiceGenerator();
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 2;
        int time = 5;
        double fare = invoiceGenerator.calculateFare(RideType.NORMAL, distance, time);
        Assert.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(RideType.NORMAL, distance, time);
        Assert.assertEquals(10, fare, 0.0);
    }

    @Test
    public void giveMultipleRides_shouldReturnInvoiceSummary() {
        ArrayList<Ride> rideList = new ArrayList<>();
        rideList.add(new Ride(RideType.NORMAL, 2.0, 5));
        rideList.add(new Ride(RideType.NORMAL, 0.1, 1));
        InvoiceSummary summary = invoiceGenerator.calculateFare(rideList);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 35.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void giveUserIdWithMultipleRides_shouldReturnInvoiceSummary() {
        ArrayList<Ride> rideList1 = new ArrayList<>();
        rideList1.add(new Ride(RideType.NORMAL, 2.0, 5));
        rideList1.add(new Ride(RideType.NORMAL, 0.1, 1));

        invoiceGenerator.addRide("sai", rideList1);
        InvoiceSummary summary = invoiceGenerator.getInvoiceSummary("sai");
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 35.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenPremiumUserId_GenerateTotalFare_ShouldReturnInvoiceSummery() {
        ArrayList<Ride> rideList2 = new ArrayList<>();

        rideList2.add(new Ride(RideType.PREMIUM, 35.0, 45));
        rideList2.add(new Ride(RideType.PREMIUM, 10.55, 30));
        rideList2.add(new Ride(RideType.PREMIUM, 20, 30));
        invoiceGenerator.addRide("Nikhil", rideList2);
        InvoiceSummary invoiceSummery = invoiceGenerator.getInvoiceSummary("Nikhil");
        InvoiceSummary expectedSummery = new InvoiceSummary(3, 1193.25);
        Assert.assertEquals(expectedSummery, invoiceSummery);
    }

    @Test
    public void givenNormalUserId_GenerateTotalFare_ShouldReturnInvoiceSummery() {
        ArrayList<Ride> rideList3 = new ArrayList<>();
        rideList3.add(new Ride(RideType.NORMAL, 35.0, 45));
        rideList3.add(new Ride(RideType.NORMAL, 10.55, 30));
        rideList3.add(new Ride(RideType.NORMAL, 20, 30));
        invoiceGenerator.addRide("sai", rideList3);
        InvoiceSummary invoiceSummery = invoiceGenerator.getInvoiceSummary("sai");
        InvoiceSummary expectedSummery = new InvoiceSummary(3, 760.5);
        Assert.assertEquals(expectedSummery, invoiceSummery);
    }
}