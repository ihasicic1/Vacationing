package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Booking;
import ba.unsa.etf.rpr.exceptions.MyException;

public class BookingManager {

    public Booking getById(int id) throws MyException {
        return DaoFactory.bookingDao().getById(id);
    }
}
