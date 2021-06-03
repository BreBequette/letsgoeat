package com.liftoff.letsgoeat.models.dto;

import com.liftoff.letsgoeat.models.Favorite;
import com.liftoff.letsgoeat.models.User;

import javax.validation.constraints.NotNull;

public class UserFavoriteDTO {

    @NotNull
    private User user;

    @NotNull
    private Favorite favorite;

    public UserFavoriteDTO() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Favorite getFavorite() {
        return favorite;
    }

    public void setFavorite(Favorite favorite) {
        this.favorite = favorite;
    }
}
