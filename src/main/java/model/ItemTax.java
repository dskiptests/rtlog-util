package model;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * Vat/Tax record specific to the TITEM record – Multiple allowed per item
 */
@Record
public class ItemTax implements RTLogRecord {


    public final static String RECORD_DESCRIPTOR = "IGTAX";

    //Identifies file record type
    @Field(ordinal = 1,length=5)
    public String record;

    //Sequential file line number
    @Field(ordinal = 2,length=10, format = "0000000000")
    public Integer sequence;

    //The taxing authority for this tax
    @Field(ordinal = 3,length=10, format = "0000000000")
    public Long taxAuthority;

    //IG tax code to represent whether it is a city, state or other tax code Valid values in CODE_DETAIL where code type is 'TAXC'
    @Field(ordinal = 4,length=6)
    public String igTaxCode;

    //Tax rate with four implied decimal places
    @Field(ordinal = 5,length=20,format = "00000000000000000000")
    public Long igTaxRate;

    //IG tax code to represent whether it is a city, state or other tax code Valid values in CODE_DETAIL where code type is 'TAXC'
    @Field(ordinal = 6,length=1)
    public String salesSign;

    //Total tax amount with five implied decimal places.
    @Field(ordinal = 7,length=21, format = "000000000000000000000")
    public Long iGTaxAmount;

    //User defined value associated with this item tax
    @Field(ordinal = 8,length=30,nillable = true)
    public String refNo21;

    //User defined value associated with this item tax
    @Field(ordinal = 9,length=30,nillable = true)
    public String refNo22;

    //User defined value associated with this item tax
    @Field(ordinal = 10,length=30,nillable = true)
    public String refNo23;

    //User defined value associated with this item tax
    @Field(ordinal = 11,length=30,nillable = true)
    public String refNo24;

    public String getRecord() {
        return record;
    }

    public Integer getSequence() {
        return sequence;
    }


}
