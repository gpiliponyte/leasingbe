package itacademy.vehicleleasingbe.leasingbe.beans.documents;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Document(collection = "posts")
public class LeasingForm {


    @Id
    private long id;
    @NotNull
    private String customerType;
    @NotNull
    private String assetType;
    @NotNull
    private String brand;
    @NotNull
    private String model;
    @NotNull
    private BigDecimal year;
    @NotNull
    private BigDecimal enginerPower;
    @NotNull
    private BigDecimal assetPrice;
    @NotNull
    private BigDecimal advancePaymentPercentage;
    @NotNull
    private BigDecimal advancePaymentAmount;
    @NotNull
    private BigDecimal leasePeriod;
    @NotNull
    private BigDecimal margin;
    @NotNull
    private BigDecimal contractFee;
    @NotNull
    private BigDecimal paymentDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getYear() {
        return year;
    }

    public void setYear(BigDecimal year) {
        this.year = year;
    }

    public BigDecimal getEnginerPower() {
        return enginerPower;
    }

    public void setEnginerPower(BigDecimal enginerPower) {
        this.enginerPower = enginerPower;
    }

    public BigDecimal getAssetPrice() {
        return assetPrice;
    }

    public void setAssetPrice(BigDecimal assetPrice) {
        this.assetPrice = assetPrice;
    }

    public BigDecimal getAdvancePaymentPercentage() {
        return advancePaymentPercentage;
    }

    public void setAdvancePaymentPercentage(BigDecimal advancePaymentPercentage) {
        this.advancePaymentPercentage = advancePaymentPercentage;
    }

    public BigDecimal getAdvancePaymentAmount() {
        return advancePaymentAmount;
    }

    public void setAdvancePaymentAmount(BigDecimal advancePaymentAmount) {
        this.advancePaymentAmount = advancePaymentAmount;
    }

    public BigDecimal getLeasePeriod() {
        return leasePeriod;
    }

    public void setLeasePeriod(BigDecimal leasePeriod) {
        this.leasePeriod = leasePeriod;
    }

    public BigDecimal getMargin() {
        return margin;
    }

    public void setMargin(BigDecimal margin) {
        this.margin = margin;
    }

    public BigDecimal getContractFee() {
        return contractFee;
    }

    public void setContractFee(BigDecimal contractFee) {
        this.contractFee = contractFee;
    }

    public BigDecimal getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(BigDecimal paymentDate) {
        this.paymentDate = paymentDate;
    }
}