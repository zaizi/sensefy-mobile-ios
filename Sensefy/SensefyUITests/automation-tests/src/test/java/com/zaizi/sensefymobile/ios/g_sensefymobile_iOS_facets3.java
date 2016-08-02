package com.zaizi.sensefymobile.ios;

import java.io.IOException;

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
public class g_sensefymobile_iOS_facets3 
{
	public static final Logger LOGGER = LogManager.getLogger(g_sensefymobile_iOS_facets3.class.getName());
	
	public static final ExtentReports extent = ExtentReports.get(g_sensefymobile_iOS_facets3.class);
	
	public static String className=g_sensefymobile_iOS_facets3.class.getSimpleName();
	
	static AppiumDriver driver;
	
	public String searchFile;
	public String creator_doctype;
	public String creator_language;
	public String creator_size;
	public String creator_modifiedDate;
	public String creator_creationDate;
	public String doctype_lamguage;
	public String doctype_size;
	public String doctype_modifiedDate;
	public String doctype_creationDate;
	public String language_size;
	public String language_modifiedDate;
	public String language_creationDate;
	public String size_modifiedDate;
	public String size_creationDate;
	public String modifieddate_cerationDate;
	
	public String screenshotname = "facets2";

	/**
	 * 
	 * @param creator_doctype
	 * @param creator_language
	 * @param creator_size
	 * @param creator_modifiedDate
	 * @param creator_creationDate
	 * @param doctype_lamguage
	 * @param doctype_size
	 * @param doctype_modifiedDate
	 * @param doctype_creationDate
	 * @param language_size
	 * @param language_modifiedDate
	 * @param language_creationDate
	 * @param size_modifiedDate
	 * @param size_creationDate
	 * @param modifieddate_cerationDate
	 */
	public g_sensefymobile_iOS_facets3(String searchFile,String creator_doctype, String creator_language, String creator_size,
			String creator_modifiedDate, String creator_creationDate, String doctype_lamguage, String doctype_size,
			String doctype_modifiedDate, String doctype_creationDate, String language_size,
			String language_modifiedDate, String language_creationDate, String size_modifiedDate,
			String size_creationDate, String modifieddate_cerationDate) 
	{
		this.searchFile = searchFile;
		this.creator_doctype = creator_doctype;
		this.creator_language = creator_language;
		this.creator_size = creator_size;
		this.creator_modifiedDate = creator_modifiedDate;
		this.creator_creationDate = creator_creationDate;
		this.doctype_lamguage = doctype_lamguage;
		this.doctype_size = doctype_size;
		this.doctype_modifiedDate = doctype_modifiedDate;
		this.doctype_creationDate = doctype_creationDate;
		this.language_size = language_size;
		this.language_modifiedDate = language_modifiedDate;
		this.language_creationDate = language_creationDate;
		this.size_modifiedDate = size_modifiedDate;
		this.size_creationDate = size_creationDate;
		this.modifieddate_cerationDate = modifieddate_cerationDate;
	}
	
	@Parameters()
	public static Iterable<Object[]> data() throws IterableException 
	{
		LOGGER.info(TestCaseProperties.TEXT_TEST_PREPARING, "g_sensefymobile_iOS_facets3");

		return TestCaseValues.documentLibraryTestValues("g_sensefymobile_iOS_facets3");
	}
	
	@BeforeClass
    public static void beforeClass() throws IOException 
	{
		//String css = "div.content{ width: 1422px;}"    + "div.filters{ width:1422px;}";
        //extent.config().addCustomStyles(css);
        
		// Initialize ExtentReport(driver,NameofReport)
		Element.reportInitial(driver, className);
		// Define DocumentTitle of ExtentReport
        extent.config().documentTitle("Sensefy Mobile");
	        
        // Define ReportTitle of ExtentReport 
        extent.config().reportTitle("Factes Funtions in Sensefy - Part 3");
		        
        // Define ReportHeadLine of ExtentReport
        extent.config().reportHeadline("Factes Funtions in Sensefy - Part 3");    
     }
	
	@Test
	public void a1() throws IOException, InterruptedException
	{
		LOGGER.info("Check on Admin under the Creator and Plain Text under the DocumentType");
		extent.startTest("Check on Admin under the Creator and Plain Text under the DocumentType");
		
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
		Element.takescreenshot(driver,className,screenshotname+"1");
		Thread.sleep(3000);
		
		//check on the plain text
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[11]/UIAStaticText[1]")).click();
		System.out.println("Checked Plain Text");
		LOGGER.info("Checked Plain Text");
		Element.takescreenshot(driver,className,screenshotname+"2");
				
		//Navigate to the document in the Row 1
		WebElement gotoDocument = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]");
		gotoDocument.click();
		System.out.println("Navigated to the Document in the Row 1");
			
		//Checks the document in the 1st row
		WebElement getDocName1 = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]");
		String message = getDocName1.getAttribute("value");
		
		//Checks for the Document Name
		extent.log(LogStatus.INFO, "Verify the Document");
		if(message.equals(creator_doctype))
		{
			extent.log(LogStatus.INFO, "Expected Result : "+creator_doctype);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Pass : Successfully selected Admin under the Creator and Plain Text under the DocumentType");
			extent.log(LogStatus.PASS, "Pass : Successfully selected Admin under the Creator and Plain Text under the DocumentType");
			Element.takescreenshot(driver,className,screenshotname+"5");
		}
		else
		{
			extent.log(LogStatus.INFO, "Expected Rersult : "+creator_doctype);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Fail : Unsuccessfully  selected Admin under the Creator and Plain Text under the DocumentType");
			extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully selected Admin under the Creator and Plain Text under the DocumentType");
			Element.takescreenshot(driver,className,screenshotname+"6");
		}
		
		extent.log(LogStatus.INFO, "Navigate back to the Results");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);		

		//check on the PlainText
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[3]/UIAStaticText[1]")).click();
		System.out.println("Unchecked Plain Text");
		LOGGER.info("Unchecked Plain Text");
		Thread.sleep(3000);
		
		//uncheck on the admin
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]")).click();
		System.out.println("Unchecked Admin");
		LOGGER.info("Unchecked Admin");
		Thread.sleep(3000);		
	}
	
	@Test
	public void a2() throws IOException, InterruptedException
	{
		LOGGER.info("Check on Admin under the Creator and English under the Language");
		extent.startTest("Check on Admin under the Creator and English under the Language");
	
		//check on the admin
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]")).click();
		System.out.println("Checked Admin");
		LOGGER.info("Checked Admin");
		Element.takescreenshot(driver,className,screenshotname+"7");
		Thread.sleep(3000);
		
		//check on the English
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[14]/UIAStaticText[1]")).click();
		System.out.println("Checked English");
		LOGGER.info("Checked English");
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
		if(message.equals(creator_language))
		{
				extent.log(LogStatus.INFO, "Expected Result : "+creator_language);
				extent.log(LogStatus.INFO, "Current Result : "+message);
				LOGGER.info("Pass : Successfully selected Admin under the Creator and English under the Language");
				extent.log(LogStatus.PASS, "Pass : Successfully selected Admin under the Creator and English under the Language");
				Element.takescreenshot(driver,className,screenshotname+"9");
		}
		else
		{
				extent.log(LogStatus.INFO, "Expected Rersult : "+creator_language);
				extent.log(LogStatus.INFO, "Current Result : "+message);
				LOGGER.info("Fail : Unsuccessfully  selected Admin under the Creator and English under the Language");
				extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully selected Admin under the Creator and English under the Language");
				Element.takescreenshot(driver,className,screenshotname+"10");
		}
				
		extent.log(LogStatus.INFO, "Navigate back to the Results");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);		
		
		//uncheck on the English
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[11]/UIAStaticText[1]")).click();
		System.out.println("Unchecked English");
		LOGGER.info("Unchecked English");
		Thread.sleep(3000);
		
		//uncheck on the admin
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]")).click();
		System.out.println("Unchecked Admin");
		LOGGER.info("Unchecked Admin");
		Thread.sleep(3000);
	}
	//------ok
	@Test
	public void a3() throws IOException, InterruptedException
	{
		LOGGER.info("Check on Admin under the Creator and 0B-10MB under the Size");
		extent.startTest("Check on Admin under the Creator and 0B-10MB under the Size");
	
		//check on the admin
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]")).click();
		System.out.println("Checked Admin");
		LOGGER.info("Checked Admin");
		Element.takescreenshot(driver,className,screenshotname+"11");
		Thread.sleep(3000);
		
		//check on the 0B-10MB
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[18]/UIAStaticText[1]")).click();
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
		if(message.equals(creator_size))
		{
				extent.log(LogStatus.INFO, "Expected Result : "+creator_size);
				extent.log(LogStatus.INFO, "Current Result : "+message);
				LOGGER.info("Pass : Successfully selected Admin under the Creator and 0B-10MB under the Size");
				extent.log(LogStatus.PASS, "Pass : Successfully selected Admin under the Creator and 0B-10MB under the Size");
				Element.takescreenshot(driver,className,screenshotname+"13");
		}
		else
		{
				extent.log(LogStatus.INFO, "Expected Rersult : "+creator_size);
				extent.log(LogStatus.INFO, "Current Result : "+message);
				LOGGER.info("Fail : Unsuccessfully  selected Admin under the Creator and 0B-10MB under the Size");
				extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully selected Admin under the Creator and 0B-10MB under the Size");
				Element.takescreenshot(driver,className,screenshotname+"14");
		}
				
		extent.log(LogStatus.INFO, "Navigate back to the Results");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);		
		
		//uncheck on the 0B-10MB
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[18]/UIAStaticText[1]")).click();
		System.out.println("Unchecked 0B-10MB");
		LOGGER.info("Unchecked 0B-10MB");
		Thread.sleep(3000);
		
		//uncheck on the admin
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]")).click();
		System.out.println("Unchecked Admin");
		LOGGER.info("Unchecked Admin");
		Thread.sleep(3000);
	}
//*********** unavailable	
/*	@Test
	public void a4() throws InterruptedException, IOException
	{
		LOGGER.info("Check on Admin under the Creator and Last Month under the Last Modified Date");
		extent.startTest("Check on Admin under the Creator and Last Month under the Last Modified Date");
	
		//check on the admin
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]")).click();
		System.out.println("Checked Admin");
		LOGGER.info("Checked Admin");
		Element.takescreenshot(driver,className,screenshotname+"15");
		Thread.sleep(3000);
		
		//check on the Last Month
		driver.findElement(By.xpath(" //UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[17]/UIAStaticText[1]")).click();
		System.out.println("Checked Last Month");
		LOGGER.info("Checked Last Month");
		Element.takescreenshot(driver,className,screenshotname+"16");
			 
		//Navigate to the document in the Row 1
		WebElement gotoDocument = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]");
		gotoDocument.click();
		System.out.println("Navigated to the Document in the Row 1");
					
		//Checks the document in the 1st row
		WebElement getDocName1 = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]");
		String message = getDocName1.getAttribute("value");
			
		//Checks for the Document Name
		extent.log(LogStatus.INFO, "Verify the Document");
		if(message.equals(creator_modifiedDate))
		{
				extent.log(LogStatus.INFO, "Expected Result : "+creator_modifiedDate);
				extent.log(LogStatus.INFO, "Current Result : "+message);
				LOGGER.info("Pass : Successfully selected Admin under the Creator and Last Month under the Last Modified Date");
				extent.log(LogStatus.PASS, "Pass : Successfully selected Admin under the Creator and Last Month under the Last Modified Date");
				Element.takescreenshot(driver,className,screenshotname+"17");
		}
		else
		{
				extent.log(LogStatus.INFO, "Expected Rersult : "+creator_modifiedDate);
				extent.log(LogStatus.INFO, "Current Result : "+message);
				LOGGER.info("Fail : Unsuccessfully  selected Admin under the Creator and Last Month under the Last Modified Date");
				extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully selected Admin under the Creator and Last Month under the Last Modified Date");
				Element.takescreenshot(driver,className,screenshotname+"18");
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
		
		//uncheck on the admin
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]")).click();
		System.out.println("Unchecked Admin");
		LOGGER.info("Unchecked Admin");
		Thread.sleep(3000);
	}
	
/*	@Test
	public void a5() throws InterruptedException, IOException
	{
		LOGGER.info("Check on Admin under the Creator and Last Month under the Creation Date");
		extent.startTest("Check on Admin under the Creator and Last Month under the Creation Date");
	
		//check on the admin
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]")).click();
		System.out.println("Checked Admin");
		LOGGER.info("Checked Admin");
		Element.takescreenshot(driver,className,screenshotname+"19");
		Thread.sleep(3000);
		
		//check on the Last Month
		driver.findElement(By.xpath(" //UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[17]/UIAStaticText[1]")).click();
		System.out.println("Checked Last Month");
		LOGGER.info("Checked Last Month");
		Element.takescreenshot(driver,className,screenshotname+"20");
			 
		//Navigate to the document in the Row 1
		WebElement gotoDocument = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]");
		gotoDocument.click();
		System.out.println("Navigated to the Document in the Row 1");
					
		//Checks the document in the 1st row
		WebElement getDocName1 = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]");
		String message = getDocName1.getAttribute("value");
			
		//Checks for the Document Name
		extent.log(LogStatus.INFO, "Verify the Document");
		if(message.equals(creator_modifiedDate))
		{
				extent.log(LogStatus.INFO, "Expected Result : "+creator_modifiedDate);
				extent.log(LogStatus.INFO, "Current Result : "+message);
				LOGGER.info("Pass : Successfully selected Admin under the Creator and Last Month under the Creation Date");
				extent.log(LogStatus.PASS, "Pass : Successfully selected Admin under the Creator and Last Month under the Creation Date");
				Element.takescreenshot(driver,className,screenshotname+"21");
		}
		else
		{
				extent.log(LogStatus.INFO, "Expected Rersult : "+creator_modifiedDate);
				extent.log(LogStatus.INFO, "Current Result : "+message);
				LOGGER.info("Fail : Unsuccessfully  selected Admin under the Creator and Last Month under the Creation Date");
				extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully selected Admin under the Creator and Last Month under the Creation Date");
				Element.takescreenshot(driver,className,screenshotname+"22");
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
		
		//uncheck on the admin
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]")).click();
		System.out.println("Unchecked Admin");
		LOGGER.info("Unchecked Admin");
		Thread.sleep(3000);
	}*/
//*********************
	
	@Test
	public void b1() throws IOException, InterruptedException
	{
		LOGGER.info("Check on Plain Text under the Document Type and English under the Language");
		extent.startTest("Check on Plain Text under the Document Type and English under the Language");
	
		//check on the Plain Text
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[11]/UIAStaticText[1]")).click();
		System.out.println("Checked Plain Text");
		LOGGER.info("Checked Plain Text");
		Element.takescreenshot(driver,className,screenshotname+"23");
		Thread.sleep(3000);
		
		//check on the English
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[12]/UIAStaticText[1]")).click();
		System.out.println("Checked English");
		LOGGER.info("Checked English");
		Element.takescreenshot(driver,className,screenshotname+"24");
			 
		//Navigate to the document in the Row 1
		WebElement gotoDocument = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]");
		gotoDocument.click();
		System.out.println("Navigated to the Document in the Row 1");
					
		//Checks the document in the 1st row
		WebElement getDocName1 = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]");
		String message = getDocName1.getAttribute("value");
			
		//Checks for the Document Name
		extent.log(LogStatus.INFO, "Verify the Document");
		if(message.equals(doctype_lamguage))
		{
				extent.log(LogStatus.INFO, "Expected Result : "+doctype_lamguage);
				extent.log(LogStatus.INFO, "Current Result : "+message);
				LOGGER.info("Pass : Successfully selected Plain Text under the Document Type and English under the Language");
				extent.log(LogStatus.PASS, "Pass : Successfully selected Plain Text under the Document Type and English under the Language");
				Element.takescreenshot(driver,className,screenshotname+"25");
		}
		else
		{
				extent.log(LogStatus.INFO, "Expected Rersult : "+doctype_lamguage);
				extent.log(LogStatus.INFO, "Current Result : "+message);
				LOGGER.info("Fail : Unsuccessfully  selected Plain Text under the Document Type and English under the Language");
				extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully selected Plain Text under the Document Type and English under the Language");
				Element.takescreenshot(driver,className,screenshotname+"26");
		}
				
		extent.log(LogStatus.INFO, "Navigate back to the Results");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);		
		
		//uncheck on the English
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[8]/UIAStaticText[1]")).click();
		System.out.println("Unchecked English");
		LOGGER.info("Unchecked English");
		Thread.sleep(3000);
		
		//uncheck on the Plain Text
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[3]/UIAStaticText[1]")).click();
		System.out.println("Unchecked Plain Text");
		LOGGER.info("Unchecked Plain Text");
		Thread.sleep(3000);
	}
	
	@Test
	public void b2() throws IOException, InterruptedException
	{
		LOGGER.info("Check on Plain Text under the Document Type and OB-10MB under the Size");
		extent.startTest("Check on Plain Text under the Document Type and OB-10MB under the Size");
	
		//check on the Plain Text
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[11]/UIAStaticText[1]")).click();
		System.out.println("Checked Plain Text");
		LOGGER.info("Checked Plain Text");
		Element.takescreenshot(driver,className,screenshotname+"27");
		Thread.sleep(3000);
		
		//check on the OB-10MB
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[13]/UIAStaticText[1]")).click();
		System.out.println("Checked OB-10MB");
		LOGGER.info("Checked OB-10MB");
		Element.takescreenshot(driver,className,screenshotname+"28");
			 
		//Navigate to the document in the Row 1
		WebElement gotoDocument = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]");
		gotoDocument.click();
		System.out.println("Navigated to the Document in the Row 1");
					
		//Checks the document in the 1st row
		WebElement getDocName1 = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]");
		String message = getDocName1.getAttribute("value");
			
		//Checks for the Document Name
		extent.log(LogStatus.INFO, "Verify the Document");
		if(message.equals(doctype_size))
		{
				extent.log(LogStatus.INFO, "Expected Result : "+doctype_size);
				extent.log(LogStatus.INFO, "Current Result : "+message);
				LOGGER.info("Pass : Successfully selected Plain Text under the Document Type and OB-10MB under the Size");
				extent.log(LogStatus.PASS, "Pass : Successfully selected Plain Text under the Document Type and OB-10MB under the Size");
				Element.takescreenshot(driver,className,screenshotname+"29");
		}
		else
		{
				extent.log(LogStatus.INFO, "Expected Rersult : "+doctype_size);
				extent.log(LogStatus.INFO, "Current Result : "+message);
				LOGGER.info("Fail : Unsuccessfully  selected Plain Text under the Document Type and OB-10MB under the Size");
				extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully selected Plain Text under the Document Type and OB-10MB under the Size");
				Element.takescreenshot(driver,className,screenshotname+"30");
		}
				
		extent.log(LogStatus.INFO, "Navigate back to the Results");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);		
		
		//uncheck on the OB-10MB
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[13]/UIAStaticText[1]")).click();
		System.out.println("Unchecked OB-10MB");
		LOGGER.info("Unchecked OB-10MB");
		Thread.sleep(3000);
		
		//uncheck on the Plain Text
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[3]/UIAStaticText[1]")).click();
		System.out.println("Unchecked Plain Text");
		LOGGER.info("Unchecked Plain Text");
		Thread.sleep(3000);
	}
	
	@Test
	public void c1() throws IOException, InterruptedException
	{
		LOGGER.info("Check on English under the Language and OB-10MB under the Size");
		extent.startTest("Check on English under the Language and OB-10MB under the Size");
	
		//check on the English
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[20]/UIAStaticText[1]")).click();
		System.out.println("Checked English");
		LOGGER.info("Checked English");
		Element.takescreenshot(driver,className,screenshotname+"31");
		Thread.sleep(3000);
		
		//check on the OB-10MB
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[19]/UIAStaticText[1]")).click();
		System.out.println("Checked OB-10MB");
		LOGGER.info("Checked OB-10MB");
		Element.takescreenshot(driver,className,screenshotname+"32");
			 
		//Navigate to the document in the Row 1
		WebElement gotoDocument = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]");
		gotoDocument.click();
		System.out.println("Navigated to the Document in the Row 1");
					
		//Checks the document in the 1st row
		WebElement getDocName1 = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]");
		String message = getDocName1.getAttribute("value");
			
		//Checks for the Document Name
		extent.log(LogStatus.INFO, "Verify the Document");
		if(message.equals(language_size))
		{
				extent.log(LogStatus.INFO, "Expected Result : "+language_size);
				extent.log(LogStatus.INFO, "Current Result : "+message);
				LOGGER.info("Pass : Successfully selected English under the Language and OB-10MB under the Size");
				extent.log(LogStatus.PASS, "Pass : Successfully selected English under the Language and OB-10MB under the Size");
				Element.takescreenshot(driver,className,screenshotname+"33");
		}
		else
		{
				extent.log(LogStatus.INFO, "Expected Rersult : "+language_size);
				extent.log(LogStatus.INFO, "Current Result : "+message);
				LOGGER.info("Fail : Unsuccessfully  selected English under the Language and OB-10MB under the Size");
				extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully selected English under the Language and OB-10MB under the Size");
				Element.takescreenshot(driver,className,screenshotname+"34");
		}
				
		extent.log(LogStatus.INFO, "Navigate back to the Results");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);		
		
		//uncheck on the OB-10MB
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[19]/UIAStaticText[1]")).click();
		System.out.println("Unchecked OB-10MB");
		LOGGER.info("Unchecked OB-10MB");
		Thread.sleep(3000);
		
		//uncheck on the English
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[14]/UIAStaticText[1]")).click();
		System.out.println("Unchecked English");
		LOGGER.info("Unchecked English");
		Thread.sleep(3000);
	}
	
	
	
	
	
	
	
	
}
