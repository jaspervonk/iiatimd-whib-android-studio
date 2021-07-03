package com.example.whib;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WhereaboutDetailActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whereabouts_detail);

        // Set actionbar title
        TextView actionbarTitle = findViewById(R.id.actionbarTitle);
        actionbarTitle.setText(R.string.detail_whereabout_title);

        // Set OnClickListener
        ImageView toPreviousScreenButton = findViewById(R.id.actionbarBackButton);
        toPreviousScreenButton.setOnClickListener(this);

        ImageView actionbarEditButton = findViewById(R.id.actionbarEditButton);
        actionbarEditButton.setOnClickListener(this);

        // Set Details
        if(getIntent().hasExtra("uuid")){
            int uuid = getIntent().getIntExtra("uuid", 0);
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            new Thread(new GetWhereaboutTask(db, uuid, findViewById(android.R.id.content))).start();
        }


    }

    public void onClick(View v) {
        // Check what button is pressed; Change activity accordingly
        switch(v.getId()) {
            case R.id.actionbarBackButton:
                Intent toWhereaboutsScreenIntent = new Intent(this, WhereaboutsActivity.class);
                startActivity(toWhereaboutsScreenIntent);
                break;
            case R.id.actionbarEditButton:
                Toast.makeText(getBaseContext(), "Editing Whereabout", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
