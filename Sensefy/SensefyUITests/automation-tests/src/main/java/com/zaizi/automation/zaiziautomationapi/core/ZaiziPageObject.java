/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaizi.automation.zaiziautomationapi.core;

import org.openqa.selenium.WebDriver;

/**
 *
 * @author kdesilva
 */
public interface ZaiziPageObject {
    public void setDriver(WebDriver driver);
    public void prepareElements();
}
