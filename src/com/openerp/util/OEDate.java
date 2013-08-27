/*
 * OpenERP, Open Source Management Solution
 * Copyright (C) 2012-today OpenERP SA (<http:www.openerp.com>)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http:www.gnu.org/licenses/>
 * 
 */
package com.openerp.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import android.text.TextUtils;
import android.util.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class OEDate.
 */
public class OEDate {

    /** The time format. */
    static SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");

    /** The date format. */
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM");

    public static String getDate(String date, String toTimezone) {
	Calendar cal = Calendar.getInstance();
	cal.setTimeZone(TimeZone.getTimeZone("GMT-1"));
	Date originalDate = convertToDate(date);
	cal.setTime(originalDate);

	Date oDate = removeTime(originalDate);
	Date today = removeTime(currentDate());

	String finalDateTime = "";
	if (today.compareTo(oDate) > 0) {
	    // sending date
	    finalDateTime = dateFormat.format(oDate);
	} else {
	    // sending time because it's today.
	    finalDateTime = timeFormat
		    .format(convertToTimezone(cal, toTimezone).getTime());
	}
	return finalDateTime;

    }

    /**
     * Gets the date.
     * 
     * @param date
     *            the date
     * @param timezone
     *            the timezone
     * @return the date
     */
    public static String getDate(String date, String toTimezone,
	    String fromTimezone) {
	Calendar cal = Calendar.getInstance();
	cal.setTimeZone(TimeZone.getTimeZone(fromTimezone));
	Date originalDate = convertToDate(date);
	cal.setTime(originalDate);

	Date oDate = removeTime(originalDate);
	Date today = removeTime(currentDate());

	String finalDateTime = "";
	if (today.compareTo(oDate) > 0) {
	    // sending date
	    finalDateTime = dateFormat.format(oDate);
	} else {
	    // sending time because it's today.
	    finalDateTime = timeFormat
		    .format(convertToTimezone(cal, toTimezone).getTime());
	}
	return finalDateTime;

    }

    private static Date currentDate() {
	return new Date();
    }

    /**
     * Convert to date.
     * 
     * @param date
     *            the date
     * @return the date
     */
    private static Date convertToDate(String date) {
	Date dt = null;
	try {
	    dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return dt;
    }

    /**
     * Convert to timezone.
     * 
     * @param cal
     *            the cal
     * @param timezone
     *            the timezone
     * @return the calendar
     */
    private static Calendar convertToTimezone(Calendar cal, String timezone) {

	Calendar localTime = Calendar.getInstance();
	localTime.set(Calendar.HOUR, cal.get(Calendar.HOUR));
	localTime.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
	localTime.set(Calendar.SECOND, cal.get(Calendar.SECOND));

	Calendar convertedTime = new GregorianCalendar(
		TimeZone.getTimeZone(timezone));
	convertedTime.setTimeInMillis(localTime.getTimeInMillis());
	return convertedTime;
    }

    /**
     * Removes the time.
     * 
     * @param date
     *            the date
     * @return the date
     */
    private static Date removeTime(Date date) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	cal.set(Calendar.HOUR_OF_DAY, 0);
	cal.set(Calendar.MINUTE, 0);
	cal.set(Calendar.SECOND, 0);
	cal.set(Calendar.MILLISECOND, 0);
	return cal.getTime();
    }

    public static String getDate() {
	SimpleDateFormat gmtFormat = new SimpleDateFormat();
	gmtFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
	TimeZone gmtTime = TimeZone.getTimeZone("GMT");
	gmtFormat.setTimeZone(gmtTime);
	return gmtFormat.format(new Date());

    }

}
