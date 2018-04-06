package itacademy.vehicleleasingbe.leasingbe.services;


import itacademy.vehicleleasingbe.leasingbe.beans.documents.LeasingForm;
import itacademy.vehicleleasingbe.leasingbe.beans.response.LeasingFormResponse;
import itacademy.vehicleleasingbe.leasingbe.repositories.LeasingFormRepository;
import itacademy.vehicleleasingbe.leasingbe.util.CalculateMarginService;
import itacademy.vehicleleasingbe.leasingbe.util.GenerateCalendarService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import itacademy.vehicleleasingbe.leasingbe.validations.CustomException;
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

    public LeasingForm addNewLease(@Valid LeasingForm leasingForm) throws CustomException {
        UniqueIdGeneratorService uniqueIdGeneratorService = new UniqueIdGeneratorService();

        FormValidation formValidation = new FormValidation();
        List<VehicleInfoResponse> vehicleInfos = vehicleInfoService.getAllVehicleInfo();

        CustomException customException = formValidation.executeFormValidation(leasingForm, vehicleInfos);
        if (customException != null) {
            throw customException;
        } else {

            String uniqueId = uniqueIdGeneratorService.generateUserId(leasingForm);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.HOUR_OF_DAY, 3);

            LeasingForm newLeasingForm = new LeasingForm(
                    leasingForm.getCustomerType(),
                    leasingForm.getAssetType(),
                    leasingForm.getBrand(),
                    leasingForm.getModel(),
                    leasingForm.getYear(),
                    leasingForm.getEnginePower(),
                    leasingForm.getAssetPrice(),
                    leasingForm.getAdvancePaymentPercentage(),
                    leasingForm.getAdvancePaymentAmount(),
                    leasingForm.getLeasePeriod(),
                    leasingForm.getMargin(),
                    leasingForm.getContractFee(),
                    leasingForm.getPaymentDate(),
                    leasingForm.getEmail(),
                    leasingForm.getPhoneNumber(),
                    leasingForm.getStreet(),
                    leasingForm.getCity(),
                    leasingForm.getPostCode(),
                    leasingForm.getCountry(),
                    leasingForm.getCompanyName(),
                    leasingForm.getCompanyCode(),
                    leasingForm.getFirstName(),
                    leasingForm.getLastName(),
                    leasingForm.getPersonalCode(),
                    uniqueId,
                    calendar.getTime(),
                    "Application is being processed"
            );
            return leasingFormRepository.save(newLeasingForm);
        }
    }

    public LeasingForm updateLease(String uniqueId, String newApplicationStatus) {

        LeasingForm leasingForm = leasingFormRepository.findByUniqueId(uniqueId);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, 3);

        LeasingForm newLeasingForm = new LeasingForm(
                leasingForm.getCustomerType(),
                leasingForm.getAssetType(),
                leasingForm.getBrand(),
                leasingForm.getModel(),
                leasingForm.getYear(),
                leasingForm.getEnginePower(),
                leasingForm.getAssetPrice(),
                leasingForm.getAdvancePaymentPercentage(),
                leasingForm.getAdvancePaymentAmount(),
                leasingForm.getLeasePeriod(),
                leasingForm.getMargin(),
                leasingForm.getContractFee(),
                leasingForm.getPaymentDate(),
                leasingForm.getEmail(),
                leasingForm.getPhoneNumber(),
                leasingForm.getStreet(),
                leasingForm.getCity(),
                leasingForm.getPostCode(),
                leasingForm.getCountry(),
                leasingForm.getCompanyName(),
                leasingForm.getCompanyCode(),
                leasingForm.getFirstName(),
                leasingForm.getLastName(),
                leasingForm.getPersonalCode(),
                leasingForm.getUniqueId(),
                calendar.getTime(),
                newApplicationStatus
        );
        leasingFormRepository.deleteById(leasingForm.getId());
        return leasingFormRepository.save(newLeasingForm);
    }

    public LeasingForm findLeaseByUniqueId(String uniqueId) {
        return leasingFormRepository.findByUniqueId(uniqueId);
    }
//
//public void deleteLeaseForm(String id) {
//leasingFormRepository.delete(leasingFormRepository.findLeasingFormById(id));
//}
}
