package com.example.tuthub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//import com.example.tuthub.R;
//import com.example.tuthub.TutorActivity;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class SignupActivity extends AppCompatActivity {

    EditText editTextPhone, editTextCode;

    FirebaseAuth mAuth;

    String codeSent;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            codeSent = s;
        }
    };

    private void sendVerificationCode() {
        String phone = editTextPhone.getText().toString();

        if(phone.isEmpty()){
            editTextPhone.setError("Phone number is required");
            editTextPhone.requestFocus();
            return;
        }

        if(phone.length() < 10 ){
            editTextPhone.setError("Please enter a valid phone");
            editTextPhone.requestFocus();
            return;
        }


        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);      // OnVerificationStateChangedCallbacks
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        editTextCode = findViewById(R.id.editTextCode);
        editTextPhone = findViewById(R.id.editTextPhone);

        findViewById(R.id.buttonGetVerificationCode).setOnClickListener(new View.OnClickListener() { //Verification Code
            @Override
            public void onClick(View v) {
                sendVerificationCode();
            }
        });

        findViewById(R.id.buttonSignIn).setOnClickListener(new View.OnClickListener() { //Sign In Button
            @Override
            public void onClick(View v) {

            }
        });


        //Temp button to move to Tutor Page
        Button openTutorActivityBtn = (Button) findViewById(R.id.loginBtn);
        openTutorActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openTutorActivity = new Intent(getApplicationContext(), TutorActivity.class);

                startActivity(openTutorActivity);
            }
        });
    }
}

