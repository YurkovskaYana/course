package com.example.core.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseTeamDetailInfo {
    @SerializedName("teams")
    private List<TeamDetailInfoItem> teams;

    public void setTeamDetailInfoItem(List<TeamDetailInfoItem> teams){
        this.teams = teams;
    }

    public List<TeamDetailInfoItem> getTeamDetailInfoItem(){
        return teams;
    }

    @Override
    public String toString(){
        return
                "ResponseTeamDetailInfo {" +
                        "teams = '" + teams + '\'' +
                        "}";
    }
}
