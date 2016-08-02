/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaizi.sensefymobile.ios.core.elements;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author kdesilva
 */
public class ExplicitWait {

    private WebDriver driver;

    public ExplicitWait(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilElementAppears(By elementIdentifier, int timeDuration) {
        WebElement dynamicElem = (new WebDriverWait(driver, timeDuration))
                .until(ExpectedConditions.presenceOfElementLocated(elementIdentifier));
    }
    
    public boolean waitUntilElementAppear(By elementIdentifier, int timeDuration) {
        WebElement dynamicElem = (new WebDriverWait(driver, timeDuration))
                .until(ExpectedConditions.presenceOfElementLocated(elementIdentifier));
		return true;
    }

    public void waitUntilAlertIsPresent(int timeDuration) {
        Alert dynamicAlert = (new WebDriverWait(driver, timeDuration))
                .until(ExpectedConditions.alertIsPresent());
    }

    public void waitUntilElementToBeClickable(By elementIdentifier, int timeDuration) {
        WebElement dynamicElem = (new WebDriverWait(driver, timeDuration))
                .until(ExpectedConditions.elementToBeClickable(elementIdentifier));
    }

    public void waitUntiltitleIs(String title, int timeDuration) {
        (new WebDriverWait(driver, timeDuration))
                .until(ExpectedConditions.titleIs(title));
    }

    public void waitUntiltitleContains(String title, int timeDuration) {
        (new WebDriverWait(driver, timeDuration))
                .until(ExpectedConditions.titleContains(title));
    }

    public void waitUntileElementTextContains(By elementIdentifier, String text, int timeDuration) {
        (new WebDriverWait(driver, timeDuration))
                .until(ExpectedConditions.textToBePresentInElement(elementIdentifier,text));
    }
    
    public void waitUntileElementValueContains(By elementIdentifier, String text, int timeDuration) {
        (new WebDriverWait(driver, timeDuration))
                .until(ExpectedConditions.textToBePresentInElementValue(elementIdentifier,text));
    }
    
}
