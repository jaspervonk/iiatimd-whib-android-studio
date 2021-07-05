package com.example.whib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    private GetUserTask getUserTask;
    private Button toHomeScreenButton;
    private Button toHomeScreenButtonRegister;
    private TextView welcomeText;
    private TextView welcomeMessage;
    private EditText addUserFormName;
    private RequestQueue queue;
    private User user;

    private boolean userFound;

    public void userFoundCallback (boolean userResult, User user) {
        this.user = user;
        if(userResult){
            userFound = true;
            // Set Welcome back Text
            welcomeText.setText("Welcome back " + user.getName() + "!");
            welcomeMessage.setText(R.string.welcome_back_message);

            // Set Continue button
            toHomeScreenButton.setVisibility(View.VISIBLE);

            Log.d("WelcomeActivity", "true");
        } else {
            userFound = false;
            // Set Welcome Text
            welcomeText.setText(R.string.welcome_text);
            welcomeMessage.setText(R.string.welcome_message);

            // Show Name input field and button
            addUserFormName.setVisibility(View.VISIBLE);
            toHomeScreenButtonRegister.setVisibility(View.VISIBLE);

            Log.d("WelcomeActivity", "false");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Set local variables
        welcomeText = findViewById(R.id.welcomeText);
        welcomeMessage = findViewById(R.id.welcomeMessage);
        addUserFormName = findViewById(R.id.addUserFormName);

        // Check if there already is a token and name given
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        getUserTask = new GetUserTask(db, this);
        new Thread(getUserTask).start();


        // Set OnClickListener
        toHomeScreenButton = findViewById(R.id.toHomeScreenButton);
        toHomeScreenButton.setOnClickListener(this);

        toHomeScreenButtonRegister = findViewById(R.id.toHomeScreenButtonRegister);
        toHomeScreenButtonRegister.setOnClickListener(this);

        // Setup Volley
        queue = Volley.newRequestQueue(this);
    }

    public void onClick(View v) {
        // Check what button is pressed; Change activity accordingly
        Intent toHomeScreenIntent = new Intent(this, HomeActivity.class);

        switch(v.getId()) {
            case R.id.toHomeScreenButton:
                startActivity(toHomeScreenIntent);
                break;
            case R.id.toHomeScreenButtonRegister:
                // Check if there is a valid Name
                if(TextUtils.isEmpty(addUserFormName.getText().toString())){
                    Toast.makeText(getBaseContext(), "Invalid name", Toast.LENGTH_SHORT).show();
                } else {
                    // Make API Call for a JWT Token
                    // Define API link
                    final String URL = "https://warm-forest-85250.herokuapp.com/api/register/" + addUserFormName.getText().toString();

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // On Volley Return: Make new user call InsertUserTask; Also call a Toast
                                    Toast.makeText(getBaseContext(), "Successfully registered!", Toast.LENGTH_LONG).show();
                                    User newUser = new User(response.substring(0), addUserFormName.getText().toString());
                                    AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                                    new Thread(new InsertUserTask(db, newUser)).start();

                                    startActivity(toHomeScreenIntent);
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // On NO Volley Return: Call a Toast
                                    Toast.makeText(getBaseContext(), "Failed to register", Toast.LENGTH_LONG).show();
                                }
                            });

                    queue.add(stringRequest);
                }
                break;
        }
    }
}
