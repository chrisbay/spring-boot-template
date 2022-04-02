package net.chrisbay.springboottemplate.models.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    private static Validator validator;
    private static User validUser;

    @BeforeAll
    public static void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        validUser = new User("me@me.com", "Test", "User", "a good password");
    }

    @Test
    public void testUserEmailValidationWhenValid() {
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);
        assertEquals(0, violations.size());
    }

    @Test
    public void testUserEmailValidationWhenInvalidFormat() {
        User user = new User("me", "Test", "User", "a good password");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void testUserEmailValidationWhenBlank() {
        User user = new User("  ", "Test", "User", "a good password");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(2, violations.size());  // @Email will also fail when non-empty
    }

    @Test
    public void testUserEmailValidationWhenEmpty() {
        User user = new User("", "Test", "User", "a good password");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void testUserEmailValidationWhenNull() {
        User user = new User(null, "Test", "User", "a good password");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void testUserFirstNameValidationWhenValid() {
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);
        assertEquals(0, violations.size());
    }

    @Test
    public void testUserFirstNameValidationWhenBlank() {
        User user = new User("me@me.com", "   ", "User", "a good password");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void testUserFirstNameValidationWhenEmpty() {
        User user = new User("me@me.com", "", "User", "a good password");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void testUserFirstNameValidationWhenNull() {
        User user = new User("me@me.com", null, "User", "a good password");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void testUserLastNameValidationWhenValid() {
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);
        assertEquals(0, violations.size());
    }

    @Test
    public void testUserLastNameValidationWhenBlank() {
        User user = new User("me@me.com", "Test", "   ", "a good password");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void testUserLastNameValidationWhenEmpty() {
        User user = new User("me@me.com", "Test", "", "a good password");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void testUserLastNameValidationWhenNull() {
        User user = new User("me@me.com", "Test", null, "a good password");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void testUserPasswordValidationWhenValid() {
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);
        assertEquals(0, violations.size());
    }

    @Test
    public void testUserPasswordValidationWhenBlank() {
        User user = new User("me@me.com", "Test", "User", " ");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void testUserPasswordValidationWhenEmpty() {
        User user = new User("me@me.com", "Test", "User", "");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void testUserPasswordValidationWhenNull() {
        User user = new User("me@me.com", "Test", "User", null);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

}
