package com.example.whib;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class WhereaboutAdapter extends RecyclerView.Adapter<WhereaboutAdapter.WhereaboutViewHolder> {

    // Define whereabouts array
    private String[] whereabouts;

    // When creating a new adapter; bind whereabouts array to this class
    public WhereaboutAdapter(String[] whereabouts){
        this.whereabouts = whereabouts;
    }

    public static class WhereaboutViewHolder extends RecyclerView.ViewHolder {

        // Define all layout components that are able to be altered
        public TextView whereaboutCardTitle;
        public TextView whereaboutCardProgress;
        public TextView whereaboutCardNote;

        public WhereaboutViewHolder(View v){
            super(v);

            // When making a new WhereAboutViewHolder bind the layout components to the pre-defined variables above
            whereaboutCardTitle = v.findViewById(R.id.whereaboutCardTitle);
            whereaboutCardNote = v.findViewById(R.id.whereaboutCardNote);
            whereaboutCardProgress = v.findViewById(R.id.whereaboutCardProgress);

        }
    }

    @NonNull
    @Override
    public WhereaboutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Make a view; based on the whereabout_card layout
        View v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.whereabout_card, parent, false);

        // Use the view to make a new WhereAboutViewHolder and return it
        WhereaboutViewHolder whereaboutViewHolder = new WhereaboutViewHolder(v);
        return whereaboutViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WhereaboutViewHolder holder, int position) {
        // Set holder content based on the content given inside the whereabouts array; using the given position
        holder.whereaboutCardTitle.setText(whereabouts[position]);
        holder.whereaboutCardNote.setText("Lorem ipsum dolor sit amet consectetur adipiscing elit.");
        holder.whereaboutCardProgress.setText("Soort " + String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return whereabouts.length;
    }
}
