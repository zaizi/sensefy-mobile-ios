package com.zaizi.sensefymobile.ios.drivers;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * @author kvithyashankar@zaizi.com
 *
 */
public class InternetExplorerDriverStore {

	/**
	 * Defining WebDriver
	 */
	private InternetExplorerDriver driver;
	/**
	 * Defining driverPath
	 */
	private String driverPath;

	/**
	 * @param driverPath
	 */
	public InternetExplorerDriverStore(String driverPath) {
		this.driverPath = driverPath;
		System.setProperty("webdriver.ie.driver", this.driverPath);
	}

	/**
	 * @return
	 */
	public InternetExplorerDriver createWebDriver() {
		
		this.driver = new InternetExplorerDriver();
		return driver;
	}

}
