package itacademy.vehicleleasingbe.leasingbe.Validations;

import itacademy.vehicleleasingbe.leasingbe.beans.documents.LeasingForm;
import itacademy.vehicleleasingbe.leasingbe.beans.response.VehicleInfoResponse;

import java.math.BigDecimal;
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
        customException = this.validateContractFee(leasingForm.getAssetPrice(), leasingForm.getContractFee());
        if (customException != null)
            return customException;
        customException = this.validatePaymentDate(leasingForm.getPaymentDate());
        if (customException != null)
            return customException;
        //Personal info
        int formType = this.validatePersonalInformationType(leasingForm);//0 - private 1 - business -1 - both
        if (formType == 0) {
            //private
            customException = this.validateFirstName(leasingForm.getFirstName());
            if (customException != null)
                return customException;
            customException = this.validateLastName(leasingForm.getLastName());
            if (customException != null)
                return customException;
            customException = this.validatePersonalCode(leasingForm.getPersonalCode());
            if (customException != null)
                return customException;
        } else if (formType == 1) {
            //business
            customException = this.validateCompanyName(leasingForm.getCompanyName());
            if (customException != null)
                return customException;
            customException = this.validateCompanyCode(leasingForm.getCompanyCode());
            if (customException != null)
                return customException;
        } else {
            return new CustomException("Both Personal and Business fields filled");
        }
        //other fields
        customException = this.validateEmail(leasingForm.getEmail());
        if (customException != null)
            return customException;
        customException = this.validatePhoneNumber(leasingForm.getPhoneNumber());
        if (customException != null)
            return customException;
        customException = this.validateStreet(leasingForm.getStreet());
        if (customException != null)
            return customException;
        customException = this.validateCity(leasingForm.getCity());
        if (customException != null)
            return customException;
        customException = this.validatePostCode(leasingForm.getPostCode());
        if (customException != null)
            return customException;
        customException = this.validateCountry(leasingForm.getCountry());
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
        if ((assetPrice.compareTo(new BigDecimal(10000)) != -1) && (assetPrice.scale() <= 2)) {
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
                if (contractFee.scale() <= 1) {
                    contractFee = contractFee.setScale(2);
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
    private CustomException validateFirstName(String firstName) {
        if (firstName.matches("[a-zA-Z]+")) {
            return null;
        }
        return new CustomException("Invalid First Name");

    }

    private CustomException validateLastName(String lastName) {
        if (lastName.matches("[a-zA-ZąčęėįųūšžĄČĖĘĮŲŪČŠŽ ,.\\'-]+")) {
            return null;
        }
        return new CustomException("Invalid Last Name");
    }

    private CustomException validatePersonalCode(String personalCode) {
        if (personalCode.matches("[3-6][0-9]{2}[0,1][0-9][0-9]{2}[0-9]{4}")) {
            return null;
        }
        return new CustomException("Invalid Personal Code");
    }

    //business
    private CustomException validateCompanyName(String companyName) {
        if (companyName.matches("[a-zA-Z0-9ĄČĘĖĮŠŲŪŽąčęėįšųūž\\s]+")) {
            return null;
        }
        return new CustomException("Invalid Company Name");
    }

    private CustomException validateCompanyCode(String companyCode) {
        if (companyCode.matches("[0-9]{9}")) {
            return null;
        }
        return new CustomException("Invalid Company Code");
    }

    //other fields
    private CustomException validateEmail(String email) {

        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);

        if (m.matches()) {
            return null;
        }
        return new CustomException("Invalid Email");
    }

    private CustomException validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("[+]+[0-9]{11}")) {
            return null;
        }
        return new CustomException("Invalid Phone Number");
    }

    private CustomException validateStreet(String street) {
        if (street.matches("[a-zA-ZĄČĘĖĮŠŲŪŽąčęėįšųūž0-9\\s\\.\\-]+")) {
            return null;
        }
        return new CustomException("Invalid Street");
    }

    private CustomException validateCity(String city) {
        if (city.matches("[a-zA-ZĄČĘĖĮŠŲŪŽąčęėįšųūž\\s]+")) {
            return null;
        }
        return new CustomException("Invalid City");
    }


    private CustomException validatePostCode(String postCode) {
        if (postCode.matches("[a-zA-Z0-9\\-]*")) {
            return null;
        }
        return new CustomException("Invalid Post Code");
    }

    private CustomException validateCountry(String country) {
        if (country.matches("[a-zA-ZĄČĘĖĮŠŲŪŽąčęėįšųūž\\s]+")) {
            return null;
        }
        return new CustomException("Invalid Country");
    }
}
