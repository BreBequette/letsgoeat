package com.liftoff.letsgoeat.controllers;

import com.liftoff.letsgoeat.models.YelpSearch;
import com.liftoff.letsgoeat.service.YelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {


//    @GetMapping()
//    public String displayForm(Model model){
//        model.addAttribute("title", "Home");
//        //model.addAttribute("search", new YelpSearch());
//        return "index";
//    }
@GetMapping()
public ModelAndView displayForm(Model model){
    return new ModelAndView("index", "search", new YelpSearch());
}

}
