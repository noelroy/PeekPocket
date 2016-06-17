package com.example.noelroy.peekpocket;

/**
 * Created by Noel Roy on 02-11-2015.
 */
public class Details {
    public String id ;
    public String name ;
    public String date ;
    public Float amount;
    public String type ;

    public Details(String id, String name,String date,Float amount,String type) {
        this.name = name;
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.type = type;
    }
    public Details(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
