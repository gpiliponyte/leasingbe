package itacademy.vehicleleasingbe.leasingbe.controllers;


import itacademy.vehicleleasingbe.leasingbe.beans.documents.Payment;
import itacademy.vehicleleasingbe.leasingbe.beans.documents.DataForSchedule;
import itacademy.vehicleleasingbe.leasingbe.util.GenerateCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/")
@CrossOrigin
public class PaymentScheduleController {

    @Autowired
    private GenerateCalendarService service;

    @RequestMapping(value = "/getCalendar", method = RequestMethod.POST)
    public Payment[] getPaymentCalendar(@Valid @RequestBody DataForSchedule dataForSchedule) {
        return service.generateCalendar(dataForSchedule);
    }
}
