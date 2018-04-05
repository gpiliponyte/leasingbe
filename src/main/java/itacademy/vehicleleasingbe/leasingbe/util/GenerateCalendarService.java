package itacademy.vehicleleasingbe.leasingbe.util;

import itacademy.vehicleleasingbe.leasingbe.beans.documents.LeasingForm;
import itacademy.vehicleleasingbe.leasingbe.beans.documents.Payment;
import itacademy.vehicleleasingbe.leasingbe.util.CalculateMarginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

@Service
public class GenerateCalendarService {

    @Autowired
    private CalculateMarginService calculateMarginService;

    public Payment[] generateCalendar(LeasingForm leasingForm){

        int leasePeriodLengthInMonths = leasingForm.getLeasePeriod().intValue();
        int paymentDay = Integer.parseInt(leasingForm.getPaymentDate());
        Payment[] payments = new Payment[leasePeriodLengthInMonths];

        BigDecimal unpaidAssetAmount = leasingForm.getAssetPrice().subtract(leasingForm.getAdvancePaymentAmount());
        BigDecimal totalPaymentAmount = getTotalPaymentAmount(leasePeriodLengthInMonths, unpaidAssetAmount, calculateMarginService.calculateMargin().doubleValue()/100);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getFirstPaymentDate(leasingForm.getDate(), paymentDay));
        Calendar now = Calendar.getInstance();
        now.setTime(calendar.getTime());

        for(int i = 0; i < leasePeriodLengthInMonths; i ++){
            calendar.add(Calendar.MONTH, i);
            if(calendar.get(Calendar.MONTH)!= 1 && paymentDay == 30) calendar.set(Calendar.DATE, 30);
            if(calendar.get(Calendar.MONTH)== 1 && paymentDay == 30 && calendar.get(Calendar.YEAR) % 4 == 0)
                calendar.set(calendar.get(Calendar.YEAR), 1, 29);

            payments[i] = new Payment();

            //date
            payments[i].setDate(calendar.getTime());
            //unpaidAssetAmount
            payments[i].setUnpaidAssetAmount(unpaidAssetAmount);
            //totalPaymentAmount
            payments[i].setTotalPaymentAmount(totalPaymentAmount);
            //interestAmount
            payments[i].setInterestAmount
                    (payments[i].getUnpaidAssetAmount().multiply
                            (new BigDecimal(calculateMarginService.calculateMargin().doubleValue()/100)).divide(new BigDecimal(12), 2, RoundingMode.HALF_UP));

            //unpaidAssetRepaymentAmount

            payments[i].setUnpaidAssetRepaymentAmount
                    (totalPaymentAmount.subtract(payments[i].getInterestAmount()));


            unpaidAssetAmount = unpaidAssetAmount.subtract(payments[i].getUnpaidAssetRepaymentAmount());

            calendar.setTime(now.getTime());
        }

        return payments;

    }

    public Date getFirstPaymentDate(Date now, int paymentDate){

        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(now);

        Calendar firstPaymentDate = Calendar.getInstance();


        if(paymentDate == 30 && currentDate.get(Calendar.MONTH) == 1 && currentDate.get(Calendar.YEAR) % 4 == 0){
            firstPaymentDate.set(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), 29);
        }

        else if(paymentDate == 30 && currentDate.get(Calendar.MONTH) == 1 && currentDate.get(Calendar.YEAR) % 4 != 0){
            firstPaymentDate.set(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), 28);
        }

        else{
            firstPaymentDate.set(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), paymentDate);
        }

        Calendar dayBeforeFirstPayment = Calendar.getInstance();
        dayBeforeFirstPayment.setTime(firstPaymentDate.getTime());
        dayBeforeFirstPayment.add(Calendar.DATE, -1);

        if(!currentDate.after(dayBeforeFirstPayment)) return firstPaymentDate.getTime();


        if(paymentDate == 30 && currentDate.get(Calendar.MONTH) == 1){
            firstPaymentDate.set(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH) + 1, 30);
        }

        else{
            firstPaymentDate.add(Calendar.MONTH, 1);
        }

        return firstPaymentDate.getTime();

    }

    public BigDecimal getTotalPaymentAmount(int leasePeriodInMonths, BigDecimal financingAmount, double interest){
//        BigDecimal n = new BigDecimal(36);
//        BigDecimal ap = new BigDecimal(14481);
//        BigDecimal adpa = new BigDecimal(2896.2);

        BigDecimal i = new BigDecimal(interest/12);

        BigDecimal incrementedI = i.add(new BigDecimal(1)); //i + 1

        BigDecimal divider = incrementedI.pow(leasePeriodInMonths - 1);

        BigDecimal calculationOne = divider.subtract(new BigDecimal(1));

        BigDecimal calculationTwo = calculationOne.divide(divider, RoundingMode.HALF_UP);

        BigDecimal calculationThree = calculationTwo.divide(i, RoundingMode.HALF_UP);

        BigDecimal calculationFour = calculationThree.add(new BigDecimal(1));

//        BigDecimal ats = financingAmount.divide(bigPapaPlus1, RoundingMode.HALF_UP);
//
//        System.out.println(ats);

        return financingAmount.divide(calculationFour, RoundingMode.HALF_UP);
    }


}
