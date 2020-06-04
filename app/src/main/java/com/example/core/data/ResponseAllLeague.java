package com.example.core.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseAllLeague{

	@SerializedName("countrys")
	private List<LeagueItem> leagueItems;

	public void setLeague(List<LeagueItem> league){
		this.leagueItems = league;
	}

	public List<LeagueItem> getLeague(){
		return leagueItems;
	}

	@Override
 	public String toString(){
		return 
			"ResponseAllLeague{" + 
			"countrys = '" + leagueItems + '\'' +
			"}";
		}
}