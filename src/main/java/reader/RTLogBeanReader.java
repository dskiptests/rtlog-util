package reader;

import exception.ExceptionWrapper;
import model.*;
import model.handler.BigDecimalHandler;
import model.handler.BusinessDateHandler;
import model.handler.CreateDateHandler;
import org.beanio.StreamFactory;
import org.beanio.Unmarshaller;
import org.beanio.builder.FixedLengthParserBuilder;
import org.beanio.builder.StreamBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class RTLogBeanReader {

    private final static String name = "Elvis";
    private ArrayList<ExceptionWrapper> logger;
    private String currentRow;

    public RTLogBeanReader() {

    }

    public List<RTLogRecord> read(Path path) {
        List<RTLogRecord> records = new ArrayList<>();
        try (Stream<String> stream = Files.lines(path)) {
            stream.map(this::mapToRecord)
                    .forEach(records::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    private RTLogRecord mapToRecord(final String row) {
        final String recordDescriptor = row.substring(0, 5);
        switch (recordDescriptor) {
            case "FHEAD": return mapRecord(FileHeader.class, row);
            case "THEAD": return mapRecord(TransactionHeader.class, row);
            case "TOTAL": return mapRecord(TransactionHeader.class, row);
            case "DCLOSE": return mapRecord(TransactionHeader.class, row);
            case "TITEM": return mapRecord(TransactionItem.class, row);
            case "IGTAX": return mapRecord(ItemTax.class, row);
            case "IDISC": return mapRecord(ItemDiscount.class, row);
            case "FTAIL": return mapRecord(TransactionTail.class, row);
            case "TTAIL": return mapRecord(TransactionTail.class, row);
            case "TTEND": return mapRecord(TransactionTender.class, row);
        }
        throw new RuntimeException("Record ["+recordDescriptor+"] is not supported");
    }

    private <T> T mapRecord(Class<T> record, String data) {
        final StreamFactory factory = StreamFactory.newInstance();
        factory.define(new StreamBuilder(name)
                .format("fixedlength")
                .parser(new FixedLengthParserBuilder())
                .addTypeHandler("CreateDateHandler", new CreateDateHandler())
                .addTypeHandler("BusinessDateHandler", new BusinessDateHandler())
                .addTypeHandler("BigDecimalHandler", new BigDecimalHandler())
                .addRecord(record));
        Unmarshaller unmarshaller = factory.createUnmarshaller(name);
        return (T) unmarshaller.unmarshal(data);
    }




    public List<RTLogRecord> read(String text) {
        this.currentRow = null;
        logger = new ArrayList<ExceptionWrapper>();
        List<RTLogRecord> records = new ArrayList<>();
        try (Stream<String> stream = Arrays.stream(text.split("\n"))) {
            stream.map(this::setCurrentRow)
                    .map(this::mapToRecord)
                    .forEach(records::add);
        } catch (Exception e) {
            this.logger.add(new ExceptionWrapper(e, currentRow));
            e.printStackTrace();
        }
        return records;
    }

    private String setCurrentRow(String r) {
        this.currentRow = r;
        return r;
    }

    public List<ExceptionWrapper> getLog() {
        if(Objects.isNull(this.logger)) {
            this.logger = new ArrayList<>();
        }
        return this.logger;
    }
}
