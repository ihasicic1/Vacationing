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
    private static BookingDaoSQLImpl instance = null;

    /**
     * constructor for connection to the database
     * @throws IOException
     */
    private BookingDaoSQLImpl() throws IOException {
        FileReader reader = new FileReader("db.properties");
        Properties p = new Properties();
        p.load(reader);
        try {
            this.conn = DriverManager.getConnection(p.getProperty("url"), p.getProperty("username"), p.getProperty("password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static BookingDaoSQLImpl getInstance() throws IOException {
        if(instance == null) instance = new BookingDaoSQLImpl();
        return instance;
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
                booking.setCustomer_id(CustomerDaoSQLImpl.getInstance().getById(rs.getInt("customer_id")));
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

        try {
            PreparedStatement stmt = this.conn.prepareStatement("UPDATE Booking SET booking_id = ?, ticket_price = ?, tour_id = ?, customer_id = ?");
            stmt.setInt(1, item.getBooking_id());
            stmt.setDouble(2, item.getTicket_price());
            //stmt.setInt(3, item.getTour_id().getTour_id());
            stmt.setObject(3, item.getTour_id());
            //stmt.setInt(4, item.getCustomer_id().getId());
            stmt.setObject(4, item.getCustomer_id());
            stmt.executeUpdate();
            return item;
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement stmt = this.conn.prepareStatement("DELETE FROM Booking WHERE booking_id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Booking> getAll() {
        List<Booking> bookingList = new ArrayList();
        try {
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM Booking");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Booking booking = new Booking();
                booking.setBooking_id(rs.getInt(1));
                booking.setTicket_price(rs.getDouble(2));
                booking.setTour_id(new TourDaoSQLImpl().getById(rs.getInt(3)));
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
