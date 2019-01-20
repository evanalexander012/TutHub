package com.example.tuthub;

import java.util.ArrayList;

public class User {

    private String  FirstName,
                    LastName,
                    PhoneNum,
                    Password;

    private ArrayList<String> Classes;

    public User(String firstName, String lastName,
                String phoneNum, String password)
    {
                FirstName = firstName;
                LastName = lastName;
                PhoneNum = phoneNum;
                Password = password;

    }

    public String getName(){return FirstName + " " + LastName;}

    public void setClasses(ArrayList<String> classCodes){
        Classes = classCodes;
    }

    public String getPhoneNum(){return PhoneNum;}

    public String getPassword(){return Password;}

}
