package com.example.Capstone.Controllers;

import com.example.Capstone.Models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value="")
public class LoginController {


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("title", "Carme | Homepage");
        return "Login/index";
    }

    @RequestMapping(value="login", method = RequestMethod.GET)
    public String userLogin(Model model) {
        model.addAttribute("title", "Carme | Login");
        model.addAttribute(new User());
        return "Login/login";
    }

    @RequestMapping(value="login", method= RequestMethod.POST)
    public String processUserLogin(@ModelAttribute @Valid User currentUser, Errors errors,
                                   Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Carme | Login");
            model.addAttribute(currentUser);
            return "Login/login";
        }

        String username = currentUser.getUsername();
        return "redirect:/home/" + username;
    }

    @RequestMapping(value="register")
    public String userRegister(Model model) {
        model.addAttribute("title", "Carme | Register");
        model.addAttribute(new User());
        return "Login/register";
    }


}
