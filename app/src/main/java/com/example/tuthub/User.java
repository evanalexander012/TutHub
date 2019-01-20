package com.example.tuthub;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String  FirstName,
                    LastName,
                    PhoneNum,
                    Password;

    private List<String> Classes,
                         Availability;

    public User(String firstName, String lastName,
                String phoneNum, String password)
    {
                FirstName = firstName;
                LastName = lastName;
                PhoneNum = phoneNum;
                Password = password;

    }

    public String getName(){return FirstName + " " + LastName;}

    public void setClasses(List<String> classes){
        Classes = classes;
    }

    public void setAvailability(List<String> availability) { Availability = availability ; }

    public String getPhoneNum(){return PhoneNum;}

    public String getPassword(){return Password;}

}
