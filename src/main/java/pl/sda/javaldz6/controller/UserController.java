package pl.sda.javaldz6.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import pl.sda.javaldz6.model.User;
import pl.sda.javaldz6.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private Logger log = Logger.getLogger(UserController.class);

    @Autowired
    @Qualifier("userServiceAnnotationSingleton")
    private UserService userServiceAnnotationSingleton;

    @GetMapping(value = "/users")
    public String getUsers(Model model){
        model.addAttribute("users", userServiceAnnotationSingleton.getAll());
        return "users";
    }

    @GetMapping(value = "/users1")
    public ModelAndView getUsersModel(Model model){
        ModelAndView mv = new  ModelAndView();
        mv.addObject("users", userServiceAnnotationSingleton.getAll());
        mv.setViewName("users");
        return mv;
    }

    @GetMapping(value = "/addUser")
    public String showUserForm(Model model){
        model.addAttribute("user", new User());
        return "user";
    }

    @GetMapping(value = "/searchUsers")
    public String getUsersWithFilter(Model model, @RequestParam(required = false, defaultValue = "") String name){
        List<User> users = userServiceAnnotationSingleton.getAll();
        if(!name.isEmpty()){
            users = users.stream().filter(u -> u.getName().toLowerCase().startsWith(name.toLowerCase())).collect(Collectors.<User>toList());
        }
        model.addAttribute("users", users);
        return "usersWithFilter";
    }

    @PostMapping(value = "saveUser")
    public String submit(@Valid @ModelAttribute User user, BindingResult result, @ModelAttribute("index")String index) { ;
        log.info("received index: "+index);
        if (result.hasErrors()) {
            return "user";
        }
        log.info("Add index value: "+index);
        log.info("Add index value: "+user);
        if(index == null || index.isEmpty()){
            userServiceAnnotationSingleton.addUser(user.getName(), user.getAge());
        } else {
            User userFound = userServiceAnnotationSingleton.getUserById(new Integer(index));
            boolean isRemoved = userServiceAnnotationSingleton.getAll().remove(userFound);
            log.info("Is user removed? "+ isRemoved);
            userServiceAnnotationSingleton.addUser(user.getName(), user.getAge());
        }

        return "redirect:/searchUsers";
    }

    @GetMapping(value = "/user/{id}/update")
    public String showUserFormForUpdate(@PathVariable("id") int id, Model model) {

        log.info("showUpdateUserForm() : "+ id);
        User user = userServiceAnnotationSingleton.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("index", id);
        return "user";

    }

}
