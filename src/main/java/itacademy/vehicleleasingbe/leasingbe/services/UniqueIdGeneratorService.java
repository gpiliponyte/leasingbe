package itacademy.vehicleleasingbe.leasingbe.services;


import itacademy.vehicleleasingbe.leasingbe.beans.documents.LeasingForm;
import itacademy.vehicleleasingbe.leasingbe.beans.response.PostLeasingForm;
import itacademy.vehicleleasingbe.leasingbe.controllers.LeasingFormController;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class UniqueIdGeneratorService {



//    public Boolean checkIfUniqueIdIsAlreadyUsed(String randomString){
//
//          LeasingFormService leasingFormService = new LeasingFormService();
//
//        List<PostLeasingForm> leasingForms=  leasingFormService.getAllLeases();
//
//        for (PostLeasingForm postLeasingForm: leasingForms) {
//            if (postLeasingForm.getUniqueId() == randomString)
//            {
//                return false;
//            }
//        }
//        return true;
//
//    }

    public String generateUserId(LeasingForm leasingForm) {

        if (leasingForm.getCustomerType().equals("Private")) {
            String firstNameLetter = leasingForm.getFirstName().substring(0, 1);
            String firstSurnameLetter = leasingForm.getLastName().substring(0, 1);
            String birthDayLastTwoDigits = leasingForm.getPersonalCode().substring(1, 3);
            String possibleChars = "abcdefghjklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            StringBuilder stringBuilder = new StringBuilder();
            Random random = new Random(System.currentTimeMillis() % 100000);
            while (stringBuilder.length() < 4) {
                int index = (int) (random.nextFloat() * (possibleChars.length()));
                stringBuilder.append(possibleChars.charAt(index));
            }
            String generatedString = stringBuilder.toString();
            String userId = firstNameLetter + firstSurnameLetter + birthDayLastTwoDigits + generatedString;
            System.out.println(userId);
            return userId;
        } else if (leasingForm.getCustomerType().equals("Business")) {
            String firstCompanyNameLetter = leasingForm.getCompanyName().substring(0, 1);
            String threeCompanyCodeNumbers = leasingForm.getCompanyCode().substring(0, 3);
            String possibleChars = "abcdefghjklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            StringBuilder stringBuilder = new StringBuilder();
            Random random = new Random(System.currentTimeMillis() % 100000);
            while (stringBuilder.length() < 4) {
                int index = (int) (random.nextFloat() * (possibleChars.length()));
                stringBuilder.append(possibleChars.charAt(index));
            }
            String generatedString = stringBuilder.toString();
            String userId = firstCompanyNameLetter + threeCompanyCodeNumbers +  generatedString;
            System.out.println(userId);
            return userId;
        } else {
            return "";
        }
    }
}
