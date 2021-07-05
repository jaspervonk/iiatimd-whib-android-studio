package com.iatjt.whib;

import android.view.View;
import android.widget.TextView;

public class GetReminderTask implements Runnable {

    AppDatabase db;
    int uuid;
    View v;

    public GetReminderTask(AppDatabase db, int uuid, View v) {
        this.db = db;
        this.uuid = uuid;
        this.v = v;
    }

    @Override
    public void run() {
        Reminder reminder = db.reminderDAO().GetReminder(this.uuid);

        TextView detailReminderTitleInput = v.findViewById(R.id.detailReminderTitleInput);
        TextView detailReminderDescriptionInput = v.findViewById(R.id.detailReminderDescriptionInput);
        TextView detailReminderDateInput = v.findViewById(R.id.detailReminderDateInput);
        TextView detailReminderTimeInput = v.findViewById(R.id.detailReminderTimeInput);
        TextView detailReminderRepeatInput = v.findViewById(R.id.detailReminderRepeatInput);

        detailReminderTitleInput.setText(reminder.getTitle());
        detailReminderDescriptionInput.setText(reminder.getDescription());
        detailReminderDateInput.setText(reminder.getDate());
        detailReminderTimeInput.setText(reminder.getTime());
        detailReminderRepeatInput.setText(Boolean.toString(reminder.getRepeat()));
    }
}
