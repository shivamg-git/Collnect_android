package com.example.vidit.collnect;

/**
 * Created by vidit on 06-10-2015.
 */
public class Events {
    private String title;
    private String description;
    private String created_at;
    private String day;
    private String hub_name;
    private String venue;
    private int audienceScore;
    public Events(){}
    public Events(String title,String description,String created_at,String hub_name,int audienceScore,String venue){
        this.title = title;
        this.description = description;
        this.created_at = created_at;
        this.hub_name = hub_name;
        this.audienceScore = audienceScore;
        this.venue = venue;
    }

    @Override
    public String toString() {
        return  title + "," + description + "," + created_at + "," + venue ;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getAudienceScore() {
        return audienceScore;
    }

    public void setAudienceScore(int audienceScore) {
        this.audienceScore = audienceScore;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHub_name() {
        return hub_name;
    }

    public void setHub_name(String hub_name) {
        this.hub_name = hub_name;
    }
}
