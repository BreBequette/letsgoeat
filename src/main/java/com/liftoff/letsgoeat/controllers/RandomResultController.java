package com.liftoff.letsgoeat.controllers;

import com.liftoff.letsgoeat.models.YelpSearch;
import com.liftoff.letsgoeat.models.data.FavoriteRepository;
import com.liftoff.letsgoeat.service.YelpService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("random-result")
public class RandomResultController {

    @Autowired
    private YelpService yelpService;

    @GetMapping
    public String showRandomResult(Model model, @ModelAttribute @Valid YelpSearch search, Errors errors) throws UnirestException {

        if(errors.hasErrors()) {
            model.addAttribute("title", "Home");
            model.addAttribute("errorMsg", "Bad data!");
            return "index";
        }

        //get all matching results
        //List<Object> allResults = yelpService.getMatchingBusinesses(search);
        JSONArray allResults = yelpService.getMatchingBusinesses(search);


        //random number generator from 0 to allResults.length
        int randomNum = (int)(Math.random()*allResults.length());

        model.addAttribute("title", "Random Result");
        model.addAttribute("result", allResults.get(randomNum));
        model.addAttribute("totalMatches", yelpService.getMatchingBusinesses(search).length());

    return "random-result";
    }
}
