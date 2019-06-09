package com.example.natali.android1;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterTemperatures extends RecyclerView.Adapter<AdapterTemperatures.ViewHolder> {

    private List<WeatherForecast> data;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView forecastDate;
        private TextView forecastTemperature;
        private TextView forecastDescription;
        private TextView forecastProbabilityOfPrecipitation;
        private TextView forecastWind;



        public ViewHolder(View rootView) {
            super(rootView);
            forecastDate = rootView.findViewById(R.id.textViewTemperatureByDays);
            forecastTemperature = rootView.findViewById(R.id.temperatures);
            forecastDescription = rootView.findViewById(R.id.forecast_description);
            forecastProbabilityOfPrecipitation = rootView.findViewById(R.id.forecast_pop);
            forecastWind = rootView.findViewById(R.id.forecast_wind);
        }
    }

    public AdapterTemperatures(List<WeatherForecast> data) {
        this.data = data;
    }

    @Override
    public AdapterTemperatures.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_temperatures, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        WeatherForecast item = data.get(position);
        viewHolder.forecastDate.setText(item.getForecastDate());
        viewHolder.forecastTemperature.setText(String.valueOf(item.getForecastTemperature()));
        viewHolder.forecastDescription.setText(item.getForecastDescription());
        Resources resources = viewHolder.forecastWind.getResources();
        viewHolder.forecastProbabilityOfPrecipitation.setText(
                resources.getString(R.string.probability_of_precipitation,
                        item.getForecastProbabilityOfPrecipitation()));
        viewHolder.forecastWind.setText(resources.getString(R.string.wind,
                item.getForecastWindDirection(), item.getForecastSpeedWind()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
