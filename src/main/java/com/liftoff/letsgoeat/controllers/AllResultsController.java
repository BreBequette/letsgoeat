package com.liftoff.letsgoeat.controllers;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import com.mashape.unirest.http.*;
import java.io.*;


@Controller
@RequestMapping("all-results")
public class AllResultsController {

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping
    public String showAllResults(Model model) throws UnirestException {

        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get("https://api.yelp.com/v3/businesses/search?location=63139&radius=8046&categories=sushi&price=2")
                .header("Authorization", "Bearer NFmlRdOTMa5OThvvXwRM-Eti-kc8nzSFhpU_y1eajhWETjPhZeLKaVOUunpMiJ3-Bccqqfa5rLgKjeFwa2iVAebG-v4oL8rhAo8otr8NXMOgKmTTr9Muu-olWRqPYHYx")
                .header("API", "NFmlRdOTMa5OThvvXwRM-Eti-kc8nzSFhpU_y1eajhWETjPhZeLKaVOUunpMiJ3-Bccqqfa5rLgKjeFwa2iVAebG-v4oL8rhAo8otr8NXMOgKmTTr9Muu-olWRqPYHYx")
                .asString();

        model.addAttribute("title", "All Matches");
        model.addAttribute("response", response.getBody());
        return "all-results";
    }
}
