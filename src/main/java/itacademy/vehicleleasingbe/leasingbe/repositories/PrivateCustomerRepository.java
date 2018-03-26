package itacademy.vehicleleasingbe.leasingbe.repositories;

import itacademy.vehicleleasingbe.leasingbe.beans.documents.PrivateCustomerInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivateCustomerRepository extends CrudRepository<PrivateCustomerInfo, String> {

        List<PrivateCustomerInfo> findAll();
        PrivateCustomerInfo findPrivateCustomerInfoById(String id);

}
