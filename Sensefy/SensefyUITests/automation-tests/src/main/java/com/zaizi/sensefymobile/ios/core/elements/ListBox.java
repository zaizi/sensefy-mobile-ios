/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaizi.sensefymobile.ios.core.elements;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author kdesilva
 */
public class ListBox extends Element {

    Select select;

    public ListBox(WebDriver driver, By elementIdentifier) {
        super(driver, elementIdentifier);
        this.select = new Select(getWebElement());
    }

    public void selectByIndex(int index) {
        this.select.selectByIndex(index);
    }

    public void selectByValue(String val) {
        this.select.selectByValue(val);
    }

    public void selectByVisibleText(String text) {
        this.select.selectByVisibleText(text);
    }

    public void selectByValueContainsText(String text) {
        List<WebElement> options = this.select.getOptions();
        int idx = 0;
        for (WebElement option : options) {
            String visibleText = option.getAttribute("value");
            if (visibleText.contains(text)) {
                this.select.selectByIndex(idx);
            }
            idx++;
        }
    }
    
    public void selectByVisibleTextContains(String text){
        List<WebElement> options = this.select.getOptions();
        int idx = 0;
        for (WebElement option : options)
        {
            String visibleText = option.getText();
            if (visibleText.contains(text))
            {
                this.select.selectByIndex(idx);
            }
            idx++;
        }
    }
    
    public Select getUnderlyingSelectElement(){
        return this.select;
    }
}
