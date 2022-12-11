package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * implementation class for CustomerDao domain bean
 * @author Ilhan Hasicic
 */

public class CustomerDaoSQLImpl implements CustomerDao{
    private Connection conn;


    /**
     * constructor for connection to the database
     */
    public CustomerDaoSQLImpl(){
        try {
            this.conn = DriverManager.getConnection("url", "username", "password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public List<Customer> searchByCustomerId(int id) {
        String query = "SELECT * FROM Customers WHERE customer_id = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Customer> customerList = new ArrayList<>();
            while(rs.next()){
                Customer c = new Customer();
                c.setId(rs.getInt("customer_id"));
                c.setFirst_name(rs.getString("first_name"));
                c.setLast_name(rs.getString("last_name"));
                c.setGender(rs.getString("gender"));
                c.setPhone_number(rs.getString("phone_number"));
                c.setEmail(rs.getString("email"));
                c.setPassword(rs.getString("password"));
                customerList.add(c);
            }
            return customerList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Customer> searchByFirstName(String firstName) {
        String query = "SELECT * FROM Customers WHERE first_name = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1, firstName);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Customer> customerList = new ArrayList<>();
            while(rs.next()){
                Customer c = new Customer();
                c.setId(rs.getInt("customer_id"));
                c.setFirst_name(rs.getString("first_name"));
                c.setLast_name(rs.getString("last_name"));
                c.setGender(rs.getString("gender"));
                c.setPhone_number(rs.getString("phone_number"));
                c.setEmail(rs.getString("email"));
                c.setPassword(rs.getString("password"));
                customerList.add(c);
            }
            return customerList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Customer> searchByLastName(String lastName) {
        return null;
    }

    @Override
    public Customer getById(int id) {
        String query = "SELECT * FROM Customers WHERE id = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFirst_name(rs.getString("first_name"));
                customer.setLast_name(rs.getString("last_name"));
                customer.setGender(rs.getString("gender"));
                customer.setPhone_number(rs.getString("phone_number"));
                customer.setEmail(rs.getString("email"));
                customer.setPassword(rs.getString("password"));
                rs.close();
                return customer;
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
