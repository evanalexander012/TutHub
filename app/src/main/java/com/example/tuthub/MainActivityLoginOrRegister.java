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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Ref;

public class MainActivityLoginOrRegister extends AppCompatActivity {

    private TextView mTextMessage;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference("Users");
    private String Word;

    private String getRealPhone()
    {
        EditText phone = findViewById(R.id.etPhoneNumber);
        String realPhone = phone.getText().toString();
        return realPhone;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login_or_register);

        mTextMessage = (TextView) findViewById(R.id.message);
        //configure login button to open profile activity
        Button loginBtn = (Button)findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText pass = findViewById(R.id.etPasswordLogin);
                String realPass = pass.getText().toString();



                ref.child(getRealPhone()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Word = dataSnapshot.child("password").getValue(String.class);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                if(realPass.equals(Word)){
                    Intent goToProfile = new Intent(getApplicationContext(), MyProfileActivity.class);
                    startActivity(goToProfile);
                }
                else{

                }

                Intent goToSearch = new Intent(getApplicationContext(), SearchScreenActivity.class);
                startActivity(goToSearch);

            }
        });
        //configure register button to open registration activity
        Button registerBtn = (Button)findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToRegistration = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(goToRegistration);
            }
        });
    }

}
