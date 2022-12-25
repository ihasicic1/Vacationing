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
     *
     * @param firstName
     * @return customers with given first name
     * @throws MyException
     */
    @Override
    public List<Customer> searchByFirstName(String firstName) throws MyException {
        return executeQuery("SELECT * FROM Customers WHERE first_name = ?", new Object[]{firstName});
    }

    /**
     *
     * @param lastName
     * @return customers with given last name
     * @throws MyException
     */
    @Override
    public List<Customer> searchByLastName(String lastName) throws MyException {
        return executeQuery("SELECT * FROM Customers WHERE last_name = ?", new Object[]{lastName});
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


}
