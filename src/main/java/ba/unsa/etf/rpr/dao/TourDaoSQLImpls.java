package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Tour;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
/**
 * implementation class for TourDao domain bean
 * @author Ilhan Hasicic
 */
public class TourDaoSQLImpls implements TourDao {
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
        return null;
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
