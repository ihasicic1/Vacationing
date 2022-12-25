package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.City;
import ba.unsa.etf.rpr.domain.Tour;
import ba.unsa.etf.rpr.exceptions.MyException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class CityDaoSQLImpl extends AbstractDao<City> implements CityDao{


    public CityDaoSQLImpl() {
        super("Cities");
    }



    @Override
    public City row2object(ResultSet rs) throws MyException {
        return null;
    }

    @Override
    public Map<String, Object> object2row(City object) {
        return null;
    }

    @Override
    public List<City> getAll() {
        List<City> cityList = new ArrayList();
        try {
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM Cities");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                City city = new City();
                city.setId(rs.getInt(1));
                city.setCity_name(rs.getString(2));
                cityList.add(city);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return cityList;
    }
}
