package itacademy.vehicleleasingbe.leasingbe.services;


import itacademy.vehicleleasingbe.leasingbe.beans.documents.BusinessCustomerInfo;
import itacademy.vehicleleasingbe.leasingbe.beans.response.ShowBusinessCustomerInfo;
import itacademy.vehicleleasingbe.leasingbe.repositories.BusinessCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessCustomerService {

    @Autowired
    private BusinessCustomerRepository businessCustomerRepository;

    public List<ShowBusinessCustomerInfo> getAllBusinessInfo() {
        return businessCustomerRepository.findAll().stream()
                .map(ShowBusinessCustomerInfo::new)
                .collect(Collectors.toList());
    }

    public BusinessCustomerInfo addNewBusinessInfo(@Valid BusinessCustomerInfo businessCustomerInfo) {
        BusinessCustomerInfo newBusinessCustomerInfo = new BusinessCustomerInfo();


        newBusinessCustomerInfo.setCompanyName(businessCustomerInfo.getCompanyName());
        newBusinessCustomerInfo.setCompanyCode(businessCustomerInfo.getCompanyCode());
        newBusinessCustomerInfo.setEmail(businessCustomerInfo.getEmail());
        newBusinessCustomerInfo.setPhoneNumber(businessCustomerInfo.getPhoneNumber());
        newBusinessCustomerInfo.setStreet(businessCustomerInfo.getStreet());
        newBusinessCustomerInfo.setCity(businessCustomerInfo.getCity());
        newBusinessCustomerInfo.setPostCode(businessCustomerInfo.getPostCode());
        newBusinessCustomerInfo.setCountry(businessCustomerInfo.getCountry());


        return businessCustomerRepository.save(newBusinessCustomerInfo);
    }

    public BusinessCustomerInfo updateBusinessInfo(String id, BusinessCustomerInfo updateBusinessCustomerInfo) {
        // steps:
        // 1. get particular blog post
        // 2. update blog post
        // 3. save blog post
        BusinessCustomerInfo businessCustomerInfo = businessCustomerRepository.findBusinessCustomerInfoById(id);

        businessCustomerInfo.setCompanyName(updateBusinessCustomerInfo.getCompanyName());
        businessCustomerInfo.setCompanyCode(updateBusinessCustomerInfo.getCompanyCode());
        businessCustomerInfo.setEmail(updateBusinessCustomerInfo.getEmail());
        businessCustomerInfo.setPhoneNumber(updateBusinessCustomerInfo.getPhoneNumber());
        businessCustomerInfo.setStreet(updateBusinessCustomerInfo.getStreet());
        businessCustomerInfo.setCountry(updateBusinessCustomerInfo.getCountry());
        businessCustomerInfo.setPostCode(updateBusinessCustomerInfo.getPostCode());
        businessCustomerInfo.setCountry(updateBusinessCustomerInfo.getCountry());

        return businessCustomerRepository.save(businessCustomerInfo);
    }

    public void deleteBusinessInfo(String id) {
        businessCustomerRepository.delete(businessCustomerRepository.findBusinessCustomerInfoById(id));
    }
}
