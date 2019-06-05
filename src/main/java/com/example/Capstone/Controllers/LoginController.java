package com.example.Capstone.Controllers;

import com.example.Capstone.Models.Data.UserDao;
import com.example.Capstone.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private UserDao userDao;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("title", "Appeaz | Homepage");
        return "Login/index";
    }

    @RequestMapping(value="login", method = RequestMethod.GET)
    public String userLogin(Model model) {
        model.addAttribute("title", "Appeaz | Login");
        model.addAttribute(new User());
        return "Login/login";
    }

    @RequestMapping(value="login", method= RequestMethod.POST)
    public String processUserLogin(@ModelAttribute @Valid User currentUser, Errors errors,
                                   Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Appeaz | Login");
            model.addAttribute(currentUser);
            return "Login/login";
        }

        String username = currentUser.getUsername();
        String password = currentUser.getPassword();

        //Added BCrypt match method to check hashed password in database
        for (User user : userDao.findAll()) {
            if (user.getUsername().equals(username) & encoder.matches(password, user.getPassword())) {
                return "redirect:/home/" + username;
            }
        }

        model.addAttribute("title", "Appeaz | Login");
        model.addAttribute("verifyError", "Login error: Please try again or register an account");
        model.addAttribute(currentUser);
        return "Login/login";



    }

    @RequestMapping(value="register")
    public String userRegister(Model model) {
        model.addAttribute("title", "Appeaz | Register");
        model.addAttribute(new User());
        return "Login/register";
    }

    @RequestMapping(value="register", method= RequestMethod.POST)
    public String processUserRegister(@ModelAttribute @Valid User newUser, Errors errors,
                                      Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Appeaz | Register");
            model.addAttribute(newUser);
            return "Login/register";
        }

        if (!newUser.getPassword().equals(newUser.getVerify())) {
            model.addAttribute("title", "Appeaz | Register");
            model.addAttribute("verifyError", "Passwords do not match");
            model.addAttribute(newUser);
            return "Login/register";
        }

        String username = newUser.getUsername();

        //used to check if the registered username is unique
        for (User user : userDao.findAll()) {
            if (user.getUsername().equals(username)) {
                model.addAttribute("title", "Appeaz | Register");
                model.addAttribute("verifyError", "Username taken, please pick another");
                model.addAttribute(newUser);
                return "Login/register";
            }
        }

        //Testing BCrypt for new user registration
        String safePass = encoder.encode(newUser.getPassword());
        newUser.setPassword(safePass);
        newUser.setVerify(safePass);

        userDao.save(newUser);
        return "redirect:/home/" + username;
    }


}
