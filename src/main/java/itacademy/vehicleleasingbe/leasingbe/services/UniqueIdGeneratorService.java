package itacademy.vehicleleasingbe.leasingbe.services;

import itacademy.vehicleleasingbe.leasingbe.beans.documents.LeasingForm;
import org.springframework.stereotype.Service;
import java.util.Random;

import java.util.*;


@Service
public class UniqueIdGeneratorService {


    public String generateUserId(LeasingForm leasingForm) {

        if (leasingForm.getCustomerType().equals("Private")) {
            long timeNow = System.currentTimeMillis();
            String timeStamp = Long.toString(timeNow).substring(0,3);
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
            String userId = firstNameLetter + firstSurnameLetter + birthDayLastTwoDigits + generatedString + timeStamp;
            System.out.println(userId);
            return userId;
        } else if (leasingForm.getCustomerType().equals("Business")) {
            long timeNow = System.currentTimeMillis();
            Random rnd = new Random();
            int randomInteger = 1000+rnd.nextInt(9000);
            String threeCompanyCodeNumbers = leasingForm.getCompanyCode().substring(0, 4);
            String possibleChars = "abcdefghjklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            StringBuilder stringBuilder = new StringBuilder();
            Random random = new Random(System.currentTimeMillis() % 100000);
            while (stringBuilder.length() < 4) {
                int index = (int) (random.nextFloat() * (possibleChars.length()));
                stringBuilder.append(possibleChars.charAt(index));
            }
            String generatedString = stringBuilder.toString();
            String userId = threeCompanyCodeNumbers +  generatedString + randomInteger;
            System.out.println(userId);
            return userId;
        } else {
            return "";
        }
    }
}
