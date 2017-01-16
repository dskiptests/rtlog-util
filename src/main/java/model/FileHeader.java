package model;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.builder.Align;

@Record
public class FileHeader implements RTLogRecord {

    @Field(ordinal = 1,length=5)
    public String record = "FHEAD";

    //Sequential file line number
    @Field(ordinal = 2,length=10, format = "0000000000", nillable = true, align = Align.RIGHT)
    public Integer sequence;

    @Field(ordinal = 3,length=4)
    public String fileType = "RTLG";

    //Timestamp when the file was created
    @Field(ordinal = 4,length=14)
    public String createDate;

    //The business date the transactions occurred
    @Field(ordinal = 5,length=8)
    public String businessDate;

    //The location where the transaction occurred.  This means that there must be one file per store.  This will be the store number.
    @Field(ordinal = 6,length=10)
    public String locationNumber;

    //This is user defined to allow you to put any number into the system you want
    @Field(ordinal = 7, length=30, nillable = true)
    public String referenceNumber;


    @Override
    public String getRecord() {
        return record;
    }

    @Override
    public Integer getSequence() {
        return sequence;
    }
}