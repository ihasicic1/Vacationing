package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Booking;
import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.exceptions.MyException;

import java.io.IOException;
import java.util.List;

/**
 * Dao interface for Booking domain bean
 * @author Ilhan Hasicic
 */
public interface BookingDao extends Dao<Booking> {

    /**
     * method that gives all bookings made by one customer
     * @param customer
     * @return list of bookings
     */
    List<Booking> searchByCustomer(Customer customer) throws IOException, MyException;
}
