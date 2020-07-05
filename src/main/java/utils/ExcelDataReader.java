package utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelDataReader {
    public static final String EXCEL_FILE_PATH = "resources/testdata.xlsx";

    public static Object[][] getExcelData(String sheetName, String testcaseName) throws IOException, InvalidFormatException {
        //ArrayList<String> dataEntry = new ArrayList<String>();

        //Create Workbook instance
        Workbook workbook = WorkbookFactory.create(new File(EXCEL_FILE_PATH));
        //XSSFWorkbook workbook = new XSSFWorkbook(new File(EXCEL_FILE_PATH));
        //Search Sheet and create sheet instance
        Sheet sheet = getSheet(workbook, sheetName);

        //Search Row from testcaseName
        int length = getTestDataColumnCount(sheet);
        int width = getTestDataEntryCount(sheet, testcaseName);

        Object[][] data = new Object[width][length];

        Iterator<Row> rowIterator = sheet.rowIterator();
        int i = 0;
        while (rowIterator.hasNext()) {
            if(i < width){
                Row row = rowIterator.next();
                if (getCellValueAsString(row.getCell(0)).equalsIgnoreCase(testcaseName)) {
                    for (int j = 0; j < length; j++) {
                        Cell cell = row.getCell(j + 1);
                        data[i][j] = getCellValueAsString(cell);
                    }
                    i++;
                }
            }else{
                break;
            }
        }

        return data;

    }
/*
        Search Sheet
        */

    private static Sheet getSheet(Workbook workbook, String sheetName){
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        while(sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            if(sheet.getSheetName().equalsIgnoreCase(sheetName))
                return sheet;
        }

        return null;
    }

    private static int getTestDataEntryCount(Sheet sheet, String testCaseName){
        Iterator<Row> rowIterator = sheet.rowIterator();
        int dataEntryCount = 0;
        while(rowIterator.hasNext()){
            Row row = rowIterator.next();
            if(getCellValueAsString(row.getCell(0)).equalsIgnoreCase(testCaseName))
                dataEntryCount++;
        }

        return dataEntryCount;
    }

    private static int getTestDataColumnCount(Sheet sheet){
        Row firstRow = sheet.getRow(0);
        return firstRow.getPhysicalNumberOfCells() - 1;
    }

    private static String getCellValueAsString(Cell cell){
        if(cell.getCellType() == CellType.STRING){
            return cell.getStringCellValue();
        }else{
            return NumberToTextConverter.toText(cell.getNumericCellValue());
        }
    }

    public void main(String [] arg) throws IOException, InvalidFormatException {

        Object[][] data = getExcelData("preference", "1");


    }


}

