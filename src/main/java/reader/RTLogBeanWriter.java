package reader;

import helpers.Parse;
import model.*;
import org.beanio.BeanWriter;
import org.beanio.StreamFactory;
import org.beanio.builder.FixedLengthParserBuilder;
import org.beanio.builder.StreamBuilder;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by david on 12/01/17.
 */
public class RTLogBeanWriter<T> {

    private final File file;
    StreamFactory factory;
    private StringWriter stringWriter;
    private BeanWriter writer;

    public RTLogBeanWriter() {
        this.file = new File("output_rtlog_" + Parse.parseString(LocalDateTime.now()));
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.factory = StreamFactory.newInstance();
        this.factory.define(new StreamBuilder("RTLOG_100")
                .format("fixedlength")
                .parser(new FixedLengthParserBuilder())
                .addRecord(FileHeader.class)
                .addRecord(TransactionHeader.class)
                .addRecord(TransactionItem.class)
                .addRecord(ItemTax.class)
                .addRecord(ItemDiscount.class)
                .addRecord(TransactionTail.class)
                .addRecord(TransactionTender.class));
        this.writer = factory.createWriter("RTLOG_100", this.file);
    }

    public void toFile(List<RTLogRecord> records) {
        records.stream().forEach(this::mapToRecord);
    }

    private RTLogRecord mapToRecord(RTLogRecord record) {
        switch (record.getRecord()) {
            case "FHEAD": return mapRecord(FileHeader.class, record);
            case "THEAD": return mapRecord(TransactionHeader.class, record);
            case "TITEM": return mapRecord(TransactionItem.class, record);
            case "IGTAX": return mapRecord(ItemTax.class, record);
            case "IDISC": return mapRecord(ItemDiscount.class, record);
            case "FTAIL": return mapRecord(TransactionTail.class, record);
            case "TTAIL": return mapRecord(TransactionTail.class, record);
            case "TTEND": return mapRecord(TransactionTender.class, record);
        }
        throw new RuntimeException("Record ["+record.getRecord()+"] is not supported");
    }

    private RTLogRecord mapRecord(Class clazz, RTLogRecord record) {
        writer.write(record);
        return record;
    }

    public void flush() {
        writer.flush();
        writer.close();
    }
}
