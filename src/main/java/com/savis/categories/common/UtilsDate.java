package com.savis.categories.common;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilsDate {

    private static final Logger LOGGER = LoggerFactory.getLogger(UtilsDate.class);

    public static Date str2date(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);//"yyyy-MM-dd HH:mm"
        Date date = null;
        try {
            if (StringUtils.isNotBlank(dateStr))
                date = sdf.parse(dateStr);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return date;
    }

    public static String date2str(Date input, String oFormat) {
        String result = "";
        if (input != null) {
            try {
                DateFormat df = new SimpleDateFormat(oFormat);
                result = df.format(input);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static String uptoDate(Date date, int dowMonth, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        c1.add(Calendar.DATE, dowMonth);
        return dateFormat.format(c1.getTime());
    }
    public static String uptoDateTime(Date date, int dowDate, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        c1.roll(Calendar.DATE, dowDate);
        return dateFormat.format(c1.getTime());
    }

}
