package itacademy.vehicleleasingbe.leasingbe.beans.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "calendars")
public class PaymentCalendar {

    @Id
    private String id;
    private String uniqueId;
    private Payment[] payments;

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
