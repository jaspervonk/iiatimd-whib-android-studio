package com.example.whib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Set OnClickListener
        Button toHomeScreenButton = findViewById(R.id.toHomeScreenButton);
        toHomeScreenButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        // Check what button is pressed; Change activity accordingly
        switch(v.getId()) {
            case R.id.toHomeScreenButton:
                Intent toHomeScreenIntent = new Intent(this, HomeActivity.class);
                startActivity(toHomeScreenIntent);
                break;
        }
    }
}
