package azzukunj.cottonuniversity.healthandfitnesskunjazzu.converttoless;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class converttoless {

    public static String convert(double n)
    {
        BigDecimal bd=new BigDecimal(n);
        bd=bd.setScale(2,RoundingMode.HALF_UP);
        String s=Double.toString(bd.doubleValue());
        return s;
    }
}
