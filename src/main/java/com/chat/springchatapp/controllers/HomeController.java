package com.chat.springchatapp.controllers;

import com.chat.springchatapp.config.AppConfig;
import com.chat.springchatapp.models.LoginUser;
import com.chat.springchatapp.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class HomeController {
    
    @GetMapping(value="/")
    public String home(Model model) {
        model.addAttribute("model", new LoginUser());
        return "index";
    }
    
    @PostMapping(value="/")
    public String postMethodName(@ModelAttribute LoginUser user, Model model) {
        
        // RedirectView redirectView = new RedirectView("", true);
        // redirectView.addStaticAttribute("loginUser", user);

        UserService.appConfigs.put(user.id, new AppConfig());
        UserService.appConfigs.get(user.id).getLoginUser().setId(user.id);
        UserService.appConfigs.get(user.id).getLoginUser().setPasswd(user.passwd);

        return "redirect:/connect/"+user.id;
    }

    
}