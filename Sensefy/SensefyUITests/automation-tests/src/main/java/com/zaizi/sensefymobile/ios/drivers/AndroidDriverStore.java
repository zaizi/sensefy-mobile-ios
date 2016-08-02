package com.zaizi.sensefymobile.ios.drivers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

/*
 *  @auther deranthika@zaizi.com
 */


public class AndroidDriverStore {
	private AndroidDriver driver;
	
	
	DesiredCapabilities Androidcapability=new DesiredCapabilities();
	
	public AndroidDriverStore(String OSversion, String deviceName,String appPath)
	{
		Androidcapability.setCapability("platformName", "Android");
		Androidcapability.setCapability("platformVersion", OSversion);
		Androidcapability.setCapability("deviceName", deviceName);
		System.out.println(OSversion+" "+ deviceName+"  "+ appPath);
		File file =new File(appPath);
		Androidcapability.setCapability("app", file.getAbsolutePath());
	}
	
	
	public AndroidDriver createDriver(String URL) throws MalformedURLException
	{		
		driver=new AndroidDriver(new URL(URL),Androidcapability);
		return driver;
	}

}
