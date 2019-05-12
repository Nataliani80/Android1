package com.example.natali.android1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TemperatureFragment extends Fragment {

    private static final String KEY_POSITION = "Position";
    private String[] temperatures;

    public static TemperatureFragment create(int position) {
        TemperatureFragment temperatureFragment = new TemperatureFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        temperatureFragment.setArguments(args);
        return temperatureFragment;
    }

    public int getPosition() {
        return getArguments().getInt(KEY_POSITION);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_temperatures, container, false);

        temperatures = getResources().getStringArray(R.array.TemperaturesByDays);

        ListView listView = layout.findViewById(R.id.listTemperatures);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.item_temperatures, temperatures);
        listView.setAdapter(arrayAdapter);



//        String[] days = getResources().getStringArray(R.array.Days);
//        String[] temperatureByDays = getResources().getStringArray(R.array.TemperaturesByDays);
//        String symbol = getResources().getString(R.string.degrees);
//
//        TextView daysView = layout.findViewById(R.id.);
//        TextView temperatureByDayView = layout.findViewById(R.id.Temperature_by_day);
//
//        daysView.setText(days[getPosition()]);
//        temperatureByDayView.setText(temperatureByDays[getPosition()]);


        return layout;
    }
}
