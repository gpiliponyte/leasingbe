package itacademy.vehicleleasingbe.leasingbe.beans.response;

import itacademy.vehicleleasingbe.leasingbe.beans.documents.Payment;
import itacademy.vehicleleasingbe.leasingbe.beans.documents.PaymentCalendar;

public class PaymentCalendarResponse extends Response {

    private String id;
    private String uniqueId;
    private Payment[] payments;

    public PaymentCalendarResponse(PaymentCalendar paymentCalendar) {
        this.id = paymentCalendar.getId();
        this.uniqueId = paymentCalendar.getUniqueId();
        this.payments = paymentCalendar.getPayments();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Payment[] getPayments() {
        return payments;
    }

    public void setPayments(Payment[] payments) {
        this.payments = payments;
    }
}