import org.apache.poi.ss.usermodel.Row;

/**
 * Created with IntelliJ IDEA.
 * User: amohammad
 * Date: 6/13/17
 * Time: 4:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class Booking {

    private String billId;
    private String status;
    private String customer;
    private String contact;
    private String address;
    private String loadType;
    private String portOfDeparture;
    private String portOfDestination;
    private String weight;
    private String cube;
    private String pieces;
    private String tariffNumber;
    private String addSvcCode;

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLoadType() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType;
    }

    public String getPortOfDeparture() {
        return portOfDeparture;
    }

    public void setPortOfDeparture(String portOfDeparture) {
        this.portOfDeparture = portOfDeparture;
    }

    public String getPortOfDestination() {
        return portOfDestination;
    }

    public void setPortOfDestination(String portOfDestination) {
        this.portOfDestination = portOfDestination;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCube() {
        return cube;
    }

    public void setCube(String cube) {
        this.cube = cube;
    }

    public String getPieces() {
        return pieces;
    }

    public void setPieces(String pieces) {
        this.pieces = pieces;
    }

    public String getTariffNumber() {
        return tariffNumber;
    }

    public void setTariffNumber(String tariffNumber) {
        this.tariffNumber = tariffNumber;
    }

    public String getAddSvcCode() {
        return addSvcCode;
    }

    public void setAddSvcCode(String addSvcCode) {
        this.addSvcCode = addSvcCode;
    }


}
