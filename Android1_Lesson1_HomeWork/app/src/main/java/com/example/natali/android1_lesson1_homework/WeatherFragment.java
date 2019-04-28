package com.example.natali.android1_lesson1_homework;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WeatherFragment extends Fragment {
    private static final String KEY_POSITION = "Position";
    private static final String KEY_NAME = "CityName";
    private int position;
    private String name;

    public static WeatherFragment create(int position, String name) {
        WeatherFragment weatherFragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        args.putString(KEY_NAME, name);
        weatherFragment.setArguments(args);
        return weatherFragment;
    }

    public int getPosition() {
        return getArguments().getInt(KEY_POSITION);
    }

    public String getName() {
        return getArguments().getString(KEY_NAME);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_weather, container, false);

        String[] weather = getResources().getStringArray(R.array.Weather);
        String[] temperature = getResources().getStringArray(R.array.Temperatures);

        TextView cityNameView = layout.findViewById(R.id.textViewCity);
        TextView weatherView = layout.findViewById(R.id.textViewWeather);
        TextView temperatureView = layout.findViewById(R.id.textViewTemperature);

        cityNameView.setText(getName());
        weatherView.setText(weather[getPosition()]);
        temperatureView.setText(temperature[getPosition()]);

        return layout;
    }
}
