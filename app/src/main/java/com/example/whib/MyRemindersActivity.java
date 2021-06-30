package com.example.whib;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyRemindersActivity extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_my_reminders);

        recyclerView = findViewById(R.id.remindersRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();

        String[] reminders = new String[200];
        for(int i = 0; i < 200; i++) {
            reminders[i] = "Reminder" + i;
        }

        recyclerViewAdapter = new ReminderAdapter(reminders);
        recyclerView.setAdapter(recyclerViewAdapter);

        ImageButton toHomeScreenButton = findViewById(R.id.backButton);
        toHomeScreenButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.backButton:
                onBackPressed();
                break;
        }
    }

}
