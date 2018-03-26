package itacademy.vehicleleasingbe.leasingbe.repositories;

import itacademy.vehicleleasingbe.leasingbe.beans.documents.BusinessCustomerInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessCustomerRepository extends CrudRepository<BusinessCustomerInfo, String> {

    List<BusinessCustomerInfo> findAll();

    BusinessCustomerInfo findBusinessCustomerInfoById(String id);

}
