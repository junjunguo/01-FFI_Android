package com.aprilchun.androidtest.util;

import java.util.TimeZone;

/**
 * Created by chun on 2/19/15.
 */
public class Constant {
    // Key for encryption
    public static final String AES_KEY = "01FFIAndroid2015";

    // URL of the server
    public static final String SERVICE_URL = "http://192.168.2.117:8080";

    // Timezone for time
    public static final TimeZone TIME_ZONE = TimeZone.getTimeZone("Europe/Oslo");

    // Time format
    public static final String TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    // Timeout in ms
    public static final int TIMEOUT = 360000;

}
