package itacademy.vehicleleasingbe.leasingbe.services;

import itacademy.vehicleleasingbe.leasingbe.Validations.CustomException;
import itacademy.vehicleleasingbe.leasingbe.Validations.FormValidation;
import itacademy.vehicleleasingbe.leasingbe.beans.documents.LeasingForm;
import itacademy.vehicleleasingbe.leasingbe.beans.response.PostLeasingForm;
import itacademy.vehicleleasingbe.leasingbe.beans.response.VehicleInfoResponse;
import itacademy.vehicleleasingbe.leasingbe.repositories.LeasingFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeasingFormService {

    @Autowired
    private LeasingFormRepository leasingFormRepository;
    @Autowired
    private VehicleInfoService vehicleInfoService;

    public List<PostLeasingForm> getAllLeases() {
        return leasingFormRepository.findAll().stream()
                .map(PostLeasingForm::new)
                .collect(Collectors.toList());
    }

    public LeasingForm addNewLease(@Valid LeasingForm leasingForm) throws CustomException {
        LeasingForm newLeasingForm = new LeasingForm();

        FormValidation formValidation = new FormValidation();
        List<VehicleInfoResponse> vehicleInfos = vehicleInfoService.getAllVehicleInfo();

        CustomException customException = formValidation.executeFormValidation(leasingForm, vehicleInfos);
        if (customException != null) {
            throw customException;
        } else {


            newLeasingForm.setCustomerType(leasingForm.getCustomerType());
            newLeasingForm.setAssetType(leasingForm.getAssetType());
            newLeasingForm.setBrand(leasingForm.getBrand());
            newLeasingForm.setModel(leasingForm.getModel());
            newLeasingForm.setYear(leasingForm.getYear());
            newLeasingForm.setEnginePower(leasingForm.getEnginePower());
            newLeasingForm.setAssetPrice(leasingForm.getAssetPrice());
            newLeasingForm.setAdvancePaymentPercentage(leasingForm.getAdvancePaymentPercentage());
            newLeasingForm.setAdvancePaymentAmount(leasingForm.getAdvancePaymentAmount());
            newLeasingForm.setLeasePeriod(leasingForm.getLeasePeriod());
            newLeasingForm.setMargin(leasingForm.getMargin());
            newLeasingForm.setContractFee(leasingForm.getContractFee());
            newLeasingForm.setPaymentDate(leasingForm.getPaymentDate());

            newLeasingForm.setCompanyName(leasingForm.getCompanyName());
            newLeasingForm.setCompanyCode(leasingForm.getCompanyCode());
            newLeasingForm.setEmail(leasingForm.getEmail());
            newLeasingForm.setPhoneNumber(leasingForm.getPhoneNumber());

            newLeasingForm.setFirstName(leasingForm.getFirstName());
            newLeasingForm.setLastName(leasingForm.getLastName());
            newLeasingForm.setPersonalCode(leasingForm.getPersonalCode());

            newLeasingForm.setStreet(leasingForm.getStreet());
            newLeasingForm.setCity(leasingForm.getCity());
            newLeasingForm.setPostCode(leasingForm.getPostCode());
            newLeasingForm.setCountry(leasingForm.getCountry());


            return leasingFormRepository.save(newLeasingForm);
        }
    }

    public LeasingForm updateBlogPost(String id, LeasingForm updateLeasingFormInfo) {
        // steps:
        // 1. get particular blog post
        // 2. update blog post
        // 3. save blog post
        LeasingForm leasingForm = leasingFormRepository.findLeasingFormById(id);

        leasingForm.setCustomerType(updateLeasingFormInfo.getCustomerType());
        leasingForm.setAssetType(updateLeasingFormInfo.getAssetType());
        leasingForm.setBrand(updateLeasingFormInfo.getBrand());
        leasingForm.setModel(updateLeasingFormInfo.getModel());
        leasingForm.setYear(updateLeasingFormInfo.getYear());
        leasingForm.setEnginePower(updateLeasingFormInfo.getEnginePower());
        leasingForm.setAssetPrice(updateLeasingFormInfo.getAssetPrice());
        leasingForm.setAdvancePaymentPercentage(updateLeasingFormInfo.getAdvancePaymentPercentage());
        leasingForm.setAdvancePaymentAmount(updateLeasingFormInfo.getAdvancePaymentAmount());
        leasingForm.setLeasePeriod(updateLeasingFormInfo.getLeasePeriod());
        leasingForm.setMargin(updateLeasingFormInfo.getMargin());
        leasingForm.setContractFee(updateLeasingFormInfo.getContractFee());
        leasingForm.setPaymentDate(updateLeasingFormInfo.getPaymentDate());

        leasingForm.setCompanyName(updateLeasingFormInfo.getCompanyName());
        leasingForm.setCompanyCode(updateLeasingFormInfo.getCompanyCode());
        leasingForm.setEmail(updateLeasingFormInfo.getEmail());
        leasingForm.setPhoneNumber(updateLeasingFormInfo.getPhoneNumber());

        leasingForm.setFirstName(updateLeasingFormInfo.getFirstName());
        leasingForm.setLastName(updateLeasingFormInfo.getLastName());
        leasingForm.setPersonalCode(updateLeasingFormInfo.getPersonalCode());
        leasingForm.setPhoneNumber(updateLeasingFormInfo.getPhoneNumber());

        leasingForm.setStreet(updateLeasingFormInfo.getStreet());
        leasingForm.setCountry(updateLeasingFormInfo.getCountry());
        leasingForm.setPostCode(updateLeasingFormInfo.getPostCode());
        leasingForm.setCountry(updateLeasingFormInfo.getCountry());

        return leasingFormRepository.save(leasingForm);
    }

    public void deleteLeaseForm(String id) {
        leasingFormRepository.delete(leasingFormRepository.findLeasingFormById(id));
    }

}
