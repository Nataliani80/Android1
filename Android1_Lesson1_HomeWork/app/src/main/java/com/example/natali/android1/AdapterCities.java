package com.example.natali.android1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterCities extends RecyclerView.Adapter<AdapterCities.ViewHolderCity> {

    private List<CardCities> cities;
    private OnItemClickListener itemClickListener;

    public class ViewHolderCity extends RecyclerView.ViewHolder {

        public TextView city;

        public ViewHolderCity(View rootView) {
            super(rootView);
            city = rootView.findViewById(R.id.textViewCity);

            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public AdapterCities(List<CardCities> cities) {
        this.cities = cities;
    }

    @Override
    public AdapterCities.ViewHolderCity onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cities, parent, false);
        AdapterCities.ViewHolderCity viewHolder = new AdapterCities.ViewHolderCity(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderCity viewHolder, int position) {
        CardCities item = cities.get(position);
        viewHolder.city.setText(item.getCity());
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }
}
