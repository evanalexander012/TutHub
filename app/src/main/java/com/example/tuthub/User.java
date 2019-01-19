package com.example.tuthub;


public class User {

    private String  FirstName,
                    LastName,
                    Age,
                    School,
                    PhoneNum,
                    Email,
                    Password;

    public User(){}

    public User(String firstName, String lastName, String age, String school, String phoneNum, String email, String password)
    {
                FirstName = firstName;
                LastName = lastName;
                Age = age;
                School = school;
                PhoneNum = phoneNum;
                Email = email;
                Password = password;
    }

    public String getName(){return FirstName + " " + LastName;}

    public String getPhoneNum(){return PhoneNum;}

    public String getPassword(){return Password;}

}
