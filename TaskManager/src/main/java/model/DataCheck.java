/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Кристина
 */
public class DataCheck {

    private static final SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    public static boolean nameCheck(String name) {
        return name.length() <= 15;
    }

    public static boolean descriptionCheck(String description) {
        return description.length() <= 30;
    }

    public static boolean contactsCheck(String contacts) {
        return contacts.length() <= 15;
    }

    public static boolean timeCheck(String time) {
        try {
            long curTime = System.currentTimeMillis();
            String curStringDate = dateTimeFormatter.format(curTime);
            int y = Integer.parseInt(time.substring(0, 4));
            int m = Integer.parseInt(time.substring(5, 7));
            int d = Integer.parseInt(time.substring(8, 10));
            int hh = Integer.parseInt(time.substring(11, 13));
            int minut = Integer.parseInt(time.substring(14, 16));

            //текущее время и дата
         /*   int ty = Integer.parseInt(curStringDate.substring(0, 4));
            int tm = Integer.parseInt(curStringDate.substring(5, 7));
            int td = Integer.parseInt(curStringDate.substring(8, 10));
            int thh = Integer.parseInt(curStringDate.substring(11, 13));
            int tminut = Integer.parseInt(curStringDate.substring(14, 16));*/
            int ty = Integer.parseInt(curStringDate.substring(0, 4));
            int tm = Integer.parseInt(curStringDate.substring(5, 7));
            int td = Integer.parseInt(curStringDate.substring(8, 10));
            int thh = Integer.parseInt(curStringDate.substring(11, 13));
            int tminut = Integer.parseInt(curStringDate.substring(14, 16));

            if (y > ty) {
                dateTimeFormatter.parse(time);
                return true;
            } else if (y == ty) {
                if (m > tm) {
                    dateTimeFormatter.parse(time);
                    return true;
                } else if (m == tm) {
                    if (d > td) {
                        dateTimeFormatter.parse(time);
                        return true;
                    } else if (d == td) {
                        if (hh > thh) {
                            dateTimeFormatter.parse(time);
                            return true;
                        } else if (hh == thh) {
                            if (minut > tminut) {
                                dateTimeFormatter.parse(time);
                                return true;
                            }

                        }
                    }
                }
            }
            return false;
        } catch (ParseException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
