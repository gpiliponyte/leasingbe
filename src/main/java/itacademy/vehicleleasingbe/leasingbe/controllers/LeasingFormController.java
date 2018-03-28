package itacademy.vehicleleasingbe.leasingbe.controllers;


import itacademy.vehicleleasingbe.leasingbe.beans.documents.LeasingForm;
import itacademy.vehicleleasingbe.leasingbe.beans.response.PostLeasingForm;
import itacademy.vehicleleasingbe.leasingbe.repositories.LeasingFormRepository;
import itacademy.vehicleleasingbe.leasingbe.services.LeasingFormService;
import itacademy.vehicleleasingbe.leasingbe.services.UniqueIdGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public PostLeasingForm addPost(@Valid @RequestBody LeasingForm leasingForm) {
        return new PostLeasingForm(service.addNewLease(leasingForm));
    }

    @RequestMapping(value ="/uniqueId/{uniqueId}", method = RequestMethod.GET)
    public LeasingForm sendUniqueId(@PathVariable String uniqueId) {
       return leasingFormRepository.findByUniqueId(uniqueId);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public PostLeasingForm updatePost(@Valid @RequestBody LeasingForm leasingForm, @PathVariable("id") String id) {
        return new PostLeasingForm(service.updateBlogPost(id, leasingForm));
    }


    @RequestMapping(value = "/deleteLease/{id}", method = RequestMethod.DELETE)
    public void removePost(@PathVariable("id") String id) {
        service.deleteLeaseForm(id);
    }

}
