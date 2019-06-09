package com.example.natali.android1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class CitiesFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_cities, container, false);

        Toolbar toolbar = layout.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        initRecyclerView(layout);
        return layout;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_cities, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                searchCity();
                return true;
            case R.id.menu_info:
                showInfoAboutApp();
                return true;
            case R.id.menu_exit:
                exit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showWeather(int position, String name) {
        WeatherFragment detail = WeatherFragment.create(position, name);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, detail);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }

    private void searchCity() {
        Toast.makeText(getActivity(), "Поиск города", Toast.LENGTH_SHORT).show();
    }

    private void showInfoAboutApp() {
        Toast.makeText(getActivity(), "Информация о приложении", Toast.LENGTH_SHORT).show();
    }

    private void exit() {
        Toast.makeText(getActivity(), "Выход", Toast.LENGTH_SHORT).show();
    }

    public void initRecyclerView(View layout) {
        DatabaseHelper db = DatabaseHelper.getInstance(getActivity().getApplicationContext());
        db.addCity("Moscow");
        db.addCity("Belgorod");
        ArrayList<City> cities = db.query();
        RecyclerView recyclerView = layout.findViewById(R.id.recycler_view_cities);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        AdapterCities adapterCities = new AdapterCities(cities);
        recyclerView.setAdapter(adapterCities);

        adapterCities.setOnItemClickListener(new AdapterCities.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String[] cities = getResources().getStringArray(R.array.Cities);
                showWeather(position, cities[position]);
            }
        });
    }
}
