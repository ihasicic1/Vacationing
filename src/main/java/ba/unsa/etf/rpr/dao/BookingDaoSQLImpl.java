package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Booking;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BookingDaoSQLImpl implements BookingDao{
    private Connection conn;

    public BookingDaoSQLImpl() throws IOException {
        FileReader reader = new FileReader("db.properties");
        Properties p = new Properties();
        p.load(reader);
        try {
            this.conn = DriverManager.getConnection(p.getProperty("url"), p.getProperty("username"), p.getProperty("password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
                b.setBooking_id(rs.getInt("booking_id"));
                b.setTicket_price(rs.getDouble("ticket_price"));
                b.setTour_id(new TourDaoSQLImpl().getById(rs.getInt("tour_id")));
                b.setCustomer_id(new CustomerDaoSQLImpl().getById(rs.getInt("customer_id")));
                bookingList.add(b);
            }
            rs.close();
            return bookingList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Booking getById(int id) {
        return null;
    }

    @Override
    public Booking add(Booking item) {
        return null;
    }

    @Override
    public Booking update(Booking item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Booking> getAll() {
        return null;
    }
}
