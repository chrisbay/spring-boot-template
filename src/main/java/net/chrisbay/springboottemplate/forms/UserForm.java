package net.chrisbay.springboottemplate.forms;

import net.chrisbay.springboottemplate.models.user.NotCommonPassword;

import javax.validation.constraints.*;

public class UserForm {

    @NotBlank
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Please provide your first name")
    private String firstName;

    @NotBlank(message = "Please provide your last name")
    private String lastName;

    @NotBlank(message = "")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @NotCommonPassword(message = "Please provide a more secure password")
    private String password;

    @NotNull(message = "Passwords do not match")
    private String verifyPassword;

    public UserForm() {}

    public UserForm(String email, String firstName, String lastName, String password, String verifyPassword) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.verifyPassword = verifyPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
        checkPasswordForRegistration();
    }

    private void checkPasswordForRegistration() {
        if (this.password == null || !this.password.equals(verifyPassword)) {
            verifyPassword = null;
            password = null;
        }
    }
}
