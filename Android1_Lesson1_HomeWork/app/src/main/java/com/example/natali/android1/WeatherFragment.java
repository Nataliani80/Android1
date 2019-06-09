package com.example.natali.android1;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WeatherFragment extends Fragment {
    private static final String KEY_POSITION = "Position";
    private static final String KEY_NAME = "CityName";
    private Sensor sensorProximity;
    private Sensor sensorPressure;
    private TextView textProximity;
    private TextView textPressure;
    private TemperatureView currentTemperature;
    private TextView currentWind;
    private TextView currentHumidity;
    private TextView currentFeelLikeWeather;
    private SensorManager sensorManager;

    SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor == sensorProximity) {
                showSensorProximity(event);
            } else if (event.sensor == sensorPressure) {
                showSensorPressure(event);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    public static WeatherFragment create(int position, String name) {
        WeatherFragment weatherFragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        args.putString(KEY_NAME, name);
        weatherFragment.setArguments(args);
        return weatherFragment;
    }

    public String dataNow() {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy");
        return formatForDateNow.format(dateNow);
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
        String[] temperature = getResources().getStringArray(R.array.Temperatures);
        String[] weather = getResources().getStringArray(R.array.Weather);
        String data = dataNow();

        TextView cityNameView = layout.findViewById(R.id.textViewCity);
        TextView weatherView = layout.findViewById(R.id.textViewWeather);
        currentTemperature = layout.findViewById(R.id.current_temperature);
        TextView textViewData = (TextView) layout.findViewById(R.id.textViewData);
        currentWind = layout.findViewById(R.id.current_wind);
        currentHumidity = layout.findViewById(R.id.current_humidity);
        currentFeelLikeWeather = layout.findViewById(R.id.current_feelLike_weather);

        cityNameView.setText(getName());
        weatherView.setText(weather[getPosition()]);
        currentTemperature.setColor(getResources().getColor(R.color.colorPrimaryDark));
        textViewData.setText(data);

        createSensors(layout);
        createButton(layout);
        loadCurrentWeatherData(getName());
        return layout;
    }

    private void showTemperature(String name) {

        TemperatureFragment detailTemperature = TemperatureFragment.create(name);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, detailTemperature);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }

    private void createSensors(View layout) {
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        textProximity = layout.findViewById(R.id.sensor_proximity);
        sensorProximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        textPressure = layout.findViewById(R.id.sensor_pressure);
        sensorPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
    }

    private void showSensorProximity(SensorEvent event) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Proximity Sensor value = ").append(event.values[0]);
        textProximity.setText(stringBuilder);
    }

    private void showSensorPressure(SensorEvent event) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Pressure Sensor value = ").append(event.values[0]);
        textPressure.setText(stringBuilder);
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(listener, sensorProximity, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(listener, sensorPressure, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listener, sensorProximity);
        sensorManager.unregisterListener(listener, sensorPressure);
    }

    private void createButton(View layout) {
        Button temperatureButton = layout.findViewById(R.id.check_temperature_button);
        temperatureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTemperature(getName());

                Intent intent = new Intent(getActivity(), ServiceWeather.class);
                intent.putExtra(ServiceWeather.CITY_NAME, getName());
                getActivity().startService(intent);
            }
        });
    }

    private void loadCurrentWeatherData(String cityName) {
        OkHttpRequester okHttpRequester = new OkHttpRequester(new OkHttpRequester.OnResponseCompleted() {
            @Override
            public void onCompleted(String content) {
                showWeather(content);
                Log.e("TEMP", content);
            }
        });
        okHttpRequester.load("https://api.weatherbit.io/v2.0/current?city=" + cityName +
                ",RU&&lang=" + Locale.getDefault().getLanguage() + "&key=a2755975c69d45a0843ea950c79402cd");
    }

    private void showWeather(String jsonResponseString) {
        try {
            JSONObject jsonResponse = new JSONObject(jsonResponseString);
            JSONArray array = jsonResponse.getJSONArray("data");
            JSONObject jsonWeatherData = array.getJSONObject(0);
            int temperature = (int)jsonWeatherData.getDouble("temp");
            currentTemperature.setTemperature(String.valueOf(temperature));
            double windSpeed = jsonWeatherData.getDouble("wind_spd");
            String windDirection = jsonWeatherData.getString("wind_cdir_full");
            currentWind.setText(getResources().getString(R.string.wind, windDirection, windSpeed));
            int relativeHumidity = jsonWeatherData.getInt("rh");
            currentHumidity.setText(getResources().getString(R.string.humidity, relativeHumidity));
            int apparentTemperature = (int)jsonWeatherData.getDouble("app_temp");
            currentFeelLikeWeather.setText(getResources().getString(R.string.feel_like, apparentTemperature));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
