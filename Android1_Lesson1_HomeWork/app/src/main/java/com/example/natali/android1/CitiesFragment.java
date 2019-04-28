package com.example.natali.android1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CitiesFragment extends ListFragment {

    private static final String KEY_POSITION = "Position";
    private static final String KEY_NAME = "CityName";
    private int position;
    private String name;

    // При создании фрагмента укажем его макет
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cities, container, false);
    }

    // Активити создана, можно к ней обращаться. Выполним начальные действия
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Для того, чтобы показать список, надо задействовать адаптер.
        // Такая конструкция работает для списков - например, ListActivity.
        // Создаем из ресурсов список городов
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.Cities, android.R.layout.simple_list_item_activated_1);

        setListAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_POSITION, position);
        outState.putString(KEY_NAME, name);
    }

    // Обработка выбора позиции
    @Override
    public void onListItemClick(ListView l, View view, int position, long id) {
        TextView cityNameView = (TextView) view;
        this.position = position;
        this.name = cityNameView.getText().toString();
        showWeather(position, name);
    }

    private void showWeather(int position, String name) {
        WeatherFragment detail = WeatherFragment.create(position, name);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, detail);  // замена фрагмента
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }
}
