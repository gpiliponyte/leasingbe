package itacademy.vehicleleasingbe.leasingbe.services;


import itacademy.vehicleleasingbe.leasingbe.beans.documents.LeasingForm;
import itacademy.vehicleleasingbe.leasingbe.beans.response.PostLeasingForm;
import itacademy.vehicleleasingbe.leasingbe.controllers.LeasingFormController;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;


@Service
public class UniqueIdGeneratorService {



    public Boolean checkIfUniqueIdIsAlreadyUsed(String randomString){

        LeasingFormService leasingFormService= new LeasingFormService();

        List<PostLeasingForm> leasingForms=leasingFormService.getAllLeases();

        for (PostLeasingForm postLeasingForm:leasingForms) {
            if (postLeasingForm.getUniqueId() == randomString)
            {
                return false;
            }
        }
        return true;
    }

    public String generateUserId(LeasingForm leasingForm) {

        String firstNameLetter = leasingForm.getFirstName().substring(0, 1);
        String firstSurnameLetter = leasingForm.getLastName().substring(0, 1);
        String birthDayLastTwoDigits = leasingForm.getPersonalCode().substring(1, 3);

        String possibleChars = "abcdefghjklmnopqrstuwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        StringBuilder stringBuilder = new StringBuilder();

        Random random = new Random();

        while (stringBuilder.length() < 4) {

            int index = (int) (random.nextFloat() * (possibleChars.length()));
            stringBuilder.append(possibleChars.charAt(index));

        }
        String generatedString = stringBuilder.toString();

        String userId = firstNameLetter + firstSurnameLetter + birthDayLastTwoDigits + generatedString;


        System.out.println(userId);

        return userId;
    }
}
