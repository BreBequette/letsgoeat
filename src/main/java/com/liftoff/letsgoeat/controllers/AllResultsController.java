package com.liftoff.letsgoeat.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liftoff.letsgoeat.models.Favorite;
import com.liftoff.letsgoeat.models.YelpSearch;
import com.liftoff.letsgoeat.models.data.FavoriteRepository;
import com.liftoff.letsgoeat.models.data.UserRepository;
import com.liftoff.letsgoeat.models.dto.UserFavoriteDTO;
import com.liftoff.letsgoeat.service.YelpService;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.HttpResponse;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import com.mashape.unirest.http.JsonNode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Controller
//@RequestMapping("all-results")
public class AllResultsController {

    @Autowired
    private YelpService yelpService;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("all-results")
    public String showAllResults( Model model, @ModelAttribute @Valid YelpSearch search, Errors errors) throws UnirestException {


        if(errors.hasErrors()) {
            model.addAttribute("title", "Home");
            model.addAttribute("errorMsg", "Bad data!");
            return "index";
        }

        //add results to page
        model.addAttribute("title", "All Results");
        model.addAttribute("results", yelpService.getMatchingBusinesses(search));
        model.addAttribute("totalMatches", yelpService.getMatchingBusinesses(search).length());

        return "all-results";
    }

    //postmapping that processes add to favorites button
    //need to use user session and restaurant ID
    @RequestMapping(value="all-results", method={RequestMethod.POST})
    public String addToFavorites(@ModelAttribute @Valid UserFavoriteDTO userFavorite, HttpSession session,
                                 HttpServletRequest request, Favorite favorite, Model model, @RequestBody String restaurantId){

        //get the id of the selected favorite
        //add it to the user_favorites DB
        favoriteRepository.save(favorite);
        return "my-favorites";
    }

    @GetMapping("view-details/{id}")
    public String displayRestaurantDetails(@PathVariable String id, Model model, @ModelAttribute @Valid Errors errors) throws UnirestException{

        return "view-details";
    }

}
