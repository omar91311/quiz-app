package com.sabdar.android.quizapp;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Profile {


    private String uname;
    @SerializedName("password")
    @Expose
    private String Password;
    @SerializedName("age")
    @Expose
    private int Age;
    @SerializedName("gender")
    @Expose
    private String Gender;

    public String getUname(){return uname;}
    public void setUname(String uname) {this.uname = uname;}
    public String getPassword(){return Password;}
    public void setPassword(String Password){this.Password = Password;}
    public int getAge(){return Age;}
    public void setAge(int Age){this.Age = Age;}
    public String getGender(){return Gender;}
    public void setGender(String Gender){this.Gender = Gender;}
}