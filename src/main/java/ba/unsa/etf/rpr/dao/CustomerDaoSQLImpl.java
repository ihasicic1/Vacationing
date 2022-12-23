package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Customer;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * implementation class for CustomerDao domain bean
 * @author Ilhan Hasicic
 */

public class CustomerDaoSQLImpl implements CustomerDao{
    private Connection conn;
    private static CustomerDaoSQLImpl instance = null;

    /**
     * constructor for connection to the database
     */
    private CustomerDaoSQLImpl() throws IOException {
        FileReader reader = new FileReader("db.properties");
        Properties p = new Properties();
        p.load(reader);
        try {
            this.conn = DriverManager.getConnection(p.getProperty("url"), p.getProperty("username"), p.getProperty("password"));
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
    }
     public static CustomerDaoSQLImpl getInstance() throws IOException {
        if(instance == null) instance = new CustomerDaoSQLImpl();
        return instance;
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
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return null;
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
            PreparedStatement stmt = this.conn.prepareStatement("UPDATE Customers SET id = ?, first_name = ?, last_name = ?, gender = ?, phone_number = ?, email = ?, password = ?");
            stmt.setInt(1, item.getId());
            stmt.setString(2, item.getFirst_name());
            stmt.setString(3, item.getLast_name());
            stmt.setString(4, item.getGender());
            stmt.setString(5, item.getPhone_number());
            stmt.setString(6, item.getEmail());
            stmt.setString(7, item.getPassword());
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
