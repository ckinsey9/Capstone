package com.example.Capstone.Controllers;

import com.example.Capstone.Models.App;
import com.example.Capstone.Models.Data.AppDao;
import com.example.Capstone.Models.Data.UserDao;
import com.example.Capstone.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value="home")
public class UserController {

    @Autowired
    private AppDao appDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value="/{username}", method = RequestMethod.GET)
    public String userHomePage(@PathVariable String username, Model model) {
        model.addAttribute("title", "Home | " + username);
        //this line used to display just the logged in user's apps
        model.addAttribute("apps", userDao.findByUsername(username).getApps());
        model.addAttribute("username", username);
        return "User/index";
    }

    @RequestMapping(value="/{username}", method = RequestMethod.POST)
    public String processRemoveApp(@PathVariable String username,
                                   @RequestParam (value = "appIds",
            required = false, defaultValue = "") int[] appIds) {
        for (int appId : appIds) {
            appDao.delete(appId);
        }
        return "redirect:/home/" + username;

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

        User currentUser = userDao.findByUsername(username);
        newApp.setUser(currentUser);
        currentUser.addApp(newApp);
        appDao.save(newApp);
        return "redirect:/home/" + username;
    }

    @RequestMapping(value = "editApp/{username}/{appId}", method = RequestMethod.GET)
    public String userEditApp(@PathVariable int appId, @PathVariable String username,
                              Model model) {
        App editApp = appDao.findOne(appId);
        model.addAttribute("title", "Edit App | " + username);
        model.addAttribute(editApp);
        return "User/editApp";
    }

    @RequestMapping(value= "editApp/{username}/{appId}", method = RequestMethod.POST)
    public String processUserEditApp(@PathVariable int appId, @PathVariable String username,
                                     @ModelAttribute @Valid App editApp, Errors errors,
                                     Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit App | " + username);
            model.addAttribute(editApp);
            return "User/editApp";
        }

        App originalApp = appDao.findOne(appId);

        originalApp.setAll(editApp.getName(),
                            editApp.getCompany(), editApp.getDescription(),
                            editApp.getSalary(), editApp.getLocation(), editApp.getNotes(),
                            editApp.getWebsite(), editApp.getPhase());

        appDao.save(originalApp);
        return "redirect:/home/" + username;

    }


//TODO: CHANGE OUT TEMP STYLING FOR STYLE SHEET
}

