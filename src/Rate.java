/**
 * Created with IntelliJ IDEA.
 * User: amohammad
 * Date: 6/13/17
 * Time: 4:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class Rate {
    private String BillId;
    private String Frt;
    private String Acc;
    private String Dest;
    private String CCS;
    private String CONS;
    private String CARGO;
    private String HAZ;
    private String LONGLength;
    private String OVRWGT;
    private String TotalCharge;

    public String getBillId() {
        return BillId;
    }

    public void setBillId(String billId) {
        BillId = billId;
    }

    public String getFrt() {
        return Frt;
    }

    public void setFrt(String frt) {
        Frt = frt;
    }

    public String getAcc() {
        return Acc;
    }

    public void setAcc(String acc) {
        Acc = acc;
    }

    public String getDest() {
        return Dest;
    }

    public void setDest(String dest) {
        Dest = dest;
    }

    public String getCCS() {
        return CCS;
    }

    public void setCCS(String CCS) {
        this.CCS = CCS;
    }

    public String getCONS() {
        return CONS;
    }

    public void setCONS(String CONS) {
        this.CONS = CONS;
    }

    public String getCARGO() {
        return CARGO;
    }

    public void setCARGO(String CARGO) {
        this.CARGO = CARGO;
    }

    public String getHAZ() {
        return HAZ;
    }

    public void setHAZ(String HAZ) {
        this.HAZ = HAZ;
    }

    public String getLONGLength() {
        return LONGLength;
    }

    public void setLONGLength(String LONGLength) {
        this.LONGLength = LONGLength;
    }

    public String getOVRWGT() {
        return OVRWGT;
    }

    public void setOVRWGT(String OVRWGT) {
        this.OVRWGT = OVRWGT;
    }

    public String getTotalCharge() {
        return TotalCharge;
    }

    public void setTotalCharge(String totalCharge) {
        TotalCharge = totalCharge;
    }
}
