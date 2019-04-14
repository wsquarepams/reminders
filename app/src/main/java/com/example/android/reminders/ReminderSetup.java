package com.example.android.reminders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReminderSetup extends AppCompatActivity {

    // region - variables
    private EditText description;
    private EditText reminderName;
    //private EditText time;

    private CheckBox timeBased;
    private CheckBox locationBased;
    private CheckBox collab;

    // endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_setup);

        //Start of Finalizing XML layout stuff [
        final Button finishButton = findViewById(R.id.finish);

        description = findViewById(R.id.reminderDescription);
        reminderName = findViewById(R.id.reminderName);
        //time = findViewById(R.id.time);

        timeBased = findViewById(R.id.timeBased);
        locationBased = findViewById(R.id.locationBased);
        collab = findViewById(R.id.collab);
        //End of Finalizing XML layout stuff ]

        finishButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(String.valueOf(this), "Next Activity Called");
                onFinishClicked(null);
            }
        });
    }
    public void onFinishClicked (View v) {
        Log.d(String.valueOf(this), "Button clicked!");
        Reminder reminder = new Reminder();
        Date currentTime = new Date();
        reminder.setName(reminderName.getText().toString());
        reminder.setDescription(description.getText().toString());
        //reminder.setTime()
        reminder.setLatitude(2324.44);
        reminder.setLongitude(3434.45);
        reminder.setTime(currentTime.getTime());

        Reminder yo = new Reminder();
        Date nowTime = new Date();
        yo.setName(reminderName.getText().toString());
        yo.setDescription(description.getText().toString());
        //reminder.setTime()
        yo.setLatitude(2324.44);
        yo.setLongitude(3434.45);
        yo.setTime(nowTime.getTime());

        List<Reminder> reminderList = new ArrayList();
        reminderList.add(reminder);
        reminderList.add(yo);

        Gson gson = new Gson();
        String myJSON = gson.toJson(reminderList);


        Log.d("varCheck", myJSON);

        Intent intent = new Intent(this, MainActivity.class);

        //intent.putExtra(EXTRA_MESSAGE, message);
        //startActivityForResult(intent, TEXT_REQUEST);
        startActivity(intent);
    }

}
