package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Tour;
import ba.unsa.etf.rpr.exceptions.MyException;

import java.sql.*;
import java.util.*;

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
        try{
            Tour tour = new Tour();
            tour.setId(rs.getInt(1));
            tour.setDestination(rs.getString(2));
            tour.setPrice(rs.getDouble(3));
            return tour;
        } catch (Exception e) {
            throw new MyException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Tour object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("destination", object.getDestination());
        item.put("price", object.getPrice());
        return item;
    }


    @Override
    public Tour searchByDestination(String destination) throws MyException {
        return executeQueryUnique("SELECT * FROM Tours WHERE destination = ?", new Object[]{destination});
    }
}
