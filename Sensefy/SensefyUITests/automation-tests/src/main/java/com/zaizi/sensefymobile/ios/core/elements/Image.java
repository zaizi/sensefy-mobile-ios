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
public class Image extends Element {

    public Image(WebDriver driver, By elementIdentifier) {
        super(driver, elementIdentifier);
    }
    
    public String getImageUrl(){
        return getElementAttribute("src");
    }

}
