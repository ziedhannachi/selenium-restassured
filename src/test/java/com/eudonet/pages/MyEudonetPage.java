package com.eudonet.pages;

import com.eudonet.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyEudonetPage extends BasePage {

    //@FindBy(css = "li[onclick=\"javascript:loadUserOption('PREFERENCES_LANGUAGE');\"]")
    @FindBy(xpath = "//li[contains(text(),'Choose language')]")
    private WebElement chooseLanguageLink;

    //@FindBy(css = "li[onclick=\"javascript:loadUserOption('ADVANCED_EXPORT');\"]")
    @FindBy(xpath = "//li[contains(text(),'Export report')]")
    private WebElement exportReportLink;

    public MyEudonetPage(WebDriver driver) {
        super(driver);
    }

    public ChooseLanguagePage clickOnChooseLanguageLink() {
        wait.until(ExpectedConditions.visibilityOf(chooseLanguageLink)).click();
        return new ChooseLanguagePage(driver);
    }

    public ExportReportPage clickOnExportReportLink() {
        wait.until(ExpectedConditions.visibilityOf(exportReportLink));
        wait.until(ExpectedConditions.elementToBeClickable(exportReportLink)).click();
        return new ExportReportPage(driver);
    }
}
