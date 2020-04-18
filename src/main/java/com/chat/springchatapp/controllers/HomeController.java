package com.chat.springchatapp.controllers;

import com.chat.springchatapp.models.LoginUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class HomeController {
    
    @Autowired
    public ApplicationContext context;

    @GetMapping(value="/")
    public String home(Model model) {
        model.addAttribute("model", new LoginUser());
        return "index";
    }
    
    @PostMapping(value="/")
    public RedirectView postMethodName(@ModelAttribute LoginUser user, Model model) {
       LoginUser userBean = (LoginUser) context.getBean("loginUser");

        userBean.setId(user.id);
        userBean.setPasswd(user.passwd);

        RedirectView redirectView = new RedirectView("/connect", true);
        redirectView.addStaticAttribute("loginUser", user);
        return redirectView;
    }

    
}