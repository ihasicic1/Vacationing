package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.City;
import ba.unsa.etf.rpr.domain.Tour;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CityDaoSQLImpl implements CityDao{
    private Connection conn;
    private static CityDaoSQLImpl instance = null;

    /**
     * constructor for connection to the database
     * @throws IOException
     */
    private CityDaoSQLImpl() throws IOException {
        FileReader reader = new FileReader("db.properties");
        Properties p = new Properties();
        p.load(reader);
        try {
            this.conn = DriverManager.getConnection(p.getProperty("url"), p.getProperty("username"), p.getProperty("password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static CityDaoSQLImpl getInstance() throws IOException {
        if(instance == null) instance = new CityDaoSQLImpl();
        return instance;
    }

    @Override
    public City getById(int id) {
        String query = "SELECT * FROM Cities WHERE id = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                City city = new City();
                city.setId(rs.getInt("id"));
                city.setCity_name(rs.getString("city_name"));
                rs.close();
                return city;
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public City add(City item) {
        try {
            PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO Cities(city_name) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getCity_name());
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
    public City update(City item) {
        try {
            PreparedStatement stmt = this.conn.prepareStatement("UPDATE Cities SET city_name = ? WHERE id = ?");
            stmt.setString(1, item.getCity_name());
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
            PreparedStatement stmt = this.conn.prepareStatement("DELETE FROM Cities WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
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
