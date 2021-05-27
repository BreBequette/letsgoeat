package com.liftoff.letsgoeat.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class YelpSearch extends AbstractEntity{

    @NotNull
    private String cuisine;

    @NotBlank(message="This field is required")
    @NotNull(message="This field is required")
    @Size(min=5,max=5,message="Zip code must be 5 digits")
    private String zip;

    @NotNull
    private String distance;

    @NotNull
    private String price;


    public YelpSearch (String cuisine, String zip, String distance, String price){
        this.cuisine = cuisine;
        this.zip = zip;
        this.distance = distance;
        this.price = price;
    }

    public YelpSearch(){}



    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
