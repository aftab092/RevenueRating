import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: amohammad
 * Date: 6/14/17
 * Time: 2:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class RateCalculator {

    public static final double CT_FRT = 1.58;
    public static final double Unit_FRT = 0.04;

    public static Rate rateBooking(Booking bkg) {
        Rate rate = new Rate();
        rate.setBillId(bkg.getBillId());
        calculateFrtRate(bkg,rate);
        calculateTotalRates(bkg,rate);
        return rate;
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
        BigDecimal frtRate = new BigDecimal(0);
        if("Container".equalsIgnoreCase(bkg.getLoadType())){
            BigDecimal wgt = new BigDecimal(bkg.getWeight());
            BigDecimal pcs = new BigDecimal(bkg.getPieces());
            frtRate = wgt.multiply(pcs).multiply(new BigDecimal(CT_FRT));
        }else{
            BigDecimal wgt = new BigDecimal(bkg.getWeight());
            BigDecimal pcs = new BigDecimal(bkg.getPieces());
            frtRate = wgt.multiply(pcs).multiply(new BigDecimal(Unit_FRT));
        }
        rate.setFrt(frtRate);
    }

}
