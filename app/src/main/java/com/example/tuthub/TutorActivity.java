package com.example.tuthub;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TutorActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private String UserPhone = MainActivityLoginOrRegister.USERPHONE;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference("Users");
    private User U;

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
        setContentView(R.layout.activity_tutor);


//        ref.child(UserPhone).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                U = dataSnapshot.getValue(User.class); //Get user object from Firebase
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_dashboard:
                        Intent goToDashboard = new Intent(getApplicationContext(), SearchScreenActivity.class);
                        startActivity(goToDashboard);
                        break;
                    case R.id.navigation_profile:
                        Intent goToProfile = new Intent(getApplicationContext(), MyProfileActivity.class);
                        startActivity(goToProfile);
                        break;
                }
                return false;
            }
        });


        // Get reference of widgets from XML layout
        final RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
        Button btn = (Button) findViewById(R.id.btn);
        final TextView tv = (TextView) findViewById(R.id.tv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(TutorActivity.this);

                // String array for alert dialog multi choice items
                String[] subjects = new String[]{
                        "CSE 331",
                        "MTH 132",
                        "BUS 190",
                        "CAS 117",
                        "IAH 241A"
                };

                // Boolean array for initial selected items
                final boolean[] checkedSubjects = new boolean[]{
                        false, // CSE
                        false, // MTH
                        false, // BUS
                        false, // CAS
                        false // IAH
                };

                // Convert the color array to list
                final List<String> classList = Arrays.asList(subjects);

                // Set multiple choice items for alert dialog
                /*
                    AlertDialog.Builder setMultiChoiceItems(CharSequence[] items, boolean[]
                    checkedItems, DialogInterface.OnMultiChoiceClickListener listener)
                        Set a list of items to be displayed in the dialog as the content,
                        you will be notified of the selected item via the supplied listener.
                 */
                /*
                    DialogInterface.OnMultiChoiceClickListener
                    public abstract void onClick (DialogInterface dialog, int which, boolean isChecked)

                        This method will be invoked when an item in the dialog is clicked.

                        Parameters
                        dialog The dialog where the selection was made.
                        which The position of the item in the list that was clicked.
                        isChecked True if the click checked the item, else false.
                 */
                builder.setMultiChoiceItems(subjects, checkedSubjects, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                        // Update the current focused item's checked status
                        checkedSubjects[which] = isChecked;

                        // Get the current focused item
                        String currentItem = classList.get(which);

                        // Notify the current action
                        Toast.makeText(getApplicationContext(),
                                currentItem + " " + isChecked, Toast.LENGTH_SHORT).show();
                    }
                });

                // Specify the dialog is not cancelable
                builder.setCancelable(false);

                // Set a title for alert dialog
                builder.setTitle("Subjects");

                // Set the positive/yes button click listener
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when click positive button
                        tv.setText("Subjects:");
                        for (int i = 0; i<checkedSubjects.length; i++){
                            boolean checked = checkedSubjects[i];
                            if (checked) {
                                tv.setText(tv.getText() + classList.get(i) + "\n");
                            }
                        }
                    }
                });

                // Set the neutral/cancel button click listener
                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when click the neutral button
                    }
                });

                AlertDialog dialog = builder.create();
                // Display the alert dialog on interface
                dialog.show();

                Button submit = (Button) findViewById(R.id.submitBtn);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Read Availability PlainTexts
                        EditText MA = findViewById(R.id.MAPlainText);
                        EditText TA = findViewById(R.id.TAPlainText);
                        EditText WA = findViewById(R.id.WAPlainText);
                        EditText ThA = findViewById(R.id.ThAPlainText);
                        EditText FA = findViewById(R.id.FAPlainText);
                        EditText SA = findViewById(R.id.SAPlainText);
                        EditText SuA = findViewById(R.id.SuAPlainText);

                        //EditTexts to strings
                        String MAvail = MA.getText().toString();
                        String TAvail = TA.getText().toString();
                        String WAvail = WA.getText().toString();
                        String ThAvail = ThA.getText().toString();
                        String FAvail = FA.getText().toString();
                        String SAvail = SA.getText().toString();
                        String SuAvail = SuA.getText().toString();

                        //Add availability strings to new array list
                        ArrayList<String> tempList = new ArrayList<>();
                        tempList.add(MAvail);
                        tempList.add(TAvail);
                        tempList.add(WAvail);
                        tempList.add(ThAvail);
                        tempList.add(FAvail);
                        tempList.add(SAvail);
                        tempList.add(SuAvail);
                        final ArrayList<String> availabilityList = tempList;

                        //Used to see if data is being stored into ArrayLists
                        //Toast.makeText(TutorActivity.this, classList.toString(), Toast.LENGTH_LONG).show();
                        //Toast.makeText(TutorActivity.this, availabilityList.toString(), Toast.LENGTH_LONG).show();

                        //Set user availability and subjects
//                        U.setAvailability(availabilityList);
//                        U.setClasses(subjectsList);
                    }
                });
            }
        });
    }
}

