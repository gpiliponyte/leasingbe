package itacademy.vehicleleasingbe.leasingbe.services;


import itacademy.vehicleleasingbe.leasingbe.beans.documents.LeasingForm;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class UniqueIdGeneratorService {

    private LeasingForm leasingForm;


    public String generateUserId() {

        String firstNameLetter = this.leasingForm.getFirstName().substring(0, 1);
        String firstSurnameLetter = this.leasingForm.getLastName().substring(0, 1);
        String birthDayLastTwoDigits = this.leasingForm.getPersonalCode().substring(2, 2);
        int randomInt = new Random().nextInt(4);
        String userId = firstNameLetter + firstSurnameLetter + birthDayLastTwoDigits + randomInt;
        System.out.println(userId);
        return userId;

    }
}
