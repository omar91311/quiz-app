package com.sabdar.android.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

public class Login_Activity extends AppCompatActivity  implements Callback<Profile> {
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    String username;
    String password;
    private ProfileService profileService;

	EditText edit_Username, edit_Password;
    CreateAccount createAccount = new CreateAccount();
    String s = createAccount.username;
    String s2 = createAccount.password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        edit_Username = findViewById(R.id.username_edit_box);
        edit_Password = findViewById(R.id.password_edit_box);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();

        checkSharedPreferences();
    }

    public void openQuiz(View view) {
        username = edit_Username.getText().toString();
        password = edit_Password.getText().toString();

        String login_username = null;
        String login_password = null;
        Log.d(login_password+login_username,"LoginActivity");

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Enter all the fields", Toast.LENGTH_SHORT).show();
            return;
        } else if (!(TextUtils.isEmpty(username)) || !(TextUtils.isEmpty(password))) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            Retrofit retrofitInstance = new Retrofit
                    .Builder()
                    .baseUrl("http://10.0.2.15:8080/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            profileService = retrofitInstance.create(ProfileService.class);

            Call<Profile> profileCall = profileService.login(username, password);
            profileCall.enqueue(this);
        }
    }

    @Override
    public void onResponse(Call<Profile> call, Response<Profile> response) {
        if(response.body() != null) {
            String j = response.body().getGender();

            if(j.equals("yes")){
                Toast.makeText(this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Dashboard.class);

                mEditor.putString("com.sabdar.android.quizapp.uname", username);
                mEditor.commit();
                mEditor.putString("com.sabdar.android.quizapp.password", password);
                mEditor.commit();

                startActivity(intent);
                finish();
            }
            if(j.equals("no")){
                Toast.makeText(this, "Account not exist!", Toast.LENGTH_SHORT).show();
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

    public void create_Account(View view) {
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
        finish();
    }
	
	private void checkSharedPreferences(){
        String uname = mPreferences.getString("com.sabdar.android.quizapp.uname", "");
        String password = mPreferences.getString("com.sabdar.android.quizapp.password", "");

        edit_Username.setText(uname);
        edit_Password.setText(password);
    }
}