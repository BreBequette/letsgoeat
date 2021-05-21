package com.liftoff.letsgoeat.models.data;

import com.liftoff.letsgoeat.models.Favorite;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FavoriteData {

    private static final Map<Integer, Favorite> favorites = new HashMap<>();

    public static Collection<Favorite> getAll(){
        return favorites.values();
    }

    public static void add(Favorite favorite){
        favorites.put(favorite.getId(), favorite);
    }

    public static Favorite getById(Integer id){
        return favorites.get(id);
    }

    public static void remove(Integer id){
        if (favorites.containsKey(id)){
            favorites.remove(id);
        }
    }
}
