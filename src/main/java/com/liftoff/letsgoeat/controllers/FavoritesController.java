package com.liftoff.letsgoeat.controllers;

import com.liftoff.letsgoeat.models.User;
import com.liftoff.letsgoeat.models.data.FavoriteData;
import com.liftoff.letsgoeat.models.data.FavoriteRepository;
import com.liftoff.letsgoeat.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("my-favorites")
public class FavoritesController {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationController authenticationController;

    //@GetMapping("/{user}")
    @GetMapping
    public String showFavorites(Model model, HttpSession session, HttpServletRequest request){
        model.addAttribute("title", "My Favorites");

        Integer userId = (Integer) session.getAttribute("user");
        Optional<User> result = userRepository.findById(userId);
        User user = result.get();

        //model.addAttribute("favorites", FavoriteData.getAll());
        model.addAttribute("favorites", favoriteRepository.findAll());
        model.addAttribute("userId", user.getUsername());

        return "my-favorites";
    }



    //example below: create a method to process button click
//    @PostMapping("create")
//    public String processCreateEventForm(@RequestParam String eventName, @RequestParam String eventDescription) {
//        events.add(new Event(eventName, eventDescription));
//        return "redirect:";
//    }


}
