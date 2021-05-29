package com.liftoff.letsgoeat.controllers;

import com.liftoff.letsgoeat.models.data.FavoriteData;
import com.liftoff.letsgoeat.models.data.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("my-favorites")
public class FavoritesController {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @GetMapping
    public String showFavorites(Model model){
        model.addAttribute("title", "My Favorites");
        //model.addAttribute("favorites", FavoriteData.getAll());
        model.addAttribute("favorites", favoriteRepository.findAll());

        return "my-favorites";
    }

    //example below: create a method to process button click
//    @PostMapping("create")
//    public String processCreateEventForm(@RequestParam String eventName, @RequestParam String eventDescription) {
//        events.add(new Event(eventName, eventDescription));
//        return "redirect:";
//    }


}
