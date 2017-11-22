package com.ksu.grad;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Test {
 public static void main (String args[]) throws ParseException {
	 String d1= "06/26/2007";
	 String d2 = "06/27/2007";
	 DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
	 Date fromDate = format.parse(d1);
		Date toDate = format.parse(d2);
		String diff =getTimeDiff(fromDate, toDate);
		int diffhours = Integer.parseInt(diff);
		System.out.println("hours---------"+diffhours );
 }
 
 public static String getTimeDiff(Date dateOne, Date dateTwo) {
     String diff = "";
     long timeDiff = Math.abs(dateOne.getTime() - dateTwo.getTime());
     diff = String.format("%d", TimeUnit.MILLISECONDS.toHours(timeDiff),
             TimeUnit.MILLISECONDS.toMinutes(timeDiff) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeDiff)));
     return diff;
}
}
