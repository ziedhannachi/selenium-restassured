package com.eudonet.pages;

import com.eudonet.base.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(css = "#menuNavBar")
    private WebElement menuNavBar;

    @FindBy(css = "#menuPin")
    private WebElement menuPin;

    //@FindBy(css = "div[class='rightMenuOption'] li:nth-child(1) span:nth-child(1) span:nth-child(2)")
    @FindBy(xpath = "//span[contains(text(),'My Eudonet')]")
    private WebElement myEudonetMenu;

    @FindBy(css = ".decBtn")
    private WebElement logOutBtn;

    @FindBy(css = "#nvb200")
    private WebElement contactsMenu;

    @FindBy(css = "#nvb400")
    private WebElement addressMenu;

    @FindBy(css = "#nvb300")
    private WebElement companiesMenu;

    @FindBy(css = "#tab_header_106000")
    private WebElement mailCampaignMenu;

    @FindBy(css = "#tab_header_100")
    private WebElement businessMenu;

    @FindBy(css = ".top-p.navEntry.has-sub")
    private WebElement plusMenu;

    @FindBy(css = "li[onclick='javascript:setTabOrder()']")
    private WebElement tabOptions;

    @FindBy(xpath = "//*[@id='FrmDefault']/table")
    private WebElement tabOptionsTable;

    @FindBy(xpath = "//*[@class='MainModal']/iframe")
    private WebElement frame1;

    @FindBy(css = "#TdSourceList")
    private WebElement addMenusTable;

    @FindBy(xpath = "//*[@id='TdTargetList']")
    private WebElement removeMenusTable;

    @FindBy(css = "#DivButtonSelectUnit")
    private WebElement addArrow;

    @FindBy(css = "#DivButtonUnselectUnit")
    private WebElement removeArrow;

    @FindBy(css = ".button-green-mid")
    private WebElement submitBtn;

    private By overlaysInfo = By.xpath("//*[contains(@class, \"xrm-widget-waiter\")]");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage menuPinHover() {
        waitForOverlaysToDisappear(overlaysInfo);
        Actions actions = new Actions(driver);
        WebElement pinMenu = wait.until(ExpectedConditions.visibilityOf(menuPin));
        actions.moveToElement(pinMenu).perform();
        return this;
    }

    public MyEudonetPage clickOnMyEudonetMenu() {
        wait.until(ExpectedConditions.visibilityOf(myEudonetMenu));
        wait.until(ExpectedConditions.elementToBeClickable(myEudonetMenu)).click();
        return new MyEudonetPage(driver);
    }

    public LoginPage clickOnLogoutBtn() {
        wait.until(ExpectedConditions.visibilityOf(logOutBtn)).click();
        return new LoginPage(driver);
    }

    public String getLogoutBtnText() {
        menuPinHover();
        return wait.until(ExpectedConditions.visibilityOf(logOutBtn)).getText();
    }

    public HomePage clickOnTabOptionsMenu() {
        wait.until(ExpectedConditions.visibilityOf(tabOptions)).click();
        return this;
    }

    public void clickOnAddArrow() {
        wait.until(ExpectedConditions.elementToBeClickable(addArrow)).click();
    }

    public void clickOnSubmitBtn() {
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
    }

    public void clickOnRemoveArrow() {
        wait.until(ExpectedConditions.elementToBeClickable(removeArrow)).click();
    }

    public HomePage menuHoverElement(String menuName) {
        switch (menuName) {
            case "Contacts":
                hoverElement(contactsMenu);
                break;
            case "+":
                hoverElement(plusMenu);
                break;
            case "Address":
                hoverElement(addressMenu);
                break;
            case "Companies":
                hoverElement(companiesMenu);
                break;
            case "Mail campaigns":
                hoverElement(mailCampaignMenu);
                break;
            case "Business":
                hoverElement(businessMenu);
                break;
        }
        return this;
    }

    private HomePage hoverElement(WebElement element) {
        waitForOverlaysToDisappear(overlaysInfo);
        wait.until(ExpectedConditions.visibilityOf(menuNavBar));
        if (checkEntryInMenuBar(menuNavBar, element.getText())) {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
        }
        return this;
    }

    private boolean checkEntryInMenuBar(WebElement menuBar, String entryName) {
        List<WebElement> entries = menuBar.findElements(By.xpath("li"));
        for (WebElement entry : entries) {
            if (entryName.equals(entry.getText())) {
                return true;
            }
        }
        return false;
    }

    private WebElement getRowFromTable(WebElement table, boolean addMode, String rowText) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame1));
        wait.until(ExpectedConditions.visibilityOf(table));
        wait.until(ExpectedConditions.elementToBeClickable(table));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            List<WebElement> tds = row.findElements(By.tagName("td"));
            for (WebElement td : tds) {
                List<WebElement> effectiveRows;
                if (addMode) {
                    effectiveRows = td.findElements(By.xpath("//*[@id='TdSourceList']/div/div"));
                } else {
                    effectiveRows = td.findElements(By.xpath("//*[@id='TdTargetList']/div/div"));
                }
                for (WebElement effectiveRow : effectiveRows) {
                    if (rowText.equals(effectiveRow.getText())) {
                        return effectiveRow;
                    }
                }
            }
        }
        return null;
    }

    public HomePage addNewEntryInMenuBar(String entryName) {
        waitForOverlaysToDisappear(overlaysInfo);
        wait.until(ExpectedConditions.visibilityOf(menuNavBar));
        if (!checkEntryInMenuBar(menuNavBar, entryName)) {
            hoverElement(plusMenu);
            clickOnTabOptionsMenu();
            WebElement menuToAdd = getRowFromTable(tabOptionsTable, true, entryName);
            wait.until(ExpectedConditions.elementToBeClickable(menuToAdd)).click();
            clickOnAddArrow();
            driver.switchTo().defaultContent();
            clickOnSubmitBtn();
            driver.navigate().refresh();
            if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
                Alert alert = driver.switchTo().alert();
                alert.accept();
            }
        }
        return this;
    }

    public HomePage removeEntryInMenuBar(String entryName) {
        waitForOverlaysToDisappear(overlaysInfo);
        wait.until(ExpectedConditions.visibilityOf(menuNavBar));
        if (checkEntryInMenuBar(menuNavBar, entryName)) {
            hoverElement(plusMenu);
            clickOnTabOptionsMenu();
            WebElement menuToRemove = getRowFromTable(tabOptionsTable, false, entryName);
            wait.until(ExpectedConditions.elementToBeClickable(menuToRemove)).click();
            clickOnRemoveArrow();
            driver.switchTo().defaultContent();
            clickOnSubmitBtn();
            driver.navigate().refresh();
            if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
                Alert alert = driver.switchTo().alert();
                alert.accept();
            }
        }
        return this;
    }

    public boolean entryIsPresentInMenuBar(String menuEntry) {
        waitForOverlaysToDisappear(overlaysInfo);
        wait.until(ExpectedConditions.visibilityOf(menuNavBar));
        return checkEntryInMenuBar(menuNavBar, menuEntry);
    }

    public HomePage addNewEntryFromSubmenu(String menuEntry) {
        waitForOverlaysToDisappear(overlaysInfo);
        wait.until(ExpectedConditions.visibilityOf(menuNavBar));
        if (checkEntryInMenuBar(menuNavBar, menuEntry)) {
            hoverElement(contactsMenu);

        }
        return this;
    }
}
