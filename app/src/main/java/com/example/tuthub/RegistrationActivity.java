package com.example.tuthub;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RegistrationActivity extends AppCompatActivity {

    private TextView mTextMessage;
    DatabaseReference myRef;

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

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        //Register Button
        Button register = (Button)findViewById(R.id.registerBtn);
        register.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                //Get info from EditTexts
                EditText firstNameGet = findViewById(R.id.etFirstName);
                EditText lastNameGet = findViewById(R.id.etLastName);
                EditText phoneGet= findViewById(R.id.etPhone);
                EditText passwordGet = findViewById(R.id.etPassword);

                //Convert text from EditTexts to strings
                String firstName = firstNameGet.getText().toString();
                String lastName = lastNameGet.getText().toString();
                String phone = phoneGet.getText().toString();
                String password = passwordGet.getText().toString();

                //Create new User
                User nUser = new User(firstName, lastName, phone, password);

                //Sends User object to Firebase
                myRef.child("Users").child(phone).setValue(nUser);

                //Trigger pop-up for phone verification when 'register' is pressed
//                onButtonShowPopupWindowClick(v);
            }
        });
    }

//    public void onButtonShowPopupWindowClick(View view) {
//        // inflate the layout of the popup window
//        LayoutInflater inflater = (LayoutInflater)
//                getSystemService(LAYOUT_INFLATER_SERVICE);
//        View popupView = inflater.inflate(R.layout.popup_register, null);
//
//        // create the popup window
//        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
//        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
//        boolean focusable = true; // lets taps outside the popup also dismiss it
//        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            popupWindow.setElevation(20);
//        }
//
//        // show the popup window
//        // which view you pass in doesn't matter, it is only used for the window tolken
//        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
//
//        // dismiss the popup window when touched
//        popupView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                popupWindow.dismiss();
//                return true;
//            }
//        });

//        Button signup = (Button)findViewById(R.id.buttonSignUp);
//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//    }
}
