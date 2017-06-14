import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: amohammad
 * Date: 6/13/17
 * Time: 4:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class Rate {
    private Long BillId;
    private BigDecimal Frt;
    private BigDecimal Acc;
    private BigDecimal Dest;
    private BigDecimal CCS;
    private BigDecimal CONS;
    private BigDecimal CARGO;
    private BigDecimal HAZ;
    private BigDecimal LONGLength;
    private BigDecimal OVRWGT;
    private BigDecimal TotalCharge;

    public Long getBillId() {
        return BillId;
    }

    public void setBillId(Long billId) {
        BillId = billId;
    }

    public BigDecimal getFrt() {
        return Frt;
    }

    public void setFrt(BigDecimal frt) {
        Frt = frt;
    }

    public BigDecimal getAcc() {
        return Acc;
    }

    public void setAcc(BigDecimal acc) {
        Acc = acc;
    }

    public BigDecimal getDest() {
        return Dest;
    }

    public void setDest(BigDecimal dest) {
        Dest = dest;
    }

    public BigDecimal getCCS() {
        return CCS;
    }

    public void setCCS(BigDecimal CCS) {
        this.CCS = CCS;
    }

    public BigDecimal getCONS() {
        return CONS;
    }

    public void setCONS(BigDecimal CONS) {
        this.CONS = CONS;
    }

    public BigDecimal getCARGO() {
        return CARGO;
    }

    public void setCARGO(BigDecimal CARGO) {
        this.CARGO = CARGO;
    }

    public BigDecimal getHAZ() {
        return HAZ;
    }

    public void setHAZ(BigDecimal HAZ) {
        this.HAZ = HAZ;
    }

    public BigDecimal getLONGLength() {
        return LONGLength;
    }

    public void setLONGLength(BigDecimal LONGLength) {
        this.LONGLength = LONGLength;
    }

    public BigDecimal getOVRWGT() {
        return OVRWGT;
    }

    public void setOVRWGT(BigDecimal OVRWGT) {
        this.OVRWGT = OVRWGT;
    }

    public BigDecimal getTotalCharge() {
        return TotalCharge;
    }

    public void setTotalCharge(BigDecimal totalCharge) {
        TotalCharge = totalCharge;
    }
}
