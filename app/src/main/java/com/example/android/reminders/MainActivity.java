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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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
    private TextView noReminders;
    private ImageView arrow;

    public static int oddColor;
    public static int evenColor;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        oddColor = getResources().getColor(R.color.oddIndexColor);
        evenColor = getResources().getColor(R.color.evenIndexColor);

        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        arrow = findViewById(R.id.arrow);
        final ImageButton newReminder = findViewById(R.id.new_reminder);
        final ImageButton settings = findViewById(R.id.settings);

        newReminder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(String.valueOf(this), "Button clicked!");
                currentIndex = -1;
                launchSetupActivity(null);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSettingsActivity(null);
            }
        });

        final String MyPREFERENCES = "MyPrefs" ;
        SharedPreferences sharedpreferences;
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String reminders = sharedpreferences.getString("Reminder", "[]");
        noReminders = findViewById(R.id.noReminders);

        Type listType = new TypeToken<List<Reminder>>() {}.getType();
        reminderList = gson.fromJson(reminders, listType);

        mAdapter = new ReminderAdapter(reminderList);
        mAdapter.setOnClickListener(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        startService(new Intent(this, ReminderService.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (reminderList.size() == 0) {
            noReminders.setVisibility(View.VISIBLE);
            arrow.setVisibility(View.VISIBLE);

        } else {
            noReminders.setVisibility(View.GONE);
            arrow.setVisibility(View.GONE);
        }

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

    public void launchSetupActivity(View v) {
        Log.d(String.valueOf(this),  "Button clicked!");

        Intent intent = new Intent(this, ReminderSetup.class);

        //intent.putExtra(EXTRA_MESSAGE, message);
        //startActivityForResult(intent, TEXT_REQUEST);
        startActivity(intent);
    }

    public void launchSettingsActivity(View v) {
        Log.d(String.valueOf(this),  "Button clicked!");

        Intent intent = new Intent(this, SettingActivity.class);

        //intent.putExtra(EXTRA_MESSAGE, message);
        //startActivityForResult(intent, TEXT_REQUEST);
        startActivity(intent);
    }

    @Override
    public void onItemClicked(int index) {
        currentIndex = index;
        launchSetupActivity(null);
    }
}