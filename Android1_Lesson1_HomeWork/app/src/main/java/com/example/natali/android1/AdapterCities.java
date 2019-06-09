package com.example.natali.android1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterCities extends RecyclerView.Adapter<AdapterCities.ViewHolderCity> {

    private List<City> cities;
    private OnItemClickListener itemClickListener;

    public class ViewHolderCity extends RecyclerView.ViewHolder {

        public TextView tvCity;

        public ViewHolderCity(View rootView) {
            super(rootView);
            tvCity = rootView.findViewById(R.id.textViewCity);

            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }

        public void bind(City city) {
            this.tvCity.setText(city.getName());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public AdapterCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public AdapterCities.ViewHolderCity onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cities, parent, false);
        return new AdapterCities.ViewHolderCity(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderCity viewHolder, int position) {
        City item = cities.get(position);
        viewHolder.bind(item);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }
}
