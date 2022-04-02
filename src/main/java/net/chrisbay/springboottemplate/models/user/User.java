package net.chrisbay.springboottemplate.models.user;

import net.chrisbay.springboottemplate.models.AbstractEntity;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Validated
public class User extends AbstractEntity {

    @NotBlank(message = "User.email may not be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "User.firstName may not be blank")
    private String firstName;

    @NotBlank(message = "User.lastName may not be blank")
    private String lastName;

    @NotBlank(message = "User.password may not be blank")
    private String password;

    @NotNull(message = "User.enabled may not be null")
    private Boolean enabled = true;

    public User() {}

    public User(String email, String firstName, String lastName, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public List<String> getRoles() {
        ArrayList<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        return roles;
    }

    public String getEmail() {
        return email;
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

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}
