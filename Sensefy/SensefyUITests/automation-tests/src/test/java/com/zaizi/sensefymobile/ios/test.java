package com.zaizi.sensefymobile.ios;

import java.net.MalformedURLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.zaizi.sensefymobile.ios.core.info.TestCaseProperties;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class test {
	public static final Logger LOGGER = LogManager.getLogger(a_sensefymobile_iOS_search.class.getName());

	static AppiumDriver driver;
	
	@Test
    public void a_searchexactdoc() throws MalformedURLException, InterruptedException
	{
		//driver.launchApp();
    	driver=(AppiumDriver) TestCaseProperties.getWebDriver();
    	driver.findElement(By.xpath("//UIASearchBar")).sendKeys("sample 1.png"+"\n");
    	MobileElement expelement=(MobileElement) (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAStaticText[contains(@name,'sample 1.png')]")));
    	String expact=expelement.getText();
    	Assert.assertEquals("sample 1.png", expact);
	}
}
