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
                EditText firstNameGet = findViewById(R.id.etFirstName);
                EditText lastNameGet = findViewById(R.id.etLastName);
                EditText ageGet = findViewById(R.id.etAge);
                EditText schoolGet = findViewById(R.id.etSchool);
                EditText phoneGet= findViewById(R.id.etPhone);
                EditText emailGet = findViewById(R.id.etEmail);
                EditText passwordGet = findViewById(R.id.etPassword);

//                if(!firstNameGet.equals("") && !lastNameGet.equals("") && !ageGet.equals("") && !schoolGet.equals("")
//                    && !phoneGet.equals("") && !emailGet.equals("") && !passwordGet.equals("")) {
//
//                } else {
//
//                }

                String firstName = firstNameGet.getText().toString();
                String lastName = lastNameGet.getText().toString();
                String age = ageGet.getText().toString();
                String school = schoolGet.getText().toString();
                String phone = phoneGet.getText().toString();
                String email = emailGet.getText().toString();
                String password = passwordGet.getText().toString();

                User nUser = new User(firstName, lastName, age, school, phone, email, password);

                myRef.child("Users").child(phone).setValue(nUser);

                //Send User object to Firebase Database
                //userRef.child("user").child(phoneGet).setValue(nUser);

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
