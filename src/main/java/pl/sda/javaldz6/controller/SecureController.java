package pl.sda.javaldz6.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.javaldz6.model.User;

@Controller
public class SecureController {

    @GetMapping(value = "/admin")
    public String showUserForm(Model model){
        return "adminPage";
    }
}
