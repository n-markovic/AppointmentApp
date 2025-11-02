package poslovne.aplikacije.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Kako bi se vreme i datum pravilno procitali iz zahteva kotisti se ova funkcija za formatiranje
 */
public final class DateTimeUtils {

    private DateTimeUtils() {}

    public static LocalDate parseDate(String s) {
        if (s == null) return null;
        
        try {
            return LocalDate.parse(s);
        } catch (DateTimeParseException ex) {
            
            DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-M-d");
            try {
                return LocalDate.parse(s, f);
            } catch (DateTimeParseException ex2) {
                throw new DateTimeParseException("Invalid date format: " + s + ". Expected yyyy-MM-dd (e.g. 2025-11-03)", s, 0);
            }
        }
    }

    public static LocalTime parseTime(String s) {
        if (s == null) return null;
        try {
            return LocalTime.parse(s);
        } catch (DateTimeParseException ex) {
           
            DateTimeFormatter f = DateTimeFormatter.ofPattern("H:mm");
            try {
                return LocalTime.parse(s, f);
            } catch (DateTimeParseException ex2) {
                throw new DateTimeParseException("Invalid time format: " + s + ". Expected HH:mm (e.g. 14:30)", s, 0);
            }
        }
    }
}
