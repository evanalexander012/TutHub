package com.example.tuthub;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String  FirstName,
                    LastName,
                    PhoneNum,
                    Password;

    private ArrayList<String> Classes, Availablity;

    
    public User(String firstName, String lastName,
                String phoneNum, String password)
    {
                FirstName = firstName;
                LastName = lastName;
                PhoneNum = phoneNum;
                Password = password;
                Availablity = new ArrayList<>();
                Availablity.add("");
                Classes = new ArrayList<>();
                Classes.add("");

    }

    public String getName(){return FirstName + " " + LastName;}

    public void setClasses(ArrayList<String> classCodes){
        Classes = classCodes;
    }

    public void setAvailability(ArrayList<String> availability){ Availablity= availability;}

    public String getPhoneNum(){return PhoneNum;}

    public String getPassword(){return Password;}

    public ArrayList<String> getClasses(){return Classes;}

    public ArrayList<String> getAvailablity(){return Availablity;}

}
