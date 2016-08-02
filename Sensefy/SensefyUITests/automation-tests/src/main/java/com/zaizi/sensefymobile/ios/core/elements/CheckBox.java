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
public class CheckBox extends Element {

    public CheckBox(WebDriver driver, By elementIdentifier) {
        super(driver, elementIdentifier);
    }

    public boolean isSelected() {
        return getWebElement().isSelected();
    }

    public void select() {
        if (!getWebElement().isSelected()) {
            getWebElement().click();
        }

    }

    public void unSelect() {
        if (getWebElement().isSelected()) {
            getWebElement().click();
        }
    }
}
