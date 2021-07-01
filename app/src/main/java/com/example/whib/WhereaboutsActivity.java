package com.example.whib;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WhereaboutsActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter recyclerViewAdapter;

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

        // Set WhereaboutsRecyclerView
        recyclerView = findViewById(R.id.whereaboutsRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();

        // Make Whereabouts data
        String[] whereabouts = new String[200]; //0..199;

        for(int i=0; i < 200; i++){
            whereabouts[i] = "Whereabout " + i;
        }

        // Make recyclerViewAdapter using the whereabouts Data
        recyclerViewAdapter = new WhereaboutAdapter(whereabouts);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public void onClick(View v) {
        // Check what button is pressed; Change activity accordingly
        switch(v.getId()) {
            case R.id.actionbarBackButton:
                onBackPressed();
                break;
            case R.id.actionbarAddButton:
                Intent toAddWhereaboutScreenIntent = new Intent(this, AddWhereaboutActivity.class);
                startActivity(toAddWhereaboutScreenIntent);
                break;
        }
    }
}
