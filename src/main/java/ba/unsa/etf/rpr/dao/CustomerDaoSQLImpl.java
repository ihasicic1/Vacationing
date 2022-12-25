package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.exceptions.MyException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * implementation class for CustomerDao domain bean
 * @author Ilhan Hasicic
 */

public class CustomerDaoSQLImpl extends AbstractDao<Customer> implements CustomerDao{

    public CustomerDaoSQLImpl() {
        super("Customers");
    }
    /**
     * constructor for connection to the database
     */

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
                c.setId(rs.getInt("id"));
                c.setFirst_name(rs.getString("first_name"));
                c.setLast_name(rs.getString("last_name"));
                c.setGender(rs.getString("gender"));
                c.setPhone_number(rs.getString("phone_number"));
                c.setEmail(rs.getString("email"));
                c.setPassword(rs.getString("password"));
                customerList.add(c);
            }
            rs.close();
            return customerList;
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Customer> searchByLastName(String lastName) {
        String query = "SELECT * FROM Customers WHERE last_name = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1, lastName);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Customer> customerList = new ArrayList<>();
            while(rs.next()){
                Customer c = new Customer();
                c.setId(rs.getInt("id"));
                c.setFirst_name(rs.getString("first_name"));
                c.setLast_name(rs.getString("last_name"));
                c.setGender(rs.getString("gender"));
                c.setPhone_number(rs.getString("phone_number"));
                c.setEmail(rs.getString("email"));
                c.setPassword(rs.getString("password"));
                customerList.add(c);
            }
            rs.close();
            return customerList;
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Customer row2object(ResultSet rs) throws MyException {
        try {
            Customer customer = new Customer();
            customer.setId(rs.getInt(1));
            customer.setFirst_name(rs.getString(2));
            customer.setLast_name(rs.getString(3));
            customer.setGender(rs.getString(4));
            customer.setPhone_number(rs.getString(5));
            customer.setEmail(rs.getString(6));
            customer.setPassword(rs.getString(7));
            return customer;
        } catch (Exception e) {
            throw new MyException(e.getMessage(), e);
        }
    }

    /**
     * @param object - object to be mapped
     * @return map representation of object
     */
    @Override
    public Map<String, Object> object2row(Customer object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("first_name", object.getFirst_name());
        item.put("last_name", object.getLast_name());
        item.put("gender", object.getGender());
        item.put("phone_number", object.getPhone_number());
        item.put("email", object.getEmail());
        item.put("password", object.getPassword());
        return item;
    }



    @Override
    public Customer add(Customer item) {
        try {
            PreparedStatement stmt =
                    this.conn.prepareStatement("INSERT INTO Customers(first_name, last_name, gender, phone_number, email, password) " +
                            "VALUES(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);//ako je auto-increment, customer_id ce se sam povecati
            stmt.setString(1, item.getFirst_name());
            stmt.setString(2, item.getLast_name());
            stmt.setString(3, item.getGender());
            stmt.setString(4, item.getPhone_number());
            stmt.setString(5, item.getEmail());
            stmt.setString(6, item.getPassword());
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
    public Customer update(Customer item) {
        try {
            PreparedStatement stmt = this.conn.prepareStatement("UPDATE Customers SET first_name = ?, last_name = ?, gender = ?, phone_number = ?, email = ?, password = ? WHERE id = ?");
            stmt.setString(1, item.getFirst_name());
            stmt.setString(2, item.getLast_name());
            stmt.setString(3, item.getGender());
            stmt.setString(4, item.getPhone_number());
            stmt.setString(5, item.getEmail());
            stmt.setString(6, item.getPassword());
            stmt.setInt(7, item.getId());
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
            PreparedStatement stmt = this.conn.prepareStatement("DELETE FROM Customers WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customersList = new ArrayList();
        try {
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM Customers");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFirst_name(rs.getString("first_name"));
                customer.setLast_name(rs.getString("last_name"));
                customer.setGender(rs.getString("gender"));
                customer.setPhone_number(rs.getString("phone_number"));
                customer.setEmail(rs.getString("email"));
                customer.setPassword(rs.getString("password"));
                customersList.add(customer);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return customersList;
    }
}
