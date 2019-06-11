package com.example.natali.android1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AddCityFragment extends Fragment {

    private EditText addCity;

    public static AddCityFragment create() {
        return new AddCityFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_add_city, container, false);
        addCity = layout.findViewById(R.id.add_city);

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
}
