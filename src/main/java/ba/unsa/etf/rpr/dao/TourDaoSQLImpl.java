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
    public List<Tour> searchByCity(String name) throws MyException {
        return executeQuery("SELECT * FROM Tours WHERE city_name = ?", new Object[]{name});
    }
}
