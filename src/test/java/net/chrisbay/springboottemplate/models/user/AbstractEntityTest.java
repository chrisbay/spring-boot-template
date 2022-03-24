package net.chrisbay.springboottemplate.models.user;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AbstractEntityTest {

    private static void setUserId(User user, Integer id) throws IllegalAccessException, NoSuchFieldException {
        Field userOneIdField = user.getClass().getSuperclass().getDeclaredField("id");
        userOneIdField.setAccessible(true);
        userOneIdField.set(user, id);
    }

    @Test
    public void testUserEqualsReturnsTrueWhenIdsMatch() throws NoSuchFieldException, IllegalAccessException {
        User userOne = new User("me@me.com", "Test", "User", "a good password");
        setUserId(userOne, 1);

        User userTwo = new User("you@you.org", "Another", "Test User", "another good password");
        setUserId(userTwo, 1);

        assertTrue(userOne.equals(userTwo));
    }

    @Test
    public void testUserEqualsReturnsTrueWhenIdsDiffer() throws NoSuchFieldException, IllegalAccessException {
        User userOne = new User("me@me.com", "Test", "User", "a good password");
        setUserId(userOne, 1);

        User userTwo = new User("me@me.com", "Test", "User", "a good password");
        setUserId(userTwo, 2);

        assertFalse(userOne.equals(userTwo));
    }

}
