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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="home")
public class UserController {

    @Autowired
    private AppDao appDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value="/{username}", method = RequestMethod.GET)
    public String userHomePage(@PathVariable String username, Model model) {

            List<List> sortedApps = new ArrayList<>();


            List<App> userApps = userDao.findByUsername(username).getApps();

            List<App> interviewApps = new ArrayList<>();
            List<App> rejectedApps = new ArrayList<>();
            List<App> workingApps = new ArrayList<>();
            List<App> submittedApps = new ArrayList<>();
            List<App> otherApps = new ArrayList<>();

            for (App app : userApps) {
                if (app.getPhase().equals("Interviewing")) {
                    interviewApps.add(app);
                }
                if (app.getPhase().equals("Application Submitted")) {
                    submittedApps.add(app);
                }
                if (app.getPhase().equals("Application Rejected")) {
                    rejectedApps.add(app);
                }
                if (app.getPhase().equals("Working on Application")) {
                    workingApps.add(app);
                }
                if (app.getPhase().equals("Other")) {
                    otherApps.add(app);
                }
            }

            sortedApps.add(interviewApps);
            sortedApps.add(workingApps);
            sortedApps.add(submittedApps);
            sortedApps.add(otherApps);
            sortedApps.add(rejectedApps);


            //first lambda expression to check sortedApps for empty lists
            sortedApps.removeIf((List appList) -> appList.isEmpty());



        model.addAttribute("title", "Home | " + username);
            //this line used to display just the logged in user's apps
            model.addAttribute("appLists", sortedApps);
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
                            editApp.getWebsite(), editApp.getPhase(),
                            editApp.getDate());

        appDao.save(originalApp);
        return "redirect:/home/" + username;

    }

    @RequestMapping(value = "info/{username}", method = RequestMethod.GET)
    public String userInfo(@PathVariable String username, Model model) {

        User currentUser = userDao.findByUsername(username);
        model.addAttribute(currentUser);

        return "User/userInfo";
    }

    @RequestMapping(value= "editInfo/{username}", method = RequestMethod.GET)
    public String userEditInfo(@PathVariable String username, Model model) {
        User currentUser = userDao.findByUsername(username);
        model.addAttribute(currentUser);

        return "User/editInfo";
    }

    @RequestMapping(value="editInfo/{username}", method = RequestMethod.POST)
    public String processEditInfo(@PathVariable String username, @ModelAttribute @Valid User editUser,
            Errors errors,
            Model model) {
        User currentUser = userDao.findByUsername(username);
        if (errors.hasErrors()) {
            model.addAttribute(editUser);
            return "User/editInfo";
        }

        if (!editUser.getPassword().equals(currentUser.getPassword())) {
            model.addAttribute(editUser);
            model.addAttribute("verifyError", "Password does not match records");
            return "User/editInfo";
        }

        currentUser.setFirstName(editUser.getFirstName());
        currentUser.setLastName(editUser.getLastName());
        currentUser.setEmail(editUser.getEmail());
        currentUser.setAddress(editUser.getAddress());
        userDao.save(currentUser);
        return "User/userInfo";
    }

    @RequestMapping(value="commuteTime/{username}/{appId}", method = RequestMethod.GET)
    public String commuteTime(@PathVariable String username, @PathVariable int appId, Model model) {

        User currentUser = userDao.findByUsername(username);
        App currentApp = appDao.findOne(appId);

        model.addAttribute("username", currentUser.getUsername());
        model.addAttribute("place", currentApp.getCompany());
        model.addAttribute("appAddress", currentApp.getLocation());
        model.addAttribute("userAddress",currentUser.getAddress());

        return "User/commuteTime";

    }

}

