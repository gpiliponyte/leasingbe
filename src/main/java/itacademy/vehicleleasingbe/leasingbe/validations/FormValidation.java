package itacademy.vehicleleasingbe.leasingbe.validations;

import itacademy.vehicleleasingbe.leasingbe.beans.documents.LeasingForm;
import itacademy.vehicleleasingbe.leasingbe.beans.response.VehicleInfoResponse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;


public class FormValidation {
    public void executeFormValidation(LeasingForm leasingForm, List<VehicleInfoResponse> vehicleInfos) {
        //form validations
        this.validateCustomerType(leasingForm.getCustomerType());
        this.validateAssetType(leasingForm.getAssetType());
        this.validateBrand(leasingForm.getBrand(), vehicleInfos);
        this.validateModel(leasingForm.getModel(), vehicleInfos);
        this.validateYear(leasingForm.getYear());
        this.validateEnginePower(leasingForm.getEnginePower());
        this.validateAssetPrice(leasingForm.getAssetPrice());
        this.validateAdvancePaymentPercentage(leasingForm.getAssetPrice(), leasingForm.getAdvancePaymentPercentage(), leasingForm.getAdvancePaymentAmount());
        this.validateAdvancePaymentAmount(leasingForm.getAssetPrice(), leasingForm.getAdvancePaymentPercentage(), leasingForm.getAdvancePaymentAmount());
        this.validateLeasePeriod(leasingForm.getLeasePeriod());
        //this.validateMargin(leasingForm.getMargin());
        this.validateContractFee(leasingForm.getAssetPrice(), leasingForm.getContractFee());
        this.validatePaymentDate(leasingForm.getPaymentDate());
        //Personal info
        int formType = this.validatePersonalInformationType(leasingForm);//0 - private 1 - business -1 - both
        if (formType == 0) {
            //private
            this.validateFirstName(leasingForm.getFirstName());
            this.validateLastName(leasingForm.getLastName());
            this.validatePersonalCode(leasingForm.getPersonalCode());

        } else if (formType == 1) {
            //business
            this.validateCompanyName(leasingForm.getCompanyName());
            this.validateCompanyCode(leasingForm.getCompanyCode());

        } else {
            throw new FormValidationException("Both Personal and Business fields filled");
        }
        //other fields
        this.validateEmail(leasingForm.getEmail());
        this.validatePhoneNumber(leasingForm.getPhoneNumber());
        this.validateStreet(leasingForm.getStreet());
        this.validateCity(leasingForm.getCity());
        this.validatePostCode(leasingForm.getPostCode());
        this.validateCountry(leasingForm.getCountry());
    }


    private void validateCustomerType(String customerType) {
        if (customerType.equals("Business") || customerType.equals("Private")) {
        } else {
            throw new FormValidationException("Invalid Customer Type");
        }
    }

    private void validateAssetType(String assetType) {
        if (assetType.equals("New") || assetType.equals("Used")) {

        } else {
            throw new FormValidationException("Invalid Asset Type");
        }
    }

    private void validateBrand(String brand, List<VehicleInfoResponse> vehicleInfos) {
        boolean isValid = false;
        for (VehicleInfoResponse vehicleInfoResponse : vehicleInfos) {
            if (vehicleInfoResponse.getGroupValue().equals(brand)) {
                isValid = true;
            }
        }
        if (isValid) {
        } else
            throw new FormValidationException("Invalid Brand");
    }

    private void validateModel(String model, List<VehicleInfoResponse> vehicleInfos) {
        boolean isValid = false;
        for (VehicleInfoResponse vehicleInfoResponse : vehicleInfos) {
            if (vehicleInfoResponse.getText().equals(model)) {
                isValid = true;
            }
        }
        if (isValid) {
        } else
            throw new FormValidationException("Invalid Model");
    }

    private void validateYear(BigDecimal year) {// no idea
        BigDecimal currentYear = new BigDecimal(Calendar.getInstance().get(Calendar.YEAR));
        if ((year.compareTo(new BigDecimal(2000)) != -1) && (year.compareTo(currentYear) != 1)) {

        } else
            throw new FormValidationException("Invalid year");

    }

    private void validateEnginePower(BigDecimal enginePower) {
        if (enginePower.compareTo(BigDecimal.ONE) > -1 && enginePower.scale() <= 2) {

        } else
            throw new FormValidationException("Invalid Engine Power");

    }

    private void validateAssetPrice(BigDecimal assetPrice) {
        if ((assetPrice.compareTo(new BigDecimal(10000)) != -1) && (assetPrice.scale() <= 2)) {

        } else
            throw new FormValidationException("Invalid Asset Price");

    }

    private void validateAdvancePaymentPercentage(BigDecimal assetPrice, BigDecimal advancePaymentPercentage, BigDecimal advancePaymentAmount) {
        if ((advancePaymentPercentage.compareTo(new BigDecimal(10)) != -1) && advancePaymentPercentage.scale() <= 2) {
            if (advancePaymentPercentage.compareTo(new BigDecimal(100)) != 1) {
                if (advancePaymentPercentage.scale() <= 1) {
                    advancePaymentPercentage = advancePaymentPercentage.setScale(2);
                }
                if (advancePaymentPercentage.equals(advancePaymentAmount.multiply(new BigDecimal(100)).divide(assetPrice, 2, RoundingMode.HALF_UP))) {

                } else {
                    throw new FormValidationException("Invalid Advance Payment Percentage or Advance Payment Amount");
                }
            }
        } else
            throw new FormValidationException("Invalid Advance Payment Percentage");

    }

    private void validateAdvancePaymentAmount(BigDecimal assetPrice, BigDecimal advancePaymentPercentage, BigDecimal advancePaymentAmount) {
        if ((advancePaymentAmount.compareTo(new BigDecimal(500)) != -1) && advancePaymentAmount.scale() <= 2) {
            if (advancePaymentAmount.scale() <= 1) {
                advancePaymentAmount = advancePaymentAmount.setScale(2);
            }
            BigDecimal locallyCalculateRoundAdvancePaymentAmount = advancePaymentPercentage.multiply(assetPrice).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
            BigDecimal locallyCalculateRoundFloorAdvancePaymentAmount = advancePaymentPercentage.multiply(assetPrice).divide(new BigDecimal(100), 0, RoundingMode.DOWN);
            locallyCalculateRoundFloorAdvancePaymentAmount = locallyCalculateRoundFloorAdvancePaymentAmount.setScale(2);
            if (advancePaymentAmount.equals(locallyCalculateRoundAdvancePaymentAmount) || advancePaymentAmount.equals(locallyCalculateRoundFloorAdvancePaymentAmount)) {

            } else {
                throw new FormValidationException("Invalid Advance Payment Amount or Advance Payment Percentage");
            }
        } else
            throw new FormValidationException("Invalid Advance Payment Amount");
    }

    private void validateLeasePeriod(BigDecimal leasePeriod) {
        if ((leasePeriod.compareTo(new BigDecimal(6)) != -1) && (leasePeriod.compareTo(new BigDecimal(84)) != 1)) {
            BigDecimal[] value = leasePeriod.divideAndRemainder(new BigDecimal(6));
            System.out.print(value);
            if (leasePeriod.divideAndRemainder(new BigDecimal(6))[1].equals(BigDecimal.ZERO)) {

            }
        } else
            throw new FormValidationException("Invalid Lease Period");
    }
/*
    private void validateMargin(BigDecimal margin) {
        BigDecimal smallestMargin = new BigDecimal(3.2);
        smallestMargin = smallestMargin.setScale(2, RoundingMode.FLOOR);
        if ((margin.compareTo(smallestMargin) != -1) && (margin.compareTo(new BigDecimal(100)) != 1) && margin.scale() <= 2) {

        } else
            throw new FormValidationException("Invalid Margin");
    }
*/
    private void validateContractFee(BigDecimal assetPrice, BigDecimal contractFee) {
        if (contractFee.scale() <= 2) {
            BigDecimal onePercentValue = assetPrice.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
            if (onePercentValue.compareTo(new BigDecimal(200)) == -1) {
                onePercentValue = new BigDecimal(200);
                onePercentValue.setScale(2);
            }
            if (onePercentValue.scale() <= 1) {
                onePercentValue = onePercentValue.setScale(2);
            }
            if (contractFee.scale() <= 1) {
                contractFee = contractFee.setScale(2);
            }
            if (onePercentValue.equals(contractFee)) {

            }
        } else
            throw new FormValidationException("Invalid Contract Fee");
    }

    private void validatePaymentDate(String paymentDate) {
        if (paymentDate.equals("15") || paymentDate.equals("30")) {

        } else {
            throw new FormValidationException("Invalid Payment Date");
        }
    }

    //personal information
    private int validatePersonalInformationType(LeasingForm leasingForm) {//0 - private 1 - business -1 - both
        boolean isPrivateForm = false;
        boolean isBusinessForm = false;
        if (leasingForm.getFirstName() != null || leasingForm.getLastName() != null || leasingForm.getPersonalCode() != null) {
            isPrivateForm = true;
        }
        if (leasingForm.getCompanyName() != null || leasingForm.getCompanyCode() != null) {
            isBusinessForm = true;
        }
        if (isBusinessForm && !isPrivateForm) {
            return 1;
        } else if (!isBusinessForm && isPrivateForm) {
            return 0;
        }
        return -1;
    }

    //private
    private void validateFirstName(String firstName) {
        if (firstName.matches("[a-zA-Z]+")) {

        } else
            throw new FormValidationException("Invalid First Name");

    }

    private void validateLastName(String lastName) {
        if (lastName.matches("[a-zA-ZąčęėįųūšžĄČĖĘĮŲŪČŠŽ ,.\\'-]+")) {

        } else
            throw new FormValidationException("Invalid Last Name");
    }

    private void validatePersonalCode(String personalCode) {
        if (personalCode.matches("[3-6][0-9]{2}[0,1][0-9][0-9]{2}[0-9]{4}")) {

        } else
            throw new FormValidationException("Invalid Personal Code");
    }

    //business
    private void validateCompanyName(String companyName) {
        if (companyName.matches("[a-zA-Z0-9ĄČĘĖĮŠŲŪŽąčęėįšųūž\\s]+")) {

        } else
            throw new FormValidationException("Invalid Company Name");
    }

    private void validateCompanyCode(String companyCode) {
        if (companyCode.matches("[0-9]+")) {

        } else
            throw new FormValidationException("Invalid Company Code");
    }

    //other fields
    private void validateEmail(String email) {

        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);

        if (m.matches()) {

        } else
            throw new FormValidationException("Invalid Email");
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("[0-9]+")) {

        } else
            throw new FormValidationException("Invalid Phone Number");
    }

    private void validateStreet(String street) {
        if (street.matches("[a-zA-ZĄČĘĖĮŠŲŪŽąčęėįšųūž0-9\\s\\.\\-]+")) {
        } else
            throw new FormValidationException("Invalid Street");
    }

    private void validateCity(String city) {
        if (city.matches("[a-zA-ZĄČĘĖĮŠŲŪŽąčęėįšųūž\\s]+")) {
        } else
            throw new FormValidationException("Invalid City");
    }


    private void validatePostCode(String postCode) {
        if (postCode.matches("[a-zA-Z0-9\\-]*")) {
        } else
            throw new FormValidationException("Invalid Post Code");
    }

    private void validateCountry(String country) {
        if (country.matches("[a-zA-ZĄČĘĖĮŠŲŪŽąčęėįšųūž\\s]+")) {
        } else
            throw new FormValidationException("Invalid Country");
    }
}