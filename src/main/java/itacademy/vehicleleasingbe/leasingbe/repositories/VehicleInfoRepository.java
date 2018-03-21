package itacademy.vehicleleasingbe.leasingbe.repositories;

import itacademy.vehicleleasingbe.leasingbe.beans.documents.LeasingForm;
import itacademy.vehicleleasingbe.leasingbe.beans.documents.VehicleInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VehicleInfoRepository extends CrudRepository<VehicleInfo, String> {
    List<VehicleInfo> findAll();

    VehicleInfo findVehicleInfoById(String id);
}
