package model.handler;

import org.beanio.types.TypeConversionException;
import org.beanio.types.TypeHandler;

import java.math.BigDecimal;

public class BigDecimalHandler implements TypeHandler {

    @Override
    public Object parse(String text) throws TypeConversionException {
        return BigDecimal.TEN;
    }

    @Override
    public String format(Object value) {
        String bigDecimalAsString = "";
        if(value instanceof BigDecimal) {
            bigDecimalAsString = ((BigDecimal) value)
                    .multiply(new BigDecimal(10000))
                    .setScale(0, BigDecimal.ROUND_HALF_DOWN)
                    .toPlainString();
        }
        return bigDecimalAsString;
    }

    @Override
    public Class<?> getType() {
        return BigDecimal.class;
    }
}
