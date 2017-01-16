package reader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class JsonSerializer<T> {


    private final ObjectMapper mapper;

    public JsonSerializer() {
        this.mapper = new ObjectMapper();
    }

    public String toString(List<Object> records) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(records);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String toString(Object rtLogRecord) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rtLogRecord);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public HashMap<String,Object> toMap(final String json) {
        try {
            TypeReference<HashMap<String,Object>> typeRef = new TypeReference<HashMap<String,Object>>() {};
            return mapper.readValue(json, typeRef);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Object getResultString(HashMap<String, String> map, String key) {


        return map.get("record") + " " + map.get(key);
    }

}
