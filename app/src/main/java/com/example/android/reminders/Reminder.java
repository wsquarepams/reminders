package com.example.android.reminders;

public class Reminder {
    private double latitude;
    private double longitude;
    private long time;
    private String name;
    private String description;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", time=" + time +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
