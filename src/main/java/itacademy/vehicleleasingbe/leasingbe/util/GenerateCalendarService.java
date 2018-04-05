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
        BigDecimal totalPaymentAmount = getTotalPaymentAmount(leasePeriodLengthInMonths, unpaidAssetAmount, 0.0427);

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
            payments[i].setDate(calendar.getTime()); //ok

            //totalPaymentAmount
            payments[i].setTotalPaymentAmount(totalPaymentAmount); //ok

            //unpaidAssetAmount
            payments[i].setUnpaidAssetAmount(unpaidAssetAmount);

            //interestAmount
            payments[i].setInterestAmount
                    (payments[i].getUnpaidAssetAmount().multiply
                            (new BigDecimal(0.0427)).divide(new BigDecimal(12), 2, RoundingMode.HALF_UP));

            //unpaidAssetRepaymentAmount

            payments[i].setUnpaidAssetRepaymentAmount
                    (totalPaymentAmount.subtract(payments[i].getInterestAmount()));


            unpaidAssetAmount = unpaidAssetAmount.subtract(payments[i].getUnpaidAssetRepaymentAmount());

            calendar.setTime(now.getTime());
        }

        payments[leasePeriodLengthInMonths-1].setUnpaidAssetRepaymentAmount(payments[leasePeriodLengthInMonths-1].getUnpaidAssetAmount());
        payments[leasePeriodLengthInMonths-1].setInterestAmount(totalPaymentAmount.subtract(payments[leasePeriodLengthInMonths-1].getUnpaidAssetRepaymentAmount()));

        return payments;

    }

//    public Payment[] roundPaymentFields(Payment[] payments){
//        for(Payment payment : payments){
//            payment.setInterestAmount(payment.getInterestAmount().setScale(2, RoundingMode.CEILING));
//            payment.setUnpaidAssetRepaymentAmount(payment.getUnpaidAssetRepaymentAmount().setScale(2, RoundingMode.CEILING));
//            payment.setUnpaidAssetAmount(payment.getUnpaidAssetAmount().setScale(2, RoundingMode.CEILING));
//            payment.setTotalPaymentAmount(payment.getTotalPaymentAmount().setScale(2, RoundingMode.CEILING));
//        }
//        return payments;
//    }

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

        BigDecimal i = new BigDecimal(interest/12);

        BigDecimal incrementedI = i.add(new BigDecimal(1)); //i + 1

        BigDecimal divider = incrementedI.pow(leasePeriodInMonths - 1);

        BigDecimal calculationOne = divider.subtract(new BigDecimal(1));

        BigDecimal calculationTwo = calculationOne.divide(divider, RoundingMode.HALF_UP);

        BigDecimal calculationThree = calculationTwo.divide(i, RoundingMode.HALF_UP);

        BigDecimal calculationFour = calculationThree.add(new BigDecimal(1));

        BigDecimal lastPayment = financingAmount.divide(calculationFour, 2, RoundingMode.HALF_UP);

        return lastPayment.multiply(i.add(BigDecimal.ONE)).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTotalInterestAmount(Payment[] payments){

        BigDecimal totalInterestAmount = BigDecimal.ZERO;

        for(Payment payment : payments){
            totalInterestAmount = totalInterestAmount.add(payment.getInterestAmount());
        }

        return totalInterestAmount;
    }

}
