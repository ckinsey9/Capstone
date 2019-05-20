package com.example.Capstone.Controllers;

import com.example.Capstone.Models.JobApp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="home")
public class UserController {

    @RequestMapping(value="/{username}", method = RequestMethod.GET)
    public String userHomePage(@PathVariable String username, Model model) {
        model.addAttribute("title", "Home | " + username);
        model.addAttribute("username", username);
        return "User/index";
    }

    @RequestMapping(value="addApp/{username}", method = RequestMethod.GET)
    public String userAddApp(@PathVariable String username, Model model) {
        model.addAttribute("title", "AddApp | " + username);
        model.addAttribute(new JobApp());
        return "User/addApp";
    }

}
