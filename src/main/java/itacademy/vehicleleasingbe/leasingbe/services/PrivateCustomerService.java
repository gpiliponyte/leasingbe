package itacademy.vehicleleasingbe.leasingbe.services;


import itacademy.vehicleleasingbe.leasingbe.beans.documents.BusinessCustomerInfo;
import itacademy.vehicleleasingbe.leasingbe.beans.documents.PrivateCustomerInfo;
import itacademy.vehicleleasingbe.leasingbe.beans.response.ShowPrivateCustomerInfo;
import itacademy.vehicleleasingbe.leasingbe.repositories.PrivateCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrivateCustomerService {

    @Autowired
    private PrivateCustomerRepository privateCustomerRepository;

    public List<ShowPrivateCustomerInfo> getAllPrivateInfo() {
        return privateCustomerRepository.findAll().stream()
                .map(ShowPrivateCustomerInfo::new)
                .collect(Collectors.toList());
    }

    public PrivateCustomerInfo addNewPrivateInfo(@Valid PrivateCustomerInfo privateCustomerInfo) {
        PrivateCustomerInfo newPrivateCustomerInfo = new PrivateCustomerInfo();

        newPrivateCustomerInfo.setFirstName(privateCustomerInfo.getFirstName());
        newPrivateCustomerInfo.setLastName(privateCustomerInfo.getLastName());
        newPrivateCustomerInfo.setIdCode(privateCustomerInfo.getIdCode());
        newPrivateCustomerInfo.setStreet(privateCustomerInfo.getStreet());
        newPrivateCustomerInfo.setCity(privateCustomerInfo.getCity());
        newPrivateCustomerInfo.setPostCode(privateCustomerInfo.getPostCode());
        newPrivateCustomerInfo.setCountry(privateCustomerInfo.getCountry());

        return privateCustomerRepository.save(newPrivateCustomerInfo);
    }
    public PrivateCustomerInfo updatePrivateInfo(String id, PrivateCustomerInfo updatePrivateCustomerInfo) {
        // steps:
        // 1. get particular blog post
        // 2. update blog post
        // 3. save blog post
        PrivateCustomerInfo privateCustomerInfo = privateCustomerRepository.findPrivateCustomerInfoById(id);

        privateCustomerInfo.setFirstName(updatePrivateCustomerInfo.getFirstName());
        privateCustomerInfo.setLastName(updatePrivateCustomerInfo.getLastName());
        privateCustomerInfo.setIdCode(updatePrivateCustomerInfo.getIdCode());
        privateCustomerInfo.setPhoneNumber(updatePrivateCustomerInfo.getPhoneNumber());
        privateCustomerInfo.setStreet(updatePrivateCustomerInfo.getStreet());
        privateCustomerInfo.setCountry(updatePrivateCustomerInfo.getCountry());
        privateCustomerInfo.setPostCode(updatePrivateCustomerInfo.getPostCode());
        privateCustomerInfo.setCountry(updatePrivateCustomerInfo.getCountry());

        return privateCustomerRepository.save(privateCustomerInfo);
    }

    public void deletePrivateInfo(String id) {
        privateCustomerRepository.delete(privateCustomerRepository.findPrivateCustomerInfoById(id));
    }
}
