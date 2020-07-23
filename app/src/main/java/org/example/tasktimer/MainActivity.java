package org.example.tasktimer;

import android.app.Dialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ContentResolver contentResolver = getContentResolver();

        String[] projection = {TasksContract.Columns.TASKS_NAME, TasksContract.Columns.TASKS_DESCRIPTION};

        Cursor cursor = contentResolver.query(TasksContract.CONTENT_URI,
                        projection, null, null ,
                        TasksContract.Columns.TASKS_NAME);

        if(cursor != null) {
            Log.d(TAG, "onCreate: number of rows in the table : " + cursor.getCount());
            while(cursor.moveToNext()) {
                for(int i=0;i<cursor.getColumnCount();i++){
                    Log.d(TAG, "onCreate: " + cursor.getColumnName(i) + cursor.getString(i));
                }
                Log.d(TAG, "onCreate: ==============================");
            }
            cursor.close();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}