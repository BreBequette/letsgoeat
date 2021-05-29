package com.liftoff.letsgoeat.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liftoff.letsgoeat.models.YelpSearch;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class YelpService {

    @Value("${api.key}")
    private String apiKey;

    public JSONArray getMatchingBusinesses(YelpSearch search) throws UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> response = Unirest.get("https://api.yelp.com/v3/businesses/search")
                .header("Authorization", "Bearer " + apiKey)
                .header("API", "apiKey")
                .queryString("location", search.getZip())
                .queryString("radius", search.getDistance())
                .queryString("categories", search.getCuisine())
                .queryString("price", search.getPrice())
                .queryString("limit", 50)
                .asJson();

        //get json array
        JSONObject responseBodyJSONObj = response.getBody().getObject();
        JSONArray businesses = (JSONArray) responseBodyJSONObj.get("businesses");
        //JSONArray businesses = responseBodyJSONObj.getJSONArray("businesses");


        //convert json array into regular list of objects
        List<Object> businessesArr = new ArrayList<>();
        for (int i=0; i<businesses.length(); i++){
            businessesArr.add(businesses.get(i));
        }


        return businesses;
    }


}
