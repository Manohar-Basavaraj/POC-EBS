package ExcelHandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.SystemOutLogger;
import org.apache.xmlbeans.soap.SOAPArrayType;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.EBSApplication.LoginPage;

public class WebTableData extends LoginPage {
	public WebDriver driver;
	List<WebElement> columnElements;
	int rowCount, columnCount;
	Map.Entry mentry2 = null;

	private static final String FILE_NAME = "C:\\Users\\deepthi.singh\\MyFirstExcel.xls";
	ArrayList<String> data = new ArrayList<String>();
	String[][] tableVal;
	WebElement myDynamicElement;
	Map<Integer, String> hm = new TreeMap<>(Collections.reverseOrder());

	Map<String, String> hm2 = new TreeMap<>(Collections.reverseOrder());

	Map<String, String> hm3 = new TreeMap<>(Collections.reverseOrder());
	// List<String> values = new ArrayList<String>();
	String values;
	String actionDate;
	String approverName;
	String SequenceNum = "";
	int key;
	
	
	String actionTaken;

	public WebTableData(WebDriver driver) {
		this.driver = driver;
	}

	public void read() throws InterruptedException {

		List<WebElement> NextTable = driver.findElements(
				By.xpath("//table[@class='x1q']//table//tbody//tr//td//a[@class='x48'][contains(text(),'Next')]"));

		// int rows_count1=0;

		while (NextTable.size() > 0 || NextTable.size() == 0) {

			writeFunction();

			/*
			 * Thread.sleep(10000);
			 * 
			 * List<WebElement> columnElements = driver.findElements(By.xpath(
			 * "//*[@id='ApprHistTable']/table[2]/tbody/tr/td/.."));
			 * 
			 * System.out.println("Element found 2");
			 * 
			 * rows_count1 = columnElements.size(); System.out.println("Number of rows :" +
			 * rows_count1);
			 * 
			 * 
			 * for(int cnum=0;cnum<columnElements.size();cnum++) { //
			 * System.out.println(columnElements.get(cnum).getText());
			 * 
			 * data.add(columnElements.get(cnum).getText()); }
			 */

			if (NextTable.size() > 0) {
				NextTable.get(0).click();
			} else
				break;

			Thread.sleep(10000);

			NextTable = driver.findElements(
					By.xpath("//table[@class='x1q']//table//tbody//tr//td//a[@class='x48'][contains(text(),'Next')]"));
			System.out.println("Next clcik & sleep done");

		}

		for (String i : data) {

			System.out.println(i);

			// writeFunction();

		}

	}

	public void writeFunction() {

		List<WebElement> row = driver.findElements(By.xpath("//*[@id='ApprHistTable']/table[2]/tbody/tr/td/.."));
		// get Column size
		List<WebElement> column = driver.findElements(By.xpath("//*[@id='ApprHistTable']/table[2]/tbody/tr/th"));
		rowCount = row.size() + 1;
		columnCount = column.size();
		System.out.println("Row :" + rowCount + " Clounm :" + columnCount);
		tableVal = new String[rowCount][columnCount];

		for (int i = 1; i <= rowCount; i++) {
			for (int j = 1; j <= columnCount; j++) {

				if (i == 1) {
					// Get header value
					tableVal[i - 1][j - 1] = driver
							.findElement(
									By.xpath("//*[@id='ApprHistTable']/table[2]/tbody/tr[" + i + "]/th[" + j + "]"))
							.getText();
					// System.out.println(driver.findElement(By.xpath("//*[@id='ApprHistTable']/table[2]/tbody/tr["
					// + i + "]/th[" + j + "]")).getText());
					// values.add(tableVal[i - 1][j - 1]);
				} else if (j == 1 || j == 4) {

					if (j == 1) {

						tableVal[i - 1][j - 1] = driver
								.findElement(
										By.xpath("//*[@id='ApprHistTable']/table[2]/tbody/tr[" + i + "]/td[" + j + "]"))
								.getText();
						System.out.println(driver
								.findElement(
										By.xpath("//*[@id='ApprHistTable']/table[2]/tbody/tr[" + i + "]/td[" + j + "]"))
								.getText());

						SequenceNum = tableVal[i - 1][j - 1];

						key = Integer.valueOf(SequenceNum);
					}

					if (j == 4) {

						if (tableVal[i - 1][j - 1] == "") {

							tableVal[i - 1][j - 1] = "NULL";
							values = tableVal[i - 1][j - 1];

						} else {

							tableVal[i - 1][j - 1] = driver
									.findElement(By.xpath(
											"//*[@id='ApprHistTable']/table[2]/tbody/tr[" + i + "]/td[" + j + "]"))
									.getText();
							values = tableVal[i - 1][j - 1];

						}

					}

					// get table data values

				} else {

					/*
					 * if (j == 2) {
					 * 
					 * tableVal[i - 1][j - 1] = driver .findElement(
					 * By.xpath("//*[@id='ApprHistTable']/table[2]/tbody/tr[" + i + "]/td[" + j +
					 * "]")) .getText(); actionDate = tableVal[i - 1][j - 1];
					 * System.out.println("Approver Name "+actionDate);
					 * 
					 * }
					 * 
					 * if (j == 5) {
					 * 
					 * tableVal[i - 1][j - 1] = driver .findElement(
					 * By.xpath("//*[@id='ApprHistTable']/table[2]/tbody/tr[" + i + "]/td[" + j +
					 * "]")) .getText(); approverName = tableVal[i - 1][j - 1];
					 * 
					 * System.out.println("Actrion Date"+approverName);
					 * 
					 * }
					 */

					// continue;
					
				

							if (j == 2) {

								tableVal[i - 1][j - 1] = driver
										.findElement(By.xpath(
												"//*[@id='ApprHistTable']/table[2]/tbody/tr[" + i + "]/td[" + j + "]"))
										.getText();
								approverName = tableVal[i - 1][j - 1];

								hm2.put(values, approverName);

								System.out.println("Approver Name INSIDE CONDITION " + approverName);

							}

							if (j == 5) {

								tableVal[i - 1][j - 1] = driver
										.findElement(By.xpath(
												"//*[@id='ApprHistTable']/table[2]/tbody/tr[" + i + "]/td[" + j + "]"))
										.getText();
								actionDate = tableVal[i - 1][j - 1];

								hm3.put(values, actionDate);

								System.out.println("DATE INSIDE CONDITION " + actionDate);
							}

					
					
					
					
				}
			}

			hm.put(key, values);

		}

		/*
		 * HSSFWorkbook workbook = new HSSFWorkbook(); HSSFSheet sheet =
		 * workbook.createSheet("Datatypes in Java");
		 * 
		 * int lastRow= sheet.getLastRowNum(); Row getRow = sheet.createRow(++lastRow);
		 * 
		 * // int rowNum = 0; System.out.println("Creating excel");
		 * 
		 * for (Object[] datatype : tableVal) { //Row getRow =
		 * sheet.createRow(rowNum++); int colNum = 0; for (Object field : datatype) {
		 * Cell cell = getRow.createCell(colNum++); if (field instanceof String) {
		 * cell.setCellValue((String) field); } else if (field instanceof Integer) {
		 * cell.setCellValue((Integer) field); } } }
		 * 
		 * try { FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
		 * workbook.write(outputStream); outputStream.close(); } catch
		 * (FileNotFoundException e) { e.printStackTrace(); } catch (IOException e) {
		 * e.printStackTrace(); }finally {
		 * 
		 * 
		 * }
		 */

	}

	public void seqNumDate() {

		Set<?> set2 = hm.entrySet();
		Iterator iterator2 = set2.iterator();

		while (iterator2.hasNext()) {
			mentry2 = (Map.Entry) iterator2.next();
			// System.out.println("Key is: " + mentry2.getKey() + " & Value is: " +
			// mentry2.getValue());

			if (mentry2.getValue().equals("Pending")) {

				System.out.println("----------------PENDING-----------------------------");

				System.out.println("Sequence Num: " + mentry2.getKey() + "   Action: " + mentry2.getValue());

				System.out.println("----------------PENDING-----------------------------");

				break;

			} else if (mentry2.getValue().equals("Approved")) {

				System.out.println("----------------APPROVED----------------------------");

				System.out.println("Sequence Num: " + mentry2.getKey() + "  Action: " + mentry2.getValue());

				System.out.println("----------------APPROVED-----------------------------");

				break;
			} else {

				continue;

			}

		}

	}

	public void actionDate() {

		System.out.println("TESTING" + mentry2.getValue());

		Set<?> set4 = hm3.entrySet();
		Iterator iterator4 = set4.iterator();
		while (iterator4.hasNext()) {

			Map.Entry mentry4 = (Map.Entry) iterator4.next();

			System.out.println("Action Date: " + mentry4.getValue());

			if (mentry2.getValue().equals("Approved")) {

				System.out.println("Action Date: " + mentry4.getValue());

			}

		}

	}

	public void approverName() {

		System.out.println("TESTING2" + mentry2.getValue());

		Set<?> set3 = hm2.entrySet();

		Iterator iterator3 = set3.iterator();
		while (iterator3.hasNext()) {

			Map.Entry mentry3 = (Map.Entry) iterator3.next();

			System.out.println("Manager Name: " + mentry3.getValue());

			if (mentry2.getValue().equals("Pending")) {

				System.out.println("Manager Name: " + mentry3.getValue());

			}

			// System.out.println("Key is: " + mentry3.getKey() + " & Value is: " +
			// mentry3.getValue());

		}

	}

}
