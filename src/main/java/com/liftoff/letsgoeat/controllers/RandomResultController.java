package com.liftoff.letsgoeat.controllers;

import com.liftoff.letsgoeat.service.YelpService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("random-result")
public class RandomResultController {

    @Autowired
    private YelpService yelpService;

    @GetMapping
    public String showRandomResult(Model model, String cuisine, String distance, String zip, String price) throws UnirestException {

        //get all matching results
        JSONArray allResults = yelpService.getMatchingBusinesses(cuisine,zip,distance,price);

        //random number generator from 0 to allResults.length
        int randomNum = (int)(Math.random()*allResults.length());

        model.addAttribute("title", "Random Result");
        model.addAttribute("result", allResults.get(randomNum));

    return "random-result";
    }
}
