package com.example.natali.android1;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherFragment extends Fragment {
    private static final String KEY_POSITION = "Position";
    private static final String KEY_NAME = "CityName";
    Sensor sensorProximity;
    Sensor sensorPressure;
    TextView textProximity;
    TextView textPressure;
    SensorManager sensorManager;

    SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor == sensorProximity) {
                showSensorTemperature(event);
            } else if (event.sensor == sensorPressure) {
                showSensorHumidity(event);
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

        String[] weather = getResources().getStringArray(R.array.Weather);
        String[] temperature = getResources().getStringArray(R.array.Temperatures);
        String data = dataNow();

        TextView cityNameView = layout.findViewById(R.id.textViewCity);
        TextView weatherView = layout.findViewById(R.id.textViewWeather);
        TextView temperatureView = layout.findViewById(R.id.textViewTemperature);
        TextView textViewData = (TextView) layout.findViewById(R.id.textViewData);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        textProximity = layout.findViewById(R.id.sensor_temperature);
        sensorProximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorManager.registerListener(listener, sensorProximity, SensorManager.SENSOR_DELAY_NORMAL);

        textPressure = layout.findViewById(R.id.sensor_humidity);
        sensorPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        sensorManager.registerListener(listener, sensorPressure, SensorManager.SENSOR_DELAY_NORMAL);

        cityNameView.setText(getName());
        weatherView.setText(weather[getPosition()]);
        temperatureView.setText(temperature[getPosition()]);
        textViewData.setText(data);

        Button temperatureButton = layout.findViewById(R.id.check_temperature_button);
        temperatureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTemperature(getName());
            }
        });

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

    private void showSensorTemperature(SensorEvent event) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Proximity Sensor value = ").append(event.values[0]).append("\n");
        textProximity.setText(stringBuilder);
    }

    private void showSensorHumidity(SensorEvent event) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Pressure Sensor value = ").append(event.values[0]).append("\n");
        textPressure.setText(stringBuilder);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listener, sensorProximity);
        sensorManager.unregisterListener(listener, sensorPressure);
    }
}
