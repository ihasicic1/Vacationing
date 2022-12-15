package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.City;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class CityDaoSQLImpl implements CityDao{
    private Connection conn;

    public CityDaoSQLImpl() throws IOException {
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
    public City getById(int id) {
        String query = "SELECT * FROM Cities WHERE city_id = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                City city = new City();
                city.setCity_id(rs.getInt("city_id"));
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
            PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO Cities (city_id, city_name) VALUES (item.getCity_id(), item.getCity_name())");
            stmt.executeUpdate();
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
            PreparedStatement stmt = this.conn.prepareStatement("UPDATE Cities SET city_id = ?, city_name = ?");
            stmt.setInt(1, item.getCity_id());
            stmt.setString(2, item.getCity_name());
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
            PreparedStatement stmt = this.conn.prepareStatement("DELETE FROM Cities WHERE city_id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<City> getAll() {
        return null;
    }
}
