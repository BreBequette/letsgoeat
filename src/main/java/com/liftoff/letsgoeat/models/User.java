package com.liftoff.letsgoeat.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity{

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @NotNull
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotNull
    @Size(min = 3, max = 50, message = "Password must be between 3 and 50 characters")
    private String pwHash;

    @OneToMany
    private List<Favorite> favorites = new ArrayList<>();

    public User(){}

    public User(String username, String password){
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    public User(String username,List<Favorite> favorites){
        this.username = username;
        this.favorites = favorites;
    }

    public String getUsername() {
        return username;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public boolean isMatchingPassword(String password){
        return encoder.matches(password, pwHash);
    }
}
