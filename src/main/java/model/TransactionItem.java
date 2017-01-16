package model;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

import java.util.Date;

/**
 * Multiple allowed per transaction, optional for some transaction types
 */


@Record
public class TransactionItem implements RTLogRecord {

    //Identifies file record type
    public static final String RECORD_DESCRIPTOR = "TITEM";

    @Field(ordinal = 1,length = 5)
    public String record;

    //Sequential file line number
    @Field(ordinal = 2,length = 10,format = "0000000000")
    public Integer sequence;

    //Status of the item within the transaction
    @Field(ordinal = 3,length = 6)
    public String itemStatus;

    //Identifies what type of item this record has.
    @Field(ordinal = 4,length = 6)
    public String itemType;

    //Identifies what type of item number it is.  Valid values from CODE_DETAIL where code type is 'UPCT'
    @Field(ordinal = 5,length = 6, nillable = true)
    public String itemNumberType;

    //Used to interpret VPLU items
    @Field(ordinal = 6,length = 1, nillable = true)
    public String formatId;

    //Item number whether ORIN, UPC, VPN or whatever
    @Field(ordinal = 7,length = 25, nillable = true)
    public String item;

    //Identifies the sub-transaction level merchandise item. Populated when the item_type = REF
    @Field(ordinal = 8,length = 25, nillable = true)
    public String referenceItem;

    //ID for the non-merchandise item.
    @Field(ordinal = 9,length = 25, nillable = true)
    public String nonMerchandiseitem;

    //Gift Certificate Number. Populated when the item_type = GCN
    @Field(ordinal = 10,length = 25, nillable = true)
    public String voucher;

    //Department to which the item is assigned
    @Field(ordinal = 11,length = 4, nillable = true, format = "0000")
    public Long department;

    //Class to which the item is assigned
    @Field(ordinal = 12,length = 4, nillable = true, format = "0000")
    public Long clazz;

    //Subclass to which the item is assigned
    @Field(ordinal = 13,length = 4, nillable = true, format = "0000")
    public Long subClass;

    //Sign of the quantity
    @Field(ordinal = 14,length = 1)
    public String quantitySign;

    //Number of items for this transaction
    @Field(ordinal = 15,length = 12, format = "000000000000")
    public Integer quantity;

    //UOM of the item quantity. UOM_CLASS contains valid ReSA unit of measure value
    @Field(ordinal = 16,length = 4)
    public String sellingUnitOfMeasure;

    //Unit retail with four implied decimal places
    @Field(ordinal = 17,length = 20, format = "00000000000000000000")
    public Long unitRetail;

    //Gives the reason the regular retail was overridden at the register.  Valid values in CODE_DETAIL with a code type of 'ORRC'
    @Field(ordinal = 18,length = 6, nillable = true)
    public String overrideReason;

    //The retail that was overridden with four implied decimal places
    @Field(ordinal = 19,length = 20, nillable = true, format = "00000000000000000000")
    public Long originalUnitRetail;

    //Indicates if the item is taxable
    @Field(ordinal = 20,length = 1)
    public String taxableIndicator;

    //Pump number for a fuel transaction
    @Field(ordinal = 21,length = 8, nillable = true)
    public String pump;

    //User defined value associated with this transaction item
    @Field(ordinal = 22,length = 30, nillable = true)
    public String referenceNumber5;

    //User defined value associated with this transaction item
    @Field(ordinal = 23,length = 30, nillable = true)
    public String referenceNumber6;

    //User defined value associated with this transaction item
    @Field(ordinal = 24,length = 30, nillable = true)
    public String referenceNumber7;

    //User defined value associated with this transaction item
    @Field(ordinal = 25,length = 30, nillable = true)
    public String referenceNumber8;

    //Indicates if the item was scanned at the register or manually entered by the cashier
    @Field(ordinal = 26,length = 1)
    public String itemSwipedIndicator;

    //The reason the item was returned.  Valid values in CODE_DETAIL where code type is 'SARR'
    @Field(ordinal = 27,length = 6, nillable = true)
    public String returnReasonCode;

    //The sales person associated with this transaction item
    @Field(ordinal = 28,length = 10, nillable = true)
    public String salesPerson;

    //Gift certificate expiration date in the format 'YYYYMMDD'
    @Field(ordinal = 29,length = 8, format = "yyyyMMdd", nillable = true)
    public Date expirationDate;

    //Indicates if the item is part of a drop shipment.
    @Field(ordinal = 30,length = 1, nillable = true)
    public String dropShipIndicator;

    //Quantity of items purchased in the given UOM with 4 implied decimals
    @Field(ordinal = 31,length = 12, nillable = true, format = "0000000000000000000000000000000")
    public Long unitOfMeasureQuantity;

    //Is the item a catchweight item (meat, bulk coffee etc).  Valid values are 'Y' or 'N'
    @Field(ordinal = 32,length = 1)
    public String catchWeightIndicator;

    //Identifies the selling item
    @Field(ordinal = 33,length = 25, nillable = true)
    public String sellingItem;

    //Line number within the customer order
    @Field(ordinal = 34,length = 6, nillable = true)
    public Long customerOrderLineNumber;

    //The customer media ID
    @Field(ordinal = 35,length = 10, nillable = true, format = "0000000000")
    public Long mediaId;

    //Total IGTax amount for the item
    @Field(ordinal = 36,length = 21, nillable = true, format = "000000000000000000000")
    public Long totalIGTaxAmount;

    //Unique idenfitication number for the item
    @Field(ordinal = 37,length = 128, nillable = true)
    public String uniqueId;

    public String getRecord() {
        return record;
    }

    public Integer getSequence() {
        return sequence;
    }


}