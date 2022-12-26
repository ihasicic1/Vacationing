package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Booking;
import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.exceptions.MyException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class BookingDaoSQLImpl extends AbstractDao<Booking> implements BookingDao{


    public BookingDaoSQLImpl() {
        super("Booking");
    }

    @Override
    public List<Booking> searchByCustomer(Customer customer) throws MyException {
        return executeQuery("SELECT * FROM Booking WHERE customer_id = ?", new Object[]{customer.getId()});
    }

    @Override
    public Booking row2object(ResultSet rs) throws MyException {
        try{
            Booking booking = new Booking();
            booking.setId(rs.getInt(1));
            booking.setTicket_price(rs.getDouble(2));
            booking.setTour(DaoFactory.tourDao().getById(rs.getInt(3)));
            booking.setCustomer(DaoFactory.customerDao().getById(rs.getInt(4)));
            booking.setDate(rs.getDate(5));
            return booking;
        } catch (Exception e) {
            throw new MyException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Booking object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("ticket_price", object.getTicket_price());
        item.put("tour_id", object.getTour().getId());
        item.put("customer_id", object.getCustomer().getId());
        item.put("date", object.getDate());
        return item;
    }

}
