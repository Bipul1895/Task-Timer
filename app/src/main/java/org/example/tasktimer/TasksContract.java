package org.example.tasktimer;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class TasksContract {
    static final String TABLE_NAME = "Tasks";

    public static class Columns {
        public static final String _ID = BaseColumns._ID;
        public static final String TASKS_NAME = "Name";
        public static final String TASKS_DESCRIPTION = "Description";
        public static final String TASKS_SORTORDER = "SortOrder";
    }

    //Other classes will call this constant with their content resolver if they want to access
    //our Tasks table. We are using CONTENT_AUTHORITY_URI constant from AppProvider class
    //To get authority of content resolver and append it with our table name to form URI
    public static final Uri CONTENT_URI = Uri.withAppendedPath(AppProvider.CONTENT_AUTHORITY_URI, TABLE_NAME);

    static Uri buildTaskUri(long taskId) {
        //this method is similar to withAppendedPath, but 2nd arg is long instead of string
        return ContentUris.withAppendedId(CONTENT_URI, taskId);
    }

    //conversion to long of the last segment of the uri
    static long getTaskId(Uri uri) {
        return ContentUris.parseId(uri);
    }

}
