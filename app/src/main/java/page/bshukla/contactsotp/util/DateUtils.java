package page.bshukla.contactsotp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Date getCurrentDate() {
        Date currentDate = Calendar.getInstance().getTime();
        return currentDate;
    }

    public static String getFormattedDateString(Date date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
            String dateString = simpleDateFormat.format(date);
            Date newDate = simpleDateFormat.parse(dateString);
            simpleDateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
            return simpleDateFormat.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
