package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Tour;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

/**
 * implementation class for TourDao domain bean
 * @author Ilhan Hasicic
 */
public class TourDaoSQLImpl implements TourDao {
    private Connection conn;
    private static TourDaoSQLImpl instance = null;

    /**
     * constructor for connection to the database
     */
    private TourDaoSQLImpl() throws IOException {
        FileReader reader = new FileReader("db.properties");
        Properties p = new Properties();
        p.load(reader);
        try {
            this.conn = DriverManager.getConnection(p.getProperty("url"), p.getProperty("username"), p.getProperty("password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static TourDaoSQLImpl getInstance() throws IOException {
        if(instance == null) instance = new TourDaoSQLImpl();
        return instance;
    }
    @Override
    public Tour getById(int id) {
        String query = "SELECT * FROM Tours WHERE id = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Tour tour = new Tour();
                tour.setId(rs.getInt("id"));
                tour.setCity_id(CityDaoSQLImpl.getInstance().getById(rs.getInt("city_id")));
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
            PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO Tours(city_id) VALUES(?)", RETURN_GENERATED_KEYS);
            stmt.setInt(1, item.getCity_id().getId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            item.setId(rs.getInt(1));
            return item;
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Tour update(Tour item) {
        try {
            PreparedStatement stmt = this.conn.prepareStatement("UPDATE Tours SET id = ?, city_id = ?");
            stmt.setInt(1, item.getId());
            stmt.setObject(2, item.getCity_id());
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
            PreparedStatement stmt = this.conn.prepareStatement("DELETE FROM Tours WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Tour> getAll() {
        List<Tour> tourList = new ArrayList();
        try {
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM Tours");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Tour tour = new Tour();
                tour.setId(rs.getInt(1));
                tour.setCity_id(CityDaoSQLImpl.getInstance().getById(rs.getInt(2)));
                tourList.add(tour);
            }
            rs.close();
        } catch (SQLException | IOException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return tourList;
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
                t.setId(rs.getInt("id"));
                t.setCity_id(CityDaoSQLImpl.getInstance().getById(rs.getInt("city_id")));
                tourList.add(t);
            }
            rs.close();
            return tourList;
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
