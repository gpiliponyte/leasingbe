package itacademy.vehicleleasingbe.leasingbe.beans.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "vehicles")
public class VehicleInfo {

    @Id
    private String id;
    @NotNull
    private String groupValue;//vehicle brand
    @NotNull
    private String text;//vehicle model
    @NotNull
    private String value;//vehicle model

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
