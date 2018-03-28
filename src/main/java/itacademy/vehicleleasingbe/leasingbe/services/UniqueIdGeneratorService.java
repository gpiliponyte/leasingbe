package itacademy.vehicleleasingbe.leasingbe.services;


import itacademy.vehicleleasingbe.leasingbe.beans.documents.LeasingForm;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class UniqueIdGeneratorService {


    public String generateUserId(LeasingForm leasingForm) {

        String firstNameLetter = leasingForm.getFirstName().substring(0, 1);
        String firstSurnameLetter = leasingForm.getLastName().substring(0, 1);
        String birthDayLastTwoDigits = leasingForm.getPersonalCode().substring(0, 2);
        int randomInt = new Random().nextInt(4);
        String userId = firstNameLetter + firstSurnameLetter + birthDayLastTwoDigits + randomInt;
        System.out.println(userId);
        return userId;

    }
}
