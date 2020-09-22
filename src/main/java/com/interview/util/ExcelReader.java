package com.interview.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	public String path;
	public FileInputStream fis;
	public FileOutputStream fileOut;
	public XSSFWorkbook workBook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	
	// Constructor that takes test data sheet path and sheet name
	public ExcelReader(String path) {
		this.path = path;
		try {
			fis = new FileInputStream(path);
			workBook = new XSSFWorkbook(fis);
			sheet = workBook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Returns row count in sheet
	public int getRowCount(String sheetName) {
		int index = workBook.getSheetIndex(sheetName);
		if (!(isSheetExist(sheetName))) {
			System.out.println("Sheet: "+sheetName+" is not present. Please proive valid sheet name.");
			return 0;
		}else {
			sheet = workBook.getSheetAt(index);
			return sheet.getLastRowNum();
		}
	}
	
	// Get cell data by provide sheet name, column name and row number
	public String getCellData(String sheetName, String columnName, int rowNumber) {
		String cellData = null;
		if (rowNumber > getRowCount(sheetName) || rowNumber <= 0) {
			return cellData;
		}
		row = sheet.getRow(0);
		for (int i = 0; i< row.getLastCellNum(); i++) {
			cell = row.getCell(i);
			String value = getCellValue(cell);
			if (value.equalsIgnoreCase(columnName)) {
				row = sheet.getRow(rowNumber);
				cell = row.getCell(i);
				cellData = getCellValue(cell);
			}
		}
		return cellData;
	}
	
	public static String getCellValue(XSSFCell cell) {
		switch (cell.getCellType()) {
		case NUMERIC:
			int i = (int)cell.getNumericCellValue();
			return String.valueOf(i);
		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		case STRING:
			return cell.getStringCellValue();
		default:
			return cell.getStringCellValue();
		}
	}
	
	// Find whether sheet name exist or not
	public boolean isSheetExist(String sheetName) {
		int index = workBook.getSheetIndex(sheetName);
		if (index == -1) {
			index = workBook.getSheetIndex(sheetName.toUpperCase());
			if (index == -1) {
				return false;
			}else{
				return true;
			}
		}
		return true;
	}
}
