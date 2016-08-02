package com.zaizi.sensefymobile.ios;


import java.io.IOException;

/*
 * author deranthika@zaizi.com
 */

import java.net.MalformedURLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.zaizi.sensefymobile.ios.core.elements.Button;
import com.zaizi.sensefymobile.ios.core.elements.Element;
import com.zaizi.sensefymobile.ios.core.elements.RadioButton;
import com.zaizi.sensefymobile.ios.core.info.TestCaseProperties;
import com.zaizi.sensefymobile.ios.core.info.TestCaseValues;
import com.zaizi.sensefymobile.ios.exceptions.IterableException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
/**
 * 
 * @author ljayasinghe
 *
 */
@RunWith(value = Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class b_sensefymobile_iOS_datasource 
{
	public static final Logger LOGGER = LogManager.getLogger(b_sensefymobile_iOS_datasource.class.getName());
	
	public static final ExtentReports extent = ExtentReports.get(b_sensefymobile_iOS_datasource.class);
	
	public static String className=b_sensefymobile_iOS_datasource.class.getSimpleName();
	
	static AppiumDriver driver;
	
	public String searchFile;
	 
	
	public String screenshotname = "datasource";
	
	
	
	public b_sensefymobile_iOS_datasource(String searchFile) 
	{
		
		this.searchFile = searchFile;
	}

	@Parameters()
	public static Iterable<Object[]> data() throws IterableException 
	{
		LOGGER.info(TestCaseProperties.TEXT_TEST_PREPARING, "b_sensefymobile_iOS_datasource");

		return TestCaseValues.documentLibraryTestValues("b_sensefymobile_iOS_datasource");
	}
	
	@BeforeClass
    public static void beforeClass() throws IOException 
	{
		String css = "div.content{ width: 1422px;}"+"div.filters{ width:1422px;}";
        
		// Initialize ExtentReport(driver,NameofReport)
		Element.reportInitial(driver, className);
		
		extent.config().addCustomStyles(css);
		
		// Define DocumentTitle of ExtentReport
        extent.config().documentTitle("Sensefy Mobile");
	        
        // Define ReportTitle of ExtentReport 
        extent.config().reportTitle("Verify the DataSource In Sensefy");
		        
        // Define ReportHeadLine of ExtentReport
        extent.config().reportHeadline("Verify the DataSource In Sensefy");    
     }
	
	@Test
	public void a() throws IOException, InterruptedException
	{
		LOGGER.info("Verify the default Data source");
		extent.startTest("Verify the default Data source");
		
		driver=(AppiumDriver) TestCaseProperties.getWebDriver();
		
		driver.findElement(By.xpath("//UIASearchBar")).sendKeys(searchFile+"\n");
		
		extent.log(LogStatus.INFO, "Search the File "+searchFile);
		System.out.println(searchFile);
		
		//Checks whether the Element is present
		extent.log(LogStatus.INFO, "Verify the default Data source button is available");
		if(Button.isElementPresent(driver, By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")))
		{
			 LOGGER.info("Pass : Data source button is Available");
			 extent.log(LogStatus.PASS, "Pass : Data source button is Available");
			 Element.takescreenshot(driver,className,screenshotname+"1");
		}
		else
		{
			 LOGGER.info("Fail : Data source button is not available");
			 extent.log(LogStatus.FAIL, "Fail : Data source button is not available");
			 Element.takescreenshot(driver,className,screenshotname+"2");
		}
		
		//click on the data source
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
		
				
		extent.log(LogStatus.INFO, "Check for the Default Data Source");
		
		MobileElement expelement=(MobileElement) (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAStaticText[contains(@name,'sample 1.png')]")));
    	String expact=expelement.getText();
    	expelement=(MobileElement)(new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIANavigationBar/UIAButton[3]")));
    	String[] parts=expelement.getText().split(" ");
    	Assert.assertEquals("All", parts[0]);
    	Element.takescreenshot(driver,className,screenshotname+"3");
		
		Thread.sleep(3000);
		
		//selects Alfresco_New				
		extent.log(LogStatus.INFO, "Select on the Alfreco_New");
		
		expelement=(MobileElement)(new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIANavigationBar/UIAButton[3]")));
    	//driver.findElement(By.xpath("//UIANavigationBar[1]/UIAButton[3]")).click();
    	System.out.println("1111");
    	//expelement=(MobileElement)(new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAWindow/UIAPopover/UIATableView/UIATableCell/UIAStaticText[contains(@name,'All')]")));
    	//expelement=(MobileElement)(new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAPopover[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]")));   	
    	System.out.println("2222");
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAPopover[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[contains(@name,'Alfresco_New')]")).click();
    	System.out.println("3333");
    	//UIAApplication[1]/UIAWindow[1]/UIAPopover[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[2]
    	driver.findElement(By.xpath("//UIAWindow/UIANavigationBar/UIAButton[1]")).click();
    	System.out.println("4444");
    	Element.takescreenshot(driver,className,screenshotname+"4");
    	expelement=(MobileElement)(new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIANavigationBar/UIAButton[3]")));
    	String[] parts1=expelement.getText().split(" ");
    	Assert.assertEquals("Alfresco_New", parts1[0]);
    	Element.takescreenshot(driver,className,screenshotname+"4");
    	System.out.println("5555");
	}
	
	
	

}
