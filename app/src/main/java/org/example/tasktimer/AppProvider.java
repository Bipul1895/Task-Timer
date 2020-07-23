package org.example.tasktimer;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Provider for our app. This is the only class that knows about (interacts with) {@link AppDatabase}
 */

public class AppProvider extends ContentProvider {
    private static final String TAG = "AppProvider";

    private AppDatabase mOpenHelper;

    private static final String CONTENT_AUTHORITY = "org.example.tasktimer.provider";
    //This is the URI in notes2 - Content Provider
    public static final Uri CONTENT_AUTHORITY_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private UriMatcher sUriMatcher = buildUriMatcher();

    private static final int TASKS = 100;
    private static final int TASKS_ID = 101;

    private static final int TIMINGS = 200;
    private static final int TIMINGS_ID = 201;

    private static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        //eg. contents://org.example.tasktimer.provider/Tasks
        uriMatcher.addURI(CONTENT_AUTHORITY, TasksContract.TABLE_NAME, TASKS); // for table TASKS
        //eg. contents://org.example.tasktimer.provider/Tasks/1
        uriMatcher.addURI(CONTENT_AUTHORITY, TasksContract.TABLE_NAME + "/#" , TASKS_ID);// for rows of table TASKS


        //uriMatcher.addURI(CONTENT_AUTHORITY, TimingsContract.TABLE_NAME, TIMINGS);
        //uriMatcher.addURI(CONTENT_AUTHORITY, TimingsContract.TABLE_NAME + "/#", TIMINGS_ID);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = AppDatabase.getInstance(getContext());
        return true;
    }

    //Content resolver will call this method
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        int match = sUriMatcher.match(uri);

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        switch (match) {
            case TASKS:
                queryBuilder.setTables(TasksContract.TABLE_NAME);
                break;
            case TASKS_ID:
                queryBuilder.setTables(TasksContract.TABLE_NAME);
                long taskId = TasksContract.getTaskId(uri);
                queryBuilder.appendWhere(TasksContract.Columns._ID + " = " + taskId);
                break;

                /*
            case TIMINGS:
                queryBuilder.setTables(TimingsContract.TABLE_NAME);
                break;
            case TIMINGS_ID:
                queryBuilder.setTables(TimingsContract.TABLE_NAME);
                long timingId;
                queryBuilder.appendWhere(TimingsContract.Columns._ID + " = " + timingId);
                break;

                 */

                /*
            case DURATIONS:
                queryBuilder.setTables(TimingsContract.TABLE_NAME);
                break;
            case DURATIONS_ID:
                queryBuilder.setTables(TimingsContract.TABLE_NAME);
                long timingId;
                queryBuilder.appendWhere(TimingsContract.Columns._ID + " = " + timingId);
                break;
                 */

            default:
                throw new IllegalArgumentException("Unknown URI : " + uri);

        }

        SQLiteDatabase sqLiteDatabase = mOpenHelper.getReadableDatabase();
        return queryBuilder.query(sqLiteDatabase, projection, selection, selectionArgs, null, null, sortOrder);

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
