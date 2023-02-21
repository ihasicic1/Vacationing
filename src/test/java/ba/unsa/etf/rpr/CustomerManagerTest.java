package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Controllers.SignUpController;
import ba.unsa.etf.rpr.business.CustomerManager;
import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.exceptions.MyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class CustomerManagerTest {
    CustomerManager customerManager = new CustomerManager();
    /**
     * Mocking test.
     * Checks email method by mocking user in database.
     * @throws Exception
     */
    @Test
    void checkEmailTest() throws Exception{
        CustomerManager mockCustomer = Mockito.mock(CustomerManager.class);

        Mockito.when(mockCustomer.getById(0)).thenReturn(new Customer("Customer","Customer","M","465456456","asf134","060316949"));
        Assertions.assertFalse(SignUpController.checkEmail(mockCustomer.getById(0).getEmail()));
        Mockito.when(mockCustomer.getById(0)).thenReturn(new Customer("Customer","Customer","M","465456456","asf134@etf.unsa.ba","060316949"));
        Assertions.assertTrue(SignUpController.checkEmail(mockCustomer.getById(0).getEmail()));
    }

    /**
     * Mocking test.
     * Checks phone number method by mocking user in database.
     * @throws Exception
     */
    @Test
    void checkPhoneNumberTest() throws Exception{
        CustomerManager mockCustomer = Mockito.mock(CustomerManager.class);

        Mockito.when(mockCustomer.getById(0)).thenReturn(new Customer("Customer","Customer","M","123","asf134@etf.unsa.ba","060316949"));
        Assertions.assertFalse(SignUpController.checkPhoneNumber(mockCustomer.getById(0).getPhone_number()));
        Mockito.when(mockCustomer.getById(0)).thenReturn(new Customer("Customer","Customer","M","465456456","asf134@etf.unsa.ba","060316949"));
        Assertions.assertTrue(SignUpController.checkPhoneNumber(mockCustomer.getById(0).getPhone_number()));
    }

    @Test
    void getByEmailTest() throws MyException {
        customerManager.add(createCustomer());
        Assertions.assertEquals("Lik", customerManager.getByEmail("lik@gmail.com").getFirst_name());
        customerManager.delete(customerManager.getByEmail("lik@gmail.com").getId());
    }

    private Customer createCustomer(){
        Customer customer = new Customer();
        customer.setFirst_name("Lik");
        customer.setLast_name("Likic");
        customer.setGender("M");
        customer.setPhone_number("062555444");
        customer.setEmail("lik@gmail.com");
        customer.setPassword("lik12345");
        return customer;
    }

    @Test
    void getByIdTest() throws MyException {
        Assertions.assertEquals("Ilhan", customerManager.getById(66).getFirst_name());
    }

    @Test
    void deleteCustomerTest() throws MyException {
        customerManager.add(createCustomer());
        customerManager.delete(customerManager.getByEmail("lik@gmail.com").getId());

        List<Customer> list = customerManager.getAll();
        boolean isValid = true;
        for(Customer c: list){
            if(c.getEmail().equals("lik@gmail.com")) isValid = false;
        }
        Assertions.assertTrue(isValid);
    }
}
