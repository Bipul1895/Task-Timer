package org.example.tasktimer;

import android.provider.BaseColumns;

public class TasksContract {
    private static final String TABLE_NAME = "Tasks";

    public static class Columns {
        private static final String _ID = BaseColumns._ID;
        private static final String TASKS_NAME = "Name";
        private static final String TASKS_DESCRIPTION = "Description";
        public static final String TASKS_SORTORDER = "SortOrder";
    }

}
