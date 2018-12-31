package com.sabdar.android.quizapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class results extends Activity {
    DatabaseHelper dh = new DatabaseHelper(this);
    List<result> st = new ArrayList<>();
    RecyclerView recyclerView;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        recyclerView= findViewById(R.id.rview);
		st.addAll(dh.getallresults());
        int g = st.size();
        Toast.makeText(this, ""+g+"", Toast.LENGTH_LONG).show();
		resultAdapter ra = new resultAdapter(this, st);
		RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(ra);
	}
}