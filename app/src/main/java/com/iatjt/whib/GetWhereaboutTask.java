package com.iatjt.whib;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class GetWhereaboutTask implements Runnable {

    AppDatabase db;
    int uuid;
    View v;

    public GetWhereaboutTask(AppDatabase db, int uuid, View v) {
        this.db = db;
        this.uuid = uuid;
        this.v = v;
    }

    @Override
    public void run() {
        // Get whereabout
        Whereabout whereabout = db.whereaboutDAO().GetWhereabout(this.uuid);

        // Set the right Titles based on the kind of whereabout
        String kind = whereabout.getKind();
        TextView detailWhereaboutPart = v.findViewById(R.id.detailWhereaboutPart);
        TextView detailWhereaboutProgress = v.findViewById(R.id.detailWhereaboutProgress);

        switch(kind){
            case "Series":
                detailWhereaboutPart.setText(R.string.detail_whereabout_part_series);
                detailWhereaboutProgress.setText(R.string.detail_whereabout_progress_series);
                break;
            case "Movie":
                detailWhereaboutPart.setText(R.string.detail_whereabout_part_movie);
                detailWhereaboutProgress.setText(R.string.detail_whereabout_progress_movie);
                break;
            case "Book":
                detailWhereaboutPart.setText(R.string.detail_whereabout_part_book);
                detailWhereaboutProgress.setText(R.string.detail_whereabout_progress_book);
                break;
        }

        // Set whereabout details into TextViews
        TextView detailWhereaboutTitleInput = v.findViewById(R.id.detailWhereaboutTitleInput);
        TextView detailWhereaboutPartInput = v.findViewById(R.id.detailWhereaboutPartInput);
        TextView detailWhereaboutProgressInput = v.findViewById(R.id.detailWhereaboutProgressInput);
        TextView detailWhereaboutSourceInput = v.findViewById(R.id.detailWhereaboutSourceInput);
        TextView detailWhereaboutNoteInput = v.findViewById(R.id.detailWhereaboutNoteInput);

        EditText detailWhereaboutTitleEditInput = v.findViewById(R.id.detailWhereaboutTitleEditInput);
        EditText detailWhereaboutPartEditInput = v.findViewById(R.id.detailWhereaboutPartEditInput);
        EditText detailWhereaboutProgressEditInput = v.findViewById(R.id.detailWhereaboutProgressEditInput);
        EditText detailWhereaboutSourceEditInput = v.findViewById(R.id.detailWhereaboutSourceEditInput);
        EditText detailWhereaboutNoteEditInput = v.findViewById(R.id.detailWhereaboutNoteEditInput);

        detailWhereaboutTitleInput.setText(whereabout.getTitle());
        detailWhereaboutPartInput.setText(String.valueOf(whereabout.getPart()));
        detailWhereaboutProgressInput.setText(whereabout.getProgress());
        detailWhereaboutSourceInput.setText(whereabout.getSource());
        detailWhereaboutNoteInput.setText(whereabout.getNote());

        detailWhereaboutTitleEditInput.setText(whereabout.getTitle());
        detailWhereaboutPartEditInput.setText(String.valueOf(whereabout.getPart()));
        detailWhereaboutProgressEditInput.setText(whereabout.getProgress());
        detailWhereaboutSourceEditInput.setText(whereabout.getSource());
        detailWhereaboutNoteEditInput.setText(whereabout.getNote());
    }
}
