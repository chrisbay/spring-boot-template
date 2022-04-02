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
    public String register(@ModelAttribute @Valid UserForm userForm, Errors errors, Model model) {

        if (userService.findByEmail(userForm.getEmail()) != null) {
            errors.rejectValue("email", "email.alreadyexists", "The email address provided has already been registered");
        }

        if (errors.hasErrors()) {
            model.addAttribute("validated", true);
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
