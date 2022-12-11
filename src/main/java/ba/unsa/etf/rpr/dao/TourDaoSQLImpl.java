package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.City;
import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.domain.Tour;

import java.sql.*;
import java.util.List;
/**
 * implementation class for TourDao domain bean
 * @author Ilhan Hasicic
 */
public class TourDaoSQLImpl implements TourDao {
    private Connection conn;

    /**
     * constructor for connection to the database
     */
    public TourDaoSQLImpl(){
        try {
            this.conn = DriverManager.getConnection("url", "username", "password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Tour getById(int id) {
        String query = "SELECT * FROM Tours WHERE tour_id = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Tour tour = new Tour();
                tour.setTour_id(rs.getInt("tour_id"));
                tour.setCity_id(new CityDaoSQLImpl().getById(rs.getInt("city_id")));
                rs.close();
                return tour;
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Tour add(Tour item) {
        return null;
    }

    @Override
    public Tour update(Tour item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Tour> getAll() {
        return null;
    }

    @Override
    public List<Tour> searchById(int id) {
        return null;
    }

    @Override
    public List<Tour> searchByCity(String name) {
        return null;
    }
}