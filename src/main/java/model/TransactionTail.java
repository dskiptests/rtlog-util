package model;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.builder.Align;

/**
 * One per THEAD record
 */

@Record
public class TransactionTail implements RTLogRecord {

    public final static String FTTAIL_RECORD_DESCRIPTOR = "FTAIL";
    public final static String TTAIL_RECORD_DESCRIPTOR =  "TTAIL";


    //Identifies file record type
    @Field(ordinal = 1,length=5, align = Align.RIGHT)
    public String record;

    //Sequential file line number
    @Field(ordinal = 2,length=10,format = "0000000000", align = Align.RIGHT)
    public Integer sequence;

    //total number of lines in file, excluding THEAD and TTAIL
    @Field(ordinal = 3,length=10, padding = '0', align = Align.RIGHT)
    public int numberOfTransactionLines;

    public String getRecord() {
        return record;
    }

    public Integer getSequence() {
        return sequence;
    }

}
