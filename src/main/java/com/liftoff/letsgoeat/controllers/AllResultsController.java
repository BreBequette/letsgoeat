package com.liftoff.letsgoeat.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
    public String showAllResults( Model model, String cuisine, String distance, String zip, String price) throws UnirestException {


        //with params hardcoded in
//        HttpResponse<JsonNode> response = Unirest.get("https://api.yelp.com/v3/businesses/search?location=63139&radius=8046&categories=sushi&price=2")
//                .header("Authorization", "Bearer " + apiKey)
//                .header("API", "apiKey").asJson();

        //add results to page
        model.addAttribute("title", "All Results");
        model.addAttribute("results", yelpService.getMatchingBusinesses(cuisine,zip,distance,price));
        model.addAttribute("totalMatches", yelpService.getMatchingBusinesses(cuisine,zip,distance,price).length());


        return "all-results";
    }
}
