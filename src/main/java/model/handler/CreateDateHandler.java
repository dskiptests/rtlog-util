package model.handler;

import org.beanio.types.TypeConversionException;
import org.beanio.types.TypeHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class CreateDateHandler implements TypeHandler{

    public final static String PATTERN = "yyyyMMddHHmmss";


    @Override
    public Object parse(String text) throws TypeConversionException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
        return LocalDateTime.parse(text, formatter);
    }

    @Override
    public String format(Object value) {
        if(value instanceof LocalDateTime){
            LocalDateTime date = (LocalDateTime) value;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
            return formatter.format(date);
        }
        return null;
    }

    @Override
    public Class<?> getType() {
        return LocalDateTime.class;
    }

}
