package itacademy.vehicleleasingbe.leasingbe.beans.documents;

import java.math.BigDecimal;
import java.util.Date;

public class Payment {

    private Date date;
    private BigDecimal unpaidAssetAmount;
    private BigDecimal unpaidAssetRepaymentAmount;
    private BigDecimal interestAmount;
    private BigDecimal totalPaymentAmount;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getUnpaidAssetAmount() {
        return unpaidAssetAmount;
    }

    public void setUnpaidAssetAmount(BigDecimal unpaidAssetAmount) {
        this.unpaidAssetAmount = unpaidAssetAmount;
    }

    public BigDecimal getUnpaidAssetRepaymentAmount() {
        return unpaidAssetRepaymentAmount;
    }

    public void setUnpaidAssetRepaymentAmount(BigDecimal unpaidAssetRepaymentAmount) {
        this.unpaidAssetRepaymentAmount = unpaidAssetRepaymentAmount;
    }

    public BigDecimal getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(BigDecimal interestAmount) {
        this.interestAmount = interestAmount;
    }

    public BigDecimal getTotalPaymentAmount() {
        return totalPaymentAmount;
    }

    public void setTotalPaymentAmount(BigDecimal totalPaymentAmount) {
        this.totalPaymentAmount = totalPaymentAmount;
    }
}