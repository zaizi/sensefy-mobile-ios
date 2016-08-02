package com.zaizi.sensefymobile.ios;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.zaizi.sensefymobile.ios.core.elements.Element;
import com.zaizi.sensefymobile.ios.core.elements.TextField;
import com.zaizi.sensefymobile.ios.core.elements.Button;
import com.zaizi.sensefymobile.ios.core.info.TestCaseProperties;
import com.zaizi.sensefymobile.ios.core.info.TestCaseValues;
import com.zaizi.sensefymobile.ios.exceptions.IterableException;

import io.appium.java_client.AppiumDriver;

/**
 * 
 * @author ljayasinghe
 *
 */
@RunWith(value = Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class c_sensefymobile_iOS_orderby 
{
	public static final Logger LOGGER = LogManager.getLogger(c_sensefymobile_iOS_orderby.class.getName());
	
	public static final ExtentReports extent = ExtentReports.get(c_sensefymobile_iOS_orderby.class);
	
	public static String className=c_sensefymobile_iOS_orderby.class.getSimpleName();

	
	static AppiumDriver driver;
	
	public String searchFile;
	public String ascFile;
	public String descFile;
	public String screenshotname = "orderby";
	
	/**
	 * 
	 * @param searchFile
	 * @param ascFile
	 * @param descFile
	 */
	public c_sensefymobile_iOS_orderby(String searchFile, String ascFile, String descFile) 
	{
		this.searchFile = searchFile;
		this.ascFile = ascFile;
		this.descFile = descFile;
	}
	
	@Parameters()
	public static Iterable<Object[]> data() throws IterableException 
	{

		LOGGER.info(TestCaseProperties.TEXT_TEST_PREPARING, "c_sensefymobile_iOS_orderby");

		return TestCaseValues.documentLibraryTestValues("c_sensefymobile_iOS_orderby");
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
        extent.config().reportTitle("Order By Funtion in Sensefy");
		        
        // Define ReportHeadLine of ExtentReport
        extent.config().reportHeadline("Order By Funtion in Sensefy");    
     }
	
	@Test
	public void a() throws InterruptedException, IOException
	{
		LOGGER.info("Checks for the Order Button availability and Default : Sort Descending");
		extent.startTest("Checks for the Order Button availability and Default : Sort Descending");
		
		driver=(AppiumDriver) TestCaseProperties.getWebDriver();
		
		driver.findElement(By.xpath("//UIASearchBar")).sendKeys(searchFile+"\n");
		
		extent.log(LogStatus.INFO, "Search the File "+searchFile);
		System.out.println(searchFile);
	
		//Checks whether the Element is present
		extent.log(LogStatus.INFO, "Verify whether the OrderBy button is available");
		if(Button.isElementPresent(driver, By.xpath("//UIAWindow/UIAToolbar[2]/UIAButton[3]")))
		{
			System.out.println("Pass : Order By Button is Available");
			extent.log(LogStatus.PASS, "Pass : Order By Button is Available");
			Element.takescreenshot(driver,className,screenshotname+"1");
		}
		else
		{
			System.out.println("Fail : Order By Button is not available");
			extent.log(LogStatus.FAIL, "Fail : Order By Button is not available");
			Element.takescreenshot(driver,className,screenshotname+"2");
		}
		
		//Checks whether the Button is Sort Descending by Default	
		extent.log(LogStatus.INFO, "Verify whether the Default is SortDescending in the OrderBy Button");
		if(Button.isElementPresent(driver,By.name("SortDescending")))
		{
			System.out.println("Pass : Sort Descending by Default");
			extent.log(LogStatus.PASS, "Pass : Sort Descending by Default");
			Element.takescreenshot(driver,className,screenshotname+"3");
		}
		else
		{
			System.out.println("Fail : Sort Ascending by Default");
			extent.log(LogStatus.FAIL, "Fail : Sort Ascending by Default");
			Element.takescreenshot(driver,className,screenshotname+"4");
		}		
		
	}
	
	@Test
	public void b() throws InterruptedException, IOException
	{
		extent.startTest("Verify whether the Documents were sorted in Ascending order");
		LOGGER.info("Verify whether the Documents were sorted in Ascending order");
					
		WebElement sort_ascending = driver.findElement(By.xpath("//UIAWindow/UIAToolbar[2]/UIAButton[3]"));
		sort_ascending.click();
		System.out.println("Clicked on the order by Button");
		Element.takescreenshot(driver,className,screenshotname+"5");		
		Thread.sleep(3000);
		
		WebElement gotoDocument = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]");
		gotoDocument.click();
		System.out.println("Navigated to the Document in the Row 1");
				
		extent.log(LogStatus.INFO, "Verify the Document");
		WebElement getDocName = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]");
		String message = getDocName.getAttribute("value");
		//Checks for the Document Name
		if(message.equals(ascFile))
		{
			extent.log(LogStatus.INFO, "Expected Rersult : "+ascFile);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			System.out.println("Pass : Successfully ordered to Ascending");
			extent.log(LogStatus.PASS, "Pass : Successfully ordered to Ascending");
			Element.takescreenshot(driver,className,screenshotname+"6");
		}
		else
		{
			extent.log(LogStatus.INFO, "Expected Rersult : "+ascFile);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			System.out.println("Fail : Unsuccessfully ordered to Ascending");
			extent.log(LogStatus.FAIL, "Fail : Unsuccessfully ordered to Ascending");
			Element.takescreenshot(driver,className,screenshotname+"7");
		}
		
		extent.log(LogStatus.INFO, "Navigate back to the Results");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);
	
	}
	
	@Test
	public void c() throws InterruptedException, IOException
	{
		extent.startTest("Verify whether the Documents were sorted in Descending order");
		LOGGER.info("Verify whether the Documents were sorted in Descending order");
		
		WebElement sort_ascending = driver.findElement(By.xpath("//UIAWindow/UIAToolbar[2]/UIAButton[3]"));
		sort_ascending.click();
		System.out.println("Clicked on the order by Button");
		Element.takescreenshot(driver,className,screenshotname+"8");		
		Thread.sleep(3000);
		
		WebElement gotoDocument = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]");
		gotoDocument.click();
		System.out.println("Navigated to the Document in the Row 1");
				
		extent.log(LogStatus.INFO, "Verify the Document");
		WebElement getDocName = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]");
		String message = getDocName.getAttribute("value");
		//Checks for the Document Name
		if(message.equals(descFile))
		{
			extent.log(LogStatus.INFO, "Expected Rersult : "+descFile);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			System.out.println("Pass : Successfully ordered to Descending");
			extent.log(LogStatus.PASS, "Pass : Successfully ordered to Descending");
			Element.takescreenshot(driver,className,screenshotname+"9");
		}
		else
		{
			extent.log(LogStatus.INFO, "Expected Rersult : "+descFile);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			System.out.println("Fail : Unsuccessfully ordered to Descending");
			extent.log(LogStatus.FAIL, "Fail : Unsuccessfully ordered to Descending");
			Element.takescreenshot(driver,className,screenshotname+"10");
		}
		
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigate back to the Results");
		
		/*//click on the profile button
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")).click();
				
		//click on logout
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAPopover[1]/UIATableView[1]/UIATableCell[3]")).click();
		
		Thread.sleep(3000);
			
		//click on login
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAPopover[1]/UIATableView[1]/UIATableCell[3]")).click();
			*/	
				
		
		
	}
}
