package itacademy.vehicleleasingbe.leasingbe.controllers;


import itacademy.vehicleleasingbe.leasingbe.beans.documents.LeasingForm;
import itacademy.vehicleleasingbe.leasingbe.beans.response.PostLeasingForm;
import itacademy.vehicleleasingbe.leasingbe.repositories.LeasingFormRepository;
import itacademy.vehicleleasingbe.leasingbe.services.LeasingFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import itacademy.vehicleleasingbe.leasingbe.validations.CustomException;

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
    public List<PostLeasingForm> getAllLeases() {
        return service.getAllLeases();
    }

    @RequestMapping(value = "/addLease", method = RequestMethod.POST)
    public PostLeasingForm addLease(@Valid @RequestBody LeasingForm leasingForm)throws CustomException {
        return new PostLeasingForm(service.addNewLease(leasingForm));
    }

    @RequestMapping(value ="/uniqueId/{uniqueId}", method = RequestMethod.GET)
    public PostLeasingForm getLeaseByUniqueId(@PathVariable("uniqueId") String uniqueId) {
       return new PostLeasingForm(service.findByUniqueId(uniqueId));
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public PostLeasingForm updateLease(@Valid @RequestBody LeasingForm leasingForm, @PathVariable("id") String id) {
        return new PostLeasingForm(service.updateBlogPost(id, leasingForm));
    }

    @RequestMapping(value ="/leaseStatus/{applicationStatus}",method = RequestMethod.GET)
    public List<LeasingForm> getAllLeasesByApplicationStatus(@PathVariable("applicationStatus") String applicationStatus) {
        return service.findAllByApplicationStatus(applicationStatus);
    }

    @RequestMapping(value = "/deleteLease/{id}", method = RequestMethod.DELETE)
    public void removePost(@PathVariable("id") String id) {
        service.deleteLeaseForm(id);
    }

}
