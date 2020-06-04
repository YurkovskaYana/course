package com.example.core.data;

import com.google.gson.annotations.SerializedName;

public class SportItem {

    @SerializedName("idSport")
    private String idSport;
    @SerializedName("strSport")

    private String strSport;
    @SerializedName("strFormat")

    private String strFormat;
    @SerializedName("strSportThumb")

    private String strSportThumb;
    @SerializedName("strSportThumbGreen")

    private String strSportThumbGreen;
    @SerializedName("strSportDescription")

    private String strSportDescription;

    public String getIdSport() {
        return idSport;
    }

    public void setIdSport(String idSport) {
        this.idSport = idSport;
    }

    public String getStrSport() {
        return strSport;
    }

    public void setStrSport(String strSport) {
        this.strSport = strSport;
    }

    public String getStrFormat() {
        return strFormat;
    }

    public void setStrFormat(String strFormat) {
        this.strFormat = strFormat;
    }

    public String getStrSportThumb() {
        return strSportThumb;
    }

    public void setStrSportThumb(String strSportThumb) {
        this.strSportThumb = strSportThumb;
    }

    public String getStrSportThumbGreen() {
        return strSportThumbGreen;
    }

    public void setStrSportThumbGreen(String strSportThumbGreen) {
        this.strSportThumbGreen = strSportThumbGreen;
    }

    public String getStrSportDescription() {
        return strSportDescription;
    }

    public void setStrSportDescription(String strSportDescription) {
        this.strSportDescription = strSportDescription;
    }

    @Override
    public String toString()
    {
        return "Sport {idSport = "+idSport+", strFormat = "+strFormat+", strSport = "+strSport+", strSportThumb = "+strSportThumb+", strSportThumbGreen = "+strSportThumbGreen+", strSportDescription = "+strSportDescription+"}";
    }
}
