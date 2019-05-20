package com.example.android.reminders;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.android.reminders.MainActivity.currentIndex;
import static com.example.android.reminders.MainActivity.reminderList;

public class ReminderSetup extends AppCompatActivity {

    // region - variables
    private EditText description;
    private EditText reminderName;
    private EditText startDate;
    private EditText startTime;
    private EditText endDate;
    private EditText endTime;
    //private EditText time;

    private LinearLayout linearLayout;

    private CheckBox timeBased;
    private CheckBox locationBased;
    private CheckBox collab;
    private CheckBox setLocation;

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
        setLocation = findViewById(R.id.currentLocation);
        linearLayout = findViewById(R.id.timeBasedStuff);
        startDate = findViewById(R.id.startDate);
        startTime = findViewById(R.id.startTime);
        endDate = findViewById(R.id.endDate);
        endTime = findViewById(R.id.endTime);

        if (currentIndex > -1) {
            reminderName.setText(reminderList.get(currentIndex).getName());
            description.setText(reminderList.get(currentIndex).getDescription());
            timeBased.setChecked(reminderList.get(currentIndex).getTimeBased());
            if (reminderList.get(currentIndex).getTimeBased()) {
                linearLayout.setVisibility(View.VISIBLE);
                startDate.setText(reminderList.get(currentIndex).getStartDate());
                startTime.setText(reminderList.get(currentIndex).getStartTime());
                endDate.setText(reminderList.get(currentIndex).getEndDate());
                endTime.setText(reminderList.get(currentIndex).getEndTime());
            } else {
                linearLayout.setVisibility(View.GONE);
            }
            delete.setVisibility(View.VISIBLE);
        } else {
            delete.setVisibility(View.GONE);
            linearLayout.setVisibility(View.GONE);
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

        timeBased.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (timeBased.isChecked()) {
                    linearLayout.setVisibility(View.VISIBLE);
                } else {
                    linearLayout.setVisibility(View.GONE);
                }
            }
        });
        startDate.setInputType(InputType.TYPE_NULL);
        startDate.setOnClickListener(new EditTextOnClickListener(startDate, this, true));
        startTime.setInputType(InputType.TYPE_NULL);
        startTime.setOnClickListener(new EditTextOnClickListener(startTime, this, false));
        endDate.setInputType(InputType.TYPE_NULL);
        endDate.setOnClickListener(new EditTextOnClickListener(endDate, this, true));
        endTime.setInputType(InputType.TYPE_NULL);
        endTime.setOnClickListener(new EditTextOnClickListener(endTime, this, false));
    }
    public void onFinishClicked (View v)  {

        Reminder reminder;

        Log.d(String.valueOf(this), "Button clicked!");
        if (currentIndex == -1) {
            reminder = new Reminder();
            reminder.setName(reminderName.getText().toString());
            reminder.setDescription(description.getText().toString());
            if (setLocation.isChecked()){
                reminder.setLatitude(49.2051);
                reminder.setLongitude(-122.78);
            } else {
                reminder.setLatitude(49.2051);
                reminder.setLongitude(-122.78);
            }

            reminder.setLatitude(49.2051);
            reminder.setLongitude(-122.78);
            reminder.setStartDate(startDate.getText().toString());
            reminder.setStartTime(startTime.getText().toString());
            reminder.setEndDate(endDate.getText().toString());
            reminder.setEndTime(endTime.getText().toString());
            reminder.setTimeBased(timeBased.isChecked());

            reminderList.add(reminder);
        } else {
            reminder = reminderList.get(currentIndex);
            reminder.setName(reminderName.getText().toString());
            reminder.setDescription(description.getText().toString());
            reminder.setLatitude(49.2052);
            reminder.setLongitude(-122.78);
            reminder.setStartDate(startDate.getText().toString());
            reminder.setStartTime(startTime.getText().toString());
            reminder.setEndDate(endDate.getText().toString());
            reminder.setEndTime(endTime.getText().toString());
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
        Toast.makeText(ReminderSetup.this,"Saved.",Toast.LENGTH_SHORT).show();
    }

    class EditTextOnClickListener implements View.OnClickListener {

        EditText editText;
        DatePickerDialog datePicker;
        TimePickerDialog timePicker;
        Context context;
        Boolean isDate;
        Calendar cldr;

        public EditTextOnClickListener(EditText editText, Context context, Boolean isDate) {
            this.editText = editText;
            this.context = context;
            this.isDate = isDate;
            this.cldr = Calendar.getInstance();
        }

        @Override
        public void onClick(View v) {
            if (isDate) {
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                datePicker = new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                editText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePicker.show();
            } else {
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minute = cldr.get(Calendar.MINUTE);
                timePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                     @Override
                     public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                         editText.setText("" + hourOfDay + ':' + minute);
                     }
                 },hour, minute, true);
                timePicker.show();
            }
        }
    }
}
