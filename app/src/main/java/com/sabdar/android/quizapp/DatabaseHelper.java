package com.sabdar.android.quizapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "quiz";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String ssl = "CREATE TABLE  results (marks INTEGER, subject TEXT)";
        db.execSQL(ssl);
    }

    public String addResult(String subject, int marks) {
        SQLiteDatabase db = this.getWritableDatabase();
		String q = "Done";
		
		String sql = "insert into results (marks, subject) values ("+marks+", '"+subject+"')";
		try{
            db.execSQL(sql);
		}
		catch(Exception e){
            q = e.toString();
		}
		db.close();
		return q;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int a, int b){
}
	public List<result> getallresults() {
        SQLiteDatabase db = this.getReadableDatabase();
		List<result> q = new ArrayList<>();
		result res;
		String sql = "select * from results";
		try{
            Cursor cr = db.rawQuery(sql, null);
			if (cr.moveToFirst()) {
do{					
res = new result();
    res.setMarks(cr.getInt(cr.getColumnIndex("marks")));
    res.setSubject(cr.getString(cr.getColumnIndex("subject")));
				q.add(res);
				} while (cr.moveToNext());
				}
			}
		catch(Exception e){
            String err = e.toString();
		}
		db.close();
		return q;
    }
}