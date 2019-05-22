package com.example.natali.android1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TemperatureFragment extends Fragment {

    private static final String KEY_POSITION = "Position";
    private static final String KEY_NAME = "CityName";

    private String[] temperatures;


    public static TemperatureFragment create(String name) {
        TemperatureFragment temperatureFragment = new TemperatureFragment();
        Bundle args = new Bundle();
        args.putString(KEY_NAME, name);
        temperatureFragment.setArguments(args);
        return temperatureFragment;
    }

    public int getPosition() {
        return getArguments().getInt(KEY_POSITION);
    }

    public String getName() {
        return getArguments().getString(KEY_NAME);
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

        CardTemperaturesBuilder builder = new CardTemperaturesBuilder(getResources());
        RecyclerView recyclerView = layout.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        AdapterTemperatures adapterTemperatures = new AdapterTemperatures(builder.build());
        recyclerView.setAdapter(adapterTemperatures);
        return layout;
    }
}
