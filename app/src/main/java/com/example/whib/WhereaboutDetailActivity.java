package com.example.whib;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WhereaboutDetailActivity extends AppCompatActivity implements View.OnClickListener {

    public boolean edit = false;

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

        Button detailWhereaboutEditButton = findViewById(R.id.detailWhereaboutEditButton);
        detailWhereaboutEditButton.setOnClickListener(this);

        Button detailWhereaboutDeleteButton = findViewById(R.id.detailWhereaboutDeleteButton);
        detailWhereaboutDeleteButton.setOnClickListener(this);

        // Set Details
        if(getIntent().hasExtra("uuid")){
            int uuid = getIntent().getIntExtra("uuid", 0);
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            new Thread(new GetWhereaboutTask(db, uuid, findViewById(android.R.id.content))).start();
        }


    }

    public void onClick(View v) {
        int uuid = getIntent().getIntExtra("uuid", 0);;
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        Intent toWhereaboutsScreenIntent = new Intent(this, WhereaboutsActivity.class);

        // Check what button is pressed; Change activity accordingly
        switch(v.getId()) {
            case R.id.actionbarBackButton:
                startActivity(toWhereaboutsScreenIntent);
                break;
            case R.id.actionbarEditButton:
                toggleEditWhereaboutForm();
                break;
            case R.id.detailWhereaboutEditButton:
                new Thread(new UpdateWhereaboutTask(db, uuid, findViewById(android.R.id.content))).start();
                Toast.makeText(getBaseContext(), "Whereabout edited!", Toast.LENGTH_LONG).show();
                toggleEditWhereaboutForm();
            case R.id.detailWhereaboutDeleteButton:
                new Thread(new DeleteWhereaboutTask(db, uuid)).start();
                Toast.makeText(getBaseContext(), "Whereabout deleted!", Toast.LENGTH_LONG).show();
                startActivity(toWhereaboutsScreenIntent);
        }

    }

    public void toggleEditWhereaboutForm(){
        TextView detailWhereaboutTitleInput = findViewById(R.id.detailWhereaboutTitleInput);
        TextView detailWhereaboutPartInput = findViewById(R.id.detailWhereaboutPartInput);
        TextView detailWhereaboutProgressInput = findViewById(R.id.detailWhereaboutProgressInput);
        TextView detailWhereaboutSourceInput = findViewById(R.id.detailWhereaboutSourceInput);
        TextView detailWhereaboutNoteInput = findViewById(R.id.detailWhereaboutNoteInput);

        EditText detailWhereaboutTitleEditInput = findViewById(R.id.detailWhereaboutTitleEditInput);
        EditText detailWhereaboutPartEditInput = findViewById(R.id.detailWhereaboutPartEditInput);
        EditText detailWhereaboutProgressEditInput = findViewById(R.id.detailWhereaboutProgressEditInput);
        EditText detailWhereaboutSourceEditInput = findViewById(R.id.detailWhereaboutSourceEditInput);
        EditText detailWhereaboutNoteEditInput = findViewById(R.id.detailWhereaboutNoteEditInput);

        Button detailWhereaboutEditButton = findViewById(R.id.detailWhereaboutEditButton);
        Button detailWhereaboutDeleteButton = findViewById(R.id.detailWhereaboutDeleteButton);

        if(!this.edit) {
            detailWhereaboutTitleInput.setVisibility(View.INVISIBLE);
            detailWhereaboutPartInput.setVisibility(View.INVISIBLE);
            detailWhereaboutProgressInput.setVisibility(View.INVISIBLE);
            detailWhereaboutSourceInput.setVisibility(View.INVISIBLE);
            detailWhereaboutNoteInput.setVisibility(View.INVISIBLE);

            detailWhereaboutTitleEditInput.setVisibility(View.VISIBLE);
            detailWhereaboutPartEditInput.setVisibility(View.VISIBLE);
            detailWhereaboutProgressEditInput.setVisibility(View.VISIBLE);
            detailWhereaboutSourceEditInput.setVisibility(View.VISIBLE);
            detailWhereaboutNoteEditInput.setVisibility(View.VISIBLE);

            detailWhereaboutEditButton.setVisibility(View.VISIBLE);
            detailWhereaboutDeleteButton.setVisibility(View.VISIBLE);

            this.edit = true;

        } else {
            detailWhereaboutTitleInput.setVisibility(View.VISIBLE);
            detailWhereaboutPartInput.setVisibility(View.VISIBLE);
            detailWhereaboutProgressInput.setVisibility(View.VISIBLE);
            detailWhereaboutSourceInput.setVisibility(View.VISIBLE);
            detailWhereaboutNoteInput.setVisibility(View.VISIBLE);

            detailWhereaboutTitleEditInput.setVisibility(View.INVISIBLE);
            detailWhereaboutPartEditInput.setVisibility(View.INVISIBLE);
            detailWhereaboutProgressEditInput.setVisibility(View.INVISIBLE);
            detailWhereaboutSourceEditInput.setVisibility(View.INVISIBLE);
            detailWhereaboutNoteEditInput.setVisibility(View.INVISIBLE);

            detailWhereaboutEditButton.setVisibility(View.INVISIBLE);
            detailWhereaboutDeleteButton.setVisibility(View.INVISIBLE);

            this.edit = false;
        }

    }
}
