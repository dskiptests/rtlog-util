package model;

import java.util.Arrays;

/**
 * Created by david on 2017-03-16.
 */
public class ErrorRecord implements RTLogRecord {



    public ErrorRecord(){}

    public ErrorRecord(Exception e, Class record) {
        this.error = e.getClass().getSimpleName();
        this.message = e.getMessage();
        this.clazz = record.getSimpleName();
    }




    @Override
    public String getRecord() {
        return "ERROR_RECORD";
    }

    @Override
    public Integer getSequence() {
        return 0;
    }

    public String error;
    public String message;
    public String clazz;
}
