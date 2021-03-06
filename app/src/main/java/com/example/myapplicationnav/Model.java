package com.example.myapplicationnav;

import android.widget.TextView;

public class Model {


    String Country, CountryCode,Slug,NewConfirmed,TotalConfirmed,TotalDeaths,NewDeaths,NewRecovered,TotalRecovered,Date;

    public Model() {
    }

    public Model(String country, String countryCode, String slug, String newConfirmed, String totalConfirmed, String totalDeaths, String newDeaths, String newRecovered, String totalRecovered, String date) {
        Country = country;
        CountryCode = countryCode;
        Slug = slug;
        NewConfirmed = newConfirmed;
        TotalConfirmed = totalConfirmed;
        TotalDeaths = totalDeaths;
        NewDeaths = newDeaths;
        NewRecovered = newRecovered;
        TotalRecovered = totalRecovered;
        Date = date;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        this.Country = country;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getSlug() {
        return Slug;
    }

    public void setSlug(String slug) {
        Slug = slug;
    }

    public String getNewConfirmed() {
        return NewConfirmed;
    }

    public void setNewConfirmed(String newConfirmed) {
        NewConfirmed = newConfirmed;
    }

    public String getTotalConfirmed() {
        return TotalConfirmed;
    }

    public void setTotalConfirmed(String totalConfirmed) {
        TotalConfirmed = totalConfirmed;
    }

    public String getTotalDeaths() {
        return TotalDeaths;
    }

    public void setTotalDeaths(String totalDeaths) {
        TotalDeaths = totalDeaths;
    }

    public String getNewDeaths() {
        return NewDeaths;
    }

    public void setNewDeaths(String newDeaths) {
        NewDeaths = newDeaths;
    }

    public String getNewRecovered() {
        return NewRecovered;
    }

    public void setNewRecovered(String newRecovered) {
        NewRecovered = newRecovered;
    }

    public String getTotalRecovered() {
        return TotalRecovered;
    }

    public void setTotalRecovered(String totalRecovered) {
        TotalRecovered = totalRecovered;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
