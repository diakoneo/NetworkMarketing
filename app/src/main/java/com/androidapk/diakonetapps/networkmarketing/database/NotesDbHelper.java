package com.androidapk.diakonetapps.networkmarketing.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.androidapk.diakonetapps.networkmarketing.R;
import com.androidapk.diakonetapps.networkmarketing.database.NotesContract.*;

import java.util.ArrayList;
import java.util.List;

public class NotesDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "all";
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
                NotesTable.COLUMN_COURSE + " TEXT, " +
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

        networkMarketing();

        proficiencyOfHardware();

        proficiencyOfSoftware();

        hackersPro();

        microsoftOffice();
    }

    private void networkMarketing(){
        Notes n1 = new Notes("Definition",
                "definition",
                con.getResources().getString(R.string.definition),
                "",
                "network marketing");
        addNote(n1);

        Notes n2 = new Notes("MLM and Pyramid Schemes",
                "mlm_and_pyramid_schemes",
                con.getResources().getString(R.string.mlm_and_pyramid_schemes),
                "",
                "network marketing");
        addNote(n2);

        Notes n3 = new Notes("What is network marketing?",
                "network_marketing",
                con.getResources().getString(R.string.network_marketing),
                "",
                "network marketing");
        addNote(n3);

        Notes n4 = new Notes("How to start network marketing?",
                "start_network_marketing",
                con.getResources().getString(R.string.start_network_marketing),
                "",
                "network marketing");
        addNote(n4);

        Notes n5 = new Notes("Scam or Slam Dunk?",
                "scam_or_slam_dunk",
                con.getResources().getString(R.string.scam_or_slam_dunk),
                "",
                "network marketing");
        addNote(n5);

        Notes n6 = new Notes("Business Model",
                "business_model",
                con.getResources().getString(R.string.business_model),
                "",
                "network marketing");
        addNote(n6);

        Notes n7 = new Notes("The Startup Process",
                "startup_process",
                con.getResources().getString(R.string.startup_process),
                "",
                "network marketing");
        addNote(n7);

        Notes n8 = new Notes("Business Model vs Business Plan",
                "business_model_vrs_plan",
                con.getResources().getString(R.string.business_model_vrs_plan),
                "",
                "network marketing");
        addNote(n8);

        Notes n9 = new Notes("Direct Marketing",
                "direct_marketing",
                con.getResources().getString(R.string.direct_marketing),
                "",
                "network marketing");
        addNote(n9);
    }

    private void proficiencyOfHardware(){
        // proficieny of hardware
        Notes n10 = new Notes("Computer Hardware",
                "computer_hardware",
                con.getResources().getString(R.string.computer_hardware),
                "",
                "proficiency of hardware");
        addNote(n10);

        Notes n11 = new Notes("Bios",
                "bios",
                con.getResources().getString(R.string.bios),
                "",
                "proficiency of hardware");
        addNote(n11);

        Notes n12 = new Notes("Bus",
                "bus",
                con.getResources().getString(R.string.bus),
                "",
                "proficiency of hardware");
        addNote(n12);

        Notes n4 = new Notes("Capacitor",
                "capacitor",
                con.getResources().getString(R.string.capacitor),
                "",
                "proficiency of hardware");
        addNote(n4);

        Notes n5 = new Notes("Power Supply",
                "power_supply",
                con.getResources().getString(R.string.power_supply),
                "",
                "proficiency of hardware");
        addNote(n5);

        Notes n6 = new Notes("Upgrade or Replace",
                "upgrade_or_replace",
                con.getResources().getString(R.string.upgrade_or_replace),
                "",
                "proficiency of hardware");
        addNote(n6);

        Notes n7 = new Notes("Computer System",
                "computer_system",
                con.getResources().getString(R.string.computer_system),
                "",
                "proficiency of hardware");
        addNote(n7);

        Notes n8 = new Notes("Hardware Terms",
                "hardware_terms",
                con.getResources().getString(R.string.hardware_terms),
                "",
                "proficiency of hardware");
        addNote(n8);
    }

    private void proficiencyOfSoftware(){
        Notes n1 = new Notes("ICT Technician",
                "ict_technician",
                con.getResources().getString(R.string.ict_technician),
                "",
                "proficiency of software");
        addNote(n1);

        Notes n2 = new Notes("System Software",
                "system_software",
                con.getResources().getString(R.string.system_software),
                "",
                "proficiency of software");
        addNote(n2);

        Notes n3 = new Notes("Operating System",
                "operating_system",
                con.getResources().getString(R.string.operating_system),
                "",
                "proficiency of software");
        addNote(n3);

        Notes n4 = new Notes("Memory Management",
                "memory_management",
                con.getResources().getString(R.string.memory_management),
                "",
                "proficiency of software");
        addNote(n4);

        Notes n5 = new Notes("Multitasking",
                "multitasking",
                con.getResources().getString(R.string.multitasking),
                "",
                "proficiency of software");
        addNote(n5);

        Notes n6 = new Notes("Device Drivers",
                "device_driver",
                con.getResources().getString(R.string.device_driver),
                "",
                "proficiency of software");
        addNote(n6);

        Notes n7 = new Notes("Networking",
                "pos_networking",
                con.getResources().getString(R.string.pos_networking),
                "",
                "proficiency of software");
        addNote(n7);

        Notes n8 = new Notes("User Interface",
                "user_interface",
                con.getResources().getString(R.string.user_interface),
                "",
                "proficiency of software");
        addNote(n8);

        Notes n9 = new Notes("Operating System Development As A Hobby",
                "os_as_a_hobby",
                con.getResources().getString(R.string.os_as_a_hobby),
                "",
                "proficiency of software");
        addNote(n9);

        Notes n10 = new Notes("Malware",
                "malware",
                con.getResources().getString(R.string.malware),
                "",
                "proficiency of software");
        addNote(n10);

        Notes n11 = new Notes("Hacktivism",
                "hacktivism",
                con.getResources().getString(R.string.hacktivism),
                "",
                "proficiency of software");
        addNote(n11);

        Notes n12 = new Notes("Security Tools",
                "security_tools",
                con.getResources().getString(R.string.security_tools),
                "",
                "proficiency of software");
        addNote(n12);


        Notes n13 = new Notes("Virtual Device Drivers",
                "virtual_device_drivers",
                con.getResources().getString(R.string.virtual_device_driver),
                "",
                "proficiency of software");
        addNote(n13);

        Notes n14 = new Notes("Microsoft Office",
                "microsoft_office",
                con.getResources().getString(R.string.microsoft_office),
                "",
                "proficiency of software");
        addNote(n14);
    }

    private void hackersPro(){
        Notes n1 = new Notes("Hacking",
                "hacking",
                con.getResources().getString(R.string.hacking),
                "",
                "hackers pro");
        addNote(n1);

        Notes n2 = new Notes("Types of Hackers",
                "types_of_hackers",
                con.getResources().getString(R.string.types_of_hackers),
                "",
                "hackers pro");
        addNote(n2);

        Notes n3 = new Notes("Cybercrime",
                "cybercrime",
                con.getResources().getString(R.string.cybercrime),
                "",
                "hackers pro");
        addNote(n3);

        Notes n4 = new Notes("Ethical Hacking",
                "ethical_hacking",
                con.getResources().getString(R.string.ethical_hacking),
                "",
                "hackers pro");
        addNote(n4);

        Notes n5 = new Notes("How to become a Hacker",
                "how_to_become_a_hacker",
                con.getResources().getString(R.string.how_to_become_a_hacker),
                "",
                "hackers pro");
        addNote(n5);

        Notes n6 = new Notes("The Deep Web vrs Dark Web",
                "deep_web_vrs_dark_web",
                con.getResources().getString(R.string.deep_web_vrs_dark_web),
                "",
                "hackers pro");
        addNote(n6);

        Notes n7 = new Notes("What is the Dark Web?",
                "what_is_darkweb",
                con.getResources().getString(R.string.what_is_darkweb),
                "",
                "hackers pro");
        addNote(n7);

        Notes n8 = new Notes("Surface Web and Dark Web",
                "surface_web_and_dark_web",
                con.getResources().getString(R.string.surface_web_and_dark_web),
                "",
                "hackers pro");
        addNote(n8);

        Notes n9 = new Notes("How Do Hackers Get Into Computer Systems?",
                "how_hackers_get_into_pc",
                con.getResources().getString(R.string.how_hackers_get_into_pc),
                "",
                "hackers pro");
        addNote(n9);

        Notes n10 = new Notes("The Fundamental Skills",
                "fundamental_skills",
                con.getResources().getString(R.string.fundamental_skills),
                "",
                "hackers pro");
        addNote(n10);

        Notes n11 = new Notes("Top 15 Ethical Hacking Tools",
                "top_ethical_hacking_tools",
                con.getResources().getString(R.string.top_ethical_hacking_tools),
                "",
                "hackers pro");
        addNote(n11);

        Notes n12 = new Notes("Hacking Tools for Windows",
                "hacking_tools_for_windows",
                con.getResources().getString(R.string.hacking_tools_for_windows),
                "",
                "hackers pro");
        addNote(n12);

        Notes n13 = new Notes("How to Access the Dark Web:",
                "how_to_access_dark_web",
                con.getResources().getString(R.string.how_to_access_dark_web),
                "",
                "hackers pro");
        addNote(n13);

        Notes n14 = new Notes("Dark Web Security Guide",
                "dark_web_security_guide",
                con.getResources().getString(R.string.dark_web_security_guide),
                "",
                "hackers pro");
        addNote(n14);

        Notes n15 = new Notes("Wrapping Up",
                "wrapping_up",
                con.getResources().getString(R.string.wrapping_up),
                "",
                "hackers pro");
        addNote(n15);
    }

    private void microsoftOffice(){
        Notes n1 = new Notes("Microsoft Office",
                "intro_microsoft_office",
                con.getResources().getString(R.string.definition),
                "",
                "microsoft office");
        addNote(n1);

        Notes n2 = new Notes("Desktop Applications",
                "desktop_applications",
                con.getResources().getString(R.string.mlm_and_pyramid_schemes),
                "",
                "microsoft office");
        addNote(n2);

        Notes n3 = new Notes("Windows Versions",
                "windows_versions",
                con.getResources().getString(R.string.network_marketing),
                "",
                "microsoft office");
        addNote(n3);

        Notes n4 = new Notes("Learning About Microsoft Office",
                "learning_about_ms_office",
                con.getResources().getString(R.string.start_network_marketing),
                "",
                "microsoft office");
        addNote(n4);

        Notes n5 = new Notes("Beginners Guide to Microsoft Office",
                "beginners_guide_to_ms_office",
                con.getResources().getString(R.string.scam_or_slam_dunk),
                "",
                "microsoft office");
        addNote(n5);

        Notes n6 = new Notes("How to Use Excel",
                "how_to_use_excel",
                con.getResources().getString(R.string.business_model),
                "",
                "microsoft office");
        addNote(n6);

        Notes n7 = new Notes("Creating Powerpoint",
                "tasks_for_creating_powerpoint",
                con.getResources().getString(R.string.startup_process),
                "",
                "microsoft office");
        addNote(n7);

        Notes n8 = new Notes("Create Database Using Access",
                "tasks_for_creating_access",
                con.getResources().getString(R.string.business_model_vrs_plan),
                "",
                "microsoft office");
        addNote(n8);
    }
    
    private void addNote(Notes notes){
        ContentValues cv = new ContentValues();
        cv.put(NotesTable.COLUMN_TOPIC, notes.getTopic());
        cv.put(NotesTable.COLUMN_TEXT, notes.getNote());
        cv.put(NotesTable.COLUMN_FILE, notes.getFile());
        cv.put(NotesTable.COLUMN_COURSE, notes.getCourse());
        cv.put(NotesTable.COLUMN_IMAGE, notes.getImage());
        db.insert(NotesTable.TABLE_NAME, null, cv);
    }

    public List<Notes> getAllNotes(String cours){
        List<Notes> notesList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + NotesTable.TABLE_NAME +
                " WHERE " + NotesTable.COLUMN_COURSE + " = " + '"' +cours + '"', null);

        if (c.moveToFirst()){
            do {
                Notes notes = new Notes();
                notes.setTopic(c.getString(c.getColumnIndex(NotesTable.COLUMN_TOPIC)));
                notes.setFile(c.getString(c.getColumnIndex(NotesTable.COLUMN_FILE)));
                notes.setNote(c.getString(c.getColumnIndex(NotesTable.COLUMN_TEXT)));
                notes.setCourse(c.getString(c.getColumnIndex(NotesTable.COLUMN_COURSE)));
                notes.setImage(c.getString(c.getColumnIndex(NotesTable.COLUMN_IMAGE)));

                notesList.add(notes);
            } while (c.moveToNext());
        }

        c.close();

        return notesList;
    }

    public Notes getNoteByTopic(String id){
        Notes notes = new Notes();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + NotesTable.TABLE_NAME +
                " WHERE " + NotesTable.COLUMN_TOPIC + " = " + '"' +id + '"', null);

        if (c.moveToFirst()){
            do {
                notes = new Notes();
                notes.setTopic(c.getString(c.getColumnIndex(NotesTable.COLUMN_TOPIC)));
                notes.setFile(c.getString(c.getColumnIndex(NotesTable.COLUMN_FILE)));
                notes.setNote(c.getString(c.getColumnIndex(NotesTable.COLUMN_TEXT)));
                notes.setCourse(c.getString(c.getColumnIndex(NotesTable.COLUMN_COURSE)));
                notes.setImage(c.getString(c.getColumnIndex(NotesTable.COLUMN_IMAGE)));
            } while (c.moveToNext());
        }

        c.close();

        return notes;
    }

}
