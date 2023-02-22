package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Booking;
import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.exceptions.MyException;

import java.io.IOException;
import java.util.List;

public class BookingManager {

    public List<Booking> searchByCustomer(Customer customer) throws MyException, IOException {
        return DaoFactory.bookingDao().searchByCustomer(customer);
    }

    public Booking getById(int id) throws MyException {
        return DaoFactory.bookingDao().getById(id);
    }

    public Booking add(Booking booking) throws MyException {
        return DaoFactory.bookingDao().add(booking);
    }

    public void update(Booking booking) throws MyException {
        DaoFactory.bookingDao().update(booking);
    }

    public void delete(int id)throws MyException {
        DaoFactory.bookingDao().delete(id);
    }

    public List<Booking> getAll() throws MyException {
        return DaoFactory.bookingDao().getAll();
    }
}
