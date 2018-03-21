package itacademy.vehicleleasingbe.leasingbe.beans.response;

import itacademy.vehicleleasingbe.leasingbe.beans.documents.VehicleInfo;

import javax.validation.constraints.NotNull;

public class VehicleInfoResponse extends Response {

    private String id;
    private String groupValue;
    private String text;
    private String value;

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
