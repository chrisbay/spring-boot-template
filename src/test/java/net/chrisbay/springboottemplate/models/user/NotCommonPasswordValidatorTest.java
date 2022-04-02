package net.chrisbay.springboottemplate.models.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NotCommonPasswordValidatorTest {

    private static NotCommonPasswordValidator validator;

    @BeforeAll
    public static void setUpValidator() {
        validator = new NotCommonPasswordValidator();
    }

    @Test
    public void testValidatorInvalidatesCommonPassword () {
        assertFalse(validator.isValid("123456789", null));
    }

    @Test
    public void testValidatorValidatesUncommonPassword () {
        assertTrue(validator.isValid("8mvz.Hvn2R4zLY", null));
    }

}
