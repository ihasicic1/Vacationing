package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.exceptions.MyException;

import java.util.List;

/**
 * Dao interface for Customer domain bean
 * @author Ilhan Hasicic
 */
public interface CustomerDao extends Dao<Customer>{

    /**
     * returns all customers with given first name
     * @param firstName
     * @return list of customers
     */
    List<Customer> searchByFirstName(String firstName) throws MyException;


    /**
     * returns all customers with given last name
     * @param lastName
     * @return list of customers
     */
    List<Customer> searchByLastName(String lastName) throws MyException;

    Customer getByEmail(String email) throws MyException;


}
