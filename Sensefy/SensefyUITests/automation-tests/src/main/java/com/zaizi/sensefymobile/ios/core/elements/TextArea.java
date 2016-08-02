/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaizi.sensefymobile.ios.core.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author kdesilva
 */
public class TextArea extends Element {

    public TextArea(WebDriver driver, By elementIdentifier) {
        super(driver, elementIdentifier);
    }
    
    public void enterText(String text){
        getWebElement().sendKeys(text);
    }
    
    public String getText(){
        return getWebElement().getText();
    }
    public void clearText(){
        getWebElement().clear();
    }
}
