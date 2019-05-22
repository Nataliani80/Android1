package com.example.natali.android1;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

public class CardTemperaturesBuilder {

    private List<CardTemperatures> cardTemperaturesList;
    private Resources resources;

    public CardTemperaturesBuilder(Resources resources) {
        cardTemperaturesList = new ArrayList<>(7);
        this.resources = resources;
    }

    public List<CardTemperatures> build() {
        String[] days = resources.getStringArray(R.array.Days);
        String[] temperatures = resources.getStringArray(R.array.TemperaturesByDays);
        for (int i = 0; i < days.length; i++) {
            cardTemperaturesList.add(new CardTemperatures(days[i], temperatures[i]));
        }
        return cardTemperaturesList;
    }
}
