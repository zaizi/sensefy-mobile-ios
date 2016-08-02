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
public class Link extends Element{ 
    
    public Link(WebDriver driver,By elementIdentifier){
        super(driver, elementIdentifier);
    }
    
    public String getUrl(){
        return getElementAttribute("href");
    }
    
    //add new operaitions here
}
