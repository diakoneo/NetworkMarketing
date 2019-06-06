package com.androidapk.diakonetapps.networkmarketing.database;

import android.provider.BaseColumns;

public final class NotesContract {

    private NotesContract(){}

    public static class NotesTable implements BaseColumns {
        public static final String TABLE_NAME = "notes";
        public static final String COLUMN_TOPIC = "topic";
        public static final String COLUMN_TEXT = "text";
        public static final String COLUMN_IMAGE = "image";
    }
}
