package com.example.tripmanagement.model;

import android.text.Editable;

public class Trip {
    private int tripId;
    private String tripName;
    private String destination;
    private String date;
    private int riskAssessment;
    private String description;
    private String necessaryStuff;
    private String weatherCondition;
    private String recommendedDestination;

    public Trip(int tripId, String tripName, String destination, String date, int riskAssessment, String description, String necessaryStuff, String weatherCondition, String recommendedDestination) {
        this.tripId = tripId;
        this.tripName = tripName;
        this.destination = destination;
        this.date = date;
        this.riskAssessment = riskAssessment;
        this.description = description;
        this.necessaryStuff = necessaryStuff;
        this.weatherCondition = weatherCondition;
        this.recommendedDestination = recommendedDestination;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRiskAssessment() {
        return riskAssessment;
    }

    public void setRiskAssessment(int riskAssessment) {
        this.riskAssessment = riskAssessment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNecessaryStuff() {
        return necessaryStuff;
    }

    public void setNecessaryStuff(String necessaryStuff) {
        this.necessaryStuff = necessaryStuff;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public String getRecommendedDestination() {
        return recommendedDestination;
    }

    public void setRecommendedDestination(String recommendedDestination) {
        this.recommendedDestination = recommendedDestination;
    }
}
