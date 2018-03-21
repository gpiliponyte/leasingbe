package itacademy.vehicleleasingbe.leasingbe.services;

import itacademy.vehicleleasingbe.leasingbe.beans.documents.LeasingForm;
import itacademy.vehicleleasingbe.leasingbe.beans.documents.VehicleInfo;
import itacademy.vehicleleasingbe.leasingbe.beans.response.VehicleInfoResponse;
import itacademy.vehicleleasingbe.leasingbe.controllers.VehicleInfoController;
import itacademy.vehicleleasingbe.leasingbe.repositories.VehicleInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleInfoService {

    @Autowired
    private VehicleInfoRepository vehicleInfoRepository;

    public List<VehicleInfoResponse> getAllVehicleInfo() {
        return vehicleInfoRepository.findAll().stream()
                .map(VehicleInfoResponse::new)
                .collect(Collectors.toList());
    }

    public VehicleInfo addNewVehicleInfo(@Valid VehicleInfo vehicleInfo) {
        VehicleInfo newVehicleInfo = new VehicleInfo();


        newVehicleInfo.setId(vehicleInfo.getId());
        newVehicleInfo.setValue(vehicleInfo.getValue());
        newVehicleInfo.setGroupValue(vehicleInfo.getGroupValue());
        newVehicleInfo.setText(vehicleInfo.getText());


        return vehicleInfoRepository.save(newVehicleInfo);
    }

    public void deleteVehicle(String id) {
        vehicleInfoRepository.delete(vehicleInfoRepository.findVehicleInfoById(id));
    }
}
