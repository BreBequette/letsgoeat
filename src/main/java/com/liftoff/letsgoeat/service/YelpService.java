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
import java.util.HashMap;
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
                .asJson();

        JSONObject responseBodyJSONObj = response.getBody().getObject();
        JSONArray businesses = (JSONArray) responseBodyJSONObj.get("businesses");

        //Store in a map - don't know if I need this
        //String jsonString = responseBodyJSONObj.toString(1);
        //Map<String, Object> mapObj = new Gson().fromJson(jsonString, new TypeToken<HashMap<String, Object>>() {}.getType());

        return businesses;
    }


}
