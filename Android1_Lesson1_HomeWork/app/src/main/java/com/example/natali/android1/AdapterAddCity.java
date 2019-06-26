package com.example.natali.android1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterAddCity extends RecyclerView.Adapter<AdapterAddCity.ViewHolderAddCity> {

    private List<City> addCities;
    private AdapterCities.OnItemClickListener itemClickListener;


    public class ViewHolderAddCity extends RecyclerView.ViewHolder {

        private TextView addCity;

        public ViewHolderAddCity(View itemView) {
            super(itemView);
            addCity = itemView.findViewById(R.id.textViewAddCity);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }

        void bind(City city) {
            this.addCity.setText(city.getName());
        }
    }

    AdapterAddCity(List<City> addCities) {
        this.addCities = addCities;
    }

    @NonNull
    @Override
    public ViewHolderAddCity onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_add_city, viewGroup, false);
        AdapterAddCity.ViewHolderAddCity viewHolder = new AdapterAddCity.ViewHolderAddCity(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAddCity viewHolderAddCity, int position) {
        City item = addCities.get(position);
        viewHolderAddCity.bind(item);
    }

    @Override
    public int getItemCount() {
        return addCities.size();
    }
}
