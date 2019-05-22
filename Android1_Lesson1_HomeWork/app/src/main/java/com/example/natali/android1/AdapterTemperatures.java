package com.example.natali.android1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterTemperatures extends RecyclerView.Adapter<AdapterTemperatures.ViewHolder> {

    private List<CardTemperatures> data;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView daysOfWeek;
        public TextView temperatures;

        public ViewHolder(View rootView) {
            super(rootView);
            daysOfWeek = rootView.findViewById(R.id.textViewTemperatureByDays);
            temperatures = rootView.findViewById(R.id.temperatures);
        }
    }

    public AdapterTemperatures(List<CardTemperatures> data) {
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
        CardTemperatures item = data.get(position);
        viewHolder.daysOfWeek.setText(item.getDayOfWeek());
        viewHolder.temperatures.setText(item.getTemperatures());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
