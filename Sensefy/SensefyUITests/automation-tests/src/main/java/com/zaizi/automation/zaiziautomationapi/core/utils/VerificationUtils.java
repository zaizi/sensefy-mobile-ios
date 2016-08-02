/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaizi.automation.zaiziautomationapi.core.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author kdesilva
 */
public class VerificationUtils {
    
    public static boolean elementExists(WebDriver driver, By identifier){
        return driver.findElements(identifier).size()!=0;
    }
}
