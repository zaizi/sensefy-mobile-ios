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
 * 
 * @author ljayasinghe
 *
 */

@RunWith(value = Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class d_sensefymobile_iOS_sortby 
{
	public static final Logger LOGGER = LogManager.getLogger(d_sensefymobile_iOS_sortby.class.getName());
	
	public static final ExtentReports extent = ExtentReports.get(d_sensefymobile_iOS_sortby.class);
	
	public static String className=d_sensefymobile_iOS_sortby.class.getSimpleName();

	
	static AppiumDriver driver;
	
	public String searchFile;
	public String relevanceDoc;
	public String nameDoc;
	public String titleDoc;
	public String created_dateDoc;
	public String modified_dateDoc;
	public String creatorDoc;
	public String modifierDoc;
	public String screenshotname = "sortby";
	
	/**
	 * 
	 * @param searchFile
	 * @param relevanceDoc
	 * @param nameDoc
	 * @param titleDoc
	 * @param created_dateDoc
	 * @param modified_dateDoc
	 * @param creatorDoc
	 * @param modifierDoc
	 */
	public d_sensefymobile_iOS_sortby(String searchFile,String relevanceDoc, String nameDoc, String titleDoc, String created_dateDoc,
			String modified_dateDoc, String creatorDoc, String modifierDoc) 
	{
		this.searchFile = searchFile;
		this.relevanceDoc = relevanceDoc;
		this.nameDoc = nameDoc;
		this.titleDoc = titleDoc;
		this.created_dateDoc = created_dateDoc;
		this.modified_dateDoc = modified_dateDoc;
		this.creatorDoc = creatorDoc;
		this.modifierDoc = modifierDoc;
	}
	
	@Parameters()
	public static Iterable<Object[]> data() throws IterableException 
	{
		LOGGER.info(TestCaseProperties.TEXT_TEST_PREPARING, "d_sensefymobile_iOS_sortby");

		return TestCaseValues.documentLibraryTestValues("d_sensefymobile_iOS_sortby");
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
        extent.config().reportTitle("Sort By Funtion in Sensefy");
		        
        // Define ReportHeadLine of ExtentReport
        extent.config().reportHeadline("Sort By Funtion in Sensefy");    
     }
	
	@Test
	public void a() throws InterruptedException, IOException
	{
		LOGGER.info("Checks for the Sort Button availability and Default : Relevance");
		extent.startTest("Checks for the Sort Button availability and Default : Relevance");
		
		driver=(AppiumDriver) TestCaseProperties.getWebDriver();
		
		driver.findElement(By.xpath("//UIASearchBar")).sendKeys(searchFile+"\n");
		
		extent.log(LogStatus.INFO, "Search the File "+searchFile);
		System.out.println(searchFile);
	
		//Checks whether the Element is present
		extent.log(LogStatus.INFO, "Verify whether the SortBy button is available");
		if(Button.isElementPresent(driver, By.xpath("//UIAWindow/UIAToolbar[2]/UIAButton[1]")))
		{
			 LOGGER.info("Pass : Sort By Button is Available");
			 extent.log(LogStatus.PASS, "Pass : Sort By Button is Available");
			 Element.takescreenshot(driver,className,screenshotname+"1");
		}
		else
		{
			 LOGGER.info("Fail : Sort By Button is not available");
			 extent.log(LogStatus.FAIL, "Fail : Sort By Button is not available");
			 Element.takescreenshot(driver,className,screenshotname+"2");
		}
		
		//Click on the Sorby Button
		driver.findElement(By.xpath("//UIAWindow/UIAToolbar[2]/UIAButton[1]")).click();
		System.out.println("Clicked in the Sort By Button");
		
		WebElement getDefaultName = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAPopover[1]/UIAActionSheet[1]/UIAScrollView[1]/UIAStaticText[1]"));
		String message = getDefaultName.getAttribute("value");
		String defaultname = "Sorted By : Releavance";
		
		//Checks whether the Button is Relevance by Default	
		extent.log(LogStatus.INFO, "Verify whether the Default is Relevance in the SortBy Button");
		if(message.equals(defaultname))
		{
			 LOGGER.info("Pass : Sorted By : Releavance is by Default.");
			 extent.log(LogStatus.PASS, "Pass : Sorted By : Releavance is by Default.");
			 Element.takescreenshot(driver,className,screenshotname+"3");
		}
		else
		{
			 LOGGER.info("Fail : Sorted By : Releavance is not by Default.");
			 extent.log(LogStatus.FAIL, "Fail : Sorted By : Releavance is not by Default.");
			 Element.takescreenshot(driver,className,screenshotname+"4");
		}
		
		//Click on the Sorby Button
		driver.findElement(By.xpath("//UIAWindow/UIAToolbar[2]/UIAButton[1]")).click();
		System.out.println("Clicked on the Sort by Button");
		
	}
	
	@Test
	public void b() throws InterruptedException, IOException
	{
		LOGGER.info("Checks for the Sort by Relevance");
		extent.startTest("Checks for the Sort by Relevance");
		
		//Click on the Sorby Button
		driver.findElement(By.xpath("//UIAWindow/UIAToolbar[2]/UIAButton[1]")).click();
		System.out.println("Clicked on the Sort by Button");
		
		//Click on the Relevance
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAPopover[1]/UIAActionSheet[1]/UIACollectionView[1]/UIACollectionCell[1]")).click();
		System.out.println("Clicked on Relevance");
		extent.log(LogStatus.INFO, "Clicked on Relevance");
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
		if(message.equals(relevanceDoc))
		{
			extent.log(LogStatus.INFO, "Expected Rersult : "+relevanceDoc);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Pass : Successfully sorted by Relevance");
			extent.log(LogStatus.PASS, "Pass : Successfully sorted by Relevance");
			Element.takescreenshot(driver,className,screenshotname+"5");
		}
		else
		{
			extent.log(LogStatus.INFO, "Expected Rersult : "+relevanceDoc);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Fail : Unsuccessfully sorted by Relevance");
			extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully sorted by Relevance");
			Element.takescreenshot(driver,className,screenshotname+"6");
		}
		
		extent.log(LogStatus.INFO, "Navigate back to the Results");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);		
	}
	
	
	@Test
	public void c() throws InterruptedException, IOException
	{
		LOGGER.info("Checks for the Sort by Name");
		extent.startTest("Checks for the Sort by Name");
		
		//Click on the Sorby Button
		driver.findElement(By.xpath("//UIAWindow/UIAToolbar[2]/UIAButton[1]")).click();
		System.out.println("Clicked on the Sort by Button");
				
		//Click on the Name
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAPopover[1]/UIAActionSheet[1]/UIACollectionView[1]/UIACollectionCell[2]")).click();
		System.out.println("Clicked on Name");
		extent.log(LogStatus.INFO, "Clicked on Name");
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
		if(message.equals(nameDoc))
		{
			extent.log(LogStatus.INFO, "Expected Rersult : "+nameDoc);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Pass : Successfully sorted by Name");
			extent.log(LogStatus.PASS, "Pass : Successfully sorted by Name");
			Element.takescreenshot(driver,className,screenshotname+"7");
		}
		else
		{
			extent.log(LogStatus.INFO, "Expected Rersult : "+nameDoc);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Fail : Unsuccessfully sorted by Name");
			extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully sorted by Name");
			Element.takescreenshot(driver,className,screenshotname+"8");
		}
				
		extent.log(LogStatus.INFO, "Navigate back to the Results");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);		
		
	}
	
	@Test
	public void d() throws InterruptedException, IOException
	{
		LOGGER.info("Checks for the Sort by Title");
		extent.startTest("Checks for the Sort by Title");
		
		//Click on the Sorby Button
		driver.findElement(By.xpath("//UIAWindow/UIAToolbar[2]/UIAButton[1]")).click();
		System.out.println("Clicked on the Sort by Button");
				
		//Click on the Title
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAPopover[1]/UIAActionSheet[1]/UIACollectionView[1]/UIACollectionCell[3]")).click();
		System.out.println("Clicked on Title");
		extent.log(LogStatus.INFO, "Clicked on Title");
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
		if(message.equals(titleDoc))
		{
			extent.log(LogStatus.INFO, "Expected Rersult : "+titleDoc);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Pass : Successfully sorted by Title");
			extent.log(LogStatus.PASS, "Pass : Successfully sorted by Title");
			Element.takescreenshot(driver,className,screenshotname+"9");
		}
		else
		{
			extent.log(LogStatus.INFO, "Expected Rersult : "+titleDoc);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Fail : Unsuccessfully sorted by Title");
			extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully sorted by Title");
			Element.takescreenshot(driver,className,screenshotname+"10");
		}
			
		extent.log(LogStatus.INFO, "Navigate back to the Results");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);				
	}
//----------- 2016/02/25--------	
	@Test
	public void e() throws InterruptedException, IOException
	{
		LOGGER.info("Checks for the Sort by Created Date");
		extent.startTest("Checks for the Sort by Created Date");
		
		//Click on the Sorby Button
		driver.findElement(By.xpath("//UIAWindow/UIAToolbar[2]/UIAButton[1]")).click();
		System.out.println("Clicked on the Sort by Button");
				
		//Click on the Title
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAPopover[1]/UIAActionSheet[1]/UIACollectionView[1]/UIACollectionCell[4]")).click();
		System.out.println("Clicked on Created Date");
		extent.log(LogStatus.INFO, "Clicked on Created Date");
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
		if(message.equals(created_dateDoc))
		{
			extent.log(LogStatus.INFO, "Expected Rersult : "+created_dateDoc);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Pass : Successfully sorted by  Created Date");
			extent.log(LogStatus.PASS, "Pass : Successfully sorted by Title");
			Element.takescreenshot(driver,className,screenshotname+"11");
			
		}
		else
		{
			extent.log(LogStatus.INFO, "Expected Rersult : "+created_dateDoc);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Fail : Unsuccessfully sorted by  Created Date");
			extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully sorted by  Created Date");
			Element.takescreenshot(driver,className,screenshotname+"12");
			
		}
		
		extent.log(LogStatus.INFO, "Navigate back to the Results");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);		
		
	}
	
	@Test
	public void f() throws InterruptedException, IOException
	{
		LOGGER.info("Checks for the Sort by Modified Date");
		extent.startTest("Checks for the Sort by Modified Date");
		
		//Click on the Sorby Button
		driver.findElement(By.xpath("//UIAWindow/UIAToolbar[2]/UIAButton[1]")).click();
		System.out.println("Clicked on the Sort by Button");
				
		//Click on the Title
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAPopover[1]/UIAActionSheet[1]/UIACollectionView[1]/UIACollectionCell[5]")).click();
		System.out.println("Clicked on Modified Date");
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
		if(message.equals(modified_dateDoc))
		{
			extent.log(LogStatus.INFO, "Expected Rersult : "+modified_dateDoc);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Pass : Successfully sorted by Modified Date");
			extent.log(LogStatus.PASS, "Pass : Successfully sorted by Modified Date");
			Element.takescreenshot(driver,className,screenshotname+"13");
		}
		else
		{
			extent.log(LogStatus.INFO, "Expected Rersult : "+modified_dateDoc);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Fail : Unsuccessfully sorted by Modified Date");
			extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully sorted by Modified Date");
			Element.takescreenshot(driver,className,screenshotname+"14");
		}
			
		extent.log(LogStatus.INFO, "Navigate back to the Results");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);		
	}
	
	@Test
	public void g() throws InterruptedException, IOException
	{
		LOGGER.info("Checks for the Sort by Creator");
		extent.startTest("Checks for the Sort by Creator");
		
		//Click on the Sorby Button
		driver.findElement(By.xpath("//UIAWindow/UIAToolbar[2]/UIAButton[1]")).click();
		System.out.println("Clicked on the Sort by Button");
				
		//Click on the Title
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAPopover[1]/UIAActionSheet[1]/UIACollectionView[1]/UIACollectionCell[6]")).click();
		System.out.println("Clicked on Creator");
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
		if(message.equals(creatorDoc))
		{
			extent.log(LogStatus.INFO, "Expected Rersult : "+creatorDoc);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Pass : Successfully sorted by Creator");
			extent.log(LogStatus.PASS, "Pass : Successfully sorted by Creator");
			Element.takescreenshot(driver,className,screenshotname+"15");
		}
		else
		{
			extent.log(LogStatus.INFO, "Expected Rersult : "+creatorDoc);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Fail : Unsuccessfully sorted by Creator");
			extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully sorted by Creator");
			Element.takescreenshot(driver,className,screenshotname+"16");
		}
		
		extent.log(LogStatus.INFO, "Navigate back to the Results");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);		
	}
	
	@Test
	public void h() throws InterruptedException, IOException
	{
		LOGGER.info("Checks for the Sort by Modifier");
		extent.startTest("Checks for the Sort by Modifier");
		
		//Click on the Sorby Button
		driver.findElement(By.xpath("//UIAWindow/UIAToolbar[2]/UIAButton[1]")).click();
		System.out.println("Clicked on the Sort by Button");
				
		//Click on the Title
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAPopover[1]/UIAActionSheet[1]/UIACollectionView[1]/UIACollectionCell[7]")).click();
		System.out.println("Clicked on Modifier");
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
		if(message.equals(modifierDoc))
		{
			extent.log(LogStatus.INFO, "Expected Rersult : "+modifierDoc);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Pass : Successfully sorted by Modifier");
			extent.log(LogStatus.PASS, "Pass : Successfully sorted by Modifier");
			Element.takescreenshot(driver,className,screenshotname+"17");
		}
		else
		{
			extent.log(LogStatus.INFO, "Expected Rersult : "+modifierDoc);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Fail : Unsuccessfully sorted by Modifier");
			extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully sorted by Modifier");
			Element.takescreenshot(driver,className,screenshotname+"8");
		}
				
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);		
	}
	
	
}
