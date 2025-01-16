package org.utils;

import static org.testng.Assert.fail;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementActions {
    private static WebDriver driver;

    public ElementActions(WebDriver driver) {
        ElementActions.driver = driver;
    }

    public static WebDriverWait getExplicitWait(WebDriver driver) {
        return new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    public static void click(By elementLocator) {
        mouseHover(elementLocator);

        try {
            getExplicitWait(driver).until(ExpectedConditions.elementToBeClickable(elementLocator));
        } catch (TimeoutException toe) {
            fail(toe.getMessage());
        }

        try {
            driver.findElement(elementLocator).click();
        } catch (Exception exception) {
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                        driver.findElement(elementLocator));
            } catch (Exception e) {
                fail("Couldn't click on the element:" + elementLocator, e);
            }
        }
    }

    public static void mouseHover(By elementLocator) {
        locatingElement(elementLocator);
        try {
            new Actions(driver)
                    .moveToElement(driver.findElement(elementLocator))
                    .perform();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }


    public static String getText(By elementLocator) {
        locatingElement(elementLocator);
        try {
            return driver.findElement(elementLocator).getText();
        } catch (Exception e) {
            fail(e.getMessage());
        }
        return null;
    }

    private static void locatingElement(By elementLocator) {
        try {
            getExplicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
            if (!driver.findElement(elementLocator).isDisplayed()) {
                fail("The element [" + elementLocator.toString() + "] is not Displayed");
            }
        } catch (TimeoutException toe) {
            fail(toe.getMessage());
        }
    }

    public static boolean isElementExist(By elementLocator){
            getExplicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        return driver.findElement(elementLocator).isDisplayed();
    }

}