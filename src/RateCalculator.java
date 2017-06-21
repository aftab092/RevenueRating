import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: amohammad
 * Date: 6/14/17
 * Time: 2:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class RateCalculator {

    public static final double CT_FRT = 0.08;
    public static final double Unit_FRT = 0.04;
    public static final double acc_CT = 10.29;
    public static final double acc_Unit = 5.00;
    public static final double haz_CT = 3.54;

    public static Rate rateBooking(Booking bkg) {
        Rate rate = new Rate();
        rate.setBillId(bkg.getBillId());
        calculateFrtRate(bkg,rate);
        calculateACCRate(bkg,rate);
        calculateDestRate(bkg, rate);
        calculateCCSRate(bkg, rate);
        calculateCONSRate(bkg, rate);
        calculateCARGORate(bkg, rate);
        calculateHAZRate(bkg, rate);
        calculateLongLengthRate(bkg, rate);
        calculateOVRWGTRate(bkg, rate);
        calculateTotalRates(bkg,rate);
        rate.setUser(getUser(bkg));
        rate.setCreateDate(addDays(bkg.getCreateDate(),5));
        return rate;
    }

    private static void calculateHAZRate(Booking bkg, Rate rate) {
        BigDecimal hazRate = new BigDecimal("0.0");
        BigDecimal wgt = new BigDecimal(bkg.getWeight());
        BigDecimal pcs = new BigDecimal(bkg.getPieces());
        if("HAZ".equalsIgnoreCase(bkg.getAddSvcCode()) && !pcs.equals(hazRate)){
            hazRate =  pcs.multiply(new BigDecimal(haz_CT));
            rate.setHAZ(hazRate);
        }else if("REF".equalsIgnoreCase(bkg.getAddSvcCode())){
            rate.setHAZ(new BigDecimal("80"));
        }
        else if("RED".equalsIgnoreCase(bkg.getAddSvcCode())){
            rate.setHAZ(new BigDecimal("800"));
        }
        else if("LDS".equalsIgnoreCase(bkg.getAddSvcCode())){
            rate.setHAZ(new BigDecimal("100"));
        }
        else if(bkg.getAddSvcCode() != null && !"".equalsIgnoreCase(bkg.getAddSvcCode()) && !hazRate.equals(wgt)){
            hazRate =  wgt.multiply(new BigDecimal("0.12"));
            rate.setHAZ(hazRate);
        }
    }

    private static void calculateCARGORate(Booking bkg, Rate rate) {
        BigDecimal pcs = new BigDecimal(bkg.getPieces());
        if(pcs.intValue()>1 && !bkg.getTariffNumber().equalsIgnoreCase("587E")){
            rate.setCARGO(new BigDecimal("9"));
        }
    }

    private static void calculateCONSRate(Booking bkg, Rate rate) {
        if(bkg.getTariffNumber().equalsIgnoreCase("1AK")){
            rate.setCONS(new BigDecimal("1.23"));
        }
    }

    private static void calculateCCSRate(Booking bkg, Rate rate) {
        rate.setCCS(new BigDecimal("2.03"));
    }

    private static void calculateDestRate(Booking bkg, Rate rate) {
         if(bkg.getPortOfDestination().equalsIgnoreCase("SHA")){
            rate.setDest(new BigDecimal("4.09"));
         } else if(bkg.getPortOfDestination().equalsIgnoreCase("SYD")){
             rate.setDest(new BigDecimal("8.03"));
         }else if(bkg.getPortOfDestination().equalsIgnoreCase("ANK")){
             rate.setDest(new BigDecimal("2.00"));
         }
    }

    private static void calculateLongLengthRate(Booking bkg, Rate rate) {
        BigDecimal longRate = new BigDecimal(0);
        Double cube = Double.parseDouble(bkg.getCube());
        if(cube > 1000){
            cube = cube-100;
            longRate = new BigDecimal(cube).multiply(new BigDecimal("0.13"));
            rate.setLONGLength(longRate);
        }
    }

    private static void calculateOVRWGTRate(Booking bkg, Rate rate) {
        BigDecimal wgtRate = new BigDecimal(0);
        Double wgt = Double.parseDouble(bkg.getWeight()) ;
        if(wgt > 10000000){
            wgt = wgt-10000;
            wgtRate = new BigDecimal(wgt).multiply(new BigDecimal("0.4"));
            rate.setOVRWGT(wgtRate);
        }
    }

    private static String getUser(Booking bkg) {
        if(bkg.getLoadType().equalsIgnoreCase("Container") && bkg.getTariffNumber().equalsIgnoreCase("1E")){
            return "nlomada";
        }else if (bkg.getLoadType().equalsIgnoreCase("Container") && bkg.getPortOfDestination().equalsIgnoreCase("SHA")){
            return "fnoor";
        }else if (bkg.getLoadType().equalsIgnoreCase("Container")){
            return "nnoor";
        }else if(bkg.getLoadType().equalsIgnoreCase("Unit") && bkg.getPortOfDestination().equalsIgnoreCase("SHA")){
            return "mshahab";
        }else if(bkg.getLoadType().equalsIgnoreCase("Unit") && bkg.getPortOfDestination().equalsIgnoreCase("HON")){
            return "rnoor";
        }
        return "pcr";
    }

    private static void calculateACCRate(Booking bkg, Rate rate) {
        BigDecimal accRate = new BigDecimal(0);
        if("Container".equalsIgnoreCase(bkg.getLoadType())){
            accRate = new BigDecimal(acc_CT);
        }else{
            accRate = new BigDecimal(acc_Unit);
        }
        rate.setAcc(accRate);
    }

    private static void calculateTotalRates(Booking bkg, Rate rate) {
        BigDecimal ttlrate = new BigDecimal("0");
        if(rate.getAcc()!=null){ttlrate= ttlrate.add(rate.getAcc());}
        if(rate.getCARGO()!=null){ttlrate= ttlrate.add(rate.getCARGO());}
        if(rate.getCCS()!=null){ttlrate= ttlrate.add(rate.getCCS());}
        if(rate.getCONS()!=null){ttlrate= ttlrate.add(rate.getCONS());}
        if(rate.getDest()!=null){ttlrate= ttlrate.add(rate.getDest());}
        if(rate.getFrt()!=null){ttlrate= ttlrate.add(rate.getFrt());}
        if(rate.getHAZ()!=null){ttlrate= ttlrate.add(rate.getHAZ());}
        if(rate.getLONGLength()!=null){ttlrate= ttlrate.add(rate.getLONGLength());}
        if(rate.getOVRWGT()!=null){ttlrate= ttlrate.add(rate.getOVRWGT());}
        rate.setTotalCharge(ttlrate);
    }

    private static void calculateFrtRate(Booking bkg, Rate rate) {
        BigDecimal frtRate = new BigDecimal("0.0");
        BigDecimal wgt = new BigDecimal(bkg.getWeight());
        BigDecimal pcs = new BigDecimal(bkg.getPieces());
        if(bkg.getBillId()==18999){
               System.out.print("");
        }
        if("Container".equalsIgnoreCase(bkg.getLoadType())){
            if(!pcs.equals(frtRate)){
                frtRate = wgt.multiply(pcs).multiply(new BigDecimal(CT_FRT));
            }else{
                frtRate = wgt.multiply(new BigDecimal(CT_FRT));
            }
        }else{
            if(!pcs.equals(frtRate)){
                frtRate = wgt.multiply(pcs).multiply(new BigDecimal(Unit_FRT));
            }else{
                frtRate = wgt.multiply(new BigDecimal(Unit_FRT));
            }
        }
        rate.setFrt(frtRate);
    }

    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

}
