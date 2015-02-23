package edu.ntnu.sair.util;

import java.util.TimeZone;

/**
 * Created by chun on 2/19/15.
 */
public class Constant {

    // Key for encryption
    protected static final String AES_KEY = "01FFIAndroid2015";

    // Timezone for time
    public static final TimeZone TIME_ZONE = TimeZone.getTimeZone("Europe/Oslo");

    // Time format
    public static final String TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    // Unit is hour
    public static final int LOGIN_PERIOD = 48;
}