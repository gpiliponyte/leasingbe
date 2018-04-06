package itacademy.vehicleleasingbe.leasingbe.beans.documents;


import java.math.BigDecimal;
import java.util.Date;

public class DataForSchedule {

        private BigDecimal advancePaymentAmount;
        private BigDecimal assetPrice;
        private Date date;
        private BigDecimal leasePeriod;
        private String paymentDate;
        private BigDecimal margin;

        public BigDecimal getMargin() {
                return margin;
        }

        public void setMargin(BigDecimal margin) {
                this.margin = margin;
        }

        public BigDecimal getAdvancePaymentAmount() {
                return advancePaymentAmount;
        }

        public void setAdvancePaymentAmount(BigDecimal advancePaymentAmount) {
                this.advancePaymentAmount = advancePaymentAmount;
        }

        public BigDecimal getAssetPrice() {
                return assetPrice;
        }

        public void setAssetPrice(BigDecimal assetPrice) {
                this.assetPrice = assetPrice;
        }

        public Date getDate() {
                return date;
        }

        public void setDate(Date date) {
                this.date = date;
        }

        public BigDecimal getLeasePeriod() {
                return leasePeriod;
        }

        public void setLeasePeriod(BigDecimal leasePeriod) {
                this.leasePeriod = leasePeriod;
        }

        public String getPaymentDate() {
                return paymentDate;
        }

        public void setPaymentDate(String paymentDate) {
                this.paymentDate = paymentDate;
        }
}
