package com.example.android.reminders;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class SettingActivity extends AppCompatActivity {

    String darkTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String MyPREFERENCES = "settingsPrefs" ;
        SharedPreferences sharedpreferences;
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        String settingsDarkTheme = sharedpreferences.getString("Settings", "");

        if (settingsDarkTheme.equals("true")) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        final Switch theme = findViewById(R.id.theme);

        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (theme.isChecked()) {
                    darkTheme = "true";

                    SharedPreferences sharedpreferences;
                    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString("Settings", darkTheme);
                    editor.apply();

                    setTheme(R.style.DarkTheme);
                } else {
                    darkTheme = "false";

                    SharedPreferences sharedpreferences;
                    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString("Settings", darkTheme);
                    editor.apply();

                    setTheme(R.style.AppTheme);
                }

                recreate();
            }
        });
    }
}
