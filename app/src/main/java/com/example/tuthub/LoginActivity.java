package com.example.tuthub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button openTutorActivityBtn = (Button) findViewById(R.id.loginBtn);
        openTutorActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openTutorActivity = new Intent(getApplicationContext(),TutorActivity.class);

                startActivity(openTutorActivity);
            }
        });
        }

        }

