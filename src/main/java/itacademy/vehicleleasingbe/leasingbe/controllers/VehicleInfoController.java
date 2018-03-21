package itacademy.vehicleleasingbe.leasingbe.controllers;

import itacademy.vehicleleasingbe.leasingbe.beans.documents.LeasingForm;
import itacademy.vehicleleasingbe.leasingbe.beans.documents.VehicleInfo;
import itacademy.vehicleleasingbe.leasingbe.beans.response.PostLeasingForm;
import itacademy.vehicleleasingbe.leasingbe.beans.response.VehicleInfoResponse;
import itacademy.vehicleleasingbe.leasingbe.services.VehicleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/")
@CrossOrigin
public class VehicleInfoController {

    @Autowired
    private VehicleInfoService service;

    @RequestMapping(value = "/vehicles")
    public List<VehicleInfoResponse> getAllVehicleInfo() {
        return service.getAllVehicleInfo();
    }

    @RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
    public VehicleInfoResponse addPost(@Valid @RequestBody VehicleInfo vehicleInfo) {
        return new VehicleInfoResponse(service.addNewVehicleInfo(vehicleInfo));
    }

    @RequestMapping(value = "/deleteVehicle/{id}", method = RequestMethod.DELETE)
    public void removePost(@PathVariable("id") String id) {
        service.deleteVehicle(id);
    }
}