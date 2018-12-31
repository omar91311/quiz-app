package com.sabdar.android.quizapp;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class
resultAdapter extends RecyclerView.Adapter<resultAdapter.MyViewHolder> {

    private Context context;
    private List<result> resultsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView subject;
        public TextView marks;
        public MyViewHolder(View view) {
            super(view);
            subject = view.findViewById(R.id.subject);
            marks = view.findViewById(R.id.marks);
        }
    }


    public resultAdapter(Context context, List<result> resultsList) {
        this.context = context;
        this.resultsList = resultsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        result resultt = resultsList.get(position);
	String gg = resultt.getSubject();
	int hh = resultt.getMarks();
	String jj = Integer.toString(hh);
        holder.subject.setText(gg);
        holder.marks.setText(jj);
    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }
}