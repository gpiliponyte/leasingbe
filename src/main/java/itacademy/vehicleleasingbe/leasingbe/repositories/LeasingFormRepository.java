package itacademy.vehicleleasingbe.leasingbe.repositories;

import itacademy.vehicleleasingbe.leasingbe.beans.documents.LeasingForm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeasingFormRepository extends CrudRepository<LeasingForm, String> {

    List<LeasingForm> findAll();
    LeasingForm findLeasingFormById(String id);
    LeasingForm findByUniqueId(String uniqueId);
    List<LeasingForm> findAllByApplicationStatus(String applicationStatus);

}
