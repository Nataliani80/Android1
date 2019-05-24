package com.example.natali.android1;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class CitiesFragment extends Fragment {

    private static final String KEY_POSITION = "Position";
    private static final String KEY_NAME = "CityName";
    private int position;
    private String name;
    private Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    // При создании фрагмента укажем его макет
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_cities, container, false);
        CardCitiesBuilder builder = new CardCitiesBuilder(getResources());
        RecyclerView recyclerView = layout.findViewById(R.id.recycler_view_cities);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        AdapterCities adapterCities = new AdapterCities(builder.build());
        recyclerView.setAdapter(adapterCities);

        final CitiesFragment citiesFragment = this;
        adapterCities.SetOnItemClickListener(new AdapterCities.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                citiesFragment.position = position;
                String[] cities = getResources().getStringArray(R.array.Cities);
                citiesFragment.name = cities[position];
                showWeather(position, name);
            }
        });

        toolbar = layout.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);


        return layout;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_POSITION, position);
        outState.putString(KEY_NAME, name);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_cities, menu);

        MenuItem search = menu.findItem(R.id.menu_search);
        SearchView searchText = (SearchView) search.getActionView();
        searchText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
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
        ft.replace(R.id.fragment_container, detail);  // замена фрагмента
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
}
