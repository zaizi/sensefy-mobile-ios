/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaizi.sensefymobile.ios.core.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Represents elements like div, span
 * @author kdesilva
 */
public class Container extends Element{
    
    public Container(WebDriver driver, By elementIdentifier) {
        super(driver, elementIdentifier);
    }
    
    public boolean isVisible(){
        return getWebElement().isDisplayed();
    }
    
    public boolean isEnabled(){
        return getWebElement().isEnabled();
    }
}
