package com.excilys.testapp.android.persistence.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by excilys on 13/06/14.
 */
public class MessageDbHelper extends SQLiteOpenHelper {
    private Context context;
    protected static final int VERSION = 2;
    protected static final String DB_NAME = "message.db";
    private final String TAG = MessageDbHelper.class.getSimpleName();

    /**
     * constantes and create/drop queries for fight table
     */
    public static final String MESSAGE_TABLE_NAME = "message";
    public static final String MESSAGE_ID = "id";
    public static final String MESSAGE_CONTENT = "content";
    public static final String MESSAGE_SENDERID = "senderid";
    public static final String CREATE_MESSAGE_TABLE = "CREATE TABLE " + MESSAGE_TABLE_NAME + " ("
            + MESSAGE_ID + " TEXT NOT NULL PRIMARY KEY, "
            + MESSAGE_CONTENT + " TEXT NOT NULL, "
            + MESSAGE_SENDERID + " TEXT NOT NULL, ";
            //+ "CONSTRAINT fk_tournament FOREIGN KEY (" + FIGHT_TOURNAMENT_ID + ") REFERENCES " + TOURNAMENT_TABLE_NAME + "(" + TOURNAMENT_ID + "),"
    public static final String DROP_MESSAGE_TABLE = "DROP TABLE IF EXISTS " + MESSAGE_TABLE_NAME + ";";

    /**
     * SQLiteOpenHelper overidden functions
     */
    public MessageDbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Entering onCreate in MessageDbHelper");
        db.execSQL(CREATE_MESSAGE_TABLE);
        Log.d(TAG, "Leaving onCreate in MessageDbHelper");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "Entering onUpgrade in MessageDbHelper");
        db.execSQL(DROP_MESSAGE_TABLE);
        onCreate(db);
        Log.d(TAG, "Leaving onUpgrade in MessageDbHelper");
    }
}