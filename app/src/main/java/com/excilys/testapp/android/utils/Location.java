package com.excilys.testapp.android.utils;

import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hrandr on 13/06/14.
 */
public class Location {
    private String iP;
    private Integer port;

    public String getiP() {
        return iP;
    }

    public void setiP(String iP) {
        this.iP = iP;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Location(String iP, Integer port) {
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

}
