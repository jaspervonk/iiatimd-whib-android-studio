package com.example.whib;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {
    private List<Reminder> reminders;

    public ReminderAdapter(List<Reminder> reminders) {
        this.reminders = reminders;
    }

    public static class ReminderViewHolder extends RecyclerView.ViewHolder {
        public TextView reminderCardTitle;
        public TextView reminderCardDescription;
        public TextView reminderCardConcurrence;

        public ReminderViewHolder(View v) {
            super(v);
            reminderCardTitle = v.findViewById(R.id.reminderCardTitle);
            reminderCardDescription = v.findViewById(R.id.reminderCardDescription);
            reminderCardConcurrence = v.findViewById(R.id.reminderCardConcurrence);
        }
    }

    @NonNull
    @Override
    public ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_card, parent, false);
        ReminderViewHolder reminderViewHolder = new ReminderViewHolder(v);
        return reminderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderViewHolder holder, int position) {
        String dateTime = reminders.get(position).getDate() + " " + reminders.get(position).getTime();

        holder.reminderCardTitle.setText(reminders.get(position).getTitle());
        holder.reminderCardDescription.setText(reminders.get(position).getDescription());
        holder.reminderCardConcurrence.setText(dateTime);
    }

    @Override
    public int getItemCount() {
        return reminders.size();
    }

}
