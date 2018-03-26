package itacademy.vehicleleasingbe.leasingbe.controllers;


import itacademy.vehicleleasingbe.leasingbe.beans.response.ShowBusinessCustomerInfo;
import itacademy.vehicleleasingbe.leasingbe.beans.response.ShowPrivateCustomerInfo;
import itacademy.vehicleleasingbe.leasingbe.services.BusinessCustomerService;
import itacademy.vehicleleasingbe.leasingbe.services.PrivateCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/")
@CrossOrigin
public class PrivateCustomerController {

    @Autowired
    private PrivateCustomerService service;

    @RequestMapping(value = "/z")
    public List<ShowPrivateCustomerInfo> getAllPrivateInfo() {
        return service.getAllPrivateInfo();
    }
}
