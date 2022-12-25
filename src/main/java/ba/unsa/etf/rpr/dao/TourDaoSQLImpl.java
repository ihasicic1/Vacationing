package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Tour;
import ba.unsa.etf.rpr.exceptions.MyException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

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
        try{
            Tour tour = new Tour();
            tour.setId(rs.getInt(1));
            tour.setCity_id(DaoFactory.cityDao().getById(rs.getInt(2)));
            return tour;
        } catch (Exception e) {
            throw new MyException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Tour object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("city_id", object.getCity_id());
        return item;
    }


    @Override
    public List<Tour> searchByCity(String name) throws MyException {
        return executeQuery("SELECT * FROM Tours WHERE city_name = ?", new Object[]{name});
    }
}
