package net.chrisbay.springboottemplate.controllers;

import net.chrisbay.springboottemplate.models.user.User;
import net.chrisbay.springboottemplate.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class AbstractBaseController {

    @Autowired
    protected UserService userService;

    protected static final String MESSAGE_KEY = "message";

    @ModelAttribute("user")
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null)
            return userService.findByEmail(authentication.getName());
        return null;
    }

}
