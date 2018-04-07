package itacademy.vehicleleasingbe.leasingbe.beans.response;

import itacademy.vehicleleasingbe.leasingbe.beans.documents.VehicleInfo;

import java.io.Serializable;

public class VehicleInfoResponse implements Serializable {

    //Data already had entity names set
    private String id;
    private String groupValue;//vehicle brand
    private String text;//vehicle model
    private String value;//vehicle model

    public VehicleInfoResponse(VehicleInfo vehicleInfo){

        this.id = vehicleInfo.getId();
        this.groupValue = vehicleInfo.getGroupValue();
        this.text = vehicleInfo.getText();
        this.value = vehicleInfo.getValue();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupValue() {
        return groupValue;
    }

    public void setGroupValue(String groupValue) {
        this.groupValue = groupValue;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
