package helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Created by david on 12/01/17.
 */
public class Parse {

    public final static String DATE_TIME_FORMAT = "yyyyMMddHHmmss";

    public static String parseString(LocalDateTime localDateTime) {
        return parseString(localDateTime, DATE_TIME_FORMAT);
    }

    public static String parseString(LocalDateTime localDateTime, String pattern) {
        if(Objects.isNull(localDateTime) || Objects.isNull(pattern)) return null;
        try {
            return DateTimeFormatter.ofPattern(pattern).format(localDateTime);
        } catch(Throwable t) {
            return null;
        }
    }
}
