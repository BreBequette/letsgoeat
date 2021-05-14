package com.liftoff.letsgoeat.controllers;

import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.HttpResponse;
//import org.apache.tomcat.util.json.JSONParser;
//import org.apache.tomcat.util.json.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String showAllResults(Model model) throws UnirestException {

        //make the API call based on user selections
        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> response = Unirest.get("https://api.yelp.com/v3/businesses/search?location=63139&radius=8046&categories=sushi&price=2")
                .header("Authorization", "Bearer " + apiKey)
                .header("API", "apiKey").asJson();

        //Convert JSON response into an arraylist of Objects
        JSONObject responseBodyJSONObj = response.getBody().getObject();
        JSONArray resultsJSONArr = responseBodyJSONObj.getJSONArray("businesses");
        ArrayList<Object> resultsArr = new ArrayList<>();

        if (resultsJSONArr != null) {
            for (int i=0; i<resultsJSONArr.length(); i++) {
                resultsArr.add(resultsJSONArr.get(i));
            }
        }

        //try to store json in map instead



        //pull out specific results needed
        String name="bre";
        String cuisine;
        String priceRange;
        String address;
        String phone;


        //add results to page
        model.addAttribute("title", "All Results");
        model.addAttribute("results", resultsArr);

        //trying to access specific pieces of info
        model.addAttribute("name", name);
        return "all-results";
    }
}
