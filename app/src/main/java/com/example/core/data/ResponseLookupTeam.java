package com.example.core.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseLookupTeam{

	@SerializedName("teams")
	private List<TeamsItem> teams;

	public void setTeams(List<TeamsItem> teams){
		this.teams = teams;
	}

	public List<TeamsItem> getTeams(){
		return teams;
	}

	@Override
 	public String toString(){
		return 
			"ResponseLookupTeam{" + 
			"teams = '" + teams + '\'' + 
			"}";
		}
}