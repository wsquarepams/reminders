package com.example.android.reminders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

//import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity implements RecyclerViewOnClickListener{

    // region - variables
    private RecyclerView recyclerView;
    private ReminderAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public static List<Reminder> reminderList = new ArrayList<>();
    public static int currentIndex;
    private Gson gson = new Gson();
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        final Button newReminder = findViewById(R.id.button);
        newReminder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(String.valueOf(this), "Button clicked!");
                currentIndex = -1;
                launchSecondActivity(null);
            }
        });

        final String MyPREFERENCES = "MyPrefs" ;
        SharedPreferences sharedpreferences;
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String reminders = sharedpreferences.getString("Reminder", "[]");

        Type listType = new TypeToken<List<Reminder>>() {}.getType();
        reminderList = gson.fromJson(reminders, listType);

        mAdapter = new ReminderAdapter(reminderList);
        mAdapter.setOnClickListener(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }
//        recyclerView.setHasFixedSize(true);
//
//        // use a linear layout manager
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
//        mAdapter = new Reminder(myDataset);
        //recyclerView.setAdapter(mAdapter);

    public void launchSecondActivity (View v) {
        Log.d(String.valueOf(this),  "Button clicked!");

        Intent intent = new Intent(this, ReminderSetup.class);

        //intent.putExtra(EXTRA_MESSAGE, message);
        //startActivityForResult(intent, TEXT_REQUEST);
        startActivity(intent);
    }
    private void prepareReminderData() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClicked(int index) {
        currentIndex = index;
        launchSecondActivity(null);
    }
}