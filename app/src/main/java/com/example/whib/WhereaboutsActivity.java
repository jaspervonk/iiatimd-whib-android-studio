package com.example.whib;

import android.content.Intent;
import android.os.Bundle;
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

        // Start a thread that makes the RecyclerViewAdapter with database data
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        new Thread(new GetWhereaboutsTask(db, recyclerView)).start();

        // Make Whereabouts data
        Whereabout[] whereaboutsArray = new Whereabout[5]; //0..x-1;
        whereaboutsArray[0] = new Whereabout(1,"Attack on titan", 4,"Eren just killed titan, Levi came to the rescue!", "Gogoanime", "5", "Series");
        whereaboutsArray[1] = new Whereabout(2,"Dolfje", 1, "He just ate a chicken from one of the other people and they got mad", "", "124", "Book");
        whereaboutsArray[2] = new Whereabout(3,"Kakegurui", 2, "They just finished playing with a finger guillotine", "Netflix", "2", "Series");
        whereaboutsArray[3] = new Whereabout(4,"Hunger Games", 2,"Eric just got killed, 4 people remaining","Netflix", "1:45:16", "Movie");
        whereaboutsArray[4] = new Whereabout(5,"Hunger Games", 3,"Looks interesting", "Netflix", "", "Movie");

        // Start a thread that adds the reminders to the database from the remindersArray
//        new Thread(new InsertWhereaboutsTask(db, whereaboutsArray)).start();
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
