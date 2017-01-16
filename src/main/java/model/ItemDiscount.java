package model;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * Discount record specific to the TITEM record – Multiple allowed per item
 */

@Record
public class ItemDiscount implements RTLogRecord {

    public final static String RECORD_DESCRIPTOR = "IDISC";

    //Identifies file record type
    @Field(ordinal = 1,length=5)
    public String record;

    //Sequential file line number
    @Field(ordinal = 2,length=10, format = "0000000000")
    public Integer sequence;

    //Valid values are found in CODE_DETAIL with code type of 'PRMT'
    @Field(ordinal = 3,length=6)
    public String promotionType;

    //Number associated with the promotion type.
    //For example, if the discount type is a promotion, then this field should be the promotion number. Retrieved from RPM when this is a RPM promotion.
    @Field(ordinal = 4,length=10,nillable = true)
    public String discountReferenceNumber;

    //The type of discount within the promotion.  Valid values from CODE_DETAIL with code type 'SADT'
    @Field(ordinal = 5,length=6,nillable = true)
    public String discountType;

    //Number of the store coupon.
    @Field(ordinal = 6,length=16,nillable = true)
    public String couponNumber;

    //Addition information for the coupon usually contained in a second bar code
    @Field(ordinal = 7,length=16,nillable = true)
    public String couponReferenceNumber;

    //Sign for the field "Quantity".
    @Field(ordinal = 8,length=1)
    public String quantitySign;

    //Four implied decimal places.  The quantity purchased to which this discount was applied
    @Field(ordinal = 9,length=12, format="000000000000")
    public Integer quantity;

    //Unit discount with four implied decimal places
    @Field(ordinal = 10,length=20, format="00000000000000000000")
    public Long unitDiscountAmount;

    //User defined value associated with this item discount
    @Field(ordinal = 11,length=30,nillable = true)
    public String referenceNumber13;

    //User defined value associated with this item discount
    @Field(ordinal = 12,length=30,nillable = true)
    public String referenceNumber14;

    //User defined value associated with this item discount
    @Field(ordinal = 13,length=30,nillable = true)
    public String referenceNumber15;

    //User defined value associated with this item discount
    @Field(ordinal = 14,length=30,nillable = true)
    public String referenceNumber16;

    //Quantity of items purchased in the given UOM with 4 implied decimal places
    @Field(ordinal = 15,length=12, format = "000000000000")
    public Integer UOMQuantity;

    //Is the item for the discount a catchweight item?
    @Field(ordinal = 16,length=1)
    public String catchWeightIndicator;

    //If this is for a promotion, this field contains the component number. RPM Promotion Component
    @Field(ordinal = 17,length=10,nillable = true)
    public String promotionComponent;

    public String getRecord() {
        return record;
    }

//    public void setRecord(String record) {
//        this.record = record;
//    }
//
    public Integer getSequence() {
        return sequence;
    }

//    public void setSequence(Integer lineSequence) {
//        this.sequence = lineSequence;
//    }
//
//    public String getPromotionType() {
//        return promotionType;
//    }
//
//    public void setPromotionType(String promotionType) {
//        this.promotionType = promotionType;
//    }
//
//    public String getDiscountReferenceNumber() {
//        return discountReferenceNumber;
//    }
//
//    public void setDiscountReferenceNumber(String discountReferenceNumber) {
//        this.discountReferenceNumber = discountReferenceNumber;
//    }
//
//    public String getDiscountType() {
//        return discountType;
//    }
//
//    public void setDiscountType(String discountType) {
//        this.discountType = discountType;
//    }
//
//    public String getCouponNumber() {
//        return couponNumber;
//    }
//
//    public void setCouponNumber(String couponNumber) {
//        this.couponNumber = couponNumber;
//    }
//
//    public String getCouponReferenceNumber() {
//        return couponReferenceNumber;
//    }
//
//    public void setCouponReferenceNumber(String couponReferenceNumber) {
//        this.couponReferenceNumber = couponReferenceNumber;
//    }
//
//    public String getQuantitySign() {
//        return quantitySign;
//    }
//
//    public void setQuantitySign(String quantitySign) {
//        this.quantitySign = quantitySign;
//    }
//
//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
//
//    public Long getUnitDiscountAmount() {
//        return unitDiscountAmount;
//    }
//
//    public void setUnitDiscountAmount(Long unitDiscountAmount) {
//        this.unitDiscountAmount = unitDiscountAmount;
//    }
//
//    public String getReferenceNumber13() {
//        return referenceNumber13;
//    }
//
//    public void setReferenceNumber13(String referenceNumber13) {
//        this.referenceNumber13 = referenceNumber13;
//    }
//
//    public String getReferenceNumber14() {
//        return referenceNumber14;
//    }
//
//    public void setReferenceNumber14(String referenceNumber14) {
//        this.referenceNumber14 = referenceNumber14;
//    }
//
//    public String getReferenceNumber15() {
//        return referenceNumber15;
//    }
//
//    public void setReferenceNumber15(String referenceNumber15) {
//        this.referenceNumber15 = referenceNumber15;
//    }
//
//    public String getReferenceNumber16() {
//        return referenceNumber16;
//    }
//
//    public void setReferenceNumber16(String referenceNumber16) {
//        this.referenceNumber16 = referenceNumber16;
//    }
//
//    public Integer getUOMQuantity() {
//        return UOMQuantity;
//    }
//
//    public void setUOMQuantity(Integer UOMQuantity) {
//        this.UOMQuantity = UOMQuantity;
//    }
//
//    public String getCatchWeightIndicator() {
//        return catchWeightIndicator;
//    }
//
//    public void setCatchWeightIndicator(String catchWeightIndicator) {
//        this.catchWeightIndicator = catchWeightIndicator;
//    }
//
//    public String getPromotionComponent() {
//        return promotionComponent;
//    }
//
//    public void setPromotionComponent(String promotionComponent) {
//        this.promotionComponent = promotionComponent;
//    }
//

}
