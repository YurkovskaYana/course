package com.example.core.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseAllSports {

    @SerializedName("sports")
    private List<SportItem> sports;

    public void setSports(List<SportItem> sports){
        this.sports = sports;
    }

    public List<SportItem> getSports(){
        return sports;
    }

    @Override
    public String toString(){
        return "ResponseAllSports{ sports = " + sports + "}";
    }
}
