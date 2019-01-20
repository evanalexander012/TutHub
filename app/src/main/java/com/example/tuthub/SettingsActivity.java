package com.example.tuthub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//Test
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //configures button to go back to profile page when pressed
        Button goBackToProfile = (Button)findViewById(R.id.goBackBtn);
        goBackToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBackProfile = new Intent(getApplicationContext(), MyProfileActivity.class);
                startActivity(goBackProfile);
            }
        });
        //configures BECOME A TUTOR! button to take you to new activity
        Button becomeTutor = (Button) findViewById(R.id.becomeTutor);
        becomeTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCreateTutor = new Intent(getApplicationContext(),CreateTutorActivity.class);
                startActivity(goToCreateTutor);
            }
        });

        Button logoutBtn = (Button) findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMain = new Intent(getApplicationContext(), MainActivityLoginOrRegister.class);
                startActivity(goToMain);
            }
        });
    }
}
