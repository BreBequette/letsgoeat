package com.liftoff.letsgoeat.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Favorite extends AbstractEntity {

    @NotNull
    private String name;

    @ManyToMany(mappedBy="favorites")
    private List<User> users = new ArrayList<>();

    public Favorite(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
