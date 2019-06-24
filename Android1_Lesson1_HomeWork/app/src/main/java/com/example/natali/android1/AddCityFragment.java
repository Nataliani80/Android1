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
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class AddCityFragment extends Fragment {

    private EditText addCity;
    private RecyclerView recyclerView;


    public static AddCityFragment create() {
        return new AddCityFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_add_city, container, false);
        addCity = layout.findViewById(R.id.add_city);

        List<City> addCities = new ArrayList<>();
        City city = new City();
        city.setId(1);
        city.setName("Белгород");
        addCities.add(city);
        city = new City();
        city.setId(2);
        city.setName("Москва");
        addCities.add(city);
        city = new City();
        city.setId(3);
        city.setName("Воронеж");
        addCities.add(city);


        initRecyclerView(layout, addCities);
        createButton(layout);
        return layout;
    }

    private void createButton(View layout) {
        Button add = layout.findViewById(R.id.add_city_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCity();
                getFragmentManager().popBackStack();
            }
        });
    }

    private void addCity() {
        String city = addCity.getText().toString();
        DatabaseHelper db = DatabaseHelper.getInstance(getActivity().getApplicationContext());
        db.addCity(city);
    }

    public void initRecyclerView(View layout, List<City> addCities) {
        RecyclerView recyclerView = layout.findViewById(R.id.recycler_view_add_city);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        AdapterAddCity adapterAddCity = new AdapterAddCity(addCities);
        recyclerView.setAdapter(adapterAddCity);

    }
}
