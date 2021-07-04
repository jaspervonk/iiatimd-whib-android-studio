package com.example.whib;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddWhereaboutActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_whereabout);

        // Set actionbar title
        TextView actionbarTitle = findViewById(R.id.actionbarTitle);
        actionbarTitle.setText(R.string.add_whereabout_actionbar_title);

        // Set OnClickListener
        ImageView toPreviousScreenButton = findViewById(R.id.actionbarBackButton);
        toPreviousScreenButton.setOnClickListener(this);

        Button addWhereaboutButton = findViewById(R.id.addWhereaboutButton);
        addWhereaboutButton.setOnClickListener(this);

        // Set Spinner
        Spinner whereaboutSpinner = findViewById(R.id.addWhereaboutFormSpinner);
        ArrayAdapter<CharSequence> whereaboutSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.add_whereabout_spinner_default, android.R.layout.simple_spinner_item);
        whereaboutSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        whereaboutSpinner.setAdapter(whereaboutSpinnerAdapter);
        whereaboutSpinner.setOnItemSelectedListener(this);
    }

    public void onClick(View v) {
        // Check what button is pressed; Change activity accordingly
        switch(v.getId()) {
            case R.id.actionbarBackButton:
                onBackPressed();
                break;
            case R.id.addWhereaboutButton:
                String title = "";
                int part = 0;
                String progress = "";
                String source = "";
                String note = "";
                String kind = "";
                EditText addWhereaboutTitleInput = findViewById(R.id.addWhereaboutFormTitleInput);
                EditText addWhereaboutPartInput = findViewById(R.id.addWhereaboutFormPartInput);
                EditText addWhereaboutProgressInput = findViewById(R.id.addWhereaboutFormProgressInput);
                EditText addWhereaboutSourceInput = findViewById(R.id.addWhereaboutFormSourceInput);
                EditText addWhereaboutNoteInput = findViewById(R.id.addWhereaboutFormNoteInput);

                title = addWhereaboutTitleInput.getText().toString();
                if(!TextUtils.isEmpty(addWhereaboutPartInput.getText().toString())){
                    part = Integer.parseInt(addWhereaboutPartInput.getText().toString());
                }
                progress = addWhereaboutProgressInput.getText().toString();
                source = addWhereaboutSourceInput.getText().toString();
                note = addWhereaboutNoteInput.getText().toString();

                Spinner whereaboutSpinner = findViewById(R.id.addWhereaboutFormSpinner);
                kind = whereaboutSpinner.getSelectedItem().toString();

                if(TextUtils.isEmpty(title) || TextUtils.isEmpty(progress) || TextUtils.isEmpty(kind) || part == 0 ){
                    Toast.makeText(getBaseContext(), "Please fill in all required fields", Toast.LENGTH_LONG).show();
                }
                else{
                    Whereabout newWhereabout = new Whereabout(title, part, note, source, progress, kind);

                    AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                    new Thread(new InsertWhereaboutTask(db, newWhereabout)).start();

                    Toast.makeText(getBaseContext(), "Whereabout added!", Toast.LENGTH_LONG).show();

                    Intent toWhereaboutsScreenIntent = new Intent(this, WhereaboutsActivity.class);
                    startActivity(toWhereaboutsScreenIntent);
                }
        }
    }

    public void showAddWhereaboutForm(){
        TextView addWhereaboutTitle = findViewById(R.id.addWhereaboutFormTitle);
        addWhereaboutTitle.setVisibility(View.VISIBLE);
        EditText addWhereaboutTitleInput = findViewById(R.id.addWhereaboutFormTitleInput);
        addWhereaboutTitleInput.setVisibility(View.VISIBLE);

        TextView addWhereaboutPart = findViewById(R.id.addWhereaboutFormPart);
        addWhereaboutPart.setVisibility(View.VISIBLE);
        EditText addWhereaboutPartInput = findViewById(R.id.addWhereaboutFormPartInput);
        addWhereaboutPartInput.setVisibility(View.VISIBLE);

        TextView addWhereaboutProgress = findViewById(R.id.addWhereaboutFormProgress);
        addWhereaboutProgress.setVisibility(View.VISIBLE);
        EditText addWhereaboutProgressInput = findViewById(R.id.addWhereaboutFormProgressInput);
        addWhereaboutProgressInput.setVisibility(View.VISIBLE);

        TextView addWhereaboutSource = findViewById(R.id.addWhereaboutFormSource);
        addWhereaboutSource.setVisibility(View.VISIBLE);
        EditText addWhereaboutSourceInput = findViewById(R.id.addWhereaboutFormSourceInput);
        addWhereaboutSourceInput.setVisibility(View.VISIBLE);

        TextView addWhereaboutNote = findViewById(R.id.addWhereaboutFormNote);
        addWhereaboutNote.setVisibility(View.VISIBLE);
        EditText addWhereaboutNoteInput = findViewById(R.id.addWhereaboutFormNoteInput);
        addWhereaboutNoteInput.setVisibility(View.VISIBLE);

        Button addWhereaboutButton = findViewById(R.id.addWhereaboutButton);
        addWhereaboutButton.setVisibility(View.VISIBLE);
    }

    public void hideAddWhereaboutForm(){
        TextView addWhereaboutTitle = findViewById(R.id.addWhereaboutFormTitle);
        addWhereaboutTitle.setVisibility(View.INVISIBLE);
        EditText addWhereaboutTitleInput = findViewById(R.id.addWhereaboutFormTitleInput);
        addWhereaboutTitleInput.setVisibility(View.INVISIBLE);

        TextView addWhereaboutPart = findViewById(R.id.addWhereaboutFormPart);
        addWhereaboutPart.setVisibility(View.INVISIBLE);
        EditText addWhereaboutPartInput = findViewById(R.id.addWhereaboutFormPartInput);
        addWhereaboutPartInput.setVisibility(View.INVISIBLE);

        TextView addWhereaboutProgress = findViewById(R.id.addWhereaboutFormProgress);
        addWhereaboutProgress.setVisibility(View.INVISIBLE);
        EditText addWhereaboutProgressInput = findViewById(R.id.addWhereaboutFormProgressInput);
        addWhereaboutProgressInput.setVisibility(View.INVISIBLE);

        TextView addWhereaboutSource = findViewById(R.id.addWhereaboutFormSource);
        addWhereaboutSource.setVisibility(View.INVISIBLE);
        EditText addWhereaboutSourceInput = findViewById(R.id.addWhereaboutFormSourceInput);
        addWhereaboutSourceInput.setVisibility(View.INVISIBLE);

        TextView addWhereaboutNote = findViewById(R.id.addWhereaboutFormNote);
        addWhereaboutNote.setVisibility(View.INVISIBLE);
        EditText addWhereaboutNoteInput = findViewById(R.id.addWhereaboutFormNoteInput);
        addWhereaboutNoteInput.setVisibility(View.INVISIBLE);

        Button addWhereaboutButton = findViewById(R.id.addWhereaboutButton);
        addWhereaboutButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView addWhereaboutPart = findViewById(R.id.addWhereaboutFormPart);
        EditText addWhereaboutTitleInput = findViewById(R.id.addWhereaboutFormTitleInput);
        EditText addWhereaboutPartInput = findViewById(R.id.addWhereaboutFormPartInput);
        EditText addWhereaboutProgressInput = findViewById(R.id.addWhereaboutFormProgressInput);
        EditText addWhereaboutSourceInput = findViewById(R.id.addWhereaboutFormSourceInput);
        EditText addWhereaboutNoteInput = findViewById(R.id.addWhereaboutFormNoteInput);

        switch(parent.getItemAtPosition(position).toString()) {
            case "Select your whereabout...":
                hideAddWhereaboutForm();
                break;
            case "Series":
                showAddWhereaboutForm();
                addWhereaboutPart.setText(R.string.add_whereabout_part_series);
                addWhereaboutTitleInput.setHint(R.string.add_whereabout_title_input_hint_series);
                addWhereaboutPartInput.setHint(R.string.add_whereabout_part_input_hint_series);
                addWhereaboutProgressInput.setHint(R.string.add_whereabout_progress_input_hint_series);
                addWhereaboutSourceInput.setHint(R.string.add_whereabout_source_input_hint_series);
                addWhereaboutNoteInput.setHint(R.string.add_whereabout_note_input_hint_series);
                Log.d("whereaboutSpinner", "onItemSelected: Series");
                break;
            case "Movie":
                showAddWhereaboutForm();
                addWhereaboutPart.setText(R.string.add_whereabout_part_movie);
                addWhereaboutTitleInput.setHint(R.string.add_whereabout_title_input_hint_movie);
                addWhereaboutPartInput.setHint(R.string.add_whereabout_part_input_hint_movie);
                addWhereaboutProgressInput.setHint(R.string.add_whereabout_progress_input_hint_movie);
                addWhereaboutSourceInput.setHint(R.string.add_whereabout_source_input_hint_movie);
                addWhereaboutNoteInput.setHint(R.string.add_whereabout_note_input_hint_movie);
                Log.d("whereaboutSpinner", "onItemSelected: Movie");
                break;
            case "Book":
                showAddWhereaboutForm();
                addWhereaboutPart.setText(R.string.add_whereabout_part_book);
                addWhereaboutTitleInput.setHint(R.string.add_whereabout_title_input_hint_book);
                addWhereaboutPartInput.setHint(R.string.add_whereabout_part_input_hint_book);
                addWhereaboutProgressInput.setHint(R.string.add_whereabout_progress_input_hint_book);
                addWhereaboutSourceInput.setHint(R.string.add_whereabout_source_input_hint_book);
                addWhereaboutNoteInput.setHint(R.string.add_whereabout_note_input_hint_book);
                Log.d("whereaboutSpinner", "onItemSelected: Book");
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
