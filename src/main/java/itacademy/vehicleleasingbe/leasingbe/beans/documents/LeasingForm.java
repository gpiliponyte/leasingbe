package itacademy.vehicleleasingbe.leasingbe.beans.documents;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Document(collection = "posts")
public class LeasingForm {

    @Id
    private String id;
    @NotNull
    private String customerType;
    @NotNull
    private String assetType;
    @NotNull
    private String make;
    @NotNull
    private String model;
    @NotNull
    private BigDecimal year;
    @NotNull
    private BigDecimal enginePower;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
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

    public BigDecimal getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(BigDecimal enginerPower) {
        this.enginePower = enginerPower;
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
