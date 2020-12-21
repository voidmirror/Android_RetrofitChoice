package com.example.jsonasker;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Properties {

    @SerializedName("date")
    @Expose
    private Date date;
    @SerializedName("milliseconds_since_epoch")
    @Expose
    private MillisecondsSinceEpoch millisecondsSinceEpoch;
    @SerializedName("time")
    @Expose
    private Time time;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public MillisecondsSinceEpoch getMillisecondsSinceEpoch() {
        return millisecondsSinceEpoch;
    }

    public void setMillisecondsSinceEpoch(MillisecondsSinceEpoch millisecondsSinceEpoch) {
        this.millisecondsSinceEpoch = millisecondsSinceEpoch;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

}
