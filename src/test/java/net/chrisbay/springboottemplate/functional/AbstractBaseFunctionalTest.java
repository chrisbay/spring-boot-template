package net.chrisbay.springboottemplate.functional;

import net.chrisbay.springboottemplate.forms.UserForm;
import net.chrisbay.springboottemplate.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

public abstract class AbstractBaseFunctionalTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    UserService userService;

    MockMvc mockMvc;

    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    static final String TEST_USER_FIRST_NAME = "New";
    static final String TEST_USER_LAST_NAME = "User";
    static final String TEST_USER_EMAIL = "test@launchcode.org";
    static final String TEST_USER_PASSWORD = "learntocode";

    @BeforeEach
    public void setUpUser() throws Exception {
        UserForm userForm = new UserForm();
        userForm.setEmail(TEST_USER_EMAIL);
        userForm.setFirstName(TEST_USER_FIRST_NAME);
        userForm.setLastName(TEST_USER_LAST_NAME);
        userForm.setPassword(TEST_USER_PASSWORD);
        userForm.setVerifyPassword(TEST_USER_PASSWORD);
        userService.save(userForm);
    }

}
