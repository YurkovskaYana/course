package com.example.core.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseEachTeamForLeg {
    @SerializedName("teams")
    private List<EachTeamItem> teams;

    public void setEachTeams(List<EachTeamItem> teams){
        this.teams = teams;
    }

    public List<EachTeamItem> getEachTeams(){
        return teams;
    }

    @Override
    public String toString(){
        return
                "ResponseEachTeamForLeg {" +
                        "teams = '" + teams + '\'' +
                        "}";
    }
}
