package com.iatjt.whib;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set OnClickListener
        Button toWhereaboutsScreenButton = findViewById(R.id.toWhereaboutsSceenButton);
        toWhereaboutsScreenButton.setOnClickListener(this);

        Button toRemindersScreenButton = findViewById(R.id.toRemindersScreenButton);
        toRemindersScreenButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        // Check what button is pressed; Change activity accordingly
        switch(v.getId()) {
            case R.id.toWhereaboutsSceenButton:
                Intent toWhereaboutsScreenIntent = new Intent(this, WhereaboutsActivity.class);
                startActivity(toWhereaboutsScreenIntent);
                break;
            case R.id.toRemindersScreenButton:
                Intent toRemindersScreenIntent = new Intent(this, RemindersActivity.class);
                startActivity(toRemindersScreenIntent);
                break;
        }
    }
}
