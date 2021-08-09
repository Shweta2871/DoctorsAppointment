package com.example.android.aayur;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class P_CustomAdapter extends RecyclerView.Adapter<P_CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList id, name, symptom;


    P_CustomAdapter(Context context, ArrayList id, ArrayList name,
                    ArrayList symptom){
        this.context = context;
        this.id = id;
        this.name = name;
        this.symptom = symptom;

    }


    @NonNull
    @Override
    public P_CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.p_row_rv, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull P_CustomAdapter.MyViewHolder holder, int position) {
        holder.id.setText(String.valueOf(id.get(position)));
        holder.name.setText(String.valueOf(name.get(position)));
        holder.symptom.setText(String.valueOf(symptom.get(position)));

    }

    @Override
    public int getItemCount() {

        return id.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id, name, symptom;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            symptom = itemView.findViewById(R.id.symptom);

        }
    }
}
