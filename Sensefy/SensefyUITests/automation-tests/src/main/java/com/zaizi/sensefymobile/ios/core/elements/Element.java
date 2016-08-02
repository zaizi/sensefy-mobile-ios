/**
 * This file is part of AlfrescoBasicFunctionalityTestingScripts.
 *
 * AlfrescoBasicFunctionalityTestingScripts is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * AlfrescoBasicFunctionalityTestingScripts is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with AlfrescoBasicFunctionalityTestingScripts.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.zaizi.sensefymobile.ios.core.elements;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import org.apache.commons.io.FileUtils;


import com.relevantcodes.extentreports.ExtentReports;
import com.zaizi.sensefymobile.ios.core.info.TestCaseProperties;



/**
 * @author cthalayasingam@zaizi.com
 * 
 */
public class Element {
	/**
	 * Defining driver
	 */
	private WebDriver driver;
	public static  ExtentReports extent = ExtentReports.get(Element.class);
	/**
	 * Defining element
	 */
	private WebElement element;

	/**
	 * @param driver
	 * @param elementIdentifier
	 *            it could be xpath, id, class etc. example :
	 *            by.xpath("//input[@id='btn1']")
	 */
	public Element(WebDriver driver, By elementIdentifier) {
		Element.waitUntilElementPresent(driver, elementIdentifier);
		
		Element.waitUntilElementClickable(driver,elementIdentifier);
		this.driver = driver;
		
		element = this.driver.findElement(elementIdentifier);
	}

	/**
	 * click method
	 */
	public void click() {
		element.click();
	}

	/**
	 * @param attribute
	 * @return
	 */
	public String getElementAttribute(String attribute) {
		return element.getAttribute(attribute);
	}

	/**
	 * @return
	 */
	public WebElement getWebElement() {
		return this.element;
	}

	/**
	 * @return
	 */
	public WebDriver getDriver() {
		return this.driver;
	}

	/**
	 * Check if the element is present in the page
	 * 
	 * @param driver
	 * @param xPath
	 * @throws InterruptedException
	 */
	public static Boolean isElementPresent(WebDriver driver, By xPath)
			throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			driver.findElement(xPath);
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}

	/**
	 * Check if the text is present in the List
	 * 
	 * @param findElements
	 * @param name
	 * @throws InterruptedException
	 */

	public static Boolean isTextPresentInList(
			java.util.List<WebElement> findElements, String name) {

		java.util.List<WebElement> myList = findElements;

		for (WebElement webele : myList) {
			if (webele.getText().equals(name)) {
				return true;
			}
		}
		return false;
		/*
		 * for(int i=0 ; i<myList.size(); i++)
		 * 
		 * { if(myList.get(i).getText().equals(name)) { return true; } }
		 * 
		 * 
		 * return false;
		 */

	}
	
	public String getText()
	{
		return getWebElement().getText();
	}

	/**
	 * Check if the text is present in Element
	 * 
	 * @param elements
	 * @param name
	 * @throws InterruptedException
	 */

	public static Boolean isTextPresentInElement(Element element, String name)
			throws InterruptedException {

		if (element.getWebElement().getText().equals(name)) {
			return true;

		} else {
			return false;

		}

	}

	/**
	 * Wait till the page loads completely
	 * 
	 * @param driver
	 */
	public static void waitForLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript(
						"return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(pageLoadCondition);
		
	}
	
//	
//	/**
//	 * Wait till Ajax call
//	 * 
//	 * @param driver
//	 */
//	public static void waitForAjax(WebDriver driver) {
//		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
//			public Boolean apply(WebDriver driver) {
//				return ((JavascriptExecutor) driver).executeScript(
//						"return jQuery.active").equals(0);
//			}
//		};
//		WebDriverWait wait = new WebDriverWait(driver, 4000);
//		wait.until(pageLoadCondition);
//		
//	}

	/**
	 * Wait Until Element is present by locator
	 */
	public static void waitUntilElementPresent(WebDriver driver, By locator)
	{
//	WebElement myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(xPath));
	WebElement myDynamicElement = (new WebDriverWait(driver, 20)).until(ExpectedConditions.presenceOfElementLocated(locator));
	
	// By.xpath("//body//div//div//div//span[contains(.,'Enter at least 1')]"));
	// Thread.sleep(3000);
	}
	
//	/**
//	 * Wait Until Element is present by ID
//	 */
//	public static void waitUntilElementPresentID(WebDriver driver, By id)
//	{
//	WebElement myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(id));
//	// Span errorMessage = new Span(driver,
//	// By.xpath("//body//div//div//div//span[contains(.,'Enter at least 1')]"));
//	// Thread.sleep(3000);
//	}
//	
//	/**
//	 * Wait Until Element is present by xpath
//	 */
//	public static void waitUntilElementClickableXpath(WebDriver driver, By xPath)
//	{
////	WebElement myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(xPath));
//	WebElement myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(xPath));
//	// By.xpath("//body//div//div//div//span[contains(.,'Enter at least 1')]"));
//	// Thread.sleep(3000);
//	}
	
	/**
	 * Wait Until Element is Clickable by locator
	 * @throws InterruptedException
	 */
	public static void waitUntilElementClickable(WebDriver driver, By locator) 
	{
		
	WebElement myDynamicElement = (new WebDriverWait(driver, 20)).until(ExpectedConditions.elementToBeClickable(locator));
		
		
		
	// Span errorMessage = new Span(driver,
	// By.xpath("//body//div//div//div//span[contains(.,'Enter at least 1')]"));
	// Thread.sleep(3000);
	}
	
	
	
	
	
	public static void takeSnapShot(WebDriver driver,String fileWithPath) throws Exception{
		 
        //Convert web driver object to TakeScreenshot
 
        TakesScreenshot scrShot =((TakesScreenshot)driver);
 
        //Call getScreenshotAs method to create image file
 
                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
 
            //Move image file to new destination
 
                File DestFile=new File(fileWithPath);
 
                //Copy file at destination
 
                FileUtils.copyFile(SrcFile, DestFile);
 
            
 
    }

	public static  void onTestfail(WebDriver driver,String fileName)
	{
//		String fillename="/Users/nbrahmananthan/Documents/workspace-sts-3.6.1.RELEASE/UOW_AUTOMATION/src"+"//"+"screenshots"+(new Random().nextInt())+".png";
		String fillename="/Users/nbrahmananthan/Documents/workspace-sts-3.6.1.RELEASE/UOW_AUTOMATION/src"+"//"+"screenshots"+fileName+".png";
		
		///Users/deranthika/Desktop/Selenium/workspace
		
		try
		{
			takeSnapShot(driver, fillename);
			System.out.println(fillename);
			
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Take ScreenShot ,Save it in REPORT_TEST_PATH AS SCREENSHOT NAME
	 * Attach the takenScreenShot to ExtentReport
	 * 	
	 * @throws IOException
	 */
	public final static void takescreenshot(WebDriver driver,String className,String Screenshotname) throws IOException
    {
		    File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		    FileUtils.copyFile(scrFile1, new File(TestCaseProperties.REPORT_TEST_PATH+className+"/"+Screenshotname+".jpg"));
		    extent.attachScreenshot("./"+className+"/"+Screenshotname+".jpg");  
            System.out.println("Screenshot Taken Successfully!!!!");        
          
    }
	/**
	 * Initialize Path,Where Extent Report Save WITH extent Report Name	 
	 * 	
	 * @throws IOException
	 */
	
	public final static void reportInitial(WebDriver driver,String htmlName) throws IOException
	{        
		//extent.init(TestCaseProperties.REPORT_TEST_PATH+htmlName+".html", true);    
		extent.init(TestCaseProperties.REPORT_TEST_PATH+htmlName+" .html", true);
	}
	
}
