package com.example.tuthub;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private TextView mTextMessage;

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
        setContentView(R.layout.activity_registration);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        Button registerBtn = (Button) findViewById(R.id.registerBtn);
//        registerBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent goToProfileActivity = new Intent(getApplicationContext(), MyProfileActivity.class);
////                startActivity(goToProfileActivity);
//            }
//        });

        FirebaseDatabase userDatabase = FirebaseDatabase.getInstance();
        DatabaseReference userRef = userDatabase.getReference();



        //Register Button
        findViewById(R.id.registerBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText firstNameEditText = findViewById(R.id.etFirstName);
                EditText lastNameEditText = findViewById(R.id.etLastName);
                EditText ageEditText = findViewById(R.id.etAge);
                EditText schoolEditText = findViewById(R.id.etSchool);
                EditText phoneEditText = findViewById(R.id.etPhone);
                EditText emailEditText = findViewById(R.id.etEmail);
                EditText passwordEditText = findViewById(R.id.etPassword);

                User nUser = new User(firstNameEditText.toString(),
                        lastNameEditText.toString(),
                        ageEditText.toString(),
                        schoolEditText.toString(),
                        phoneEditText.toString(),
                        emailEditText.toString(),
                        passwordEditText.toString());

                startActivity(new Intent(RegistrationActivity.this, Pop.class));
                Intent goToPRegistrationActivity = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(goToPRegistrationActivity);

            }
        });
    }


}
