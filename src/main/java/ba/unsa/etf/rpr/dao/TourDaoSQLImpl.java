package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Tour;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * implementation class for TourDao domain bean
 * @author Ilhan Hasicic
 */
public class TourDaoSQLImpl implements TourDao {
    private Connection conn;

    /**
     * constructor for connection to the database
     */
    public TourDaoSQLImpl() throws IOException {
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
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Tour add(Tour item) {
        try {
            PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO Tours (tour_id, city_id) VALUES (item.getTour_id(), item.getCity_id())");
            stmt.executeUpdate();
            return item;
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
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
    public List<Tour> searchByCity(String name) {
        String query = "SELECT * FROM Tours WHERE city_name = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Tour> tourList = new ArrayList<>();
            while(rs.next()){
                Tour t = new Tour();
                t.setTour_id(rs.getInt("tour_id"));
                t.setCity_id(new CityDaoSQLImpl().getById(rs.getInt("city_id")));
                tourList.add(t);
            }
            rs.close();
            return tourList;
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
