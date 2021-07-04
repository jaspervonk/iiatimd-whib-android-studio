package com.example.whib;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReminderDetailActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_reminder_detail);

        // Set actionbar title
        TextView actionbarTitle = findViewById(R.id.actionbarTitle);
        actionbarTitle.setText("Reminders");

        // Set OnClickListener
        ImageView toPreviousScreenButton = findViewById(R.id.actionbarBackButton);
        toPreviousScreenButton.setOnClickListener(this);

        ImageView toAddReminderScreenButton = findViewById(R.id.actionbarDeleteButton);
        toAddReminderScreenButton.setOnClickListener(this);

        // Set Details
        if(getIntent().hasExtra("uuid")){
            int uuid = getIntent().getIntExtra("uuid", 0);
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            new Thread(new GetReminderTask(db, uuid, findViewById(android.R.id.content))).start();
        }
    }

    public void onClick(View v) {
        int uuid = getIntent().getIntExtra("uuid", 0);;
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        Intent toRemindersScreenIntent = new Intent(this, RemindersActivity.class);

        // Check what button is pressed; Change activity accordingly
        switch(v.getId()) {
            case R.id.actionbarBackButton:
                Intent toHomeScreenIntent = new Intent(this, HomeActivity.class);
                startActivity(toHomeScreenIntent);
                break;
            case R.id.actionbarDeleteButton:
                new Thread(new DeleteReminderTask(db, uuid)).start();
                Toast.makeText(getBaseContext(), "Reminder removed!", Toast.LENGTH_LONG).show();
                startActivity(toRemindersScreenIntent);
                break;
        }
    }
}
