package com.example.android.reminders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

//import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    // region - variables
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        final Button newReminder = findViewById(R.id.button);
        newReminder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(String.valueOf(this), "Button clicked!");
                launchSecondActivity(null);
            }
        });
//        recyclerView.setHasFixedSize(true);
//
//        // use a linear layout manager
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
//        mAdapter = new Reminder(myDataset);
        //recyclerView.setAdapter(mAdapter);
    }
    public void launchSecondActivity (View v) {
        Log.d(String.valueOf(this),  "Button clicked!");

        Intent intent = new Intent(this, ReminderSetup.class);

        //intent.putExtra(EXTRA_MESSAGE, message);
        //startActivityForResult(intent, TEXT_REQUEST);
        startActivity(intent);
    }
}
