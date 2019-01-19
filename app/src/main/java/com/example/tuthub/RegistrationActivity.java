package com.example.tuthub;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private TextView mTextMessage;
    DatabaseReference userRef;

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
        userRef = userDatabase.getReference();



        //Register Button
        findViewById(R.id.registerBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstNameGet = findViewById(R.id.etFirstName).toString();
                String lastNameGet = findViewById(R.id.etLastName).toString()
                String ageGet = findViewById(R.id.etAge).toString();
                String schoolGet = findViewById(R.id.etSchool).toString();
                String phoneGet= findViewById(R.id.etPhone).toString();
                String emailGet = findViewById(R.id.etEmail).toString();
                String passwordGet = findViewById(R.id.etPassword).toString();

                User nUser = new User(firstNameGet, lastNameGet, ageGet, schoolGet, phoneGet, emailGet, passwordGet);

                System.out.print("dog");

                userRef.child("user").child(phoneGet).setValue(nUser);

//                startActivity(new Intent(RegistrationActivity.this, .class));
//                Intent goToPRegistrationActivity = new Intent(getApplicationContext(), SignupActivity.class);
//                startActivity(goToPRegistrationActivity);

                //Trigger pop-up for phone verification when 'register' is pressed
                onButtonShowPopupWindowClick(v);

            }
        });


    }



    public void onButtonShowPopupWindowClick(View view) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_register, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(20);
        }

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }


}
