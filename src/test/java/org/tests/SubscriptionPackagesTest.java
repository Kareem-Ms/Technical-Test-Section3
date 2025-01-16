package org.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.utils.BrowserActions;
import org.utils.BrowserFactory;
import org.utils.JsonFileManager;
import org.utils.PropertiesManager;

import static org.utils.JsonFileManager.getTestData;

@Epic("Regression")
@Feature("Validate Packages")
public class SubscriptionPackagesTest {

    //Variables
    WebDriver driver;
    HomePage homePage;
    JsonFileManager jsonFileManager;

    //Tests
    @Test(description = "Verify changing the country and validating the existence of the packages, their prices and currencies")
    @Severity(SeverityLevel.CRITICAL)
    public void ValidatePackagesInfo(){
        homePage.changeCountry(getTestData("countryName"));
        assertPackageExistence();
        assertPackagesPrice();
        assertPackagesCurrencies();
    }

    //Configurations
    @BeforeClass
    public void setUp(){
        jsonFileManager = new JsonFileManager("src\\test\\java\\org\\testData\\PackagesTestData.json");
        driver = BrowserFactory.getBrowser(getTestData("browser"));
        BrowserActions.navigateToUrl(driver, PropertiesManager.getProperty("JawwyUrl"));
        homePage = new HomePage(driver);
    }

    @AfterClass
    public void tearDown(){
        BrowserActions.closeAllBrowserTabs(driver);
    }

    //Assertions
    public void assertPackageExistence(){
        Assert.assertTrue(homePage.isPackageTypeExist(getTestData("LitePackage.Type")));
        Assert.assertTrue(homePage.isPackageTypeExist(getTestData("mainPackage.Type")));
        Assert.assertTrue(homePage.isPackageTypeExist(getTestData("premiumPackage.Type")));
    }

    public void assertPackagesPrice(){
        Assert.assertEquals(homePage.getPackagePrice(getTestData("LitePackage.Type")), getTestData("LitePackage.price"));
        Assert.assertEquals(homePage.getPackagePrice(getTestData("mainPackage.Type")), getTestData("mainPackage.price"));
        Assert.assertEquals(homePage.getPackagePrice(getTestData("premiumPackage.Type")), getTestData("premiumPackage.price"));
    }

    public void assertPackagesCurrencies(){
        Assert.assertTrue(homePage.getPackageCurrency(getTestData("LitePackage.Type")).contains(getTestData("LitePackage.currency")));
        Assert.assertTrue(homePage.getPackageCurrency(getTestData("mainPackage.Type")).contains(getTestData("mainPackage.currency")));
        Assert.assertTrue(homePage.getPackageCurrency(getTestData("premiumPackage.Type")).contains(getTestData("premiumPackage.currency")));

    }

}
