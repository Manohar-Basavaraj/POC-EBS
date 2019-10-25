package ExcelHandling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.text.NumberFormatter;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Sort {

	private static final String FILE_NAME = "C:\\Users\\deepthi.singh\\Desktop\\test2\\Macys EBS Application\\fileToSort1.xlsx";

	public static void main(String[] args) throws Exception {

	
		FileInputStream excelFile = new FileInputStream(
				new File("C:\\Users\\deepthi.singh\\Desktop\\User_Details.xlsx"));
		Workbook originalWorkbook = new XSSFWorkbook(excelFile);
		Sheet originalSheet = originalWorkbook.getSheetAt(0);

		 Map<Integer, Row> sortedRowsMap = new TreeMap<>(Collections.reverseOrder());
		Iterator<Row> rowIterator = originalSheet.rowIterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			DataFormatter formatter = new DataFormatter();
			String val = formatter.formatCellValue(row.getCell(0));
	
			Integer inte = Integer.valueOf(val);
			
		    sortedRowsMap.put(inte.intValue(), row);
			
		}
			

		Workbook sortedWorkbook = new XSSFWorkbook();
		Sheet sortedSheet = sortedWorkbook.createSheet(originalSheet.getSheetName());

		// Copy all the sorted rows to the new workbook
		int rowIndex = 0;
		for (Row row : sortedRowsMap.values()) {
			Row newRow = sortedSheet.createRow(rowIndex);
			copyRowToRow(row, newRow);
			rowIndex++;
		}

		// Write your new workbook to your file
		try (FileOutputStream out = new FileOutputStream(FILE_NAME)) {
			sortedWorkbook.write(out);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	// Utility method to copy rows
	private static void copyRowToRow(Row row, Row newRow) {
		Iterator<Cell> cellIterator = row.cellIterator();
		int cellIndex = 0;
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();

			DataFormatter formatter = new DataFormatter();
			String val = formatter.formatCellValue(cell);

			Cell newCell = newRow.createCell(cellIndex);
			newCell.setCellValue(val);
			cellIndex++;
		}
	}

}
