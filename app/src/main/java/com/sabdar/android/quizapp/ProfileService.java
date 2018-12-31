package com.sabdar.android.quizapp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ProfileService {

    @GET("/getprofile.php")
    Call<Profile> searchProfile(@Query("uname") String uname);

    @FormUrlEncoded
    @POST("/createProfile.php?")
    Call<Profile> createProfile(@Field("uname") String uname, @Field("password") String password, @Field("age") int age, @Field("gender") String gender);

    @FormUrlEncoded
    @POST("/login.php?")
    Call<Profile> login(@Field("uname") String uname, @Field("password") String password);
}
