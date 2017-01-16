package model;


import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

import java.util.Date;


/**
 * Multiple allowed per transaction, optional for some transaction types
 */
@Record
public class TransactionTender implements RTLogRecord {

    public static final String RECORD_DESCRIPTOR = "TTEND";
    //Identifies file record type
    @Field(ordinal = 1,length=5)
    public String record;

    //Sequential file line number
    @Field(ordinal = 2,length=10, format = "0000000000")
    public Integer sequence;

    //High level grouping of tender types.  Valid values from CODE_DETAIL with code type 'TENT'
    @Field(ordinal = 3,length=6)
    public String tenderTypeGroup;

    //Valid values in POS_TENDER_TYPE_HEAD table
    @Field(ordinal = 4,length=6)
    public String tenderTypeId;

    //Sign of the field 'Tender Amount'
    @Field(ordinal = 5,length=1)
    public String tenderSign;

    //Amount paid with this tender with four implied decimal places
    @Field(ordinal = 6,length=20, format = "00000000000000000000")
    public Long tenderAmount;

    //The credit card number
    @Field(ordinal = 7,length=40,nillable = true)
    public String creditCardNumber;

    //The authorization number received for the tender
    @Field(ordinal = 8,length=16,nillable = true)
    public String creditCardAuthorizationNumber;

    //Valid values from CODE_DETAIL where CODE_TYPE is CCAS
    @Field(ordinal = 9,length=6,nillable = true)
    public String creditCardAuthorizationSource;

    //Type of verification performed.  Valid value in CODE_DETAIL where code type is 'CCVF'
    @Field(ordinal = 10,length=6,nillable = true)
    public String creditCardHolderVerification;

    //Format 'YYYYMMDD"
    @Field(ordinal = 11,length=8,format = "yyyyMMdd",nillable = true)
    public Date creditCardExpirationDate;

    //Indicates if the card was swiped or manually typed.  Valid values from CODE_DETAIL where code type is 'CCEM'
    @Field(ordinal = 12,length=6,nillable = true)
    public String creditCardEntryMode;

    //Terminal for the transaction
    @Field(ordinal = 13,length=5,nillable = true)
    public String creditCardTerminalId;

    //Valid values from CODE_DETAIL where CODE_TYPE is CCSC
    @Field(ordinal = 14,length=6,nillable = true)
    public String creditCardSpecialCondition;

    //VGift certificate or credit voucher serial number
    @Field(ordinal = 15,length=25,nillable = true)
    public String voucherNumber;

    //Number of the manufacturer's coupon used as tender
    @Field(ordinal = 16,length=16,nillable = true)
    public String couponNumber;

    //Additional information about the coupon, usually from a second bar code on the couponr
    @Field(ordinal = 17,length=16,nillable = true)
    public String couponReferenceNumber;

    //Account number for the check
    @Field(ordinal = 18,length=30,nillable = true)
    public String checkAccountNumber;

    //Required for tender_type of 'CHECK'
    @Field(ordinal = 19,length=10,nillable = true)
    public Long checkNumber;

    //Method used to determine the customer was who the name on the check said he/she was.  Valid values from CODE_DETAIL where code type is 'IDMH'
    @Field(ordinal = 20,length=6,nillable = true)
    public String identificationMethod;

    //Identification Number taken to validate identity such as driver's license number
    @Field(ordinal = 21,length=40,nillable = true)
    public String identificationNumber;

    //The currency code at which the customer made payment.  Valid values from CURRENCY
    @Field(ordinal = 22,length=3,nillable = true)
    public String originalCurrency;

    //Amound paid in the original currency with four implied decimal places
    @Field(ordinal = 23,length=20, nillable = true, format = "00000000000000000000")
    public Long originalCurrencyAmount;

    //User defined value associated with this transaction item
    @Field(ordinal = 24,length=30,nillable = true)
    public String referenceNumber9;

    //User defined value associated with this transaction item
    @Field(ordinal = 25,length=30,nillable = true)
    public String referenceNumber10;

    //User defined value associated with this transaction item
    @Field(ordinal = 26,length=30,nillable = true)
    public String referenceNumber11;

    //User defined value associated with this transaction item
    @Field(ordinal = 27,length=30,nillable = true)
    public String referenceNumber12;

    public String getRecord() {
        return record;
    }

    @Override
    public Integer getSequence() {
        return sequence;
    }


}
