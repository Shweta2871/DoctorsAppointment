package com.example.android.aayur;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class D_CustomAdapter extends RecyclerView.Adapter<D_CustomAdapter.MyViewHolder> {

    Context context;
    ArrayList d_name, d_degree, d_fees, d_clinic, d_location, d_open, d_close, d_id;
    private RecyclerViewClickListener listener;

    public interface  RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    D_CustomAdapter(Context context,ArrayList d_id, ArrayList d_name, ArrayList d_degree, ArrayList d_fees, ArrayList d_clinic, ArrayList d_location, ArrayList d_open, ArrayList d_close, RecyclerViewClickListener listener){
        this.context = context;
        this.d_id = d_id;
        this.d_degree = d_degree;
        this.d_fees = d_fees;
        this.d_name = d_name;
        this.d_clinic = d_clinic;
        this.d_location = d_location;
        this.d_open = d_open;
        this.d_close = d_close;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.d_row_rv,parent,false);
        return new MyViewHolder(view);



    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.d_id_d.setText(String.valueOf(d_id.get(position)));
        holder.d_name_d.setText(String.valueOf(d_name.get(position)));
        holder.d_degree_d.setText(String.valueOf(d_degree.get(position)));
        holder.d_fees_d.setText(String.valueOf(d_fees.get(position)));
        holder.d_clinic_d.setText(String.valueOf(d_clinic.get(position)));
        holder.d_location_d.setText(String.valueOf(d_location.get(position)));
        holder.d_open_d.setText(String.valueOf(d_open.get(position)));
        holder.d_close_d.setText(String.valueOf(d_close.get(position)));
    }

    @Override
    public int getItemCount() {
        return d_id.size() ;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        TextView d_id_d, d_name_d, d_degree_d, d_fees_d, d_clinic_d, d_location_d, d_open_d, d_close_d;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            d_id_d = itemView.findViewById(R.id.doc_id);
            d_name_d = itemView.findViewById(R.id.docName);
            d_degree_d = itemView.findViewById(R.id.degree);
            d_fees_d = itemView.findViewById(R.id.fees);
            d_clinic_d = itemView.findViewById(R.id.ClinicNm);
            d_location_d = itemView.findViewById(R.id.location);
            d_open_d = itemView.findViewById(R.id.open);
            d_close_d = itemView.findViewById(R.id.close);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(itemView, getAdapterPosition());
        }
    }
}
