package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Booking;
import ba.unsa.etf.rpr.exceptions.MyException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class BookingDaoSQLImpl extends AbstractDao<Booking> implements BookingDao{


    public BookingDaoSQLImpl() {
        super("Booking");
    }

    @Override
    public List<Booking> searchByCustomerId(int id) throws IOException {
        String query = "SELECT * FROM Booking WHERE customer_id = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Booking> bookingList = new ArrayList<>();
            while(rs.next()){
                Booking b = new Booking();
                b.setId(rs.getInt("id"));
                b.setTicket_price(rs.getDouble("ticket_price"));
                b.setTour_id(TourDaoSQLImpl.getInstance().getById(rs.getInt("tour_id")));
                b.setCustomer_id(CustomerDaoSQLImpl.getInstance().getById(rs.getInt("customer_id")));
                bookingList.add(b);
            }
            rs.close();
            return bookingList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Booking row2object(ResultSet rs) throws MyException {
        return null;
    }

    @Override
    public Map<String, Object> object2row(Booking object) {
        return null;
    }


    @Override
    public List<Booking> getAll() {
        List<Booking> bookingList = new ArrayList();
        try {
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM Booking");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Booking booking = new Booking();
                booking.setId(rs.getInt(1));
                booking.setTicket_price(rs.getDouble(2));
                booking.setTour_id(TourDaoSQLImpl.getInstance().getById(rs.getInt(3)));
                booking.setCustomer_id(CustomerDaoSQLImpl.getInstance().getById(rs.getInt(4)));
                bookingList.add(booking);
            }
            rs.close();
        } catch (SQLException | IOException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return bookingList;
    }
}
