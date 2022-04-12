import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class RelaySurvey {

    public static void main(String[] args) throws IOException {

        Map<String, List<Object>> excelMap = new HashMap<>();

        // obtaining input bytes from a file
        FileInputStream fis = new FileInputStream(new File("C:\\Users\\cks\\Downloads\\연계조사\\00.전체기관연계통합_0412_v1.0.xlsx"));

        // creating workbook instance that refers to .xlsx file
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);  // creating a Sheet object to retrieve object
        Iterator<Row> itr = sheet.iterator();  //iterating over excel file

        List<List<Object>> excelRow = new ArrayList<>();  // List for saving excelCell List format as row
        while (itr.hasNext()) {
            Row row = itr.next();

            List<Object> excelCell = new ArrayList<>();  // List for saving cell
            Iterator<Cell> cellIterator = row.cellIterator();  // iterating over each column
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case NUMERIC:
                        excelCell.add(cell.getNumericCellValue());
                        break;
                    case STRING:
                        excelCell.add(cell.getStringCellValue());
                }
            }
            excelRow.add(excelCell);
        }

        for (int i=0; i<excelRow.size(); i++) {
            for (int j=0; j<excelRow.get(i).size(); j++) {
                System.out.print(excelRow.get(i).get(j) + ",\t");
            }
            System.out.println("\n-------------------------------------");
        }

        for (int i=0; i<excelRow.size(); i++) {
            for (int j=0; j<excelRow.get(i).size(); i++) {
                System.out.println("asd");
            }
        }
    }

}
