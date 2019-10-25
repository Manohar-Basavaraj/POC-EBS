package ExcelHandling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {
	
public void writeExcel(String filePath,String fileName,String sheetName) throws IOException
{
	File file=new File(filePath +"\\" + fileName);
	FileInputStream inputStream = new FileInputStream(file);
	Workbook ExcekWorkbook = null;
	String fileExtensionName = fileName.substring(fileName.indexOf("."));
	if(fileExtensionName.equals(".xlsx"))
			{
		ExcekWorkbook = new XSSFWorkbook(inputStream);
		
			}
	
	else if(fileExtensionName.equals(".xls"))
	{
		
		ExcekWorkbook = new HSSFWorkbook(inputStream);
		
	}
	
	Sheet ExcelSheet = ExcekWorkbook.getSheet(sheetName);
	int rowCount =  ExcelSheet.getLastRowNum() - ExcelSheet.getFirstRowNum();
	for (int i = 1; i < rowCount + 1; i++)
	{

		try {
			// Block of code to try
			Row row = ExcelSheet.getRow(i);
			//String cell = row.getCell(2).getStringCellValue();

			String cell1 = row.getCell(3).getStringCellValue();

			System.out.println(cell1);

		} catch (IllegalStateException e) {
			// Block of code to handle errors
			Row row = ExcelSheet.getRow(i);
			//String cell = row.getCell(2).getStringCellValue();

			double cell1 = row.getCell(3).getNumericCellValue();

			System.out.println(cell1);

		}

	}
	
	inputStream.close();
	FileOutputStream outputStream = new FileOutputStream(file);
	ExcekWorkbook.write(outputStream);
	outputStream.close();
	
	
}

	
	public static void main(String[] args)
	{
		
		
		
		
		
	}
	


}
