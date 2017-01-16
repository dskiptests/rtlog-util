package model.handler;

import org.beanio.types.TypeConversionException;
import org.beanio.types.TypeHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BusinessDateHandler implements TypeHandler {

    public final static String PATTERN = "yyyyMMdd";

    @Override
    public Object parse(String text) throws TypeConversionException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
        return LocalDate.parse(text, formatter);
    }

    @Override
    public String format(Object value) {
        if(value instanceof LocalDate){
            LocalDate date = (LocalDate) value;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
            return formatter.format(date);
        }
        return null;
    }

    @Override
    public Class<?> getType() {
        return LocalDate.class;
    }


}
