package com.liftoff.letsgoeat.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class YelpService {

    @Value("${api.key}")
    private String apiKey;


    private String cuisine;
    private String zip;
    private String distance;
    private String price;

    public JSONArray getMatchingBusinesses(String cuisine, String zip, String distance, String price) throws UnirestException {
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
        //String jsonString = responseBodyJSONObj.toString(1);
        //Map<String, Object> mapObj = new Gson().fromJson(jsonString, new TypeToken<HashMap<String, Object>>() {}.getType());

        return businesses;
    }


}
