package com.iatjt.whib;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.logging.Filter;

public class WhereaboutsActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private GetWhereaboutsTask whereaboutsTask;
    private WhereaboutAdapter recyclerViewAdapter;

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

        actionbarTitle.setOnClickListener(this);

        // Set WhereaboutsRecyclerView
        recyclerView = findViewById(R.id.whereaboutsRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();

        // Start a thread that makes the RecyclerViewAdapter with database data
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        whereaboutsTask = new GetWhereaboutsTask(db, recyclerView);
        new Thread(whereaboutsTask).start();

        // Set Spinner
        Spinner whereaboutsSpinner = findViewById(R.id.whereaboutsSpinner);
        ArrayAdapter<CharSequence> whereaboutsSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.whereabouts_spinner, android.R.layout.simple_spinner_item);
        whereaboutsSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        whereaboutsSpinner.setAdapter(whereaboutsSpinnerAdapter);
        whereaboutsSpinner.setOnItemSelectedListener(this);

        // Make Whereabouts data
//        Whereabout[] whereaboutsArray = new Whereabout[5]; //0..x-1;
//        whereaboutsArray[0] = new Whereabout("Attack on titan", 4,"Eren just killed titan, Levi came to the rescue!", "Gogoanime", "5", "Series");
//        whereaboutsArray[1] = new Whereabout("Dolfje", 1, "He just ate a chicken from one of the other people and they got mad", "", "124", "Book");
//        whereaboutsArray[2] = new Whereabout("Kakegurui", 2, "They just finished playing with a finger guillotine", "Netflix", "2", "Series");
//        whereaboutsArray[3] = new Whereabout("Hunger Games", 2,"Eric just got killed, 4 people remaining","Netflix", "1:45:16", "Movie");
//        whereaboutsArray[4] = new Whereabout("Hunger Games", 3,"Looks interesting", "Netflix", "0", "Movie");

        // Start a thread that adds the reminders to the database from the remindersArray
//        new Thread(new InsertWhereaboutsTask(db, whereaboutsArray)).start();
    }

    public void onClick(View v) {
        // Check what button is pressed; Change activity accordingly
        switch(v.getId()) {
            case R.id.actionbarBackButton:
                Intent toHomeScreenIntent = new Intent(this , HomeActivity.class);
                startActivity(toHomeScreenIntent);
                break;
            case R.id.actionbarAddButton:
                Intent toAddWhereaboutScreenIntent = new Intent(this, AddWhereaboutActivity.class);
                startActivity(toAddWhereaboutScreenIntent);
                break;
            case R.id.actionbarTitle:
                whereaboutsTask.recyclerViewAdapter.getFilter().filter("Movie");
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d("onItemSelected", parent.getItemAtPosition(position).toString());
        whereaboutsTask.recyclerViewAdapter.getFilter().filter(parent.getItemAtPosition(position).toString());

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
