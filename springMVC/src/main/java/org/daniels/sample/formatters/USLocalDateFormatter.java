package org.daniels.sample.formatters;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by daniels on 26.04.2017.
 */
public class USLocalDateFormatter implements Formatter<LocalDate> {

    static final String US_PATTERN = "MM/dd/yyyy";
    static final String NORMAL_PATTERN = "yyyy-MM-dd";

    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        LocalDate localDate = LocalDate.parse(text, DateTimeFormatter.ofPattern(getPattern(locale)));
        return localDate;
    }

    @Override
    public String print(LocalDate localDate, Locale locale) {
        String value = DateTimeFormatter.ofPattern(getPattern(locale)).format(localDate);
        return value;
    }

    public static String getPattern(Locale locale) {
        return isUnitedStates(locale) ? US_PATTERN : NORMAL_PATTERN;
    }

    public static boolean isUnitedStates(Locale locale) {
        return Locale.US.getCountry().equals(locale.getCountry());
    }

}
