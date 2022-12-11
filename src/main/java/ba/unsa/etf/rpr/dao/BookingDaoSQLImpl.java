package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Booking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDaoSQLImpl implements BookingDao{
    private Connection conn;
    @Override
    public List<Booking> searchByCustomerId(int id) {
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
