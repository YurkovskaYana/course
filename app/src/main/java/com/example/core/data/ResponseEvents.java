package com.example.core.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseEvents {
    @SerializedName("results")
    private List<EventItem> eventItems;

    public void setEvent(List<EventItem> eventItems){
        this.eventItems = eventItems;
    }

    public List<EventItem> getEvent(){
        return eventItems;
    }

    @Override
    public String toString(){
        return
                "ResponseEvents{" +
                        "results = '" + eventItems + '\'' +
                        "}";
    }
}
