package com.example.Capstone.Controllers;

import com.example.Capstone.Models.App;
import com.example.Capstone.Models.Data.AppDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value="home")
public class UserController {

    @Autowired
    private AppDao appDao;

    @RequestMapping(value="/{username}", method = RequestMethod.GET)
    public String userHomePage(@PathVariable String username, Model model) {
        model.addAttribute("title", "Home | " + username);
        model.addAttribute("apps", appDao.findAll());
        model.addAttribute("username", username);
        return "User/index";
    }

    @RequestMapping(value="addApp/{username}", method = RequestMethod.GET)
    public String userAddApp(@PathVariable String username, Model model) {
        model.addAttribute("title", "Add App | " + username);
        model.addAttribute(new App());
        return "User/addApp";
    }

    @RequestMapping(value="addApp/{username}", method = RequestMethod.POST)
    public String processAddApp(@PathVariable String username, @ModelAttribute @Valid App newApp,
                                Errors errors,
                                Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add App | " + username);
            model.addAttribute(newApp);
            return "User/addApp";
        }

        appDao.save(newApp);
        return "redirect:/home/" + username;
    }

}

//TODO: CHANGE OUT TEMP STYLING FOR STYLE SHEET