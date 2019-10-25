package ExcelHandling;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateExcelSheet {
	
	public static WebDriver driver;
	

	public static void CreateExcelSheet() throws IOException {
	Workbook NewWb = new XSSFWorkbook();

		FileOutputStream fileOut = new FileOutputStream(
				"C:\\Users\\deepthi.singh\\Desktop\\test2\\Macys EBS Application\\RequistionSearch.xlsx");

		org.apache.poi.ss.usermodel.Sheet sheet = NewWb.createSheet("RequistionSearch");

		CreationHelper createHelper = NewWb.getCreationHelper();

		// Create the first Row

		Row row1 = sheet.createRow((short) 0);

		// inserting first row cell value
		row1.createCell(0).setCellValue(

				createHelper.createRichTextString("Sequence"));

		row1.createCell(1).setCellValue(

				createHelper.createRichTextString("Approver"));

		row1.createCell(2).setCellValue(

				createHelper.createRichTextString("Organizantion Name"));

		row1.createCell(3).setCellValue(

				createHelper.createRichTextString("Action"));

		row1.createCell(4).setCellValue(

				createHelper.createRichTextString("Action Date"));

		row1.createCell(5).setCellValue(

				createHelper.createRichTextString("Notes"));

		NewWb.write(fileOut);

		fileOut.close();

	}
	
	
	
}
