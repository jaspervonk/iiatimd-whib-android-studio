package com.example.whib;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WhereaboutsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whereabouts);

        // Set actionbar title
        TextView actionbarTitle = findViewById(R.id.actionbarTitle);
        actionbarTitle.setText("Whereabouts");

        // Set OnClickListener
        ImageView toPreviousScreenButton = findViewById(R.id.actionbarBackButton);
        toPreviousScreenButton.setOnClickListener(this);

        ImageView toAddWhereaboutScreenButton = findViewById(R.id.actionbarAddButton);
        toAddWhereaboutScreenButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        // Check what button is pressed; Change activity accordingly
        switch(v.getId()) {
            case R.id.actionbarBackButton:
                onBackPressed();
                break;
            case R.id.actionbarAddButton:
                Intent toAddWhereaboutScreenIntent = new Intent(this, TestActivity.class);
                startActivity(toAddWhereaboutScreenIntent);
                break;
        }
    }
}
