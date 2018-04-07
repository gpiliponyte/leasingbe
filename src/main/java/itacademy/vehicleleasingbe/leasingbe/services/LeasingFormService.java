package itacademy.vehicleleasingbe.leasingbe.services;


import itacademy.vehicleleasingbe.leasingbe.beans.documents.LeasingForm;
import itacademy.vehicleleasingbe.leasingbe.beans.response.LeasingFormResponse;
import itacademy.vehicleleasingbe.leasingbe.repositories.LeasingFormRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import itacademy.vehicleleasingbe.leasingbe.validations.FormValidationException;
import itacademy.vehicleleasingbe.leasingbe.validations.FormValidation;
import itacademy.vehicleleasingbe.leasingbe.beans.response.VehicleInfoResponse;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeasingFormService {

    @Autowired
    private LeasingFormRepository leasingFormRepository;
    @Autowired
    private VehicleInfoService vehicleInfoService;


    public List<LeasingFormResponse> getAllLeases() {
        return leasingFormRepository.findAll().stream()
                .map(LeasingFormResponse::new)
                .collect(Collectors.toList());
    }

    public LeasingForm addNewLease(@Valid LeasingForm leasingForm) throws FormValidationException {
        UniqueIdGeneratorService uniqueIdGeneratorService = new UniqueIdGeneratorService();

        FormValidation formValidation = new FormValidation();
        List<VehicleInfoResponse> vehicleInfos = vehicleInfoService.getAllVehicleInfo();

        formValidation.executeFormValidation(leasingForm, vehicleInfos);

        String uniqueId = uniqueIdGeneratorService.generateUserId(leasingForm);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, 3);

        leasingForm.setUniqueId(uniqueId);
        leasingForm.setDate(calendar.getTime());
        leasingForm.setApplicationStatus('pending');

        LeasingForm newLeasingForm = leasingForm;
        return leasingFormRepository.save(newLeasingForm);
    }


    public LeasingForm updateLease(String id, String newApplicationStatus) {

        LeasingForm leasingForm = leasingFormRepository.findLeasingFormById(id);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, 3);

        leasingForm.setDate(calendar.getTime());
        leasingForm.setApplicationStatus(newApplicationStatus);
        return leasingFormRepository.save(leasingForm);
    }

    public LeasingForm findLeaseByUniqueId(String uniqueId) {
        return leasingFormRepository.findByUniqueId(uniqueId);
    }

    public List<LeasingForm> findAllByApplicationStatus(String applicationStatus) { return leasingFormRepository.findAllByApplicationStatus(applicationStatus); }

    public void deleteLeaseForm(String id) {
        leasingFormRepository.delete(leasingFormRepository.findLeasingFormById(id));
    }

}
