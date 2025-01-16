package org.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.utils.ElementActions;

import static org.utils.ElementActions.*;

public class HomePage {

    ElementActions elementActions;

    public HomePage(WebDriver driver){
        elementActions = new ElementActions(driver);
    }

    //Locators
    By countrySelectorLocator = By.id("country-btn");

    //Actions
    @Step("Change Country to: {countryName}")
    public void changeCountry(String countryName){
        click(countrySelectorLocator);
        String countryNameXpath = "//span[contains(text(),'"+countryName+"')]";
        click(By.xpath(countryNameXpath));
    }

    @Step("Check if Package: {packageName} exist")
    public boolean isPackageTypeExist(String packageName){
        String packageId = "name-"+packageName;
        return isElementExist(By.id(packageId));
    }

    @Step("Get the price of Package: {packageName} ")
    public String getPackagePrice(String packageName){
        String packagePriceXpath = "//div[@id = 'currency-"+packageName+"']//b";
        return getText(By.xpath(packagePriceXpath));
    }

    @Step("Get the currency of Package: {packageName} ")
    public String getPackageCurrency(String packageName){
        String packageCurrencyXpath = "//div[@id = 'currency-"+packageName+"']//i";
        return getText(By.xpath(packageCurrencyXpath));
    }


}
