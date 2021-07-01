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
    private Whereabout[] whereabouts;

    // When creating a new adapter; bind whereabouts array to this class
    public WhereaboutAdapter(Whereabout[] whereabouts){
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
        String source = "";
        String progress = "";

        // Series
        if(whereabouts[position].getKind() == "Series"){
            holder.whereaboutCardTitle.setText(whereabouts[position].getTitle());
            holder.whereaboutCardNote.setText(whereabouts[position].getNote());
            // Check if there is a source
            if(whereabouts[position].getSource() != ""){
                source = " on " + whereabouts[position].getSource() + " ";
            }
            holder.whereaboutCardProgress.setText("Season " + whereabouts[position].getPart() + " Episode " + whereabouts[position].getProgress() + source);
        }

        // Movie
        if(whereabouts[position].getKind() == "Movie"){
            holder.whereaboutCardTitle.setText(whereabouts[position].getTitle());
            holder.whereaboutCardNote.setText(whereabouts[position].getNote());
            if(whereabouts[position].getProgress() != ""){
                progress = " at " + whereabouts[position].getProgress();
            }
            // Check if there is a source
            if(whereabouts[position].getSource() != ""){
                source = " on " + whereabouts[position].getSource() + " ";
            }
            holder.whereaboutCardProgress.setText("Part " + whereabouts[position].getPart() + progress + source);
        }

        // Book
        if(whereabouts[position].getKind() == "Book"){
            holder.whereaboutCardTitle.setText(whereabouts[position].getTitle());
            holder.whereaboutCardNote.setText(whereabouts[position].getNote());
            holder.whereaboutCardProgress.setText("Part " + whereabouts[position].getPart() + " page " + whereabouts[position].getProgress());
        }
    }

    @Override
    public int getItemCount() {
        return whereabouts.length;
    }
}
