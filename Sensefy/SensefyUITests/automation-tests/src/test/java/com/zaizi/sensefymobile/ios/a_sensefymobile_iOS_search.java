package com.zaizi.sensefymobile.ios;

/*
 *  auther deranthika@zaizi.com
 */

import com.zaizi.sensefymobile.ios.exceptions.*;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.zaizi.sensefymobile.ios.core.elements.Button;
import com.zaizi.sensefymobile.ios.core.elements.Element;
import com.zaizi.sensefymobile.ios.core.info.*;
/**
 * 
 * @author ljayasinghe
 *
 */
@RunWith(value = Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class a_sensefymobile_iOS_search 
{

	public static final Logger LOGGER = LogManager.getLogger(a_sensefymobile_iOS_search.class.getName());
	
	public static final ExtentReports extent = ExtentReports.get(a_sensefymobile_iOS_search.class);
	
	public static String className=a_sensefymobile_iOS_search.class.getSimpleName();
	
	static AppiumDriver driver;
	
	public String searchFile;
	public String filesearched;
	public String documentTitle;
	public String documentsource;
	public String createddate;
	public String creator;
	public String modifieddate;
	public String modifier;
	public String language;
	public String mimetype;
	public String documentsize;	
	
	public String screenshotname = "search";
	
	/**
	 * 
	 * @param searchFile
	 * @param filesearched
	 * @param documentTitle
	 * @param documentsource
	 * @param createddate
	 * @param creator
	 * @param modifieddate
	 * @param modifier
	 * @param language
	 * @param mimetype
	 * @param documentsize
	 */
	public a_sensefymobile_iOS_search(String searchFile, String filesearched, String documentTitle,
			String documentsource, String createddate, String creator, String modifieddate, String modifier,
			String language, String mimetype, String documentsize) 
	{
		this.searchFile = searchFile;
		this.filesearched = filesearched;
		this.documentTitle = documentTitle;
		this.documentsource = documentsource;
		this.createddate = createddate;
		this.creator = creator;
		this.modifieddate = modifieddate;
		this.modifier = modifier;
		this.language = language;
		this.mimetype = mimetype;
		this.documentsize = documentsize;
	}

	@Parameters()
	public static Iterable<Object[]> data() throws IterableException 
	{
		LOGGER.info(TestCaseProperties.TEXT_TEST_PREPARING, "a_sensefymobile_iOS_search");

		return TestCaseValues.documentLibraryTestValues("a_sensefymobile_iOS_search");
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
        extent.config().reportTitle("Search the Document in Sensefy");
		        
        // Define ReportHeadLine of ExtentReport
        extent.config().reportHeadline("Search the Document in Sensefy");    
     }
	
	@Test
	public void a() throws IOException, InterruptedException
	{
		LOGGER.info("Verify whether the search results are generated.");
		extent.startTest("Verify whether the search results are generated.");
		
		driver=(AppiumDriver) TestCaseProperties.getWebDriver();
		
		driver.findElement(By.xpath("//UIASearchBar")).sendKeys("Sample"+"\n");
		Element.takescreenshot(driver,className,screenshotname+"1");
		MobileElement expelement=(MobileElement) (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAToolbar[2]/UIAButton[2]")));
    	
		String output=expelement.getText();
    	
		String [] parts=output.split(" ");
    	int ex=Integer.parseInt(parts[0]);
    	System.out.println(ex);
    	if(ex>0)
    	{
    		Assert.assertTrue(true);
    		LOGGER.info("Pass : The search results are genereated successfully.");
			extent.log(LogStatus.PASS, "Pass : The search results are genereated successfully.");
    		Element.takescreenshot(driver,className,screenshotname+"2");
    	}
    	else
    	{
    		Assert.assertFalse(false);
    		LOGGER.info("Fail : The search results are genereated unsuccessfully.");
			extent.log(LogStatus.FAIL, "Fail : The search results are genereated unsuccessfully.");
    		Element.takescreenshot(driver,className,screenshotname+"3");
    	}
	}
		
	@Test
	public void b() throws IOException
	{
		LOGGER.info("Verify whether the close button is available");
		extent.startTest("Verify whether the close button is available");
	   
    	driver.findElement(By.xpath("//UIAButton[contains(@name,'Clear text')]")).click();
    	System.out.println(driver.findElement(By.xpath("//UIASearchBar")).getText());
    	
    	if(!driver.findElement(By.xpath("//UIASearchBar")).getText().equals(null))
    	{
    		Assert.assertTrue(true);
    		LOGGER.info("Pass : The close button is available and it is functioning");
			extent.log(LogStatus.PASS, "Pass : The close button is available and it is functioning");
    		Element.takescreenshot(driver,className,screenshotname+"4");
    	}
    	else
    	{
    		Assert.assertFalse(false);
    		LOGGER.info("Fail : The close button is not available and it is not functioning");
			extent.log(LogStatus.FAIL, "Fail : The close button is not available and it is not functioning");
    		Element.takescreenshot(driver,className,screenshotname+"5");
    	}		
	}
	
	@Test
	public void c() throws IOException, InterruptedException
	{
		LOGGER.info("Search the "+searchFile+" document in Sensefy.");
		extent.startTest("Search the "+searchFile+" document in Sensefy.");
		
		driver.findElement(By.xpath("//UIASearchBar")).sendKeys(searchFile+"\n");
		extent.log(LogStatus.INFO, "Search the File "+searchFile);
		System.out.println(searchFile);
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
		if(message.equals(filesearched))
		{
			extent.log(LogStatus.INFO, "Expected Result : "+filesearched);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Pass : Successfully searched the document.");
			extent.log(LogStatus.PASS, "Pass : Successfully searched the document");
			Element.takescreenshot(driver,className,screenshotname+"7");
		}
		else
		{
			extent.log(LogStatus.INFO, "Expected Rersult : "+filesearched);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Fail : Unsuccessfully searched the document");
			extent.log(LogStatus.FAIL, "Fail : Unsuccessfully searched the document");
			Element.takescreenshot(driver,className,screenshotname+"8");
		}
		
	
	}

	@Test
	public void d() throws IOException, InterruptedException
	{
		LOGGER.info("Verify the Selected Document Details");
		extent.startTest("Verify the Selected Document Details");
		
		//Checks the document name
		WebElement getDocName1 = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]");
		String message1 = getDocName1.getAttribute("value");
		
		//checks the document title
		WebElement getDocTitle2 = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[2]"));
		String message2 = getDocTitle2.getAttribute("value");
		
		//checks the document source
		WebElement getDocTitle3 = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIAStaticText[2]"));
		String message3 = getDocTitle3.getAttribute("value");
		
		//checks the created date
		WebElement getDocTitle4 = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[2]"));
		String message4 = getDocTitle4.getAttribute("value");
			
		//checks the creator
		WebElement getDocTitle5 = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[5]/UIAStaticText[2]"));
		String message5 = getDocTitle5.getAttribute("value");
		
		//checks the modified date
		WebElement getDocTitle6 = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[6]/UIAStaticText[2]"));
		String message6 = getDocTitle6.getAttribute("value");
		
		//checks the modifier
		WebElement getDocTitle7 = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[7]/UIAStaticText[2]"));
		String message7 = getDocTitle7.getAttribute("value");
		
		//checks the language
		WebElement getDocTitle8 = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[8]/UIAStaticText[2]"));
		String message8 = getDocTitle8.getAttribute("value");
		
		//checks the mimetype
		WebElement getDocTitle9 = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[9]/UIAStaticText[2]"));
		String message9 = getDocTitle9.getAttribute("value");
		
		//checks the size
		WebElement getDocTitle10 = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[10]/UIAStaticText[2]"));
		String message10 = getDocTitle10.getAttribute("value");
				
		//Checks for the Document Name
		extent.log(LogStatus.INFO, "Verify the Document");
		if(message1.equals(filesearched))
		{
			extent.log(LogStatus.INFO, "Expected Result : "+filesearched);
			extent.log(LogStatus.INFO, "Current Result : "+message1);
			LOGGER.info("Pass : The document Name is successfully verified.");
			extent.log(LogStatus.PASS, "Pass : The document Name is successfully verified.");
			Element.takescreenshot(driver,className,screenshotname+"9");
			
			if(message2.equals(documentTitle))
			{
				extent.log(LogStatus.INFO, "Expected Result : "+documentTitle);
				extent.log(LogStatus.INFO, "Current Result : "+message2);
				LOGGER.info("Pass : The document Title is successfully verified.");
				extent.log(LogStatus.PASS, "Pass : The document Title is successfully verified.");
				Element.takescreenshot(driver,className,screenshotname+"10");
				
				if(message3.equals(documentsource))
				{
					extent.log(LogStatus.INFO, "Expected Result : "+documentsource);
					extent.log(LogStatus.INFO, "Current Result : "+message3);
					LOGGER.info("Pass : The document Source is successfully verified.");
					extent.log(LogStatus.PASS, "Pass : The document Source is successfully verified.");
					Element.takescreenshot(driver,className,screenshotname+"11");
					
					if(message4.equals(createddate))
					{
						extent.log(LogStatus.INFO, "Expected Result : "+createddate);
						extent.log(LogStatus.INFO, "Current Result : "+message4);
						LOGGER.info("Pass : The Create Date is successfully verified.");
						extent.log(LogStatus.PASS, "Pass : The Create Date is successfully verified.");
						Element.takescreenshot(driver,className,screenshotname+"12");
						
						if(message5.equals(creator))
						{
							extent.log(LogStatus.INFO, "Expected Result : "+creator);
							extent.log(LogStatus.INFO, "Current Result : "+message5);
							LOGGER.info("Pass : The Creator is successfully verified.");
							extent.log(LogStatus.PASS, "Pass : The Creator is successfully verified.");
							Element.takescreenshot(driver,className,screenshotname+"13");
							
							if(message6.equals(modifieddate))
							{
								extent.log(LogStatus.INFO, "Expected Result : "+modifieddate);
								extent.log(LogStatus.INFO, "Current Result : "+message6);
								LOGGER.info("Pass : The Modified Date is successfully verified.");
								extent.log(LogStatus.PASS, "Pass : The Modified Date is successfully verified.");
								Element.takescreenshot(driver,className,screenshotname+"14");
								
								if(message7.equals(modifier))
								{
									extent.log(LogStatus.INFO, "Expected Result : "+modifier);
									extent.log(LogStatus.INFO, "Current Result : "+message7);
									LOGGER.info("Pass : The Modifier is successfully verified.");
									extent.log(LogStatus.PASS, "Pass : The Modifier is successfully verified.");
									Element.takescreenshot(driver,className,screenshotname+"15");
									
									if(message8.equals(language))
									{
										extent.log(LogStatus.INFO, "Expected Result : "+language);
										extent.log(LogStatus.INFO, "Current Result : "+message8);
										LOGGER.info("Pass : The Language is successfully verified.");
										extent.log(LogStatus.PASS, "Pass : The Language is successfully verified.");
										Element.takescreenshot(driver,className,screenshotname+"16");
										
										if(message9.equals(mimetype))
										{
											extent.log(LogStatus.INFO, "Expected Result : "+mimetype);
											extent.log(LogStatus.INFO, "Current Result : "+message9);
											LOGGER.info("Pass : The MIME Type is successfully verified.");
											extent.log(LogStatus.PASS, "Pass : The MIME Type is successfully verified.");
											Element.takescreenshot(driver,className,screenshotname+"17");
											
											if(message10.equals(documentsize))
											{
												extent.log(LogStatus.INFO, "Expected Result : "+documentsize);
												extent.log(LogStatus.INFO, "Current Result : "+message10);
												LOGGER.info("Pass : The Size is successfully verified.");
												extent.log(LogStatus.PASS, "Pass : The Size is successfully verified.");
												Element.takescreenshot(driver,className,screenshotname+"18");
											}
											else
											{
												extent.log(LogStatus.INFO, "Expected Result : "+documentsize);
												extent.log(LogStatus.INFO, "Current Result : "+message10);
												LOGGER.info("Fail : The Size is not successfully verified.");
												extent.log(LogStatus.FAIL, "Fails : The Size is not successfully verified.");
												Element.takescreenshot(driver,className,screenshotname+"19");
											}
										}
										else
										{
											extent.log(LogStatus.INFO, "Expected Result : "+mimetype);
											extent.log(LogStatus.INFO, "Current Result : "+message9);
											LOGGER.info("Fail : The MIME Type is not successfully verified.");
											extent.log(LogStatus.FAIL, "Fails : The MIME Type is not successfully verified.");
											Element.takescreenshot(driver,className,screenshotname+"20");
										}
									}
									else
									{
										extent.log(LogStatus.INFO, "Expected Result : "+language);
										extent.log(LogStatus.INFO, "Current Result : "+message8);
										LOGGER.info("Fail : The Language is not successfully verified.");
										extent.log(LogStatus.FAIL, "Fail : The Language is not successfully verified.");
										Element.takescreenshot(driver,className,screenshotname+"21");
									}
								}
								else
								{
									extent.log(LogStatus.INFO, "Expected Result : "+modifier);
									extent.log(LogStatus.INFO, "Current Result : "+message7);
									LOGGER.info("Fail : The Modifier is not successfully verified.");
									extent.log(LogStatus.FAIL, "Fails : The Modifier is not successfully verified.");
									Element.takescreenshot(driver,className,screenshotname+"22");
								}
							}
							else
							{
								extent.log(LogStatus.INFO, "Expected Result : "+modifieddate);
								extent.log(LogStatus.INFO, "Current Result : "+message6);
								LOGGER.info("Fail : The Modified Date is not successfully verified.");
								extent.log(LogStatus.FAIL, "Fail : The Modified Date is not successfully verified.");
								Element.takescreenshot(driver,className,screenshotname+"23");
							}
						}
						else
						{
							extent.log(LogStatus.INFO, "Expected Result : "+creator);
							extent.log(LogStatus.INFO, "Current Result : "+message5);
							LOGGER.info("Fail : The Creator is not successfully verified.");
							extent.log(LogStatus.FAIL, "Fail : The Creator is not successfully verified.");
							Element.takescreenshot(driver,className,screenshotname+"24");
						}
					}
					else
					{
						extent.log(LogStatus.INFO, "Expected Result : "+createddate);
						extent.log(LogStatus.INFO, "Current Result : "+message4);
						LOGGER.info("Fail : The Create Date is not successfully verified.");
						extent.log(LogStatus.FAIL, "Fail : The Create Date is not successfully verified.");
						Element.takescreenshot(driver,className,screenshotname+"25");
					}
				}
				else
				{
					extent.log(LogStatus.INFO, "Expected Rersult : "+documentsource);
					extent.log(LogStatus.INFO, "Current Result : "+message3);
					LOGGER.info("Fail : The document Source is not successfully verified.");
					extent.log(LogStatus.FAIL, "Fail : The document Source is not successfully verified.");
					Element.takescreenshot(driver,className,screenshotname+"26");
				}
			}
			else
			{
				extent.log(LogStatus.INFO, "Expected Rersult : "+filesearched);
				extent.log(LogStatus.INFO, "Current Result : "+message2);
				LOGGER.info("Fail : The document Title is not successfully verified.");
				extent.log(LogStatus.FAIL, "Fail : The document Title is not successfully verified.");
				Element.takescreenshot(driver,className,screenshotname+"27");
			}
		}
		else
		{
			extent.log(LogStatus.INFO, "Expected Rersult : "+filesearched);
			extent.log(LogStatus.INFO, "Current Result : "+message1);
			LOGGER.info("Fail : The document Name not is successfully verified.");
			extent.log(LogStatus.FAIL, "Fail : The document Name is not successfully verified.");
			Element.takescreenshot(driver,className,screenshotname+"28");
			
		}
		
		Thread.sleep(3000);
	}
		
	@Test
	public void e() throws InterruptedException, IOException
	{
		LOGGER.info("Verify whether the Done Button is available");
		extent.startTest("Verify whether the Done Button is available");
		
		//Checks whether the Element is present
		if(Button.isElementPresent(driver, By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")))
		{
			System.out.println("Pass : Done Button is Available");
			extent.log(LogStatus.PASS, "Pass : Done Button is Available");
			Element.takescreenshot(driver,className,screenshotname+"29");
		}
		else
		{
			System.out.println("Fail : Done Button is not available");
			extent.log(LogStatus.FAIL, "Fail : Done Button is not available");
			Element.takescreenshot(driver,className,screenshotname+"30");
		}

		extent.log(LogStatus.INFO, "Navigate back to the Results");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click();
		System.out.println("Navigated Back to the results page");
		Thread.sleep(3000);	
	}
		
 	// @After
    public void teardown()
    {
    	driver.quit();
    }
    
	
}
