package com.example.whib;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        // Make Reminders data
        Reminder[] reminders = new Reminder[2];
        reminders[0] = new Reminder(0,"New episode aot", "Season 4 new episode", "Friday");
        reminders[1] = new Reminder(1,"New episode demon slayer", "Season 2 new episode", "Thursday");

        // Make recyclerViewAdapter using the Reminders Data
        recyclerViewAdapter = new ReminderAdapter(reminders);
        recyclerView.setAdapter(recyclerViewAdapter);

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
