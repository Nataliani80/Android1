package com.example.natali.android1;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

public class CardCitiesBuilder {

    private List<CardCities> cardCitiessList;
    private Resources resources;

    public CardCitiesBuilder(Resources resources) {
        cardCitiessList = new ArrayList<>(16);
        this.resources = resources;
    }

    public List<CardCities> build() {
        String[] city = resources.getStringArray(R.array.Cities);
        for (int i = 0; i < city.length; i++) {
            cardCitiessList.add(new CardCities(city[i]));
        }
        return cardCitiessList;
    }

}
