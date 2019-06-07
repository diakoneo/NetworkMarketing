package com.androidapk.diakonetapps.networkmarketing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.androidapk.diakonetapps.networkmarketing.database.Notes;
import com.androidapk.diakonetapps.networkmarketing.database.NotesDbHelper;

public class ReadNotes extends AppCompatActivity {

    private int itemNumber;

    private Notes notes;
    private NotesDbHelper helper;

    private TextView topic, note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_notes);

        helper = new NotesDbHelper(ReadNotes.this);

        if (getIntent().getExtras() != null){
            itemNumber = getIntent().getExtras().getInt("item_number", 0);
            notes = helper.getNoteByID(itemNumber);
        }

        topic = findViewById(R.id.read_topic);
        note = findViewById(R.id.read_note);

        topic.setText(notes.getTopic());
        note.setText(notes.getNote());

        setTitle("Network Marketing - " +notes.getTopic());



    }
}
