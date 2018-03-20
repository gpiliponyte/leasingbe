package itacademy.vehicleleasingbe.leasingbe.beans.response;

import itacademy.vehicleleasingbe.leasingbe.beans.documents.LeasingForm;
import org.bson.types.ObjectId;

import java.math.BigDecimal;

public class PostLeasingForm extends Response {

    private String id;
    private String customerType;
    private String assetType;
    private String brand;
    private String model;
    private BigDecimal year;
    private BigDecimal enginerPower;
    private BigDecimal assetPrice;
    private BigDecimal advancePaymentPercentage;
    private BigDecimal advancePaymentAmount;
    private BigDecimal leasePeriod;
    private BigDecimal margin;
    private BigDecimal contractFee;
    private BigDecimal paymentDate;

    public PostLeasingForm(LeasingForm leasingForm) {


        this.model = leasingForm.getCustomerType();
        this.assetType = leasingForm.getAssetType();
        this.brand = leasingForm.getBrand();
        this.model = leasingForm.getModel();
        this.year = leasingForm.getYear();
        this.enginerPower = leasingForm.getEnginerPower();
        this.assetPrice = leasingForm.getAssetPrice();
        this.advancePaymentPercentage = leasingForm.getAdvancePaymentPercentage();
        this.advancePaymentAmount = leasingForm.getAdvancePaymentAmount();
        this.leasePeriod = leasingForm.getLeasePeriod();
        this.margin = leasingForm.getMargin();
        this.contractFee = leasingForm.getContractFee();
        this.paymentDate = leasingForm.getPaymentDate();
    }

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
