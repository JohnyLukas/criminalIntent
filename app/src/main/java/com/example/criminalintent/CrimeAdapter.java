package com.example.criminalintent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.List;

public class CrimeAdapter extends RecyclerView.Adapter<CrimeAdapter.CrimeHolder> {

    private final List<Crime> mCrimes;

    public  CrimeAdapter(List<Crime> crimes) {
        mCrimes = crimes;
    }

    @NonNull
    @Override
    public CrimeAdapter.CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new CrimeAdapter.CrimeHolder(layoutInflater, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull CrimeAdapter.CrimeHolder holder, int position) {
        Crime crime = mCrimes.get(position);
        holder.bind(crime);
    }

    @Override
    public int getItemCount() {
        return mCrimes.size();
    }

    public static class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView titleTextView;
        private final TextView dateTextView;
        private final ImageView solvedImageView;
        private Crime mCrime;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));
            itemView.setOnClickListener(this);

            titleTextView = itemView.findViewById(R.id.crime_title);
            dateTextView = itemView.findViewById(R.id.crime_date);
            solvedImageView = itemView.findViewById(R.id.crime_solved_ic);
        }

        @Override
        public void onClick (View view){
            Toast.makeText(view.getContext(), mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT).show();
        }

        public void bind (Crime crime){
            mCrime = crime;
            titleTextView.setText(mCrime.getTitle());
            dateTextView.setText(DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT).format(mCrime.getDate()));
            solvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);
        }
    }
}
