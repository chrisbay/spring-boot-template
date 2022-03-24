package net.chrisbay.springboottemplate.controllers.auth;

import net.chrisbay.springboottemplate.controllers.AbstractBaseController;
import net.chrisbay.springboottemplate.forms.UserForm;
import net.chrisbay.springboottemplate.user.EmailExistsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class AuthenticationController extends AbstractBaseController {

    @GetMapping(value = "/register")
    public String registerForm(Model model) {
        model.addAttribute(new UserForm());
        model.addAttribute("title", "Register");
        return "register";
    }

    @PostMapping(value = "/register")
    public String register(@ModelAttribute @Valid UserForm userForm, Errors errors) {

        if (errors.hasErrors())
            return "register";

        try {
            userService.save(userForm);
        } catch (EmailExistsException e) {
            errors.rejectValue("email", "email.alreadyexists", e.getMessage());
            return "register";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String login(Model model, Principal user, String error, String logout) {

        if (user != null)
            return "redirect:/";

        if (error != null)
            model.addAttribute(MESSAGE_KEY, "danger|Invalid username and/or password");

        if (logout != null)
            model.addAttribute(MESSAGE_KEY, "info|You have been logged out");

        return "login";
    }

}
