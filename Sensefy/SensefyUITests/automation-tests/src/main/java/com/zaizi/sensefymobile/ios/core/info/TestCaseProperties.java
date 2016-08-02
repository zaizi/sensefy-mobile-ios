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
package com.zaizi.sensefymobile.ios.core.info;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.zaizi.sensefymobile.ios.drivers.*;

import io.appium.java_client.ios.IOSDriver;

/**
 * @author cthalayasingam@zaizi.com
 * 
 */
public class TestCaseProperties {
	/**
	 * Defining log4j
	 */
	public static final Logger LOGGER = LogManager
			.getLogger(TestCaseProperties.class.getName());

	public static final String TEXT_TEST_PREPARING = "Preparing For {} ...";
	public static final String TEXT_TEST_EXECUTING = "Executing Test :  {} ...";
	public static final String TEXT_TEST_PASS = "Test Case : {} PASSED!";
	public static final String TEXT_TEST_FAIL = "Test Case : {} FAILED!";
	
	/*
	 * Define Threadsleep value
	 */
	public static final int THREAD_SLEEP_TIME_100 = 100;
	public static final int THREAD_SLEEP_TIME_200 = 200;
	public static final int THREAD_SLEEP_TIME_300 = 300;
	public static final int THREAD_SLEEP_TIME_500 = 500;
	public static final int THREAD_SLEEP_TIME_1000 = 1000;
	public static final int THREAD_SLEEP_TIME_2000 = 2000;
	public static final int THREAD_SLEEP_TIME_5000 = 5000;
	public static final int THREAD_SLEEP_TIME_10000 = 10000;

	/**
	 * Defining Alfresco Share URL
	 */


	/**
	 * Defining Browser (Firefox or Safari or Chrome)
	 */
	/*public static final String BROWSER = getPropertyValue("Browser");
	
	
	 */
	public static int dashletCount;

	/**
	 * Defining WebDriver
	 */
	private static WebDriver driver = null;
	public static void test() {
		System.out.println("test calld...");
	}
	
	
	private static final String TEST_CASE_PROPERTIES_XML = "pom.xml";

	public static final String OSNAME = getPropertyValue("osname");
	public static final String OSVERSION = getPropertyValue("osversion");
	public static final String DEVICENAME = getPropertyValue("devicename");
	public static final String APPPATH = getPropertyValue("apppath");
	public static final String URL = getPropertyValue("appurl");
	public static final String UDID= getPropertyValue("udid");
	public static final String REPORT_TEST_PATH = getPropertyValue("ReportPath");
	   
	
	
	
	// Calling Appium Driver
	public static WebDriver getWebDriver() throws MalformedURLException
	 {
		 
//		 System.out.println(OSVERSION);
//		 System.out.println(DEVICENAME);
//		 System.out.println(APPPATH);
//		 System.out.println(OSNAME);
//		 System.out.println(URL);
		 
//	     if (driver != null)
//	     {
//	        closeDriver(driver);
//	     }
	     
	     
	     
	     //////////****************
	     //NEW BROWSER
	     //************
	 if("Android".equals(OSNAME))
	     {
	    	 AndroidDriverStore webDrvFac=new AndroidDriverStore(OSVERSION,DEVICENAME,APPPATH);
	    	 driver=webDrvFac.createDriver(URL);
	    	 System.out.println(URL);
	     }
	     
	     else if("IOS".equals(OSNAME))
	     {
	    	IOSDriverStore webDrvFac=new IOSDriverStore(OSVERSION, DEVICENAME, APPPATH, UDID);
	 		driver=webDrvFac.createDriver(URL);
	 		System.out.println("IOS driver created");
	     }

	     
	     return driver;
	 }
	
	
	 public static String getPropertyValue(String propertyName)
	 {
	     String result = null;
	     try
	     {
	         Node serverUrl = getProperties(propertyName);
	         result = serverUrl.getNodeValue();
	     }
	     catch (ParserConfigurationException e)
	     {
	         LOGGER.error("ParserConfigurationException", e);
	     }
	     catch (SAXException e)
	     {
	         LOGGER.error("SAXException", e);
	     }
	     catch (IOException e)
	     {
	         LOGGER.error("IOException", e);
	     }
	     return result;
	 }
	
	
	 private static Node getProperties(String propertyName) throws ParserConfigurationException, SAXException, IOException
	 {
	     File testValues = new File(TEST_CASE_PROPERTIES_XML);
	     DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	     DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	     Document doc = dBuilder.parse(testValues);
	     doc.getDocumentElement().normalize();
	     NodeList nodes = doc.getElementsByTagName(propertyName);
	     Node node = nodes.item(0);
	     NodeList testdata = node.getChildNodes();
	     return testdata.item(0);
	 }
	
	 public static void closeDriver(WebDriver oldDriver)
	 {
	     oldDriver.close();
	     driver = null;
	 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
