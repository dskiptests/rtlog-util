package model;


import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

import java.util.ArrayList;
import java.util.List;

@Record
public class TransactionHeader implements RTLogRecord {

    public final static String RECORD_DESCRIPTOR = "THEAD";

    @Field(ordinal = 1,length=5)
    public String record;

    //Sequential file line number
    @Field(ordinal = 2, length=10, format = "0000000000")
    public Integer sequence;

    //The store register number
    @Field(ordinal = 3,length=5)
    public String registerNumber;

    //Date and time of the transaction
    @Field(ordinal = 4,length=14)
    public String transactionDatetime;

    //Transaction identifier.
    @Field(ordinal = 5,length=10, format = "0000000000")
    public Long transactionNumber;

    //Character representation of the cashier for this transaction.
    @Field(ordinal = 6,length=10, nillable = true)
    public String cashier;

    //Usually this is used for commissioned sales.  This would be the person who gets the commission for this transaction.
    @Field(ordinal = 7,length=10, nillable = true)
    public String salesPerson;

    //The type of transaction.
    @Field(ordinal = 8,length=6)
    public String transactionType;

    //Sub-transaction type.
    @Field(ordinal = 9,length=6, nillable = true)
    public String subTransactionType;

    //Populated only for post-void transactions. Transaction number for the original transaction number that was voided.
    @Field(ordinal = 10,length=10, nillable = true)
    public String originalTransactionNumber;

    //Populated only for post-void transactions. Register number from the original transaction.
    @Field(ordinal = 11,length=5, nillable = true, format = "00000")
    public Long originalRegisterNumber;

    //Reason entered by the cashier for some transaction types. Required for Paid In and Paid Out transaction types, but can be used for voids, returns, etc.
    @Field(ordinal = 12,length=6, nillable = true)
    public String reasonCode;

    //Supplier/Partner number for the merchandise vendor or partner for an expense paid out transaction.
    @Field(ordinal = 13,length=10,nillable = true)
    public String vendorNumber;

    //Invoice number for a paid out transaction
    @Field(ordinal = 14,length=30, nillable = true)
    public String vendorInvoiceNumber;

    //The reference number of the tender used for a vendor/partner paid out.  This could be money order number, check number, etc.
    @Field(ordinal = 15,length=16, nillable = true)
    public String paymentReferenceNumber;

    //Proof of receipt number given by the vendor/partner at time of delivery when this is a paid out transaction
    @Field(ordinal = 16,length=30, nillable = true)
    public String proofOfDeliveryNumber;

    //User defined number associated with a given transaction.  Valid values from SA_REFERENCE
    @Field(ordinal = 17,length=30,nillable = true)
    public String referenceNumber1;

    //User defined number associated with a given transaction.  Valid values from SA_REFERENCE
    @Field(ordinal = 18,length=30,nillable = true)
    public String referenceNumber2;

    //User defined number associated with a given transaction.  Valid values from SA_REFERENCE
    @Field(ordinal = 19,length=30, nillable = true)
    public String referenceNumber3;

    //User defined number associated with a given transaction.  Valid values from SA_REFERENCE
    @Field(ordinal = 20,length=30, nillable = true)
    public String referenceNumber4;

    //Sign of "Value"
    @Field(ordinal = 21,length=1, nillable = true,format = "")
    public String valueSign;

    //Four implied decimals. In other words, $1 would be 00000000000000010000.
    @Field(ordinal = 22,length=20,nillable = true, format = "00000000000000000000")
    public Long value;

    //The banner ID for the location
    @Field(ordinal = 23,length=4, nillable = true)
    public Long bannerId;

    //Customer order number
    @Field(ordinal = 24,length=30, nillable = true)
    public String customerOrderHeadId;

    //The date of the customer order
    @Field(ordinal = 25,length=14, nillable = true )
    public String customerOrderHeadDate;

    //Sign of "Rounded Amount"
    @Field(ordinal = 26,length=1, nillable = true)
    public String roundedAmountSign;

    //Four implied decimals.  In other words, $1 would be 00000000000000010000.
    @Field(ordinal = 27,length=20, nillable = true, format = "00000000000000000000")
    public Long roundedAmount;

    @Field(ordinal = 28,length=1, nillable = true)
    public String roundedOffSign;

    //Four implied decimals.  In other words, $1 would be 00000000000000010000.
    @Field(ordinal = 29,length=20, nillable = true)
    public Long roundedOffAmount;

    //The ID number for a credit promotion
    @Field(ordinal = 30,length=10, nillable = true)
    public String creditPromotionalId;

    //User defined number associated with a given transaction.  Valid values from SA_REFERENCE
    @Field(ordinal = 31,length=30, nillable = true)
    public String referenceNumber25;

    //User defined number associated with a given transaction.  Valid values from SA_REFERENCE
    @Field(ordinal = 32,length=30,  nillable = true)
    public String referenceNumber26;

    //User defined number associated with a given transaction.  Valid values from SA_REFERENCE
    @Field(ordinal = 33,length=30, nillable = true)
    public String referenceNumber27;

    public List<TransactionItem> transactionItems = new ArrayList<>();
    public TransactionTail transactionTail;

    @Override
    public String getRecord() {
        return record;
    }

    @Override
    public Integer getSequence() {
        return sequence;
    }
}

