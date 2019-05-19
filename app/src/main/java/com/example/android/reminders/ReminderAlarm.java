package com.example.android.reminders;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.PowerManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ReminderAlarm extends BroadcastReceiver implements LocationUpdateListener
{
    public static List<Reminder> sharedReminderList = new ArrayList<>();
    private Gson gson = new Gson();

    @Override
    public void onReceive(Context context, Intent intent)
    {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();

        // Put here YOUR code.
        //LocationUtil.getLocation(context, this);
        LocationUtil.startLocationUpdatesIfNecessary(context, this);
        //Toast.makeText(context, "ReminderAlarm failed. Error: ErrorNo. 1033: Too lame. <p>Make More interesting.</p>", Toast.LENGTH_LONG).show();

        wl.release();

        final String MyPREFERENCES = "MyPrefs" ;
        SharedPreferences sharedpreferences;
        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String reminders = sharedpreferences.getString("Reminder", "[]");

        Type listType = new TypeToken<List<Reminder>>() {}.getType();
        sharedReminderList= gson.fromJson(reminders, listType);
    }

    public void setAlarm(Context context)
    {
        AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, ReminderAlarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 2, pi); // Millisecond * Second * Minute
    }

    public void cancelAlarm(Context context)
    {
        Intent intent = new Intent(context, ReminderAlarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }

    @Override
    public void locationUpdated(Location location, Context context) {
        Log.d("Location", "Updated lat: " + location.getLatitude() + " long: " + location.getLongitude());
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        checkLocationTriggered(latitude, longitude, context);
    }

    private void checkLocationTriggered(double latitude, double longitude, Context context) {
        for (Reminder r : sharedReminderList) {
            float distance = LocationUtil.getDistanceBetweenTwoPoints(latitude, longitude, r.getLatitude(), r.getLongitude());
            if (distance < 100) {
                Log.d("HEY!", "You have arrived!");
                NotificationUtil.createNotification(context, r);
            }
        }
    }
}