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
import com.zaizi.sensefymobile.ios.core.elements.Button;
import com.zaizi.sensefymobile.ios.core.elements.Element;
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
public class e_sensefymobile_iOS_facets1 
{
	public static final Logger LOGGER = LogManager.getLogger(e_sensefymobile_iOS_facets1.class.getName());
	
	public static final ExtentReports extent = ExtentReports.get(e_sensefymobile_iOS_facets1.class);
	
	public static String className=e_sensefymobile_iOS_facets1.class.getSimpleName();
	
	static AppiumDriver driver;
	
	public String searchFile;
	public String creatorDoc;
	public String documentTypeDoc;
	public String languageDoc;
	public String sizeDoc;
	public String lastmodifieddateDoc;
	public String creationdateDoc;
	public String screenshotname = "facets";
	
	/**
	 * 
	 * @param creatorDoc
	 * @param documentTypeDoc
	 * @param languageDoc
	 * @param sizeDoc
	 * @param lastmodifieddateDoc
	 * @param creationdateDoc
	 * @param screenshotname
	 */
	public e_sensefymobile_iOS_facets1(String searchFile,String creatorDoc, String documentTypeDoc, String languageDoc, String sizeDoc,
			String lastmodifieddateDoc, String creationdateDoc) 
	{		
		this.searchFile = searchFile;
		this.creatorDoc = creatorDoc;
		this.documentTypeDoc = documentTypeDoc;
		this.languageDoc = languageDoc;
		this.sizeDoc = sizeDoc;
		this.lastmodifieddateDoc = lastmodifieddateDoc;
		this.creationdateDoc = creationdateDoc;
	}
	
	@Parameters()
	public static Iterable<Object[]> data() throws IterableException 
	{
		LOGGER.info(TestCaseProperties.TEXT_TEST_PREPARING, "e_sensefymobile_iOS_facets1");

		return TestCaseValues.documentLibraryTestValues("e_sensefymobile_iOS_facets1");
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
        extent.config().reportTitle("Factes Funtions in Sensefy - Part 1");
		        
        // Define ReportHeadLine of ExtentReport
        extent.config().reportHeadline("Factes Funtions in Sensefy - Part 1");    
     }

	@Test
	public void a() throws InterruptedException, IOException
	{
		LOGGER.info("Checks for the Facet Button availability and Check on Admin under the Creator");
		extent.startTest("Checks for the Facet Button availability and Check on Admin under the Creator");
		
		driver=(AppiumDriver) TestCaseProperties.getWebDriver();
		
		driver.findElement(By.xpath("//UIASearchBar")).sendKeys(searchFile+"\n");
		
		extent.log(LogStatus.INFO, "Search the File "+searchFile);
		System.out.println(searchFile);
		
		//Checks whether the Element is present
		extent.log(LogStatus.INFO, "Verify whether the SortBy button is available");
		if(Button.isElementPresent(driver, By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[4]")))
		{
			 LOGGER.info("Pass : Facets Button is Available");
			 extent.log(LogStatus.PASS, "Pass : Facets Button is Available");
			 Element.takescreenshot(driver,className,screenshotname+"1");
		}
		else
		{
			 LOGGER.info("Fail : Facets Button is not available");
			 extent.log(LogStatus.FAIL, "Fail : Facets Button is not available");
			 Element.takescreenshot(driver,className,screenshotname+"2");
		}
		
		//Click on the facets Button
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[4]")).click();
		System.out.println("Clicked in the Facets");
		
		//check on the admin
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]")).click();
		System.out.println("Checked Admin");
		LOGGER.info("Checked Admin");
		Element.takescreenshot(driver,className,screenshotname+"3");
		 
		//Navigate to the document in the Row 1
		WebElement gotoDocument = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]");
		gotoDocument.click();
		System.out.println("Navigated to the Document in the Row 1");
			
		//Checks the document in the 1st row
		WebElement getDocName1 = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]");
		String message = getDocName1.getAttribute("value");
		
		//Checks for the Document Name
		extent.log(LogStatus.INFO, "Verify the Document");
		if(message.equals(creatorDoc))
		{
			extent.log(LogStatus.INFO, "Expected Result : "+creatorDoc);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Pass : Successfully selected Admin under Creator");
			extent.log(LogStatus.PASS, "Pass : Successfully  selected Admin under Creator");
			Element.takescreenshot(driver,className,screenshotname+"4");
		}
		else
		{
			extent.log(LogStatus.INFO, "Expected Rersult : "+creatorDoc);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Fail : Unsuccessfully  selected Admin under Creator");
			extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully  selected Admin under Creator");
			Element.takescreenshot(driver,className,screenshotname+"5");
		}
		
		extent.log(LogStatus.INFO, "Navigate back to the Results");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);		

		//uncheck on the admin
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]")).click();
		System.out.println("Unchecked Admin");
		LOGGER.info("Unchecked Admin");
		Thread.sleep(3000);
	}

	
	@Test
	public void b() throws InterruptedException, IOException
	{
		
		LOGGER.info("Check on Plain Text under the Document Type");
		extent.startTest("Check on Plain Text under the Document Type");
	
		//check on the plain text
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[11]/UIAStaticText[1]")).click();
		System.out.println("Checked Plain Text");
		LOGGER.info("Checked Plain Text");
		Element.takescreenshot(driver,className,screenshotname+"6");
			 
		//Navigate to the document in the Row 1
		WebElement gotoDocument = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]");
		gotoDocument.click();
		System.out.println("Navigated to the Document in the Row 1");
					
		//Checks the document in the 1st row
		WebElement getDocName1 = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]");
		String message = getDocName1.getAttribute("value");
			
		//Checks for the Document Name
		extent.log(LogStatus.INFO, "Verify the Document");
		if(message.equals(documentTypeDoc))
		{
				extent.log(LogStatus.INFO, "Expected Result : "+documentTypeDoc);
				extent.log(LogStatus.INFO, "Current Result : "+message);
				LOGGER.info("Pass : Successfully selected Plain Text under the Document Type");
				extent.log(LogStatus.PASS, "Pass : Successfully selected Plain Text under the Document Type");
				Element.takescreenshot(driver,className,screenshotname+"7");
		}
		else
		{
				extent.log(LogStatus.INFO, "Expected Rersult : "+documentTypeDoc);
				extent.log(LogStatus.INFO, "Current Result : "+message);
				LOGGER.info("Fail : Unsuccessfully  selected Plain Text under the Document Type");
				extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully selected Plain Text under the Document Type");
				Element.takescreenshot(driver,className,screenshotname+"8");
		}
				
		extent.log(LogStatus.INFO, "Navigate back to the Results");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);		

		//uncheck on the plain text
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[3]/UIAStaticText[1]")).click();
		System.out.println("Unchecked Plain Text");
		LOGGER.info("Unchecked Plain Text");
		Thread.sleep(3000);
	}
	
	@Test
	public void c() throws InterruptedException, IOException
	{
		
		LOGGER.info("Check on English under the Language");
		extent.startTest("Check on English under the Language");
	
		//check on the english
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[20]/UIAStaticText[1]")).click();
		System.out.println("Checked English");
		LOGGER.info("Checked English");
		Element.takescreenshot(driver,className,screenshotname+"9");
			 
		//Navigate to the document in the Row 1
		WebElement gotoDocument = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]");
		gotoDocument.click();
		System.out.println("Navigated to the Document in the Row 1");
					
		//Checks the document in the 1st row
		WebElement getDocName1 = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]");
		String message = getDocName1.getAttribute("value");
			
		//Checks for the Document Name
		extent.log(LogStatus.INFO, "Verify the Document");
		if(message.equals(languageDoc))
		{
				extent.log(LogStatus.INFO, "Expected Result : "+languageDoc);
				extent.log(LogStatus.INFO, "Current Result : "+message);
				LOGGER.info("Pass : Successfully selected English under the Language");
				extent.log(LogStatus.PASS, "Pass : Successfully selected English under the Language");
				Element.takescreenshot(driver,className,screenshotname+"10");
		}
		else
		{
				extent.log(LogStatus.INFO, "Expected Rersult : "+languageDoc);
				extent.log(LogStatus.INFO, "Current Result : "+message);
				LOGGER.info("Fail : Unsuccessfully  selected English under the Language");
				extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully selected English under the Language");
				Element.takescreenshot(driver,className,screenshotname+"11");
		}
				
		extent.log(LogStatus.INFO, "Navigate back to the Results");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);		

		//uncheck on the english
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[14]/UIAStaticText[1]")).click();
		System.out.println("Unchecked English");
		LOGGER.info("Unchecked English");
		Thread.sleep(3000);
	}

	@Test
	public void d() throws InterruptedException, IOException
	{
		
		LOGGER.info("Check on 0B-10MB under Size");
		extent.startTest("Check on 0B-10MB under Size");
	
		//check on the 0B-10MB
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[25]/UIAStaticText[1]")).click();
		System.out.println("Checked 0B-10MB");
		LOGGER.info("Checked 0B-10MB");
		Element.takescreenshot(driver,className,screenshotname+"12");
			 
		//Navigate to the document in the Row 1
		WebElement gotoDocument = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]");
		gotoDocument.click();
		System.out.println("Navigated to the Document in the Row 1");
					
		//Checks the document in the 1st row
		WebElement getDocName1 = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]");
		String message = getDocName1.getAttribute("value");
			
		//Checks for the Document Name
		extent.log(LogStatus.INFO, "Verify the Document");
		if(message.equals(sizeDoc))
		{
				extent.log(LogStatus.INFO, "Expected Result : "+sizeDoc);
				extent.log(LogStatus.INFO, "Current Result : "+message);
				LOGGER.info("Pass : Successfully selected 0B-10MB under Size");
				extent.log(LogStatus.PASS, "Pass : Successfully selected 0B-10MB under Size");
				Element.takescreenshot(driver,className,screenshotname+"13");
		}
		else
		{
				extent.log(LogStatus.INFO, "Expected Rersult : "+sizeDoc);
				extent.log(LogStatus.INFO, "Current Result : "+message);
				LOGGER.info("Fail : Unsuccessfully  selected 0B-10MB under Size");
				extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully selected 0B-10MB under Size");
				Element.takescreenshot(driver,className,screenshotname+"14");
		}
				
		extent.log(LogStatus.INFO, "Navigate back to the Results");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);		

		//uncheck on the 0B-10MB
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[25]/UIAStaticText[1]")).click();
		System.out.println("Unchecked 0B-10MB");
		LOGGER.info("Unchecked 0B-10MB");
		Thread.sleep(3000);
	}
	
/*	@Test
	public void e() throws InterruptedException, IOException
	{
		
		LOGGER.info("Check on Last Month under Last Modified Date");
		extent.startTest("Check on Last Month under Last Modified Date");
	
		//check on the Last Month
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[24]/UIAStaticText[1]")).click();
		System.out.println("Checked Last Month");
		LOGGER.info("Checked Last Month");
		Element.takescreenshot(driver,className,screenshotname+"15");
			 
		//Navigate to the document in the Row 1
		WebElement gotoDocument = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]");
		gotoDocument.click();
		System.out.println("Navigated to the Document in the Row 1");
					
		//Checks the document in the 1st row
		WebElement getDocName1 = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]");
		String message = getDocName1.getAttribute("value");
			
		//Checks for the Document Name
		extent.log(LogStatus.INFO, "Verify the Document");
		if(message.equals(lastmodifieddateDoc))
		{
				extent.log(LogStatus.INFO, "Expected Result : "+lastmodifieddateDoc);
				extent.log(LogStatus.INFO, "Current Result : "+message);
				LOGGER.info("Pass : Successfully selected Last Month under Last Modified Date");
				extent.log(LogStatus.PASS, "Pass : Successfully selected Last Month under Last Modified Date");
				Element.takescreenshot(driver,className,screenshotname+"16");
		}
		else
		{
				extent.log(LogStatus.INFO, "Expected Rersult : "+lastmodifieddateDoc);
				extent.log(LogStatus.INFO, "Current Result : "+message);
				LOGGER.info("Fail : Unsuccessfully  selected Last Month under Last Modified Date");
				extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully selected Last Month under Last Modified Date");
				Element.takescreenshot(driver,className,screenshotname+"17");
		}
				
		extent.log(LogStatus.INFO, "Navigate back to the Results");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);		

		//uncheck on the Last Month
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[8]/UIAStaticText[1]")).click();
		System.out.println("Unchecked Last Month");
		LOGGER.info("Unchecked Last Month");
		Thread.sleep(3000);
	}
	
/*	@Test
	public void f() throws InterruptedException, IOException
	{
		
		LOGGER.info("Check on Last Month under Creation Date");
		extent.startTest("Check on Last Month under Creation Date");
	
		//check on the Last Month
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[25]/UIAStaticText[1]")).click();
		System.out.println("Checked Last Month");
		LOGGER.info("Checked Last Month");
		Element.takescreenshot(driver,className,screenshotname+"18");
			 
		//Navigate to the document in the Row 1
		WebElement gotoDocument = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]");
		gotoDocument.click();
		System.out.println("Navigated to the Document in the Row 1");
					
		//Checks the document in the 1st row
		WebElement getDocName1 = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]");
		String message = getDocName1.getAttribute("value");
		
		//Checks for the Document Name
		extent.log(LogStatus.INFO, "Verify the Document");
		if(message.equals(lastmodifieddateDoc))
		{
				extent.log(LogStatus.INFO, "Expected Result : "+lastmodifieddateDoc);
				extent.log(LogStatus.INFO, "Current Result : "+message);
				LOGGER.info("Pass : Successfully selected Last Month under Creation Date");
				extent.log(LogStatus.PASS, "Pass : Successfully selected Last Month under Creation Date");
				Element.takescreenshot(driver,className,screenshotname+"19");
		}
		else
		{
				extent.log(LogStatus.INFO, "Expected Rersult : "+lastmodifieddateDoc);
				extent.log(LogStatus.INFO, "Current Result : "+message);
				LOGGER.info("Fail : Unsuccessfully  selected Last Month under Creation Date");
				extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully selected Last Month under Creation Date");
				Element.takescreenshot(driver,className,screenshotname+"20");
		}
				
		extent.log(LogStatus.INFO, "Navigate back to the Results");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);		

		//uncheck on the Last Month
		driver.findElement(By.xpath(" //UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[9]/UIAStaticText[1]")).click();
		System.out.println("Unchecked Last Month");
		LOGGER.info("Unchecked Last Month");
		Thread.sleep(3000);
	}*/
}
