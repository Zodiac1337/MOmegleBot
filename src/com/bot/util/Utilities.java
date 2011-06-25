package com.bot.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by IntelliJ IDEA.
 * User: TeJanca
 * Date: 6/24/11
 * Time: 5:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class Utilities {

    public static void sleep(int toSleep) {
        try {
            long now;
            long start = System.currentTimeMillis();
            Thread.sleep(toSleep);
            while (start + toSleep > (now = System.currentTimeMillis())) {
                Thread.sleep(start + toSleep - now);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String encode(String toEncode) throws UnsupportedEncodingException {
        return URLEncoder.encode(toEncode, "UTF-8");
    }
}
