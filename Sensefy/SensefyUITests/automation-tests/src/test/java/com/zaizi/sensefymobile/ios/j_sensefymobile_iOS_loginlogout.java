package com.zaizi.sensefymobile.ios;

import java.io.IOException;
import java.net.MalformedURLException;
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
public class j_sensefymobile_iOS_loginlogout 
{
public static final Logger LOGGER = LogManager.getLogger(j_sensefymobile_iOS_loginlogout.class.getName());
	
	public static final ExtentReports extent = ExtentReports.get(j_sensefymobile_iOS_loginlogout.class);
	
	public static String className=j_sensefymobile_iOS_loginlogout.class.getSimpleName();

	static AppiumDriver driver;
	
	public String sensefy_username;
	public String sensefy_password;
	
	public String screenshotname = "loginlogout";
	
	/**
	 * 
	 * @param sensefy_username
	 * @param sensefy_password
	 */
	public j_sensefymobile_iOS_loginlogout(String sensefy_username, String sensefy_password) 
	{
		
		this.sensefy_username = sensefy_username;
		this.sensefy_password = sensefy_password;
	}
	
	@Parameters()
	public static Iterable<Object[]> data() throws IterableException 
	{
		LOGGER.info(TestCaseProperties.TEXT_TEST_PREPARING, "j_sensefymobile_iOS_loginlogout");

		return TestCaseValues.documentLibraryTestValues("j_sensefymobile_iOS_loginlogout");
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
        extent.config().reportTitle("Login and Logout Functionality in Sensefy");
		        
        // Define ReportHeadLine of ExtentReport
        extent.config().reportHeadline("Login and Logout Functionality in Sensefy"); 
      
	}
	
	@Test
	public void a() throws InterruptedException, IOException
	{
		LOGGER.info("Login And Logout Funtion in Sensefy");
		extent.startTest("Login And Logout Funtion in Sensefy");
		
		driver=(AppiumDriver) TestCaseProperties.getWebDriver();
		
		//click on the profile button
		extent.log(LogStatus.INFO, "Navigate to Profile button");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")).click();
		Element.takescreenshot(driver,className,screenshotname+"1");
		
		//click on logout
		extent.log(LogStatus.INFO, "Logout from the Current Profile");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAPopover[1]/UIATableView[1]/UIATableCell[3]")).click();
		
		Thread.sleep(3000);
		Element.takescreenshot(driver,className,screenshotname+"2");
		//click on login
		extent.log(LogStatus.INFO, "Login to Sensefy");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAPopover[1]/UIATableView[1]/UIATableCell[3]")).click();
		
		// E.G.['NATIVE_APP', 'WEBVIEW_1', ...]
		extent.log(LogStatus.INFO, "Switch to the Web View");
	    
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
	            	
	   Thread.sleep(10000);
		
	   extent.log(LogStatus.INFO, "Enter the Username and Password and login");
	   //Enter the username
	   TextField s_username = new TextField(driver, By.id("username"));
	   s_username.enterText(sensefy_username);
	
	   System.out.println("username OK");	
	   //Enter the password
	   Thread.sleep(3000);
	   TextField s_password = new TextField(driver, By.id("password"));
	   s_password.enterText(sensefy_password);
	   Element.takescreenshot(driver,className,screenshotname+"4");
	   System.out.println("password OK");	
	   //Click on the login 
	   Thread.sleep(3000);
	   driver.findElementById("loginButton").click();
	   
	   Thread.sleep(3000);
	   
	   Element.takescreenshot(driver,className,screenshotname+"5");
	   
		// E.G.['NATIVE_APP', 'WEBVIEW_1', ...]
		extent.log(LogStatus.INFO, "Switch to NATIVE");
	    
		Set<String> contexts2 = driver.getContextHandles();
	    
		// make sure we have something other than the native context
	    // assertThat(contexts.size(), greaterThan(1));
	    for (String context : contexts2) 
	    {
	        // System.out.println(contexts);
	        if (context.contains("NATIVE")) 
	        {
	            driver.context((String) context);
	            break;
	        }
	    }
	    System.out.println("execute context switch successfully!!!");
		
		
	  Thread.sleep(10000);
	    
	  //click on the profile button
	  extent.log(LogStatus.INFO, "Navigate to Profile button");
	  driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")).click();
	  			
	  WebElement getDocName1 = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAPopover[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[2]");
	  String message = getDocName1.getAttribute("value");
			
	  //Checks for the Document Name
	  extent.log(LogStatus.INFO, "Verify the User");
	  if(message.equals(sensefy_username))
	  {
			extent.log(LogStatus.INFO, "Expected Result : "+sensefy_username);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Pass : Successfully Logged in as the Admin");
			extent.log(LogStatus.PASS, "Pass : Successfully Logged in as the Admin");
			Element.takescreenshot(driver,className,screenshotname+"6");
	  }
	  else
	  {
			extent.log(LogStatus.INFO, "Expected Rersult : "+sensefy_username);
			extent.log(LogStatus.INFO, "Current Result : "+message);
			LOGGER.info("Fail : Unsuccessfully Logged in as the Admin");
			extent.log(LogStatus.FAIL, "Fail :  Unsuccessfully Logged in as the Admin");
			Element.takescreenshot(driver,className,screenshotname+"7");
	  }
	}
	}
