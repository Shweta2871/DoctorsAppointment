package com.example.android.aayur;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class D_HistoryAdapter extends RecyclerView.Adapter<D_HistoryAdapter.NyViewHolder> {

    Context context;
    ArrayList id, name, symptom;

    public D_HistoryAdapter(Context context, ArrayList id, ArrayList name, ArrayList symptom) {
        this.context = context;
        this.id = id;
        this.name = name;
        this.symptom = symptom;
    }

    @NonNull
    @Override
    public D_HistoryAdapter.NyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.p_row_rv, parent, false);
        return new NyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull D_HistoryAdapter.NyViewHolder holder, int position) {

        holder.id.setText(String.valueOf(id.get(position)));
        holder.name.setText(String.valueOf(name.get(position)));
        holder.symptom.setText(String.valueOf(symptom.get(position)));

    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class NyViewHolder extends RecyclerView.ViewHolder {

        TextView id, name, symptom;
        public NyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            symptom = itemView.findViewById(R.id.symptom);

        }
    }
}
