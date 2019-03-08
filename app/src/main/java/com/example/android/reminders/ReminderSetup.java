package com.example.android.reminders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class ReminderSetup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_setup);

        //Start of Finalizing XML layout stuff
        final Button nextAction = findViewById(R.id.finish);

        final EditText description = findViewById(R.id.reminderDescription);

        final CheckBox timeBased = findViewById(R.id.timeBased);
        final CheckBox locationBased = findViewById(R.id.locationBased);
        final CheckBox collab = findViewById(R.id.collab);
        //End of Finalizing XML layout stuff

        nextAction.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(String.valueOf(this), "Next Activity Called");
                launchSecondActivity(null);
            }
        });
    }
    public void launchSecondActivity (View v) {
        Log.d(String.valueOf(this), "Button clicked!");

        Intent intent = new Intent(this, ReminderSetup.class);

        //intent.putExtra(EXTRA_MESSAGE, message);
        //startActivityForResult(intent, TEXT_REQUEST);
        startActivity(intent);
    }

}
