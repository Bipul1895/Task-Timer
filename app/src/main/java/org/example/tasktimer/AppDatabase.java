package org.example.tasktimer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * Visibility of the class is "package-private" the only class that should use this class
 * is AppProvider which is Content Provider class that we will write for db access
 */

class AppDatabase extends SQLiteOpenHelper {
    private static final String TAG = "AppDatabase";

    public static final String DATABASE_NAME = "TaskTimer.db";
    public static final int DATABASE_VERSION = 1;

    //Implement AppDatabase class as singleton
    private static AppDatabase instance = null;

    private AppDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //The parameter passed null is actually CursorFactory
        //We will use default Cursors that's why it is null
        Log.d(TAG, "AppDatabase: Constructor");
    }

    /**
     * Get an instance of app's singleton database helper object
     * @param context the content provider's context
     * @return SQLite database helper object
     */
    static AppDatabase getInstance(Context context) {
        if(instance == null) {
            Log.d(TAG, "getInstance: creating a new instance");
            instance = new AppDatabase(context);
        }

        return instance;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate: starts");

        String createSQL ="CREATE TABLE Tasks (_id INTEGER PRIMARY KEY NOT NULL, Name TEXT NOT NULL, Description TEXT, SortOrder INTEGER);";
        sqLiteDatabase.execSQL(createSQL);

        Log.d(TAG, "onCreate: ends");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
