package itacademy.vehicleleasingbe.leasingbe.controllers;


import itacademy.vehicleleasingbe.leasingbe.beans.documents.LeasingForm;
import itacademy.vehicleleasingbe.leasingbe.beans.response.LeasingFormResponse;
import itacademy.vehicleleasingbe.leasingbe.repositories.LeasingFormRepository;
import itacademy.vehicleleasingbe.leasingbe.services.LeasingFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import itacademy.vehicleleasingbe.leasingbe.validations.FormValidationException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/")
@CrossOrigin
public class LeasingFormController {

    @Autowired
    private LeasingFormService service;

    @Autowired
    private LeasingFormRepository leasingFormRepository;

    @RequestMapping(value = "/")
    public List<LeasingFormResponse> getAllLeases() {
        return service.getAllLeases();
    }

    @RequestMapping(value = "/addLease", method = RequestMethod.POST)
    public LeasingFormResponse addLease(@Valid @RequestBody LeasingForm leasingForm)throws FormValidationException {
        return new LeasingFormResponse(service.addNewLease(leasingForm));
    }

    @RequestMapping(value ="/uniqueId/{uniqueId}", method = RequestMethod.GET)
    public LeasingFormResponse getLeaseByUniqueId(@PathVariable("uniqueId") String uniqueId) {
       return new LeasingFormResponse(service.findLeaseByUniqueId(uniqueId));
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public LeasingFormResponse updateLease(@Valid @RequestBody String applicationStatus, @PathVariable("id") String id) {
        return new LeasingFormResponse(service.updateLease(id, applicationStatus));
    }

//
//@RequestMapping(value = "/deleteLease/{id}", method = RequestMethod.DELETE)
//public void removePost(@PathVariable("id") String id) {
//service.deleteLeaseForm(id);
//}
}
