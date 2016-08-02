package com.zaizi.sensefymobile.ios.drivers;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.ios.IOSDriver;

/*
 *  @auther deranthika@zaizi.com
 */

public class IOSDriverStore {
	private IOSDriver driver;
	
	DesiredCapabilities IOSCapability=new DesiredCapabilities();
	
	public IOSDriverStore(String OSVERSION,String DEVICENAME,String APPPATH, String UDID)
	{
		
   	 IOSCapability.setCapability("platformName", "iOS");
		IOSCapability.setCapability("platformVersion", OSVERSION);
		IOSCapability.setCapability("deviceName", DEVICENAME);
		IOSCapability.setCapability("udid", UDID);
		IOSCapability.setCapability("app", APPPATH);
	}	

	
	public IOSDriver createDriver(String URL) throws MalformedURLException
	{
		driver=new IOSDriver(new URL(URL),IOSCapability);
		return driver;
	}

}
