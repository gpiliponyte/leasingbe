package itacademy.vehicleleasingbe.leasingbe.repositories;

import itacademy.vehicleleasingbe.leasingbe.beans.documents.PaymentCalendar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentCalendarRepository extends CrudRepository<PaymentCalendar, String> {
    List<PaymentCalendar> findAll();
    PaymentCalendar findPaymentCalendarById(String id);
    PaymentCalendar findPaymentCalendarByUniqueId(String uniqueId);
}
