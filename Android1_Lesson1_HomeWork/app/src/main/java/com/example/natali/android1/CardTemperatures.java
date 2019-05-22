package com.example.natali.android1;

public class CardTemperatures {

    private String dayOfWeek;
    private String temperatures;

    public CardTemperatures(String dayOfWeek, String temperatures) {
        this.dayOfWeek=dayOfWeek;
        this.temperatures=temperatures;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getTemperatures() {
        return temperatures;
    }

}
