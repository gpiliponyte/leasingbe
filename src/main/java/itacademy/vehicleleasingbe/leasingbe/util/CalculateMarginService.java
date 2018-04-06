package itacademy.vehicleleasingbe.leasingbe.util;


import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculateMarginService {

    public BigDecimal calculateMargin(){
        BigDecimal margin = null;
        return margin.valueOf(4+0.27);
    }

}
