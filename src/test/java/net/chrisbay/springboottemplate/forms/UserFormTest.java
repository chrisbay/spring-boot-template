package net.chrisbay.springboottemplate.forms;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserFormTest {

    private static Validator validator;
    private static UserForm validForm;

    @BeforeAll
    public static void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        validForm = new UserForm("me@me.com", "Test", "User", "8mvz.Hvn2R4zLY", "8mvz.Hvn2R4zLY");
    }

    @Test
    public void testUserFormEmailValidationWhenValid() {
        Set<ConstraintViolation<UserForm>> violations = validator.validate(validForm);
        assertEquals(0, violations.size());
    }

    @Test
    public void testUserFormEmailValidationWhenInvalidFormat() {
        UserForm userForm = new UserForm("me", "Test", "User", "a good password", "a good password");
        Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);
        assertEquals(1, violations.size());
    }

    @Test
    public void testUserFormEmailValidationWhenBlank() {
        UserForm userForm = new UserForm("  ", "Test", "User", "a good password", "a good password");
        Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);
        assertEquals(2, violations.size());
    }

    @Test
    public void testUserFormEmailValidationWhenEmpty() {
        UserForm userForm = new UserForm("", "Test", "User", "a good password", "a good password");
        Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);
        assertEquals(1, violations.size());
    }

    @Test
    public void testUserFormEmailValidationWhenNull() {
        UserForm userForm = new UserForm(null, "Test", "User", "a good password", "a good password");
        Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);
        assertEquals(1, violations.size());
    }

    @Test
    public void testUserFormFirstNameValidationWhenValid() {
        Set<ConstraintViolation<UserForm>> violations = validator.validate(validForm);
        assertEquals(0, violations.size());
    }

    @Test
    public void testUserFormFirstNameValidationWhenBlank() {
        UserForm userForm = new UserForm("me@me.com", "   ", "User", "a good password", "a good password");
        Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);
        assertEquals(1, violations.size());
    }

    @Test
    public void testUserFormFirstNameValidationWhenEmpty() {
        UserForm userForm = new UserForm("me@me.com", "   ", "User", "a good password", "a good password");
        Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);
        assertEquals(1, violations.size());
    }

    @Test
    public void testUserFormFirstNameValidationWhenNull() {
        UserForm userForm = new UserForm("me@me.com", null, "User", "a good password", "a good password");
        Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);
        assertEquals(1, violations.size());    }

    @Test
    public void testUserFormLastNameValidationWhenValid() {
        Set<ConstraintViolation<UserForm>> violations = validator.validate(validForm);
        assertEquals(0, violations.size());
    }

    @Test
    public void testUserFormLastNameValidationWhenBlank() {
        UserForm userForm = new UserForm("me@me.com", "Test", "   ", "a good password", "a good password");
        Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);
        assertEquals(1, violations.size());
    }

    @Test
    public void testUserFormLastNameValidationWhenEmpty() {
        UserForm userForm = new UserForm("me@me.com", "Test", "", "a good password", "a good password");
        Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);
        assertEquals(1, violations.size());
    }

    @Test
    public void testUserFormLastNameValidationWhenNull() {
        UserForm userForm = new UserForm("me@me.com", "Test", null, "a good password", "a good password");
        Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);
        assertEquals(1, violations.size());
    }

    @Test
    public void testUserFormPasswordValidationWhenValid() {
        Set<ConstraintViolation<UserForm>> violations = validator.validate(validForm);
        assertEquals(0, violations.size());
    }

    @Test
    public void testUserFormPasswordValidationWhenBlank() {
        UserForm userForm = new UserForm("me@me.com", "Test", "User", "        ", "a good password");
        Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);
        assertEquals(1, violations.size());
    }

    @Test
    public void testUserFormPasswordValidationWhenEmpty() {
        UserForm userForm = new UserForm("me@me.com", "Test", "User", "", "a good password");
        Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);
        assertEquals(2, violations.size()); // also fails size constraint
    }

    @Test
    public void testUserFormPasswordValidationWhenNull() {
        UserForm userForm = new UserForm("me@me.com", "Test", "User", null, "a good password");
        Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);
        assertEquals(1, violations.size());
    }

    @Test
    public void testUserFormPasswordValidationWhenCommon() {
        UserForm userForm = new UserForm("me@me.com", "Test", "User", "123456789", "123456789");
        Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);
        assertEquals(1, violations.size());
    }

    @Test
    public void testUserFormPasswordValidationWhenNotCommon() {
        UserForm userForm = new UserForm("me@me.com", "Test", "User", "8mvz.Hvn2R4zLY", "8mvz.Hvn2R4zLY");
        Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);
        assertEquals(0, violations.size());
    }

}
