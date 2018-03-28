package itacademy.vehicleleasingbe.leasingbe.Validations;

import itacademy.vehicleleasingbe.leasingbe.beans.documents.LeasingForm;
import itacademy.vehicleleasingbe.leasingbe.beans.documents.VehicleInfo;
import itacademy.vehicleleasingbe.leasingbe.beans.response.VehicleInfoResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;


public class FormValidation {
    public CustomException executeFormValidation(LeasingForm leasingForm, List<VehicleInfoResponse> vehicleInfos) {
        //form validations
        CustomException customException = this.validateCustomerType(leasingForm.getCustomerType());
        if (customException != null)
            return customException;
        customException = this.validateAssetType(leasingForm.getAssetType());
        if (customException != null)
            return customException;
        customException = this.validateBrand(leasingForm.getBrand(), vehicleInfos);
        if (customException != null)
            return customException;
        customException = this.validateModel(leasingForm.getModel(), vehicleInfos);
        if (customException != null)
            return customException;
        customException = this.validateYear(leasingForm.getYear());
        if (customException != null)
            return customException;
        customException = this.validateEnginePower(leasingForm.getEnginePower());
        if (customException != null)
            return customException;
        customException = this.validateAssetPrice(leasingForm.getAssetPrice());
        if (customException != null)
            return customException;
        customException = this.validateAdvancePaymentPercentage(leasingForm.getAssetPrice(), leasingForm.getAdvancePaymentPercentage(), leasingForm.getAdvancePaymentAmount());
        if (customException != null)
            return customException;
        customException = this.validateAdvancePaymentAmount(leasingForm.getAssetPrice(), leasingForm.getAdvancePaymentPercentage(), leasingForm.getAdvancePaymentAmount());
        if (customException != null)
            return customException;
        customException = this.validateLeasePeriod(leasingForm.getLeasePeriod());
        if (customException != null)
            return customException;
        customException = this.validateMargin(leasingForm.getMargin());
        if (customException != null)
            return customException;
        customException = this.validateContractFee(leasingForm.getAssetPrice(),leasingForm.getContractFee());
        if (customException != null)
            return customException;
        customException = this.validatePaymentDate(leasingForm.getPaymentDate());
        if (customException != null)
            return customException;
        //Personal info
        customException = this.validatePersonalInformationType();
        if (customException != null)
            return customException;
        //private
        customException = this.validateFirstName();
        if (customException != null)
            return customException;
        customException = this.validateLastName();
        if (customException != null)
            return customException;
        customException = this.validatePersonalCode();
        if (customException != null)
            return customException;
        //business
        customException = this.validateCompanyName();
        if (customException != null)
            return customException;
        customException = this.validateCompanyCode();
        if (customException != null)
            return customException;
        //other fields
        customException = this.validateEmail();
        if (customException != null)
            return customException;
        customException = this.validatePhoneNumber();
        if (customException != null)
            return customException;
        customException = this.validateStreet();
        if (customException != null)
            return customException;
        customException = this.validateCity();
        if (customException != null)
            return customException;
        customException = this.validatePostCode();
        if (customException != null)
            return customException;
        customException = this.validateCountry();
        if (customException != null)
            return customException;

        return null;

    }


    private CustomException validateCustomerType(String customerType) {
        if (customerType.equals("Business") || customerType.equals("Private")) {
            return null;
        } else {
            return new CustomException("Invalid Customer Type");
        }
    }

    private CustomException validateAssetType(String assetType) {
        if (assetType.equals("New") || assetType.equals("Used")) {
            return null;
        } else {
            return new CustomException("Invalid Asset Type");
        }
    }

    private CustomException validateBrand(String brand, List<VehicleInfoResponse> vehicleInfos) {
        boolean isValid = false;
        for (VehicleInfoResponse vehicleInfoResponse : vehicleInfos) {
            if (vehicleInfoResponse.getGroupValue().equals(brand)) {
                isValid = true;
            }
        }
        if (isValid)
            return null;
        else
            return new CustomException("Invalid Brand");
    }

    private CustomException validateModel(String model, List<VehicleInfoResponse> vehicleInfos) {
        boolean isValid = false;
        for (VehicleInfoResponse vehicleInfoResponse : vehicleInfos) {
            if (vehicleInfoResponse.getText().equals(model)) {
                isValid = true;
            }
        }
        if (isValid)
            return null;
        else
            return new CustomException("Invalid Model");
    }

    private CustomException validateYear(BigDecimal year) {// no idea
        BigDecimal currentYear = new BigDecimal(Calendar.getInstance().get(Calendar.YEAR));
        if ((year.compareTo(new BigDecimal(2000)) != -1) && (year.compareTo(currentYear) != 1)) {
            return null;
        }
        return new CustomException("Invalid year");
    }

    private CustomException validateEnginePower(BigDecimal enginePower) {
        if (enginePower.compareTo(BigDecimal.ONE) > -1 && enginePower.scale() <= 2) {
            return null;
        } else {
            return new CustomException("Invalid Engine Power");
        }
    }

    private CustomException validateAssetPrice(BigDecimal assetPrice) {
        if ((assetPrice.compareTo(new BigDecimal(5000)) != -1) && (assetPrice.scale() <= 2)) {
            return null;
        }
        return new CustomException("Invalid Asset Price");
    }

    private CustomException validateAdvancePaymentPercentage(BigDecimal assetPrice, BigDecimal advancePaymentPercentage, BigDecimal advancePaymentAmount) {
        if ((advancePaymentPercentage.compareTo(new BigDecimal(10)) != -1) && advancePaymentPercentage.scale() <= 2) {
            if (advancePaymentPercentage.compareTo(new BigDecimal(100)) != 1) {
                if (advancePaymentPercentage.scale() <= 1) {
                    advancePaymentPercentage = advancePaymentPercentage.setScale(2);
                }
                BigDecimal kappa = advancePaymentAmount.multiply(new BigDecimal(100)).divide(assetPrice, 2, RoundingMode.HALF_UP);
                if (advancePaymentPercentage.equals(advancePaymentAmount.multiply(new BigDecimal(100)).divide(assetPrice, 2, RoundingMode.HALF_UP))) {
                    return null;
                } else {
                    return new CustomException("Invalid Advance Payment Percentage or Advance Payment Amount");
                }
            }
        }
        return new CustomException("Invalid Advance Payment Percentage");
    }

    private CustomException validateAdvancePaymentAmount(BigDecimal assetPrice, BigDecimal advancePaymentPercentage, BigDecimal advancePaymentAmount) {
        if ((advancePaymentAmount.compareTo(new BigDecimal(500)) != -1) && advancePaymentAmount.scale() <= 2) {
            if (advancePaymentAmount.scale() <= 1) {
                advancePaymentAmount = advancePaymentAmount.setScale(2);
            }
            if (advancePaymentAmount.equals(advancePaymentPercentage.multiply(assetPrice).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP))) {
                return null;
            } else {
                return new CustomException("Invalid Advance Payment Amount or Advance Payment Percentage");
            }
        }
        return new CustomException("Invalid Advance Payment Amount");
    }

    private CustomException validateLeasePeriod(BigDecimal leasePeriod) {
        if ((leasePeriod.compareTo(new BigDecimal(6)) != -1) && (leasePeriod.compareTo(new BigDecimal(84)) != 1)) {
            BigDecimal[] value = leasePeriod.divideAndRemainder(new BigDecimal(6));
            System.out.print(value);
            if (leasePeriod.divideAndRemainder(new BigDecimal(6))[1].equals(BigDecimal.ZERO)) {
                return null;
            }
        }
        return new CustomException("Invalid Lease Period");
    }

    private CustomException validateMargin(BigDecimal margin) {
        BigDecimal smallestMargin = new BigDecimal(3.2);
        smallestMargin = smallestMargin.setScale(2, RoundingMode.FLOOR);
        if ((margin.compareTo(smallestMargin) != -1) && (margin.compareTo(new BigDecimal(100)) != 1) && margin.scale() <= 2) {
            return null;
        }

        return new CustomException("Invalid Margin");
    }

    private CustomException validateContractFee(BigDecimal assetPrice, BigDecimal contractFee) {
        if (contractFee.scale() <= 2) {
            BigDecimal onePercentValue = assetPrice.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
            if (onePercentValue.compareTo(new BigDecimal(200)) != 1) {
                if (contractFee.equals(new BigDecimal(50))) {
                    return null;
                }
            } else {
                if(contractFee.scale() <= 1){
                    contractFee=contractFee.setScale(2);
                }
                if (onePercentValue.equals(contractFee)) {
                    return null;
                }
            }
        }
        return new CustomException("Invalid Contract Fee");
    }

    private CustomException validatePaymentDate(String paymentDate) {
        if (paymentDate.equals("15") || paymentDate.equals("30")) {
            return null;
        } else {
            return new CustomException("Invalid Payment Date");
        }
    }

    private CustomException validatePersonalInformationType() {
        return null;
    }

    //personal information
    //private
    private CustomException validateFirstName() {
        return null;

    }

    private CustomException validateLastName() {
        return null;
    }

    private CustomException validatePersonalCode() {
        return null;
    }

    //business
    private CustomException validateCompanyName() {
        return null;
    }

    private CustomException validateCompanyCode() {
        return null;
    }

    //other fields
    private CustomException validateEmail() {
        return null;
    }

    private CustomException validatePhoneNumber() {
        return null;
    }

    private CustomException validateStreet() {
        return null;
    }

    private CustomException validateCity() {
        return null;
    }

    private CustomException validatePostCode() {
        return null;
    }

    private CustomException validateCountry() {
        return null;
    }
}
