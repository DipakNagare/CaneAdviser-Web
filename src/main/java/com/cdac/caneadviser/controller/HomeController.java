package com.cdac.caneadviser.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String base()
    {
        return "index";
    }

    @GetMapping("/Adminlogin")
    public String Adminlogin()
    {
        return "Adminlogin";
    }

    @GetMapping("/adminDashboard")
    public String adminDashboard()
    {
        return "adminDashboard";
    }
    
   @GetMapping("/manageTechnology")
    public String manageTechnology()
    {
        return "manageTechnology";
    }

    @GetMapping("/viewExpert")
    public String viewExpert()
    {
        return "viewExpert";
    }

    @GetMapping("/manageExpert")
    public String manageExpert()
    {
        return "manageExpert";
    }

    @GetMapping("/viewQueries")
    public String viewQueries()
    {
        return "viewQueries";
    }

     @GetMapping("/assignQueries")
    public String assignQueries()
    {
        return "assignQueries";
    }
    
}