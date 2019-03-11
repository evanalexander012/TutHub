package com.example.tuthub;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;

import org.w3c.dom.Text;

public class TutorProfileActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private DataSnapshot mDataSnapshot;
    TextView TutClassesTextView = (TextView) findViewById(R.id.TutClassesTextView);
    TextView TutAvailabilityTextView = (TextView) findViewById(R.id.TutAvailabilityTextView);

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_profile:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_profile);
        //configure button to go to settings
        final ImageButton settings = findViewById(R.id.settingsButton);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            }
        });

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView TutBottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        TutBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_dashboard:
                        Intent goToDashboard = new Intent(getApplicationContext(), SearchScreenActivity.class);
                        startActivity(goToDashboard);
                        break;
                    case R.id.navigation_home:
                        Intent goToHome = new Intent(getApplicationContext(), TutorActivity.class);
                        startActivity(goToHome);
                        break;
                }
                return false;
            }
        });
    }
}
