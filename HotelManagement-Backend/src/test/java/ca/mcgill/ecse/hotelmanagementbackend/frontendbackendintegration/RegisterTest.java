package ca.mcgill.ecse.hotelmanagementbackend.frontendbackendintegration;

import ca.mcgill.ecse.hotelmanagementbackend.controller.LoginController;
import ca.mcgill.ecse.hotelmanagementbackend.dto.LoginData;
import ca.mcgill.ecse.hotelmanagementbackend.dto.RegisterData;
import ca.mcgill.ecse.hotelmanagementbackend.entity.Customer;
import ca.mcgill.ecse.hotelmanagementbackend.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class RegisterTest {
    @Autowired
    private LoginController loginController;

    @Test
    void testRegister() {
        RegisterData registerData = new RegisterData("Name", "UserName", "12345@qq.com", "114514");
        ResponseEntity<Boolean> newCustomer = loginController.register(registerData);
        assertEquals("Register Failed, should be true", Boolean.TRUE, newCustomer);

    }

    @Test
    void testLogin() {
        LoginData loginData = new LoginData("existingUser", "correctPassword");
        ResponseEntity<String> response = loginController.login(loginData);
        assertEquals("Login Failed", 200, response.getStatusCode().value());
    }
}
