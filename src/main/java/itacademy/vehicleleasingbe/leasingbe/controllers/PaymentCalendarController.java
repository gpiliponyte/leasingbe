package itacademy.vehicleleasingbe.leasingbe.controllers;

import itacademy.vehicleleasingbe.leasingbe.beans.documents.LeasingForm;
import itacademy.vehicleleasingbe.leasingbe.beans.response.PaymentCalendarResponse;
import itacademy.vehicleleasingbe.leasingbe.beans.response.PostLeasingForm;
import itacademy.vehicleleasingbe.leasingbe.repositories.LeasingFormRepository;
import itacademy.vehicleleasingbe.leasingbe.services.LeasingFormService;
import itacademy.vehicleleasingbe.leasingbe.services.PaymentCalendarService;
import itacademy.vehicleleasingbe.leasingbe.validations.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/")
@CrossOrigin
public class PaymentCalendarController {

    @Autowired
    private PaymentCalendarService service;

    @RequestMapping(value = "/calendars")
    public List<PaymentCalendarResponse> getAllCalendars() {
        return service.getAllCalendars();
    }

    @RequestMapping(value ="/calendarByUniqueId/{uniqueId}", method = RequestMethod.GET)
    public PaymentCalendarResponse getCalendarByUniqueId(@PathVariable("uniqueId") String uniqueId) {
        return new PaymentCalendarResponse(service.findByUniqueId(uniqueId));
    }
}
