package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Controllers.SignUpController;
import ba.unsa.etf.rpr.business.CustomerManager;
import ba.unsa.etf.rpr.domain.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CustomerManagerTest {
    @Test
    void checkEmailTest() throws Exception{
        CustomerManager mockMem = Mockito.mock(CustomerManager.class);

        Mockito.when(mockMem.getById(0)).thenReturn(new Customer("Customer","Customer","M","465456456","asf134","060316949"));
        Assertions.assertFalse(SignUpController.checkEmail(mockMem.getById(0).getEmail()));
        Mockito.when(mockMem.getById(0)).thenReturn(new Customer("Customer","Customer","M","465456456","asf134@etf.unsa.ba","060316949"));
        Assertions.assertTrue(SignUpController.checkEmail(mockMem.getById(0).getEmail()));
    }

    /**
     * Mocking test.
     * Checks phone number method by mocking user in database.
     * @throws Exception
     */
    @Test
    void checkPhoneNumberTest() throws Exception{
        CustomerManager mockMem = Mockito.mock(CustomerManager.class);

        Mockito.when(mockMem.getById(0)).thenReturn(new Customer("Customer","Customer","M","123","asf134@etf.unsa.ba","060316949"));
        Assertions.assertFalse(SignUpController.checkPhoneNumber(mockMem.getById(0).getPhone_number()));
        Mockito.when(mockMem.getById(0)).thenReturn(new Customer("Customer","Customer","M","465456456","asf134@etf.unsa.ba","060316949"));
        Assertions.assertTrue(SignUpController.checkPhoneNumber(mockMem.getById(0).getPhone_number()));
    }
}
