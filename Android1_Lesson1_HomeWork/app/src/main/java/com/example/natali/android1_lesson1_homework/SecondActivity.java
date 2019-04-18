package com.example.natali.android1_lesson1_homework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_second);
        String text = getIntent().getExtras().getString("city");
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(text);
    }
}
