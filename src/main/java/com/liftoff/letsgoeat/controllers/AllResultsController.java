package com.liftoff.letsgoeat.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.HttpResponse;
//import org.apache.tomcat.util.json.JSONParser;
//import org.apache.tomcat.util.json.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import com.mashape.unirest.http.JsonNode;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("all-results")
public class AllResultsController {

    @Value("${api.key}")
    private String apiKey;


    @GetMapping
    public String showAllResults(Model model, Errors errors,
                                 String cuisine, String distance, String zip, String price) throws UnirestException {

        //make the API call based on user selections
        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> response = Unirest.get("https://api.yelp.com/v3/businesses/search")
                .header("Authorization", "Bearer " + apiKey)
                .header("API", "apiKey")
                .queryString("location", zip)
                .queryString("radius", distance)
                .queryString("categories", cuisine)
                .queryString("price", price)
                .asJson();

        JSONObject responseBodyJSONObj = response.getBody().getObject();
        JSONArray businesses = (JSONArray) responseBodyJSONObj.get("businesses");

        //Store in a map - don't know if I need this
        String jsonString = responseBodyJSONObj.toString(1);
        Map<String, Object> mapObj = new Gson().fromJson(jsonString, new TypeToken<HashMap<String, Object>>() {}.getType());

        //get number of matches
        int numMatches = businesses.length();


        //add results to page
        model.addAttribute("title", "All Results");
        model.addAttribute("results", businesses);
        model.addAttribute("totalMatches", numMatches);

        return "all-results";
    }
}
