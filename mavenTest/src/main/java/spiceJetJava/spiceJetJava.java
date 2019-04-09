package spiceJetJava;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.WebDriver;


public class spiceJetJava {
	WebDriver driver;
	HSSFWorkbook wb;
	HSSFSheet sheet;
	HSSFCell cell;
	HSSFRow row;
	FileInputStream input ;
	File source;
	String expectedResult="";
	String actualResult="";
	public  int rowNumber;

	String excelpath = "C:\\Eclipse\\spiceJet.xls";


	// This method has two parameters: "Test data excel file name" and "Excel sheet name"
	// It creates FileInputStream and set excel file and excel sheet to excelWBook and excelWSheet variables.

	public  void setExcelFileSheet(String sheetName ) throws Exception {

		source = new File(excelpath);
		input= new FileInputStream(source);
		wb = new HSSFWorkbook(input);
		sheet=wb.getSheet(sheetName);

	}


	//This method reads the test data from the Excel cell.
	//We are passing row number and column number as parameters.

	public  String getCellData(int RowNum, int ColNum) throws Exception {

		cell = sheet.getRow(RowNum).getCell(ColNum);
		DataFormatter formatter = new DataFormatter();
		String cellData = formatter.formatCellValue(cell);
		return cellData;

	}

	//This method takes row number as a parameter and returns the data of given row number.
	public  HSSFRow getRowData(int RowNum) throws Exception {


		row = sheet.getRow(RowNum) ;
		return row;

	}


	public  void setCellData(String value, int RowNum, int ColNum) throws Exception {
		try {
			row = sheet.getRow(RowNum);
			cell = row.getCell(ColNum);
			if (cell == null) {
				cell = row.createCell(ColNum);
				cell.setCellValue(value);
			} else {
				cell.setCellValue(value);
			}
			// Constant variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(excelpath);
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			try {
				throw (e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}


	public  int getTotalRow(int sheetIndex ) throws Exception
	{


		int  total_row = wb.getSheetAt(sheetIndex).getLastRowNum();
		total_row=total_row+1;

		return total_row;

	}
	



}
