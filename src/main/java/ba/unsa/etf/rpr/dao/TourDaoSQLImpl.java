package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Tour;
import ba.unsa.etf.rpr.exceptions.MyException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

/**
 * implementation class for TourDao domain bean
 * @author Ilhan Hasicic
 */
public class TourDaoSQLImpl extends AbstractDao<Tour> implements TourDao {


    public TourDaoSQLImpl() {
        super("Tours");
    }

    @Override
    public Tour row2object(ResultSet rs) throws MyException {
        return null;
    }

    @Override
    public Map<String, Object> object2row(Tour object) {
        return null;
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
            PreparedStatement stmt = this.conn.prepareStatement("UPDATE Tours SET city_id = ? WHERE id = ?");
            stmt.setObject(1, item.getCity_id());
            stmt.setInt(2, item.getId());
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
