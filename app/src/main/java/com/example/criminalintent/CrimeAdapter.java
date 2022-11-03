package com.example.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.DateFormat;
import java.util.List;

public class CrimeAdapter extends RecyclerView.Adapter<CrimeAdapter.CrimeHolder> {

    private final List<Crime> crimes;

    public CrimeAdapter(List<Crime> crimes) {
        this.crimes = crimes;
    }

    @NonNull
    @Override
    public CrimeAdapter.CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new CrimeAdapter.CrimeHolder(layoutInflater, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull CrimeAdapter.CrimeHolder holder, int position) {
        Crime crime = crimes.get(position);
        holder.bind(crime);
    }

    @Override
    public int getItemCount() {
        return crimes.size();
    }

    public static class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        private final TextView titleTextView;
        private final TextView dateTextView;
        private final ImageView solvedImageView;
        private Crime crime;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));
            itemView.setOnClickListener(this);

            titleTextView = itemView.findViewById(R.id.crime_title);
            dateTextView = itemView.findViewById(R.id.crime_date);
            solvedImageView = itemView.findViewById(R.id.crime_solved_ic);
        }

        @Override
        public void onClick (View view){
            Context context = view.getContext();

            Intent intent = CrimeActivity.newIntent(context, crime.getId());
            context.startActivity(intent);
        }

        public void bind (Crime crime){
            this.crime = crime;
            titleTextView.setText(crime.getTitle());
            dateTextView.setText(DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT).format(crime.getDate()));
            solvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);
        }
    }
}