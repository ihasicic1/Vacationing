package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.City;
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
            tour.setCity(DaoFactory.cityDao().getById(rs.getInt(2)));
            return tour;
        } catch (Exception e) {
            throw new MyException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Tour object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("city_id", object.getCity().getId());
        return item;
    }


    @Override
    public List<Tour> searchByCity(City city) throws MyException {
        return executeQuery("SELECT * FROM Tours WHERE city_name = ?", new Object[]{city.getCity_name()});
    }
}
