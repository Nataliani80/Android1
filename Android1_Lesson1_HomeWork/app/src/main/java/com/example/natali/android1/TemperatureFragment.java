package com.example.natali.android1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class TemperatureFragment extends Fragment {

    private static final String KEY_NAME = "CityName";
    private static final String KEY_ID = "CityID";
    private RecyclerView recyclerView;


    public static TemperatureFragment create(String name, long id) {
        TemperatureFragment temperatureFragment = new TemperatureFragment();
        Bundle args = new Bundle();
        args.putString(KEY_NAME, name);
        args.putLong(KEY_ID, id);
        temperatureFragment.setArguments(args);
        return temperatureFragment;
    }

    private String getName() {
        return getArguments().getString(KEY_NAME);
    }

    private long getCityId() {
        return getArguments().getLong(KEY_ID);
    }

    public String dataNow() {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy");
        return formatForDateNow.format(dateNow);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_temperatures, container, false);

        String data = dataNow();
        TextView cityNameView = layout.findViewById(R.id.textViewCity1);
        TextView textViewData = layout.findViewById(R.id.textViewData);
        cityNameView.setText(getName());
        textViewData.setText(data);

        recyclerView = layout.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        loadDetailWeatherData(getName(), getCityId());
        requestWeather(getCityId());
        return layout;
    }

    private void loadDetailWeatherData(String cityName, long id) {
        DatabaseHelper db = DatabaseHelper.getInstance(getActivity().getApplicationContext());
        configureRecyclerView(db.queryWeather(id));
        OkHttpRequester okHttpRequester = new OkHttpRequester(new OkHttpRequester.OnResponseCompleted() {
            @Override
            public void onCompleted(String content) {
                showWeather(content);
            }
        });
        okHttpRequester.load("https://api.weatherbit.io/v2.0/forecast/daily?city=" + cityName +
                ",RU&&lang=" + Locale.getDefault().getLanguage()
                + "&key=a2755975c69d45a0843ea950c79402cd");
    }

    private void showWeather(String jsonResponseString) {
        try {
            if (jsonResponseString != null) {
                JSONObject jsonResponse = new JSONObject(jsonResponseString);
                ArrayList<WeatherForecast> forecastList = new ArrayList<>();
                JSONArray array = jsonResponse.getJSONArray("data");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonWeatherData = array.getJSONObject(i);
                    String day = jsonWeatherData.getString("valid_date");
                    int temperature = (int) jsonWeatherData.getDouble("temp");
                    JSONObject weather = jsonWeatherData.getJSONObject("weather");
                    String description = weather.getString("description");
                    int probabilityOfPrecipitation = jsonWeatherData.getInt("pop");
                    double windSpeed = jsonWeatherData.getDouble("wind_spd");
                    String windDirection = jsonWeatherData.getString("wind_cdir_full");

                    WeatherForecast forecast = new WeatherForecast(day, temperature, description,
                            probabilityOfPrecipitation, windSpeed, windDirection);
                    forecastList.add(forecast);

                    DatabaseHelper db = DatabaseHelper.getInstance(getActivity().getApplicationContext());
                    db.addWeather(getCityId(), day, temperature, description, probabilityOfPrecipitation, windSpeed, windDirection);
                }
                configureRecyclerView(forecastList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void configureRecyclerView(ArrayList<WeatherForecast> forecastList) {
        AdapterTemperatures adapterTemperatures = new AdapterTemperatures(forecastList);
        recyclerView.setAdapter(adapterTemperatures);
    }

    private ArrayList<WeatherForecast> requestWeather(long id) {
        DatabaseHelper db = DatabaseHelper.getInstance(getActivity().getApplicationContext());
        return db.queryWeather(id);
    }

}
