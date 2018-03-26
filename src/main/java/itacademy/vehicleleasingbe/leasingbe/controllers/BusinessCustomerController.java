package itacademy.vehicleleasingbe.leasingbe.controllers;



import itacademy.vehicleleasingbe.leasingbe.beans.response.PostLeasingForm;
import itacademy.vehicleleasingbe.leasingbe.beans.response.ShowBusinessCustomerInfo;
import itacademy.vehicleleasingbe.leasingbe.services.BusinessCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = "/")
@CrossOrigin
public class BusinessCustomerController {

    @Autowired
    private BusinessCustomerService service;

    @RequestMapping(value = "/d")
    public List<ShowBusinessCustomerInfo> getAllBusinessInfo() {
        return service.getAllBusinessInfo();
    }

//    @RequestMapping(value = "/addBusinessInfo", method = RequestMethod.POST)
//    public PostLeasingForm addPost(@Valid @RequestBody LeasingForm leasingForm) {
//        return new PostLeasingForm(service.addNewBusinessInfo(leasingForm));
//    }
}
