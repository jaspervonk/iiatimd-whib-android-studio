package com.example.whib;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class RemindersActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_reminders);

        // Set actionbar title
        TextView actionbarTitle = findViewById(R.id.actionbarTitle);
        actionbarTitle.setText("Reminders");

        // Set OnClickListener
        ImageView toPreviousScreenButton = findViewById(R.id.actionbarBackButton);
        toPreviousScreenButton.setOnClickListener(this);

        ImageView toAddReminderScreenButton = findViewById(R.id.actionbarAddButton);
        toAddReminderScreenButton.setOnClickListener(this);

        // Set ReminderRecyclerView
        recyclerView = findViewById(R.id.remindersRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();

        // Start a thread that makes the RecyclerViewAdapter with database data
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        new Thread(new GetRemindersTask(db, recyclerView)).start();

        // Make Reminders data
        Reminder[] remindersArray = new Reminder[2]; //0..x-1;
        remindersArray[0] = new Reminder("New episode aot", "Season 4 new episode", "21/10/2021", "21:00", true);
        remindersArray[1] = new Reminder("New episode demon slayer", "Season 2 new episode", "25/7/2021", "15:00", false);

        // Start a thread that adds the reminders to the database from the remindersArray
//        new Thread(new InsertRemindersTask(db, remindersArray)).start();
    }

    public void onClick(View v) {
        // Check what button is pressed; Change activity accordingly
        switch(v.getId()) {
            case R.id.actionbarBackButton:
                onBackPressed();
                break;
            case R.id.actionbarAddButton:
                Intent toAddReminderScreenIntent = new Intent(this, AddReminderActivity.class);
                startActivity(toAddReminderScreenIntent);
                break;
        }
    }
}
