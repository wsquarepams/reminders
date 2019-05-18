package com.example.android.reminders;

import android.content.Context;
import android.location.Location;

public interface LocationUpdateListener {
    void locationUpdated(Location location, Context context);
}
