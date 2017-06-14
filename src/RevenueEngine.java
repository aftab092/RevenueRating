import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;


public class RevenueEngine {
     public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        int count = 0;
         try{
            FileInputStream file = new FileInputStream(new File("C:\\Users\\amohammad\\Documents\\699\\Bookings.xlsx"));
            FileInputStream fileOut = new FileInputStream(new File("C:\\Users\\amohammad\\Documents\\699\\RATES.xlsx"));
            XSSFWorkbook workbookInput = new XSSFWorkbook(file);
            XSSFSheet sheet = workbookInput.getSheetAt(0);
            XSSFWorkbook workbookOut = new XSSFWorkbook(fileOut);
            XSSFSheet sheetOut = workbookOut.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                String billIdIn = "";
                Row row = rowIterator.next();
                Booking bkg = assignBooking(row);
                if(bkg.getBillId()!=null && !"Bill_Id".equalsIgnoreCase(bkg.getBillId())){
                   Rate rate = rateBooking(bkg);
                   System.out.println(rate.getBillId());
                   createOutRates(rate,sheetOut,count);
                   count=count++;
                } 
            }
             //Write the workbook in file system
             FileOutputStream out = new FileOutputStream(new File("C:\\Users\\amohammad\\Documents\\699\\RATES.xlsx"));
             workbookOut.write(out);
             out.close();
         } catch (Exception e){
             e.printStackTrace();
         }

        System.out.println("xlsx written successfully on disk. Time taken - "+(System.currentTimeMillis() - startTime));
     }

    private static Rate rateBooking(Booking bkg) {
       Rate rate = new Rate();
       rate.setBillId(bkg.getBillId());

        //to do

       return rate;
    }

    public static void createOutRates (Rate rate,XSSFSheet sheetOut,int count){
        sheetOut.shiftRows(1,sheetOut.getLastRowNum()+1,1,true, true);
        Row rowOut = sheetOut.createRow(1);
        rowOut.createCell(0).setCellValue(rate.getBillId());
        rowOut.createCell(1).setCellValue(rate.getFrt());
        rowOut.createCell(2).setCellValue(rate.getAcc());
        rowOut.createCell(3).setCellValue(rate.getDest());
        rowOut.createCell(4).setCellValue(rate.getCCS());
        rowOut.createCell(5).setCellValue(rate.getCONS());
        rowOut.createCell(6).setCellValue(rate.getCARGO());
        rowOut.createCell(7).setCellValue(rate.getHAZ());
        rowOut.createCell(8).setCellValue(rate.getLONGLength());
        rowOut.createCell(9).setCellValue(rate.getOVRWGT());
        rowOut.createCell(10).setCellValue(rate.getTotalCharge());
        //return rowOut;
    }

    public static Booking assignBooking(Row row){
        Booking bkg = new Booking();
        if(row.getCell(0)!=null)
            bkg.setBillId(row.getCell(0).toString());
        if(row.getCell(1)!=null)
            bkg.setStatus(row.getCell(1).toString());
        if(row.getCell(2)!=null)
             bkg.setCustomer(row.getCell(2).toString());
        if(row.getCell(3)!=null)
            bkg.setContact(row.getCell(3).toString());
        if(row.getCell(4)!=null)
            bkg.setAddress(row.getCell(4).toString());
        if(row.getCell(5)!=null)
            bkg.setLoadType(row.getCell(5).toString());
        if(row.getCell(6)!=null)
            bkg.setPortOfDeparture(row.getCell(6).toString());
        if(row.getCell(7)!=null)
            bkg.setPortOfDestination(row.getCell(7).toString());
        if(row.getCell(8)!=null)
            bkg.setWeight(row.getCell(8).toString());
        if(row.getCell(9)!=null)
            bkg.setCube(row.getCell(9).toString());
        if(row.getCell(10)!=null)
            bkg.setPieces(row.getCell(10).toString());
        if(row.getCell(11)!=null)
            bkg.setTariffNumber(row.getCell(11).toString());
        if(row.getCell(12)!=null)
            bkg.setAddSvcCode(row.getCell(12).toString());
        return bkg;
    }
}
