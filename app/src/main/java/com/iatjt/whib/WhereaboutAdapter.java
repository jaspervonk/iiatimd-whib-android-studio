package com.iatjt.whib;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class WhereaboutAdapter extends RecyclerView.Adapter<WhereaboutAdapter.WhereaboutViewHolder> implements View.OnClickListener, Filterable {

    // Define whereabouts array
    private List<Whereabout> whereabouts;
    // Define whereaboutsFull array to use as filterable array
    private List<Whereabout> whereaboutsFull;

    // When creating a new adapter; bind whereabouts array to this class
    public WhereaboutAdapter(List<Whereabout> whereabouts){
        this.whereabouts = whereabouts;
        // Fill the whereaboutsFull array with a copy of the original array
        whereaboutsFull = new ArrayList<>(whereabouts);
    }

    @Override
    public void onClick(View v) {

    }

    public static class WhereaboutViewHolder extends RecyclerView.ViewHolder {

        // Define all layout components that are able to be altered
        public ConstraintLayout whereaboutCard;
        public TextView whereaboutCardTitle;
        public TextView whereaboutCardProgress;
        public TextView whereaboutCardNote;

        public WhereaboutViewHolder(View v){
            super(v);

            // When making a new WhereAboutViewHolder bind the layout components to the pre-defined variables above
            whereaboutCard = v.findViewById(R.id.whereaboutCard);
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
        // Set holder content based on the content given inside the whereabouts List; using the given position
        String source = "";

        // Series
        if(whereabouts.get(position).getKind().equals("Series")){
            holder.whereaboutCardTitle.setText(whereabouts.get(position).getTitle());
            holder.whereaboutCardNote.setText(whereabouts.get(position).getNote());
            // Check if there is a source
            if(!TextUtils.isEmpty(whereabouts.get(position).getSource())){
                source = " on " + whereabouts.get(position).getSource() + " ";
            }
            holder.whereaboutCardProgress.setText("Season " + whereabouts.get(position).getPart() + " Episode " + whereabouts.get(position).getProgress() + source);
        }

        // Movie
        if(whereabouts.get(position).getKind().equals("Movie")){
            holder.whereaboutCardTitle.setText(whereabouts.get(position).getTitle() + " Part " + whereabouts.get(position).getPart());
            holder.whereaboutCardNote.setText(whereabouts.get(position).getNote());
            // Check if there is a source
            if(!TextUtils.isEmpty(whereabouts.get(position).getSource())){
                source = " on " + whereabouts.get(position).getSource() + " ";
            }
            holder.whereaboutCardProgress.setText("At " + whereabouts.get(position).getProgress() + source);
        }

        // Book
        if(whereabouts.get(position).getKind().equals("Book")){
            holder.whereaboutCardTitle.setText(whereabouts.get(position).getTitle());
            holder.whereaboutCardNote.setText(whereabouts.get(position).getNote());
            // Check if there is a source
            if(!TextUtils.isEmpty(whereabouts.get(position).getSource())){
                source = " on " + whereabouts.get(position).getSource() + " ";
            }
            holder.whereaboutCardProgress.setText("Part " + whereabouts.get(position).getPart() + " page " + whereabouts.get(position).getProgress() + source);
        }

        holder.whereaboutCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toWhereaboutDetailScreenIntent = new Intent(v.getContext(), WhereaboutDetailActivity.class);

                Log.d("ONCLICK ADAPTER", "onClick: " + whereabouts.get(position).getUuid());

                toWhereaboutDetailScreenIntent.putExtra("uuid", whereabouts.get(position).getUuid());
                v.getContext().startActivity(toWhereaboutDetailScreenIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return whereabouts.size();
    }

    // Filterable
    @Override
    public Filter getFilter() {
        return whereaboutsFilter;
    }

    private Filter whereaboutsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            // Make a new filteredList to put in all the items that passed the constraints
            List<Whereabout> filteredList = new ArrayList<>();

            // If there is no constraint; give all results (whereaboutsFull)
            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(whereaboutsFull);
            }
            // If there were constraints
            else {
                // Get the filterText
                String filterPattern = constraint.toString();
                Log.d("performFiltering", "Check everything with: " + constraint.toString());

                // Go through all Whereabouts and only add the ones to the filteredList that passed the constraints
                for (Whereabout item : whereaboutsFull) {
                    Log.d("performFiltering", item.getUuid() + " returns " + Boolean.toString(item.getKind().contains(filterPattern)));
                    if (item.getKind().contains(filterPattern)) {
                        Log.d( "performFiltering", "adding: " + item.getUuid());
                        filteredList.add(item);
                    }
                }
            }

            // Save results in a FilterResults object
            FilterResults results = new FilterResults();
            results.values = filteredList;
            Log.d("performFiltering", "Finished filtering, returning results");

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            whereabouts.clear();
            whereabouts.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


}
