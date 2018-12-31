package com.sabdar.android.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.UnicodeSetSpanner;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateAccount extends Activity implements Callback<Profile> {
    private SharedPreferences mPreferences;
	private SharedPreferences.Editor mEditor;

    private EditText un;
    private EditText pa;
    private EditText ag;
    private EditText gn;
    String q,w,r,y;
    int e;

    private ProfileService profileService;
    String username = null;
    String password = null;
    String repassword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		mEditor = mPreferences.edit();
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        un = findViewById(R.id.un);
        pa = findViewById(R.id.pa);
        ag = findViewById(R.id.ag);
        gn = findViewById(R.id.gn);
    }

    public void openQuiz(View view) {
        q = un.getText().toString();
        w = pa.getText().toString();
        y  = ag.getText().toString();
        r = gn.getText().toString();
        if (TextUtils.isEmpty(q) || TextUtils.isEmpty(w) || TextUtils.isEmpty(y) || TextUtils.isEmpty(r) ) {
            Toast.makeText(this, "Enter all the fields", Toast.LENGTH_SHORT).show();
            return;
        }
        e = Integer.parseInt(y);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofitInstance = new Retrofit
                .Builder()
                .baseUrl("http://10.0.2.15:8080/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        profileService = retrofitInstance.create(ProfileService.class);

        Call<Profile> profileCall = profileService.createProfile(q, w, e, r);
        profileCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<Profile> call, Response<Profile> response) {
        if(response.body() != null) {
            String j = response.body().getGender();

            if(j.equals("yes")){
                Toast.makeText(this, "Sign up Successfully!", Toast.LENGTH_SHORT).show();
                mEditor.putString("com.sabdar.android.quizapp.uname", q);
                mEditor.commit();
                mEditor.putString("com.sabdar.android.quizapp.password", w);
                mEditor.commit();
                Intent intent = new Intent(this, Login_Activity.class);
                startActivity(intent);
                finish();
            }
            if(j.equals("no")){
                Toast.makeText(this, "Username already taken!", Toast.LENGTH_SHORT).show();
            }
            if(j.equals("o")){
                Toast.makeText(this, "Something Went Wrong! Try Again!", Toast.LENGTH_SHORT).show();
            }
        }
        else
            Toast.makeText(this, "Unexpected error occur!!", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onFailure(Call<Profile> call, Throwable t) {
        Toast.makeText(this, "Error getting movie info" + t.toString(), Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onBackPressed()    {
        super.onBackPressed();
    }
}