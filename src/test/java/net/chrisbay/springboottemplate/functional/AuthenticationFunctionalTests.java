package net.chrisbay.springboottemplate.functional;

import net.chrisbay.springboottemplate.functional.config.FunctionalTestConfig;
import net.chrisbay.springboottemplate.models.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitConfig
@FunctionalTestConfig
@ContextConfiguration
public class AuthenticationFunctionalTests extends AbstractBaseFunctionalTest {

    @Test
    public void testCanViewRegistrationForm() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(xpath("//form//input[@name='firstName']").exists())
                .andExpect(xpath("//form//input[@name='lastName']").exists())
                .andExpect(xpath("//form//input[@name='email']").exists())
                .andExpect(xpath("//form//input[@name='password']").exists())
                .andExpect(xpath("//form//input[@name='verifyPassword']").exists());
    }

    @Test
    public void testCanRegister() throws Exception {
        String email = "newuser@domain.com";
        String password = "abc123";
        mockMvc.perform(post("/register").with(csrf())
                        .param("firstName", "New")
                        .param("lastName", "User")
                        .param("email", email)
                        .param("password", password)
                        .param("verifyPassword", password))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/"));
        User user = userService.findByEmail(email);
        assertEquals(user.getEmail(), email);
    }

    @Test
    public void testRegistrationFormChecksPasswords() throws Exception {
        mockMvc.perform(post("/register").with(csrf())
                        .param("firstName", "New")
                        .param("lastName", "User")
                        .param("email", "newuser@domain.com")
                        .param("password", "password")
                        .param("verifyPassword", "passord"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("userForm", "verifyPassword"));
    }

    @Test
    public void testRegistrationFormChecksEmailFormat() throws Exception {
        mockMvc.perform(post("/register").with(csrf())
                        .param("firstName", "New")
                        .param("lastName", "User")
                        .param("email", "newuser")
                        .param("password", "password")
                        .param("verifyPassword", "password"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("userForm", "email"));
    }

    @Test
    public void testRegistrationFormChecksForExistingEmail() throws Exception {
        mockMvc.perform(post("/register").with(csrf())
                        .param("firstName", TEST_USER_FIRST_NAME)
                        .param("lastName", TEST_USER_LAST_NAME)
                        .param("email", TEST_USER_EMAIL)
                        .param("password", TEST_USER_PASSWORD)
                        .param("verifyPassword", TEST_USER_PASSWORD))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("userForm", "email"));
    }

    @Test
    public void testRegistrationFormValidatesFirstName() throws Exception {
        mockMvc.perform(post("/register").with(csrf())
                        .param("firstName", "")
                        .param("lastName", "User")
                        .param("email", "newuser")
                        .param("password", "password")
                        .param("verifyPassword", "password"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("userForm", "firstName"));
    }

    @Test
    public void testRegistrationFormValidatesLastName() throws Exception {
        mockMvc.perform(post("/register").with(csrf())
                        .param("firstName", "New")
                        .param("lastName", "")
                        .param("email", "newuser")
                        .param("password", "password")
                        .param("verifyPassword", "password"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("userForm", "lastName"));
    }

    @Test
    public void testCanViewLoginForm() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(xpath("//form//input[@name='email']").exists())
                .andExpect(xpath("//form//input[@name='password']").exists())
                .andExpect(xpath("//form//input[@name='remember-me']").exists());
    }

    @Test
    public void testCanLogIn() throws Exception {
        mockMvc.perform(formLogin("/login")
                        .user("email", TEST_USER_EMAIL)
                        .password(TEST_USER_PASSWORD))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/"));
    }

    @Test
    public void testRedirectsToRootIfAlreadyLoggedIn() throws Exception {
        mockMvc.perform(get("/login")
                        .with(user(TEST_USER_EMAIL)))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/"));
    }

    @Test
    public void testCanLogOut() throws Exception {
        mockMvc.perform(formLogin("/login")
                .user("email", TEST_USER_EMAIL)
                .password(TEST_USER_PASSWORD));
        mockMvc.perform(post("/logout").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/login?logout"));
    }

}
