package com.sabdar.android.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Dashboard extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
    }
    public void css(View v){
        Intent intent = new Intent(this, css.class);
        startActivity(intent);
    }
    public void results(View v){
        Intent intent = new Intent(this, results.class);
        startActivity(intent);
    }

    public void myprofile(View v){
        Intent intent = new Intent(this, Myprofile.class);
        startActivity(intent);
    }
	
	public void html(View v){
        Intent intent = new Intent(this, html.class);
        startActivity(intent);
    }
	
	public void javascript(View v){
        Intent intent = new Intent(this, javascript.class);
        startActivity(intent);
    }
}
