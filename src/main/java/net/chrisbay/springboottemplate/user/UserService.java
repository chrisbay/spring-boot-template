package net.chrisbay.springboottemplate.user;

import net.chrisbay.springboottemplate.forms.UserForm;
import net.chrisbay.springboottemplate.models.user.User;


public interface UserService {

    public User save(UserForm userForm) throws EmailExistsException;
    public User findByEmail(String email);

}
