package com.example.android.reminders;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Date;

import static com.example.android.reminders.MainActivity.currentIndex;
import static com.example.android.reminders.MainActivity.reminderList;

public class ReminderSetup extends AppCompatActivity {

    // region - variables
    private EditText description;
    private EditText reminderName;
    //private EditText time;

    private CheckBox timeBased;
    private CheckBox locationBased;
    private CheckBox collab;

    private Button delete;

    // endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reminder_setup);

        final Button finishButton = findViewById(R.id.finish);


        description = findViewById(R.id.reminderDescription);
        reminderName = findViewById(R.id.reminderName);
        //time = findViewById(R.id.time);
        timeBased = findViewById(R.id.timeBased);
        locationBased = findViewById(R.id.locationBased);
        collab = findViewById(R.id.collab);
        delete = findViewById(R.id.delete);

        if (currentIndex > -1) {
            reminderName.setText(reminderList.get(currentIndex).getName());
            description.setText(reminderList.get(currentIndex).getDescription());
            delete.setVisibility(View.VISIBLE);
        } else {
            delete.setVisibility(View.GONE);
        }

        finishButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(String.valueOf(this), "Next Activity Called");
                onFinishClicked(null);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(String.valueOf(this), "User to Delete");
                onDeleteClicked(null);
            }
        });
    }
    public void onFinishClicked (View v)  {

        Reminder reminder;
        Date currentTime;

        Log.d(String.valueOf(this), "Button clicked!");
        if (currentIndex == -1) {
            reminder = new Reminder();
            currentTime = new Date();
            reminder.setName(reminderName.getText().toString());
            reminder.setDescription(description.getText().toString());
            //reminder.setTime()
            reminder.setLatitude(2324.44);
            reminder.setLongitude(3434.45);
            reminder.setTime(currentTime.getTime());

            reminderList.add(reminder);
        } else {
            reminder = reminderList.get(currentIndex);
            currentTime = new Date();
            reminder.setName(reminderName.getText().toString());
            reminder.setDescription(description.getText().toString());
            //reminder.setTime()
            reminder.setLatitude(2324.44);
            reminder.setLongitude(3434.45);
            reminder.setTime(currentTime.getTime());
        }

        saveList();

        //region - Commented URL items

//        URL url = new URL("http://www.reminderapp.tk/");
//        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//        try {
//            urlConnection.setDoOutput(true);
//            urlConnection.setChunkedStreamingMode(0);
//
//            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
//            writeStream(out);
//
//            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//            readStream(in);
//        } finally {
//            urlConnection.disconnect();
//        }
        //endregion

        onBackPressed();
    }

    public void onDeleteClicked (View v) {
        reminderList.remove(currentIndex);
        saveList();
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void saveList() {
        final String MyPREFERENCES = "MyPrefs" ;
        SharedPreferences sharedpreferences;
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        Gson gson = new Gson();
        String myJSON = gson.toJson(reminderList);


        Log.d("varCheck", myJSON);

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("Reminder", myJSON);
        editor.apply();
        Toast.makeText(ReminderSetup.this,"Saved.",Toast.LENGTH_LONG).show();
    }
}
