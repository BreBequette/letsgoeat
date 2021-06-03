package com.liftoff.letsgoeat.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity{

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @NotNull
    private String username;

    @NotNull
    private String pwHash;

    @ManyToMany
    private final List<Favorite> favorites = new ArrayList<>();

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    public void addFavorite(Favorite favorite) {
        this.favorites.add(favorite);
    }

    public String getUsername() {
        return username;
    }

    public boolean isMatchingPassword(String password){
        return encoder.matches(password, pwHash);
    }

    public List<Favorite> getFavorite() {
        return favorites;
    }

}
