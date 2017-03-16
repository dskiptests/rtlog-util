package util;

import dto.DiffResult;
import dto.SearchResult;
import exception.ExceptionWrapper;
import exception.RTLogUtilException;
import model.*;
import reader.JsonSerializer;
import reader.RTLogBeanReader;
import reader.RTLogBeanWriter;

import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class RTLogUtil {

    private final RTLogBeanReader reader;
    private final JsonSerializer<RTLogRecord> jsonSerializer;

    public RTLogUtil() {
        this.reader = new RTLogBeanReader();
        this.jsonSerializer = new JsonSerializer<>();
    }

    public List<RTLogRecord> parseFile(final Path path) {
        return this.reader.read(path);
    }

    public List<RTLogRecord> parseFile(final String pathToFile) {
        return this.parseFile(Paths.get(pathToFile));
    }

    public List<SearchResult> findField(final String token, List<RTLogRecord> records) {
        List<SearchResult> results = new ArrayList<>();
        records.stream()
                .map(jsonSerializer::toString)
                .map(jsonSerializer::toMap)
                .filter(map -> map.keySet().contains(token))
                .forEach(map -> results.add(new SearchResult(map.get("record"), map.get("sequence"), map.get(token))));

        return results;
    }


    public String toJson(Object o) {
        return jsonSerializer.toString(o);
    }

    public String toJson(List<Object> o) {
        return jsonSerializer.toString(o);
    }

    public List<DiffResult> calculateDiff(String file1, String file2) {
        final List<RTLogRecord> records1 = reader.read(Paths.get(file1));
        final List<RTLogRecord> records2 = reader.read(Paths.get(file2));


        return this.calculateDiff(records1, records2);
    }

    public static Class getRecordClass(String record) {
        switch (record) {
            case "FHEAD": return FileHeader.class;
            case "THEAD": return TransactionHeader.class;
            case "TOTAL": return TransactionHeader.class;
            case "DCLOSE": return TransactionHeader.class;
            case "TITEM": return TransactionItem.class;
            case "IGTAX": return ItemTax.class;
            case "IDISC": return ItemDiscount.class;
            case "FTAIL": return TransactionTail.class;
            case "TTAIL": return TransactionTail.class;
            case "TTEND": return TransactionTender.class;
        }
        throw new RTLogUtilException("Record ["+record+"] is not supported");
    }

    public List<String> getFields(String record) {
        try {
            return getFields(getRecordClass(record));
        } catch (RTLogUtilException ignored) {
            return new ArrayList<>();
        }
    }

    private List<String> getFields(Class clazz) {
        List<String> fields = new ArrayList<>();
        Arrays.stream(clazz.getDeclaredFields())
                .map(field -> field.getName())
                .forEach(fields::add);
        return fields;
    }

    public List<SearchResult> findContent(final String token, final List<RTLogRecord> records) {
        List<SearchResult> results = new ArrayList<>();
        records.stream().forEach(record -> {
            Arrays.stream(record.getClass().getDeclaredFields())
                    .filter(field -> fieldHasCorrectValue(field, token, record))
                    .filter(Objects::nonNull)
                    .map(Field::getName)
                    .forEach(v -> results.add(new SearchResult(record.getRecord(), record.getSequence(), v)));

        });
        return results;
    }

    private boolean fieldHasCorrectValue(Field f, String token, RTLogRecord rtlogRecord) {
        try {
            return token.equals("" + f.get(rtlogRecord));
        } catch (IllegalAccessException e) {
            return false;
        }
    }

    public List<SearchResult> modify(List<RTLogRecord> records, String sequence, String fieldName, String newFieldValue) {
        try {
            final RTLogRecord record = records.stream()
                    .filter(r -> Objects.equals(r.getSequence(), Integer.valueOf(sequence)))
                    .findFirst()
                    .orElseThrow(RuntimeException::new);

            Field field = record.getClass().getDeclaredField(fieldName);
            field.set(record, newFieldValue);
            saveRecordsToFile(records);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return findField(fieldName, records);
    }

    public void saveRecordsToFile(List<RTLogRecord> records) {
        RTLogBeanWriter rtLogBeanWriter = new RTLogBeanWriter();
        rtLogBeanWriter.toFile(records);
        rtLogBeanWriter.flush();
    }

    public List<RecordType> supportedRecordTypes() {
        return Arrays.asList(RecordType.values());
    }

    public List<RTLogRecord> parseString(String text) {
        return this.reader.read(text);
    }

    public List<DiffResult> calculateDiff(List<RTLogRecord> records1, List<RTLogRecord> records2) {
        final List<DiffResult> diffs = new ArrayList<>();

        if(records1.size() != records2.size()) {
            throw new RTLogUtilException("Files needs to have the same number of rows");
        }

        for(int i = 0; i < records2.size(); i++) {
            RTLogRecord r1 = records1.get(i);
            RTLogRecord r2 = records2.get(i);
            if(r1.getRecord().equals(r2.getRecord()) && r1.getSequence().equals(r2.getSequence())) {
                Field[] fields = r1.getClass().getDeclaredFields();
                for(Field field : fields) {
                    try {
                        String value1 = ("" + field.get(r1)).trim();
                        String value2 = ("" + field.get(r2)).trim();
                        if(! value1.equals(value2)) {
                            diffs.add(new DiffResult(r1.getSequence(), field.getName(), value1, value2));
                        }
                    } catch (Exception e) {
                        System.out.println("e -> " + e.getClass().getSimpleName());
                    }
                }
            } else {
                diffs.add(new DiffResult(r1.getSequence(), null, null, null));
            }
        }
        return diffs;
    }

    public List<ExceptionWrapper> getLog() {
        return reader.getLog();
    }
}
