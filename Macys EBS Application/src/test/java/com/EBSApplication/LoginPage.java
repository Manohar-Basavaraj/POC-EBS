package com.EBSApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ExcelHandling.ReadExcel;
import ExcelHandling.WebTableData;

public class LoginPage extends ReadExcel
{

	public WebDriver driver = null;

	@BeforeTest
	public void launchbrowser() throws InterruptedException {

		System.setProperty("webdriver.ie.driver",
				"C:\\Users\\deepthi.singh\\Desktop\\test2\\Macys EBS Application\\drivers\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		driver.manage().deleteAllCookies();
		driver.get("https://ebs.federated.fds/OA_HTML/AppsLogin");
		driver.manage().window().maximize();
		Thread.sleep(5000);
		driver.findElement(By.id("overridelink")).click();
		driver.findElement(By.id("overridelink")).click();
		driver.findElement(By.id("overridelink")).click();

	}

	@Test(priority = 0)
	public void login() {

		Properties pro = new Properties();
		File f = new File(
				"C:\\Users\\deepthi.singh\\Desktop\\test2\\Macys EBS Application\\configproperties\\config.properties");
		FileInputStream FileIo;
		try {
			FileIo = new FileInputStream(f);
			pro.load(FileIo);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		driver.findElement(By.id("username")).sendKeys(pro.getProperty("username"));
		driver.findElement(By.id("password")).sendKeys(pro.getProperty("password"));
		driver.findElement(By.xpath("//input[@class='formButton']")).click();
	}

	@Test(priority = 1)
	public void EBusinessSuitePage() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='MCY CONTRACTOR ENTRY-VR']")));
		driver.findElement(By.xpath("//a[text()='MCY CONTRACTOR ENTRY-VR']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='iProcurement Home Page']")));
		driver.findElement(By.xpath("//a[text()='iProcurement Home Page']")).click();

	}

	@Test(priority = 2)
	public void Requisitions() throws InterruptedException {

		driver.findElement(By.id("ICXPOR_REQSTATUS")).click();
		driver.findElement(By.id("Search")).click();
		WebElement ReqCreatedBy = driver.findElement(By.id("PreparerLov"));
		ReqCreatedBy.clear();
		WebElement testDropDown = driver.findElement(By.id("CreationDatePoplist"));
		Select dropdown = new Select(testDropDown);
		dropdown.selectByIndex(0);
		
		
		ReadExcel objExcelFile = new ReadExcel();

		String filePath = System.getProperty("user.dir");

		try {
			objExcelFile.ReadExcel(filePath, "EBSExcelFile.xlsx", "Contract Pending Approval List");
		} catch (IOException e) {
			
			
			e.printStackTrace();
		}
		
		
		String data = ReadExcel.reqNumber.get(0);
	
		driver.findElement(By.id("ReqNumberInput")).sendKeys(data);
		driver.findElement(By.xpath("//button[text()='Go']")).click();
		driver.findElement(By.id("N3:ApprovalStatus:0")).click();
	
		WebTableData ed = new WebTableData(driver);
		ed.read();
		
		ed.seqNumDate();
		ed.actionDate();
		ed.approverName();
				

		
	}

	/*
	  @AfterTest public void logout() 
	  
	  
	  {
		  driver.close(); driver.quit();
	  
	 }
	 */
	
	
	

}
