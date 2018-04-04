package itacademy.vehicleleasingbe.leasingbe.services;

import itacademy.vehicleleasingbe.leasingbe.beans.documents.LeasingForm;
import itacademy.vehicleleasingbe.leasingbe.beans.documents.PaymentCalendar;
import itacademy.vehicleleasingbe.leasingbe.beans.response.PaymentCalendarResponse;
import itacademy.vehicleleasingbe.leasingbe.beans.response.PostLeasingForm;
import itacademy.vehicleleasingbe.leasingbe.beans.response.VehicleInfoResponse;
import itacademy.vehicleleasingbe.leasingbe.repositories.PaymentCalendarRepository;
import itacademy.vehicleleasingbe.leasingbe.validations.CustomException;
import itacademy.vehicleleasingbe.leasingbe.validations.FormValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentCalendarService {

    @Autowired
    private GenerateCalendarService generateCalendarService;

    @Autowired
    private PaymentCalendarRepository paymentCalendarRepository;

    public List<PaymentCalendarResponse> getAllCalendars() {
        return paymentCalendarRepository.findAll().stream()
                .map(PaymentCalendarResponse::new)
                .collect(Collectors.toList());
    }

    public PaymentCalendar addCalendar(String uniqueId, LeasingForm leasingForm){
        PaymentCalendar newPaymentCalendar = new PaymentCalendar();
        newPaymentCalendar.setUniqueId(uniqueId);
        newPaymentCalendar.setPayments(generateCalendarService.generateCalendar(uniqueId, leasingForm));
        return paymentCalendarRepository.save(newPaymentCalendar);
    }

    public PaymentCalendar findByUniqueId(String uniqueId) {
        return paymentCalendarRepository.findPaymentCalendarById(uniqueId);
    }


}
