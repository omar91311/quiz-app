package com.sabdar.android.quizapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Myprofile extends Activity implements Callback<Profile> {
    private TextView un;
    private TextView pa;
    private TextView ag;
    private TextView gn;
    private SharedPreferences mPreferences;

    private ProfileService profileService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile);

        un = findViewById(R.id.un);
        pa = findViewById(R.id.pa);
        ag = findViewById(R.id.ag);
        gn = findViewById(R.id.gn);

        Retrofit retrofitInstance = new Retrofit
                .Builder()
                .baseUrl("http://10.0.2.15:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        profileService = retrofitInstance.create(ProfileService.class);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String uame = mPreferences.getString("com.sabdar.android.quizapp.uname", "");
        Call<Profile> profileCall = profileService.searchProfile(uame);
        profileCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<Profile> call, Response<Profile> response) {
        //Toast.makeText(this, response.body().getActors(), Toast.LENGTH_LONG).show();
        //Log.i(TAG, "onResponse: " + response.body().getPoster());
        if(response.body() != null) {
            //Picasso.with(this).load(response.body().getUname()).into((Target) un);
            //Picasso.with(this).load(response.body().getPassword()).into((Target) pa);
            //Picasso.with(this).load(response.body().getAge()).into((Target) ag);
            //Picasso.with(this).load(response.body().getGender()).into((Target) gn);
            Toast.makeText(this, "Yes", Toast.LENGTH_LONG).show();
            String a;
            a = response.body().getUname();
            String b;
            b = response.body().getPassword();
            int cc;
            cc = response.body().getAge();
            String c;
            c = Integer.toString(cc);
            String d;
            d = response.body().getGender();

            un.setText(a);
            pa.setText(b);
            ag.setText(c);
            gn.setText(d);

        }
        else
            Toast.makeText(this, "response is empty", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onFailure(Call<Profile> call, Throwable t) {
        Toast.makeText(this, "Error getting movie info" + t.toString(), Toast.LENGTH_SHORT).show();
    }
}