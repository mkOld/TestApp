package com.excilys.testapp.android.utils;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by excilys on 13/06/14.
 */
public class Location implements Parcelable{
    private String iP;
    private String port;

    public String getiP() {
        return iP;
    }

    public void setiP(String iP) {
        this.iP = iP;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Location(String iP, String port) {
        this.iP = iP;
        this.port = port;
    }

    public boolean validateIP(String sIP) {
        String patternIP = "^((([1]?[0-9]?[0-9])|([2]?[0-5]?[0-5]))[.]" +
                "(([1]?[0-9]?[0-9])|([2]?[0-5]?[0-5]))[.]" +
                "(([1]?[0-9]?[0-9])|([2]?[0-5]?[0-5]))[.]" +
                "(([1]?[0-9]?[0-9])|([2]?[0-5]?[0-5])))$";
        Pattern pattern = Pattern.compile(patternIP);
        Matcher matcher = pattern.matcher(sIP);

        // if it's an IP address format
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validatePort(String sPort){
        String patternIP = "^[0-9]*$";
        Pattern pattern = Pattern.compile(patternIP);
        Matcher matcher = pattern.matcher(sPort);

        // if it's an Integer format
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validate(){
        return (validateIP(iP))&&(validatePort(port));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(iP);
        parcel.writeString(port);
    }

    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel source) {
            return new Location(source);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    public Location(Parcel in) {
        iP = in.readString();
        port = in.readString();
    }

    @Override
    public String toString() {
        return "Location{" +
                "iP='" + iP + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}
