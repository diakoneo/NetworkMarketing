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
                "Network marketing is a referral-based sales business. A company will offer a product or a" +
                        "service and recruit others to sell it for them. Sales reps, who are independent contractors and not" +
                        "employees, are paid commissions for what they sell. If they recruit other reps, then they‘ll also" +
                        "earn a portion of their sales, too.",
                "");
        addNote(n3);

        Notes n4 = new Notes("How to start network marketing?",
                "start_network_marketing",
                "Network marketing is a referral-based sales business. A company will offer a product or a" +
                        "service and recruit others to sell it for them. Sales reps, who are independent contractors and not" +
                        "employees, are paid commissions for what they sell. If they recruit other reps, then they‘ll also" +
                        "earn a portion of their sales, too.",
                "");
        addNote(n4);

        Notes n5 = new Notes("Scam or Slam Dunk?",
                "scam_or_slam_dunk",
                "The greatest advantage of multi-level marketing is that you can build a thriving business" +
                        "without having to develop a product or a brand.",
                "");
        addNote(n5);

        Notes n6 = new Notes("Business Model",
                "business_model",
                "The greatest advantage of multi-level marketing is that you can build a thriving business" +
                        "without having to develop a product or a brand.",
                "");
        addNote(n6);
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
