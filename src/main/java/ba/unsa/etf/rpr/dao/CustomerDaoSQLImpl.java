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
    public Customer getById(int id) {
        String query = "SELECT * FROM Customers WHERE id = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getInt("id");
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
