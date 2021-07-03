package com.example.whib;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;

public class AddReminderActivity extends AppCompatActivity implements View.OnClickListener, NumberPicker.OnValueChangeListener {
    int Hours = 0;
    int Minutes = 0;
    String time = "";
    boolean repeat = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        // Set actionbar title
        TextView actionbarTitle = findViewById(R.id.actionbarTitle);
        actionbarTitle.setText("Add Reminder");

        // Set OnClickListener
        ImageView toPreviousScreenButton = findViewById(R.id.actionbarBackButton);
        toPreviousScreenButton.setOnClickListener(this);

        Button addReminderButton = findViewById(R.id.addReminderButton);
        addReminderButton.setOnClickListener(this);

        // Set numberPickers (set time)
        NumberPicker numberPickerHours = findViewById(R.id.numberPickerHours);
        numberPickerHours.setMinValue(0);
        numberPickerHours.setMaxValue(23);
        numberPickerHours.setOnValueChangedListener(this);

        NumberPicker numberPickerMinutes = findViewById(R.id.numberPickerMinutes);
        numberPickerMinutes.setMinValue(0);
        numberPickerMinutes.setMaxValue(59);
        numberPickerMinutes.setOnValueChangedListener(this);

        // Set Calendar
        CalendarView calendarView = findViewById(R.id.reminderCalendarView);


    }

    public void onClick(View v) {
        // Check what button is pressed; Change activity accordingly
        switch(v.getId()) {
            case R.id.actionbarBackButton:
                onBackPressed();
                break;
            case R.id.addReminderButton:

                String title = "";
                String description = "";
                String date = "";

                EditText addReminderFormTitleInput = findViewById(R.id.addReminderFormTitleInput);
                EditText addReminderFormDescriptionInput = findViewById(R.id.addReminderFormDescriptionInput);
                title = addReminderFormTitleInput.getText().toString();
                description = addReminderFormDescriptionInput.getText().toString();

                //date
                CalendarView calendarView = findViewById(R.id.reminderCalendarView);
                long selectedDate = calendarView.getDate();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy");
                date = formatter.format(selectedDate);

                //checkbox
                CheckBox checkBox = findViewById(R.id.reminderCheckBox);
                if(checkBox.isChecked()) {
                    repeat = true;                }

                if(TextUtils.isEmpty(title) || TextUtils.isEmpty(description) || TextUtils.isEmpty(date) || TextUtils.isEmpty(time)){
                    Toast.makeText(getBaseContext(),  "Please fill in all required fields", Toast.LENGTH_LONG).show();
                } else {
                    Reminder newReminder = new Reminder(title, description, date, time, repeat);

                    AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                    new Thread(new InsertReminderTask(db, newReminder)).start();

                    Toast.makeText(getBaseContext(),  "Reminder added!", Toast.LENGTH_LONG).show();

                    Intent toRemindersScreenIntent = new Intent(this, RemindersActivity.class);
                    startActivity(toRemindersScreenIntent);
                }
                break;
        }
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        String idString = picker.getResources().getResourceEntryName(picker.getId());

        if(idString.equals("numberPickerHours")) {
            Hours = newVal;
        }else if(idString.equals("numberPickerMinutes")) {
            Minutes = newVal;
        }

        if(Hours < 10 && Minutes < 10) {
            time = "0" + Hours + ":0" + Minutes;
        }else if(Minutes < 10) {
            time = Hours + ":0" + Minutes;
        }else if(Hours < 10) {
            time = "0" + Hours + ":" + Minutes;
        }else {
            time = Hours + ":" + Minutes;
        }

    }
}
