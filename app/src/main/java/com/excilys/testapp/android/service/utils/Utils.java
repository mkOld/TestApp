package com.excilys.testapp.android.service.utils;

import java.util.UUID;

/**
 * Created by excilys on 13/06/14.
 */
public class Utils {

    public static String doUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
