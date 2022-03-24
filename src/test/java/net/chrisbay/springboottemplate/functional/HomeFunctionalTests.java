package net.chrisbay.springboottemplate.functional;

import net.chrisbay.springboottemplate.functional.config.FunctionalTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig
@FunctionalTestConfig
@ContextConfiguration
public class HomeFunctionalTests extends AbstractBaseFunctionalTest {

    @Test
    public void testCanViewRegistrationForm() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void testErrorPageIsDisplayedOn404() throws Exception {
        // TODO - test 404.html template is displayed
        mockMvc.perform(get("/asdfasdf")
                .with(user(TEST_USER_EMAIL)))
                .andExpect(status().is4xxClientError());
    }

}
