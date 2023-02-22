package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.BookingManager;
import ba.unsa.etf.rpr.business.CustomerManager;
import ba.unsa.etf.rpr.domain.Booking;
import ba.unsa.etf.rpr.exceptions.MyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BookingManagerTest {
    BookingManager bookingManager = new BookingManager();
    CustomerManager customerManager = new CustomerManager();

    @Test
    void searchByCustomerTest() throws MyException {
        List<Booking> bookingList = bookingManager.getAll();
        int counter = 0;
        for(Booking b: bookingList){
            if(b.getCustomerId() == customerManager.getById(67).getId()) {
                counter++;
            }
        }

        boolean isValid = false;
        if(bookingList.size() == 5) isValid = true;
        Assertions.assertTrue(isValid);

    }








}
