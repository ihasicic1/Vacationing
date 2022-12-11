package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Booking;
import ba.unsa.etf.rpr.domain.Customer;

import java.sql.*;
import java.util.List;

/**
 * implementation class for CustomerDao domain bean
 * @author Ilhan Hasicic
 */

public class CustomerDaoSQLImpl implements CustomerDao{
    private Connection conn;
    private String url = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7583349";

    /**
     * constructor for connection to the database
     */
    public CustomerDaoSQLImpl(){
        try {
            this.conn = DriverManager.getConnection(url, "username", "password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public List<Customer> searchById(int id) {
        return null;
    }

    @Override
    public List<Customer> searchByFirstName(String firstName) {
        return null;
    }

    @Override
    public List<Customer> searchByLastName(String lastName) {
        return null;
    }

    @Override
    public List<Booking> getById(int id) {
        return null;
    }

    @Override
    public Customer add(Customer item) {
        return null;
    }

    @Override
    public Customer update(Customer item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Customer> getAll() {
        return null;
    }
}
