package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Booking;
import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.domain.Tour;

import java.awt.print.Book;
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
        String query = "SELECT * FROM Booking WHERE booking_id = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Booking booking = new Booking();
                booking.setBooking_id(rs.getInt("id"));
                booking.setTicket_price(rs.getDouble("ticket_price"));
                booking.setTour_id(new TourDaoSQLImpl().getById(rs.getInt("tour_id")));
                booking.setCustomer_id(new CustomerDaoSQLImpl().getById(rs.getInt("customer_id")));
                rs.close();
                return booking;
            }else{
                return null;
            }
        } catch (SQLException | IOException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Booking add(Booking item) {
        try {
            PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO Booking (booking_id, ticket_price, tour_id, customer_id) " +
                    "VALUES (item.getBooking_id, item.getTicket_price(), item.getTour_id(), item.getCustomer_id())");
            stmt.executeUpdate();
            return item;
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
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
