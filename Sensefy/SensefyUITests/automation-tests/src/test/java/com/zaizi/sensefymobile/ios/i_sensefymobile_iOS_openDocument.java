package com.zaizi.sensefymobile.ios;

import java.io.IOException;
import java.util.Set;

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
import com.zaizi.sensefymobile.ios.core.elements.TextField;
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
public class i_sensefymobile_iOS_openDocument 
{
	public static final Logger LOGGER = LogManager.getLogger(i_sensefymobile_iOS_openDocument.class.getName());
	
	public static final ExtentReports extent = ExtentReports.get(i_sensefymobile_iOS_openDocument.class);
	
	public static String className=i_sensefymobile_iOS_openDocument.class.getSimpleName();

	static AppiumDriver driver;
	
	public String searchFile;
	public String alfresco_username;
	public String alfresco_password;
	public String filename;
	
	public String screenshotname = "opendocument";

	/**
	 * 
	 * 
	 * @param searchFile
	 * @param alfresco_username
	 * @param alfresco_password
	 * @param filename
	 */
	public i_sensefymobile_iOS_openDocument(String searchFile, String alfresco_username, String alfresco_password,String filename) 
	{
		this.searchFile = searchFile;
		this.alfresco_username = alfresco_username;
		this.alfresco_password = alfresco_password;
		this.filename = filename;
	}
	
	@Parameters()
	public static Iterable<Object[]> data() throws IterableException 
	{
		LOGGER.info(TestCaseProperties.TEXT_TEST_PREPARING, "i_sensefymobile_iOS_openDocument");

		return TestCaseValues.documentLibraryTestValues("i_sensefymobile_iOS_openDocument");
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
        extent.config().reportTitle("Open the Docuemnt via Alfresco in Sensefy");
		        
        // Define ReportHeadLine of ExtentReport
        extent.config().reportHeadline("Open the Docuemnt via Alfresco in Sensefy");    
     }
	
	@Test
	public void a() throws InterruptedException, IOException
	{
		LOGGER.info("Search for the Document and Open it via Alfresco in Sensefy");
		extent.startTest("Search for the Document and Open it via Alfresco in Sensefy");
		
		driver=(AppiumDriver) TestCaseProperties.getWebDriver();
		
		driver.findElement(By.xpath("//UIASearchBar")).sendKeys(searchFile+"\n");
		
		extent.log(LogStatus.INFO, "Search the File "+searchFile);
		System.out.println(searchFile);
		Thread.sleep(3000);
		Element.takescreenshot(driver,className,screenshotname+"1");
		
		
		//Navigate to the document in the Row 1
		WebElement gotoDocument = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]");
		gotoDocument.click();
		System.out.println("Navigated to the Document in the Row 1");
		
		//Click on Open Document
		extent.log(LogStatus.INFO, "Open the Document");
		System.out.println("Open the Document");
		Element.takescreenshot(driver,className,screenshotname+"2");
		WebElement opendocument = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAToolbar[1]/UIAButton[1]");
		opendocument.click();
		//Element.takescreenshot(driver,className,screenshotname+"2");
		//Thread.sleep(3000);
		
		//Navigate to Alfresco
		extent.log(LogStatus.INFO, "Navigate to ALFRESCO");
		System.out.println("Navigate to ALFRESCO");
		Thread.sleep(10000);
		
		
		Set<String> contexts1 = driver.getContextHandles();

	    // make sure we have something other than the native context
	    // assertThat(contexts.size(), greaterThan(1));
	    for (String context : contexts1) 
	    {
	        // System.out.println(contexts);
	        if (!context.equals("NATIVE_APP")) 
	        {
	            driver.context((String) context);
	            break;
	        }
	    }
	    System.out.println("execute context switch successfully!!!");
				
	    extent.log(LogStatus.INFO, "Enter the Username and Password");
		System.out.println("Enter the Username and Password");
		//Element.takescreenshot(driver,className,screenshotname+"3");
		
		Thread.sleep(50000);
		
		//Enter username and password
		TextField a_username = new TextField(driver, By.id("page_x002e_components_x002e_slingshot-login_x0023_default-username"));
		//a_username.enterText(null);
		//Thread.sleep(3000);
		a_username.enterText(alfresco_username);
	
		TextField a_password = new TextField(driver, By.id("page_x002e_components_x002e_slingshot-login_x0023_default-password"));
		//Thread.sleep(3000);
		a_password.enterText(alfresco_password);
		
		Element.takescreenshot(driver,className,screenshotname+"3");
		Thread.sleep(3000);
		driver.findElement(By.id("page_x002e_components_x002e_slingshot-login_x0023_default-submit-button")).click();
		
		Thread.sleep(10000);
		
		extent.log(LogStatus.INFO, "Verify the Document");
		if(!Element.isElementPresent(driver, By.xpath("//div[@id='template_x002e_node-header_x002e_document-details_x0023_default’]//h1[contains(.,'sample 1.png')]")))
		{
			 LOGGER.info("Pass : Successfully Opened the Document via Alfresco");
			 extent.log(LogStatus.PASS, "Pass : Successfully Opened the Document via Alfresco");
			 Element.takescreenshot(driver,className,screenshotname+"4");
		}
		else
		{
			 LOGGER.info("Fail : Unsuccessfully Opened the Document via Alfresco");
			 extent.log(LogStatus.FAIL, "Fail : Unsuccessfully Opened the Document via Alfresco");
			 Element.takescreenshot(driver,className,screenshotname+"5");
		}
		/*//verify the opened document
		WebElement docname = driver.findElement(By.xpath("//div[@id='template_x002e_node-header_x002e_document-details_x0023_default’]//h1"));
		String message = docname.getAttribute("value");
		
		extent.log(LogStatus.INFO, "Verify the Document");
		if(message.equals(filename))
		{
			extent.log(LogStatus.INFO, "Expected Result : "+filename);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Pass : The Document is opened in Alfresco successfully.");
			extent.log(LogStatus.PASS, "Pass : The Document is opened in Alfresco successfully.");
			Element.takescreenshot(driver,className,screenshotname+"5");
		}
		else
		{
			extent.log(LogStatus.INFO, "Expected Result : "+filename);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Fail : The Document is not opened in Alfresco successfully.");
			extent.log(LogStatus.FAIL, "Fail : The Document is not opened in Alfresco successfully..");
			Element.takescreenshot(driver,className,screenshotname+"6");
		}*/
		
		
			
	}
}
