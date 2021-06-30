package com.example.whib;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {
    private String[] reminders;

    public ReminderAdapter(String[] reminders) {
        this.reminders = reminders;
    }

    public static class ReminderViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ReminderViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.textView2);
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
        Log.d("reminderPosition", reminders[position]);
        holder.textView.setText(reminders[position]);
    }

    @Override
    public int getItemCount() {
        return reminders.length;
    }

}
