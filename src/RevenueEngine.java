import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.math.BigDecimal;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.util.Iterator;


public class RevenueEngine {
     public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        int count = 0;
         try{
            FileInputStream file = new FileInputStream(new File("C:\\Users\\amohammad\\IdeaProjects\\RevenueRating\\resources\\Bookings.xlsx"));
            FileInputStream fileOut = new FileInputStream(new File("C:\\Users\\amohammad\\IdeaProjects\\RevenueRating\\resources\\RATES.xlsx"));
            XSSFWorkbook workbookInput = new XSSFWorkbook(file);
            XSSFSheet sheet = workbookInput.getSheetAt(0);
            XSSFWorkbook workbookOut = new XSSFWorkbook(fileOut);
            XSSFSheet sheetOut = workbookOut.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                String billIdIn = "";
                Row row = rowIterator.next();
                if(!"Bill_Id".equalsIgnoreCase(row.getCell(0).toString())){
                    Booking bkg = assignBooking(row);
                    if(bkg.getBillId()!=null){
                       Rate rate = RateCalculator.rateBooking(bkg);
                       System.out.println(rate.getBillId());
                       createOutRates(rate,sheetOut,count);
                       count=count++;
                    }
                }
            }
             //Write the workbook in file system
             FileOutputStream out = new FileOutputStream(new File("C:\\Users\\amohammad\\IdeaProjects\\RevenueRating\\resources\\RATES.xlsx"));
             workbookOut.write(out);
             out.close();
         } catch (Exception e){
             e.printStackTrace();
         }

        System.out.println("xlsx written successfully on disk. Time taken - "+(System.currentTimeMillis() - startTime));
     }



    public static void createOutRates (Rate rate,XSSFSheet sheetOut,int count){
        sheetOut.shiftRows(1,sheetOut.getLastRowNum()+1,1,true, true);
        Row rowOut = sheetOut.createRow(1);
        rowOut.createCell(0).setCellValue(rate.getBillId());
        rowOut.createCell(1).setCellValue(getLocalizedBigDecimalValue(rate.getFrt()));
        rowOut.createCell(2).setCellValue(getLocalizedBigDecimalValue(rate.getAcc()));
        rowOut.createCell(3).setCellValue(getLocalizedBigDecimalValue(rate.getDest()));
        rowOut.createCell(4).setCellValue(getLocalizedBigDecimalValue(rate.getCCS()));
        rowOut.createCell(5).setCellValue(getLocalizedBigDecimalValue(rate.getCONS()));
        rowOut.createCell(6).setCellValue(getLocalizedBigDecimalValue(rate.getCARGO()));
        rowOut.createCell(7).setCellValue(getLocalizedBigDecimalValue(rate.getHAZ()));
        rowOut.createCell(8).setCellValue(getLocalizedBigDecimalValue(rate.getLONGLength()));
        rowOut.createCell(9).setCellValue(getLocalizedBigDecimalValue(rate.getOVRWGT()));
        rowOut.createCell(10).setCellValue(getLocalizedBigDecimalValue(rate.getTotalCharge()));
        rowOut.createCell(11).setCellValue(rate.getCreateDate());
        rowOut.createCell(12).setCellValue(rate.getUser());

        //return rowOut;
    }

    public static Booking assignBooking(Row row){
        Booking bkg = new Booking();
        if(row.getCell(0)!=null)
            bkg.setBillId(new Long((long) row.getCell(0).getNumericCellValue()));
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
        if(row.getCell(13)!=null)
            bkg.setCreateDate(row.getCell(13).getDateCellValue());
        if(row.getCell(14)!=null)
            bkg.setUser(row.getCell(14).toString());
        return bkg;
    }
    protected static String getLocalizedBigDecimalValue(BigDecimal input) {
        if(input==null){
            return "0";
        }
        final NumberFormat numberFormat = NumberFormat.getNumberInstance(java.util.Locale.US);
        numberFormat.setGroupingUsed(true);
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        return numberFormat.format(input);
    }

}
