package itacademy.vehicleleasingbe.leasingbe.controllers;


import itacademy.vehicleleasingbe.leasingbe.beans.documents.LeasingForm;
import itacademy.vehicleleasingbe.leasingbe.beans.response.PostLeasingForm;
import itacademy.vehicleleasingbe.leasingbe.services.LeasingFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public class LeasingFormController {

    @Autowired
    private LeasingFormService service;

    @RequestMapping(value = "/")
    public List<PostLeasingForm> getAllPosts() {
        return service.getAllLeases();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public PostLeasingForm addPost(@Valid @RequestBody LeasingForm leasingForm) {
        return new PostLeasingForm(service.addNewPost(leasingForm));
    }


    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public PostLeasingForm updatePost(@Valid @RequestBody LeasingForm leasingForm, @PathVariable("id") String id) {
        return new PostLeasingForm(service.updateBlogPost(id, leasingForm));
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void removePost(@PathVariable("id") String id) {
        service.deleteBlogPost(id);
    }

}
