package net.chrisbay.springboottemplate.user;

/**
 * Created by Chris Bay
 */
public class EmailExistsException extends Exception {

    public EmailExistsException(String message) {
        super(message);
    }

}
