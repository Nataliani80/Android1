package com.example.natali.android1_lesson1_homework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SecondActivity extends AppCompatActivity {

    public String dataNow() {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy");
        return formatForDateNow.format(dateNow);
    }

    @Override
    protected void onCreate (Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_second);
        String text = getIntent().getExtras().getString("city");
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(text);
        String data = dataNow();
        TextView textViewData = (TextView) findViewById(R.id.textViewData);
        textViewData.setText(data);
    }
}
