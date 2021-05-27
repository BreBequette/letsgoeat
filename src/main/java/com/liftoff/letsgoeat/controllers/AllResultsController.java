package com.liftoff.letsgoeat.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liftoff.letsgoeat.models.YelpSearch;
import com.liftoff.letsgoeat.models.data.FavoriteRepository;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import com.mashape.unirest.http.JsonNode;

import javax.validation.Valid;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("all-results")
public class AllResultsController {

    @Autowired
    private YelpService yelpService;

    @GetMapping
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
}
