package com.androidapk.diakonetapps.networkmarketing.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.androidapk.diakonetapps.networkmarketing.R;
import com.androidapk.diakonetapps.networkmarketing.database.NotesContract.*;

import java.util.ArrayList;
import java.util.List;

public class NotesDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "network_marketing";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    private Context con;

    public NotesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        con = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_NOTES_TABLE = "CREATE TABLE " +
                NotesTable.TABLE_NAME + " ( " +
                NotesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NotesTable.COLUMN_TOPIC + " TEXT, " +
                NotesTable.COLUMN_FILE + " TEXT, " +
                NotesTable.COLUMN_TEXT + " TEXT, " +
                NotesTable.COLUMN_IMAGE + " TEXT " +
                ")";

        db.execSQL(SQL_CREATE_NOTES_TABLE);
        fillNotesTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NotesTable.TABLE_NAME);
        onCreate(db);
    }

    // Uri.parse("android.resource://com....../drawable/" + filename)
    private void fillNotesTable() {
        Notes n1 = new Notes("Definition",
                 "definition",
                 con.getResources().getString(R.string.definition),
                "");
        addNote(n1);

        Notes n2 = new Notes("MLM and Pyramid Schemes",
                "mlm_and_pyramid_schemes",
                con.getResources().getString(R.string.mlm_and_pyramid_schemes),
                "");
        addNote(n2);

        Notes n3 = new Notes("What is network marketing?",
                "network_marketing",
                con.getResources().getString(R.string.network_marketing),
                "");
        addNote(n3);

        Notes n4 = new Notes("How to start network marketing?",
                "start_network_marketing",
                con.getResources().getString(R.string.start_network_marketing),
                "");
        addNote(n4);

        Notes n5 = new Notes("Scam or Slam Dunk?",
                "scam_or_slam_dunk",
                con.getResources().getString(R.string.scam_or_slam_dunk),
                "");
        addNote(n5);

        Notes n6 = new Notes("Business Model",
                "business_model",
                con.getResources().getString(R.string.business_model),
                "");
        addNote(n6);

        Notes n7 = new Notes("The Startup Process",
                "startup_process",
                con.getResources().getString(R.string.startup_process),
                "");
        addNote(n7);

        Notes n8 = new Notes("Business Model vs Business Plan",
                "business_model_vrs_plan",
                con.getResources().getString(R.string.business_model_vrs_plan),
                "");
        addNote(n8);

        Notes n9 = new Notes("Direct Marketing",
                "direct_marketing",
                con.getResources().getString(R.string.direct_marketing),
                "");
        addNote(n9);
    }

    private void addNote(Notes notes){
        ContentValues cv = new ContentValues();
        cv.put(NotesTable.COLUMN_TOPIC, notes.getTopic());
        cv.put(NotesTable.COLUMN_TEXT, notes.getNote());
        cv.put(NotesTable.COLUMN_FILE, notes.getFile());
        cv.put(NotesTable.COLUMN_IMAGE, notes.getImage());
        db.insert(NotesTable.TABLE_NAME, null, cv);
    }

    public List<Notes> getAllNotes(){
        List<Notes> notesList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + NotesTable.TABLE_NAME, null);

        if (c.moveToFirst()){
            do {
                Notes notes = new Notes();
                notes.setTopic(c.getString(c.getColumnIndex(NotesTable.COLUMN_TOPIC)));
                notes.setFile(c.getString(c.getColumnIndex(NotesTable.COLUMN_FILE)));
                notes.setNote(c.getString(c.getColumnIndex(NotesTable.COLUMN_TEXT)));
                notes.setImage(c.getString(c.getColumnIndex(NotesTable.COLUMN_IMAGE)));

                notesList.add(notes);
            } while (c.moveToNext());
        }

        c.close();
        return notesList;
    }

    public Notes getNoteByID(int id){
        Notes notes = new Notes();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + NotesTable.TABLE_NAME + " WHERE _id = " + id, null);

        if (c.moveToFirst()){
            do {
                notes = new Notes();
                notes.setTopic(c.getString(c.getColumnIndex(NotesTable.COLUMN_TOPIC)));
                notes.setFile(c.getString(c.getColumnIndex(NotesTable.COLUMN_FILE)));
                notes.setNote(c.getString(c.getColumnIndex(NotesTable.COLUMN_TEXT)));
                notes.setImage(c.getString(c.getColumnIndex(NotesTable.COLUMN_IMAGE)));
            } while (c.moveToNext());
        }

        c.close();

        return notes;
    }

}
