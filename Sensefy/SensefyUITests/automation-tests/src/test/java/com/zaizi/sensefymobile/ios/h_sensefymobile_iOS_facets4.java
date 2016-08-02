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
import org.omg.CORBA.PUBLIC_MEMBER;
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
public class h_sensefymobile_iOS_facets4 
{
public static final Logger LOGGER = LogManager.getLogger(h_sensefymobile_iOS_facets4.class.getName());
	
	public static final ExtentReports extent = ExtentReports.get(h_sensefymobile_iOS_facets4.class);
	
	public static String className=h_sensefymobile_iOS_facets4.class.getSimpleName();
	
	static AppiumDriver driver;
	
	public String searchFile;
	public String cdl_category;
	public String cds_category;
	public String dls_category;
	public String cls_category;
	public String cdls_category;
	
	public String screenshotname = "facets3";

	public h_sensefymobile_iOS_facets4(String searchFile, String cdl_category, String cds_category, String dls_category,
			String cls_category, String cdls_category) 
	{
		this.searchFile = searchFile;
		this.cdl_category = cdl_category;
		this.cds_category = cds_category;
		this.dls_category = dls_category;
		this.cls_category = cls_category;
		this.cdls_category = cdls_category;
	}
	
	@Parameters()
	public static Iterable<Object[]> data() throws IterableException 
	{
		LOGGER.info(TestCaseProperties.TEXT_TEST_PREPARING, "h_sensefymobile_iOS_facets4");

		return TestCaseValues.documentLibraryTestValues("h_sensefymobile_iOS_facets4");
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
        extent.config().reportTitle("Factes Funtions in Sensefy - Part 4");
		        
        // Define ReportHeadLine of ExtentReport
        extent.config().reportHeadline("Factes Funtions in Sensefy - Part 4");    
     }
	
	@Test
	public void a() throws IOException, InterruptedException
	{
		LOGGER.info("Check on Admin under the Creator, Plain Text under the DocumentType and English under the Language");
		extent.startTest("Check on Admin under the Creator, Plain Text under the DocumentType and English under the Language");
		
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
		Thread.sleep(3000);
		
		//check on the English
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[6]/UIAStaticText[1]")).click();
		System.out.println("Checked English");
		LOGGER.info("Checked English");
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
		if(message.equals(cdl_category))
		{
			extent.log(LogStatus.INFO, "Expected Result : "+cdl_category);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Pass : Successfully selected Admin under the Creator, Plain Text under the DocumentType and English under the Language");
			extent.log(LogStatus.PASS, "Pass : Successfully selected Admin under the Creator, Plain Text under the DocumentType and English under the Language");
			Element.takescreenshot(driver,className,screenshotname+"4");
		}
		else
		{
			extent.log(LogStatus.INFO, "Expected Rersult : "+cdl_category);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Fail : Unsuccessfully  selected Admin under the Creator, Plain Text under the DocumentType and English under the Language");
			extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully selected Admin under the Creator, Plain Text under the DocumentType and English under the Language");
			Element.takescreenshot(driver,className,screenshotname+"5");
		}
		
		extent.log(LogStatus.INFO, "Navigate back to the Results");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);		

		//check on the English	
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[5]/UIAStaticText[1]")).click();
		System.out.println("Unchecked English");
		LOGGER.info("Unchecked English");
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
	public void b() throws IOException, InterruptedException
	{
		LOGGER.info("Check on Admin under the Creator, Plain Text under the DocumentType and 0B-10MB under the Size");
		extent.startTest("Check on Admin under the Creator, Plain Text under the DocumentType and 0B-10MB under the Size");
			
		//check on the admin
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]")).click();
		System.out.println("Checked Admin");
		LOGGER.info("Checked Admin");
		Element.takescreenshot(driver,className,screenshotname+"6");
		Thread.sleep(3000);
		
		//check on the plain text
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[11]/UIAStaticText[1]")).click();
		System.out.println("Checked Plain Text");
		LOGGER.info("Checked Plain Text");
		Element.takescreenshot(driver,className,screenshotname+"7");
		Thread.sleep(3000);
		
		//check on the 0B-10MB
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[7]/UIAStaticText[1]")).click();
		System.out.println("Checked 0B-10MB");
		LOGGER.info("Checked 0B-10MB");
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
		if(message.equals(cds_category))
		{
			extent.log(LogStatus.INFO, "Expected Result : "+cds_category);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Pass : Successfully selected Admin under the Creator, Plain Text under the DocumentType and 0B-10MB under the Size");
			extent.log(LogStatus.PASS, "Pass : Successfully selected Admin under the Creator, Plain Text under the DocumentType and 0B-10MB under the Size");
			Element.takescreenshot(driver,className,screenshotname+"9");
		}
		else
		{
			extent.log(LogStatus.INFO, "Expected Rersult : "+cds_category);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Fail : Unsuccessfully  selected Admin under the Creator, Plain Text under the DocumentType and 0B-10MB under the Size");
			extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully selected Admin under the Creator, Plain Text under the DocumentType and 0B-10MB under the Size");
			Element.takescreenshot(driver,className,screenshotname+"10");
		}
		
		extent.log(LogStatus.INFO, "Navigate back to the Results");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);		

		//check on the 0B-10MB	
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[7]/UIAStaticText[1]")).click();
		System.out.println("Unchecked 0B-10MB");
		LOGGER.info("Unchecked 0B-10MB");
		Thread.sleep(3000);
		
		//check on the PlainText
		driver.findElement(By.xpath(" //UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[3]/UIAStaticText[1]")).click();
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
	public void c() throws IOException, InterruptedException
	{
		LOGGER.info("Plain Text under the DocumentType, English under the Language and 0B-10MB under the Size");
		extent.startTest("Plain Text under the DocumentType, English under the Language and 0B-10MB under the Size");
			
		//check on the plain text
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[11]/UIAStaticText[1]")).click();
		System.out.println("Checked plain text");
		LOGGER.info("Checked plain text");
		Element.takescreenshot(driver,className,screenshotname+"11");
		Thread.sleep(3000);
		
		//check on the English
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[12]/UIAStaticText[1]")).click();
		System.out.println("Checked English");
		LOGGER.info("Checked English");
		Element.takescreenshot(driver,className,screenshotname+"12");
		Thread.sleep(3000);
		
		//check on the 0B-10MB
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[9]/UIAStaticText[1]")).click();
		System.out.println("Checked 0B-10MB");
		LOGGER.info("Checked 0B-10MB");
		Element.takescreenshot(driver,className,screenshotname+"13");
				
		//Navigate to the document in the Row 1
		WebElement gotoDocument = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]");
		gotoDocument.click();
		System.out.println("Navigated to the Document in the Row 1");
			
		//Checks the document in the 1st row
		WebElement getDocName1 = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]");
		String message = getDocName1.getAttribute("value");
		
		//Checks for the Document Name
		extent.log(LogStatus.INFO, "Verify the Document");
		if(message.equals(dls_category))
		{
			extent.log(LogStatus.INFO, "Expected Result : "+dls_category);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Pass : Successfully selected Plain Text under the DocumentType, English under the Language and 0B-10MB under the Size");
			extent.log(LogStatus.PASS, "Pass : Successfully selected Plain Text under the DocumentType, English under the Language and 0B-10MB under the Size");
			Element.takescreenshot(driver,className,screenshotname+"14");
		}
		else
		{
			extent.log(LogStatus.INFO, "Expected Rersult : "+dls_category);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Fail : Unsuccessfully  selected Plain Text under the DocumentType, English under the Language and 0B-10MB under the Size");
			extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully selected Plain Text under the DocumentType, English under the Language and 0B-10MB under the Size");
			Element.takescreenshot(driver,className,screenshotname+"15");
		}
		
		extent.log(LogStatus.INFO, "Navigate back to the Results");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);		

		//check on the 0B-10MB	
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[9]/UIAStaticText[1]")).click();
		System.out.println("Unchecked 0B-10MB");
		LOGGER.info("Unchecked 0B-10MB");
		Thread.sleep(3000);
		
		//check on the English
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
	/*
	@Test
	public void d() throws IOException, InterruptedException
	{
		LOGGER.info("Check on Admin under the Creator, English under the Language and 0B-10MB under the Size");
		extent.startTest("Check on Admin under the Creator, English under the Language and 0B-10MB under the Size");
			
		//check on the admin
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]")).click();
		System.out.println("Checked admin");
		LOGGER.info("Checked admin");
		Element.takescreenshot(driver,className,screenshotname+"16");
		Thread.sleep(3000);
		
		//check on the English
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[14]/UIAStaticText[1]")).click();
		System.out.println("Checked English");
		LOGGER.info("Checked English");
		Element.takescreenshot(driver,className,screenshotname+"17");
		Thread.sleep(3000);
		
		//check on the 0B-10MB
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[14]/UIAStaticText[1]")).click();
		System.out.println("Checked 0B-10MB");
		LOGGER.info("Checked 0B-10MB");
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
		if(message.equals(cls_category))
		{
			extent.log(LogStatus.INFO, "Expected Result : "+cls_category);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Pass : Successfully selected Admin under the Creator, English under the Language and 0B-10MB under the Size");
			extent.log(LogStatus.PASS, "Pass : Successfully selected Admin under the Creator, English under the Language and 0B-10MB under the Size");
			Element.takescreenshot(driver,className,screenshotname+"19");
		}
		else
		{
			extent.log(LogStatus.INFO, "Expected Rersult : "+cls_category);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Fail : Unsuccessfully  selected Admin under the Creator, English under the Language and 0B-10MB under the Size");
			extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully selected Admin under the Creator, English under the Language and 0B-10MB under the Size");
			Element.takescreenshot(driver,className,screenshotname+"20");
		}
		
		extent.log(LogStatus.INFO, "Navigate back to the Results");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);		

		//check on the 0B-10MB	
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[14]/UIAStaticText[1]")).click();
		System.out.println("Unchecked 0B-10MB");
		LOGGER.info("Unchecked 0B-10MB");
		Thread.sleep(3000);
		
		//check on the English
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[12]/UIAStaticText[1]")).click();
		System.out.println("Unchecked English");
		LOGGER.info("Unchecked English");
		Thread.sleep(3000);
		
		//uncheck on the admin
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]")).click();
		System.out.println("Unchecked admin");
		LOGGER.info("Unchecked admin");
		Thread.sleep(3000);		
	}
	
	@Test
	public void e() throws IOException, InterruptedException
	{
		LOGGER.info("Check on Admin under the Creator, Plain Text under the DocumentType, English under the Language and 0B-10MB under Size");
		extent.startTest("Check on Admin under the Creator, Plain Text under the DocumentType, English under the Language and 0B-10MB under Size");
		
		//check on the admin
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]")).click();
		System.out.println("Checked Admin");
		LOGGER.info("Checked Admin");
		Element.takescreenshot(driver,className,screenshotname+"21");
		Thread.sleep(3000);
		
		//check on the plain text
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[11]/UIAStaticText[1]")).click();
		System.out.println("Checked Plain Text");
		LOGGER.info("Checked Plain Text");
		Element.takescreenshot(driver,className,screenshotname+"22");
		Thread.sleep(3000);
		
		//check on the English
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[6]/UIAStaticText[1]")).click();
		System.out.println("Checked English");
		LOGGER.info("Checked English");
		Element.takescreenshot(driver,className,screenshotname+"23");
		Thread.sleep(3000);
		
		//check on the 0B-10MB
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[7]/UIAStaticText[1]")).click();
		System.out.println("Checked 0B-10MB");
		LOGGER.info("Checked 0B-10MB");
		Element.takescreenshot(driver,className,screenshotname+"24");
		Thread.sleep(3000);
				
		//Navigate to the document in the Row 1
		WebElement gotoDocument = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]");
		gotoDocument.click();
		System.out.println("Navigated to the Document in the Row 1");
			
		//Checks the document in the 1st row
		WebElement getDocName1 = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]");
		String message = getDocName1.getAttribute("value");
		
		//Checks for the Document Name
		extent.log(LogStatus.INFO, "Verify the Document");
		if(message.equals(cdls_category))
		{
			extent.log(LogStatus.INFO, "Expected Result : "+cdls_category);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Pass : Successfully selected Admin under the Creator, Plain Text under the DocumentType, English under the Language and 0B-10MB under Size");
			extent.log(LogStatus.PASS, "Pass : Successfully selected Admin under the Creator, Plain Text under the DocumentType, English under the Language and 0B-10MB under Size");
			Element.takescreenshot(driver,className,screenshotname+"25");
		}
		else
		{
			extent.log(LogStatus.INFO, "Expected Rersult : "+cdls_category);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Fail : Unsuccessfully  selected Admin under the Creator, Plain Text under the DocumentType, English under the Language and 0B-10MB under Size");
			extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully selected Admin under the Creator, Plain Text under the DocumentType, English under the Language and 0B-10MB under Size");
			Element.takescreenshot(driver,className,screenshotname+"26");
		}
		
		extent.log(LogStatus.INFO, "Navigate back to the Results");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);		

		//check on the 0B-10MB	
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[7]/UIAStaticText[1]")).click();
		System.out.println("Unchecked 0B-10MB");
		LOGGER.info("Unchecked 0B-10MB");
		Thread.sleep(3000);
				
		//check on the English	
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[6]/UIAStaticText[1]")).click();
		System.out.println("Unchecked English");
		LOGGER.info("Unchecked English");
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
	}*/
	
	
}
