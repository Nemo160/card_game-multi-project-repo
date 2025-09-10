package com.GOTCHA.CardGame_Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.*;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home(){
        return "index.html";
    }

    @RequestMapping("/upload")
    public String upload(){
        String viewName = getViewName();

        return viewName;
    }

    public String getViewName(){
        return "upload.html";
    }
}
