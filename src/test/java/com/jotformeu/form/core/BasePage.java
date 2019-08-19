package com.jotformeu.form.core;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    private WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitElementVisible(WebElement el, int time) {
        return new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(el));
    }

    public WebElement waitElementVisibleByXpath(String xpath, int time) {
        return new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));
    }

    public void selectOptionByText(String selectId, String optionText) {
        Select se = new Select(driver.findElement(By.id(selectId)));
        se.deselectByVisibleText(optionText);
//        selectByValue(optionText);
    }

    public void selectOptionByIndex(String selectId, int optionIndex) {
        Select se = new Select(driver.findElement(By.id(selectId)));
        se.selectByIndex(optionIndex);
    }

    public void srollToElementByXpth(String xpath) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(xpath)));
    }

    public void clearAndFillElement(WebElement el, String keys){
         el.clear();
        el.sendKeys(keys);
    }

    public Boolean SourceContainsText(String text){
        if(driver.getPageSource().contains(text))return true;
        return false;
    }

    public void sleep(int mSec) {
        try {
            Thread.sleep(mSec);
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }
}
