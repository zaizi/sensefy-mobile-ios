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
public class f_sensefymobile_iOS_facets2 
{
	public static final Logger LOGGER = LogManager.getLogger(f_sensefymobile_iOS_facets2.class.getName());
	
	public static final ExtentReports extent = ExtentReports.get(f_sensefymobile_iOS_facets2.class);
	
	public static String className=f_sensefymobile_iOS_facets2.class.getSimpleName();
	
	static AppiumDriver driver;
	
	public String searchFile;
	public String creatorDoc;
	public String documentTypeDoc;
	public String languageDoc;
	
	public String screenshotname = "facets1";
	
	/**
	 * 
	 * @param searchFile
	 * @param creatorDoc
	 * @param documentTypeDoc
	 * @param languageDoc
	 */
	public f_sensefymobile_iOS_facets2(String searchFile, String creatorDoc, String documentTypeDoc, String languageDoc) 
	{
		this.searchFile = searchFile;
		this.creatorDoc = creatorDoc;
		this.documentTypeDoc = documentTypeDoc;
		this.languageDoc = languageDoc;
	}
	
	@Parameters()
	public static Iterable<Object[]> data() throws IterableException 
	{
		LOGGER.info(TestCaseProperties.TEXT_TEST_PREPARING, "f_sensefymobile_iOS_facets2");

		return TestCaseValues.documentLibraryTestValues("f_sensefymobile_iOS_facets2");
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
        extent.config().reportTitle("Factes Funtions in Sensefy - Part 2");
		        
        // Define ReportHeadLine of ExtentReport
        extent.config().reportHeadline("Factes Funtions in Sensefy - Part 2");    
     }
	
	@Test
	public void a() throws InterruptedException, IOException
	{
		LOGGER.info("Check on Admin and System under the Creator");
		extent.startTest("Check on Admin and System under the Creator");
		
		driver=(AppiumDriver) TestCaseProperties.getWebDriver();
		
		driver.findElement(By.xpath("//UIASearchBar")).sendKeys(searchFile+"\n");
		
		extent.log(LogStatus.INFO, "Search the File "+searchFile);
		System.out.println(searchFile);
		
		//Click on the facets Button
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[4]")).click();
		System.out.println("Clicked in the Facets");
		
		//check on the admin
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]")).click();
		System.out.println("Checked Admin");
		LOGGER.info("Checked Admin");
		Element.takescreenshot(driver,className,screenshotname+"3");
		Thread.sleep(3000);
		
		//check on the system
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[2]/UIAStaticText[1]")).click();
		System.out.println("Checked System");
		LOGGER.info("Checked System");
		Element.takescreenshot(driver,className,screenshotname+"4");
				
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
			LOGGER.info("Pass : Successfully selected Admin and System under Creator");
			extent.log(LogStatus.PASS, "Pass : Successfully selected Admin and System under Creator");
			Element.takescreenshot(driver,className,screenshotname+"5");
		}
		else
		{
			extent.log(LogStatus.INFO, "Expected Rersult : "+creatorDoc);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Fail : Unsuccessfully  selected Admin and System under Creator");
			extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully selected Admin and System under Creator");
			Element.takescreenshot(driver,className,screenshotname+"6");
		}
		
		extent.log(LogStatus.INFO, "Navigate back to the Results");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);		

		//check on the system
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[2]/UIAStaticText[1]")).click();
		System.out.println("Unchecked System");
		LOGGER.info("Unchecked System");
		Thread.sleep(3000);
		
		//uncheck on the admin
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]")).click();
		System.out.println("Unchecked Admin");
		LOGGER.info("Unchecked Admin");
		Thread.sleep(3000);		
	}
	
	@Test
	public void b() throws IOException, InterruptedException
	{
		LOGGER.info("Check on Plain Text and XML under the Document Type");
		extent.startTest("Check on Plain Text and XML under the Document Type");
	
		//check on the plain text
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[11]/UIAStaticText[1]")).click();
		System.out.println("Checked Plain Text");
		LOGGER.info("Checked Plain Text");
		Element.takescreenshot(driver,className,screenshotname+"7");
		Thread.sleep(3000);
		
		//check on the XML
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[4]/UIAStaticText[1]")).click();
		System.out.println("Checked XML");
		LOGGER.info("Checked XML");
		Element.takescreenshot(driver,className,screenshotname+"8");
			 
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
				LOGGER.info("Pass : Successfully selected Plain Text and XML under the Document Type");
				extent.log(LogStatus.PASS, "Pass : Successfully selected Plain Text and XML under the Document Type");
				Element.takescreenshot(driver,className,screenshotname+"9");
		}
		else
		{
				extent.log(LogStatus.INFO, "Expected Rersult : "+documentTypeDoc);
				extent.log(LogStatus.INFO, "Current Result : "+message);
				LOGGER.info("Fail : Unsuccessfully  selected Plain Text and XML under the Document Type");
				extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully selected Plain Text and XML under the Document Type");
				Element.takescreenshot(driver,className,screenshotname+"10");
		}
				
		extent.log(LogStatus.INFO, "Navigate back to the Results");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);		
		
		//uncheck on the XML
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[9]/UIAStaticText[1]")).click();
		System.out.println("Unchecked XML");
		LOGGER.info("Unchecked XML");
		Thread.sleep(3000);
		
		//uncheck on the plain text
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[3]/UIAStaticText[1]")).click();
		System.out.println("Unchecked Plain Text");
		LOGGER.info("Unchecked Plain Text");
		Thread.sleep(3000);
	}
	
	@Test
	public void c() throws IOException, InterruptedException
	{

		LOGGER.info("Check on English and French under the Language");
		extent.startTest("Check on English and French under the Language");
	
		//check on the english
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[20]/UIAStaticText[1]")).click();
		System.out.println("Checked English");
		LOGGER.info("Checked English");
		Element.takescreenshot(driver,className,screenshotname+"11");
		
		//check on the french
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[15]/UIAStaticText[1]")).click();
		System.out.println("Checked French");
		LOGGER.info("Checked French");
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
		if(message.equals(languageDoc))
		{
				extent.log(LogStatus.INFO, "Expected Result : "+languageDoc);
				extent.log(LogStatus.INFO, "Current Result : "+message);
				LOGGER.info("Pass : Successfully selected English and French under the Language");
				extent.log(LogStatus.PASS, "Pass : Successfully selected English under the Language");
				Element.takescreenshot(driver,className,screenshotname+"13");
		}
		else
		{
				extent.log(LogStatus.INFO, "Expected Rersult : "+languageDoc);
				extent.log(LogStatus.INFO, "Current Result : "+message);
				LOGGER.info("Fail : Unsuccessfully  selected English and Frnech under the Language");
				extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully selected English under the Language");
				Element.takescreenshot(driver,className,screenshotname+"14");
		}
				
		extent.log(LogStatus.INFO, "Navigate back to the Results");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);		

		//uncheck on the french
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[16]/UIAStaticText[1]")).click();
		System.out.println("Unchecked French");
		LOGGER.info("Unchecked French");
		Thread.sleep(3000);
				
		//uncheck on the english
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[14]/UIAStaticText[1]")).click();
		System.out.println("Unchecked English");
		LOGGER.info("Unchecked English");
		Thread.sleep(3000);
	}
	
	
}
