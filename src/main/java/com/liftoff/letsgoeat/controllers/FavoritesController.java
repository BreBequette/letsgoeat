package com.liftoff.letsgoeat.controllers;

import com.liftoff.letsgoeat.models.data.FavoriteData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("my-favorites")
public class FavoritesController {

    @GetMapping
    public String showFavorites(Model model){
        model.addAttribute("title", "Favorites");
        model.addAttribute("favorites", FavoriteData.getAll());

        return "my-favorites";
    }

    //example below: create a method to process button click
//    @PostMapping("create")
//    public String processCreateEventForm(@RequestParam String eventName, @RequestParam String eventDescription) {
//        events.add(new Event(eventName, eventDescription));
//        return "redirect:";
//    }


}
