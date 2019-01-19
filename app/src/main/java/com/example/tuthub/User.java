package com.example.tuthub;


public class User {

    private String  PhoneNum,
                    FirstName,
                    LastName,
                    Password;

    public User(){}

    public User(String phoneNum, String firstName, String lastName, String password)
    {
        PhoneNum = phoneNum;
        FirstName = firstName;
        LastName = lastName;
        Password = password;
    }

    public String getName(){return FirstName + " " + LastName;}

    public String getPhoneNum(){return PhoneNum;}

    public String getPassword(){return Password;}


}
