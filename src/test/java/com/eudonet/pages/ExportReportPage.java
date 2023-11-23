package com.eudonet.pages;

import com.eudonet.base.BasePage;
import com.eudonet.constants.OfficeVersion;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static org.testng.Assert.assertEquals;


public class ExportReportPage extends BasePage {

    @FindBy(css = "#export-mode")
    private WebElement exportModeDropdown;

    @FindBy(css = ".button-green-mid")
    private WebElement submitBtn;

    @FindBy(css = "#version-choice")
    private WebElement chosenVersion;

    @FindBy(css = "option[value='0']")
    private WebElement chosenExportMode;

    private final By closeBtn = By.cssSelector("#cancel-mid");

    public ExportReportPage(WebDriver driver) {
        super(driver);
    }

    public By getOfficeVersionElement(String version) {
        return By.cssSelector("#version-" + version);
    }

    public void selectOfficeVersion(By officeLocator) {
        wait.until(ExpectedConditions.elementToBeClickable(officeLocator)).click();
    }

    public ExportReportPage chooseOfficeVersion(String officeVersion, String exportMode) {
        switch (OfficeVersion.valueOf(officeVersion)) {
            case Office_2019_64:
                By officeLocator = getOfficeVersionElement("19");
                selectOfficeVersion(officeLocator);
                break;
            case Office_2016_64:
                officeLocator = getOfficeVersionElement("17");
                selectOfficeVersion(officeLocator);
                break;
            case Office_2013_64:
                officeLocator = getOfficeVersionElement("15");
                selectOfficeVersion(officeLocator);
                break;
            case Office_2010_64:
                officeLocator = getOfficeVersionElement("12");
                selectOfficeVersion(officeLocator);
                break;
            case Office_2007_2010:
                officeLocator = getOfficeVersionElement("10");
                selectOfficeVersion(officeLocator);
                break;
        }
        selectExportMode(exportMode);
        clickOnSubmitBtn();
        clickOnCloseBtn();
        return this;
    }

    public ExportReportPage checkSelectionCorectness(String officeVersion, String exportMode) {
        switch (OfficeVersion.valueOf(officeVersion)) {
            case Office_2019_64:
                assertEquals(wait.until(ExpectedConditions.visibilityOf(chosenVersion)).getAttribute("curr-office"), "19");
                break;
            case Office_2016_64:
                assertEquals(wait.until(ExpectedConditions.visibilityOf(chosenVersion)).getAttribute("curr-office"), "17");
                break;
            case Office_2013_64:
                assertEquals(wait.until(ExpectedConditions.visibilityOf(chosenVersion)).getAttribute("curr-office"), "15");
                break;
            case Office_2010_64:
                assertEquals(wait.until(ExpectedConditions.visibilityOf(chosenVersion)).getAttribute("curr-office"), "12");
                break;
            case Office_2007_2010:
                assertEquals(wait.until(ExpectedConditions.visibilityOf(chosenVersion)).getAttribute("curr-office"), "10");
                break;
        }
        assertEquals(wait.until(ExpectedConditions.visibilityOf(chosenExportMode)).getText(), exportMode);
        return this;
    }


    public ExportReportPage selectExportMode(String exportMode) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOf(exportModeDropdown)));
        select.selectByVisibleText(exportMode);
        return this;
    }

    public void clickOnSubmitBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
    }

    public ExportReportPage clickOnCloseBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(closeBtn)).click();
        return this;
    }
}
