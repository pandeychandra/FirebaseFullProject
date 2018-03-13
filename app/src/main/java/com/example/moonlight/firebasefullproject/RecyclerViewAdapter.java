package com.example.moonlight.firebasefullproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by MoonLight on 3/12/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    List<StudentDetails> MainImageUploadInfoList;

    public RecyclerViewAdapter(Context context, List<StudentDetails> TempList) {

        this.MainImageUploadInfoList = TempList;

        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final StudentDetails studentDetails = MainImageUploadInfoList.get(position);

        holder.StudentNameTextView.setText(studentDetails.getStudentName());

        holder.StudentNumberTextView.setText(studentDetails.getStudentPhoneNumber());
        holder.adress.setText(studentDetails.getLocation());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent i=new Intent(context,DetailsActivity.class);
                context.startActivity(i);
                Intent myintent=new Intent(context, DetailsActivity.class);

                myintent.putExtra("Name",studentDetails.getStudentName());
                myintent.putExtra("Number",studentDetails.getStudentPhoneNumber());


                context.startActivity(myintent);*/
            }
        });


    }

    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView StudentNameTextView,adress;
        public TextView StudentNumberTextView;
        CardView cardView;

        public ViewHolder(View itemView) {

            super(itemView);
            adress=(TextView)itemView.findViewById(R.id.ShowStudentAdress);

            StudentNameTextView = (TextView) itemView.findViewById(R.id.ShowStudentNameTextView);

            StudentNumberTextView = (TextView) itemView.findViewById(R.id.ShowStudentNumberTextView);
            cardView=(CardView)itemView.findViewById(R.id.cardview1);
        }
    }
}

