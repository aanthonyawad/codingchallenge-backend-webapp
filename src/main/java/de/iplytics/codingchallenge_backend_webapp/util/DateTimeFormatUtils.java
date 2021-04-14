package de.iplytics.codingchallenge_backend_webapp.util;

import de.iplytics.codingchallenge_backend_webapp.exception.InvalidArgumentException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatUtils {
    public  static String DateToString(LocalDate localDate){
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDate.format(formatter);
    }

    public static LocalDate StringToDate(String date) {
        if(StringUtility.isEmptyOrNull(date)){
            throw new InvalidArgumentException("publicatondate required");
        }
        try{

            DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(date,formatter);
        }catch (Exception e){
            throw new InvalidArgumentException("publicatondate format yyyy-MM-dd");
        }
    }
}
