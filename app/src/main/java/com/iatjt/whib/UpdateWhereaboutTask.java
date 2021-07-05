package com.iatjt.whib;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateWhereaboutTask implements Runnable {

    AppDatabase db;
    int uuid;
    View v;

    public UpdateWhereaboutTask(AppDatabase db, int uuid, View v) {
        this.db = db;
        this.uuid = uuid;
        this.v = v;
    }

    @Override
    public void run() {
        // Get whereabout
        Whereabout whereabout = db.whereaboutDAO().GetWhereabout(this.uuid);

        EditText detailWhereaboutTitleEditInput = v.findViewById(R.id.detailWhereaboutTitleEditInput);
        EditText detailWhereaboutPartEditInput = v.findViewById(R.id.detailWhereaboutPartEditInput);
        EditText detailWhereaboutProgressEditInput = v.findViewById(R.id.detailWhereaboutProgressEditInput);
        EditText detailWhereaboutSourceEditInput = v.findViewById(R.id.detailWhereaboutSourceEditInput);
        EditText detailWhereaboutNoteEditInput = v.findViewById(R.id.detailWhereaboutNoteEditInput);

        whereabout.setTitle(detailWhereaboutTitleEditInput.getText().toString());
        whereabout.setPart(Integer.parseInt(detailWhereaboutPartEditInput.getText().toString()));
        whereabout.setProgress(detailWhereaboutProgressEditInput.getText().toString());
        whereabout.setSource(detailWhereaboutSourceEditInput.getText().toString());
        whereabout.setNote(detailWhereaboutNoteEditInput.getText().toString());

        db.whereaboutDAO().UpdateWhereabout(whereabout);

        TextView detailWhereaboutTitleInput = v.findViewById(R.id.detailWhereaboutTitleInput);
        TextView detailWhereaboutPartInput = v.findViewById(R.id.detailWhereaboutPartInput);
        TextView detailWhereaboutProgressInput = v.findViewById(R.id.detailWhereaboutProgressInput);
        TextView detailWhereaboutSourceInput = v.findViewById(R.id.detailWhereaboutSourceInput);
        TextView detailWhereaboutNoteInput = v.findViewById(R.id.detailWhereaboutNoteInput);

        detailWhereaboutTitleInput.setText(detailWhereaboutTitleEditInput.getText());
        detailWhereaboutPartInput.setText(detailWhereaboutPartEditInput.getText());
        detailWhereaboutProgressInput.setText(detailWhereaboutProgressEditInput.getText());
        detailWhereaboutSourceInput.setText(detailWhereaboutSourceEditInput.getText());
        detailWhereaboutNoteInput.setText(detailWhereaboutNoteEditInput.getText());
    }
}
