package com.example.whib;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set OnClickListener
        Button toMyWhereaboutsScreenButton = findViewById(R.id.toMyWhereaboutsScreenButton);
        toMyWhereaboutsScreenButton.setOnClickListener(this);

        Button toMyRemindersScreenButton = findViewById(R.id.toMyRemindersScreenButton);
        toMyRemindersScreenButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        // Check what button is pressed; Change activity accordingly
        switch(v.getId()) {
            case R.id.toMyWhereaboutsScreenButton:
                Intent toMyWhereaboutsScreenIntent = new Intent(this, TestActivity.class);
                startActivity(toMyWhereaboutsScreenIntent);
                break;
            case R.id.toMyRemindersScreenButton:
                Intent toMyRemindersScreenIntent = new Intent(this, TestActivity.class);
                startActivity(toMyRemindersScreenIntent);
                break;
        }
    }
}
