package com.eudonet.pages;

import com.eudonet.base.BasePage;
import com.eudonet.constants.Language;
import com.eudonet.objects.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class LoginPage extends BasePage {
    @FindBy(css = "#txtLoginSpace")
    private WebElement logoLabel;

    @FindBy(css = "#DIV_LANG_00")
    private WebElement frenchTabLanguage;

    @FindBy(css = "#DIV_LANG_01")
    private WebElement englishTabLanguage;

    @FindBy(css = "#DIV_LANG_02")
    private WebElement germanTabLanguage;

    @FindBy(css = "#DIV_LANG_03")
    private WebElement dutchTabLanguage;

    @FindBy(css = "#DIV_LANG_04")
    private WebElement spanishTabLanguage;

    @FindBy(css = "#DIV_LANG_05")
    private WebElement italianTabLanguage;

    @FindBy(css = "label[for='txtLoginSubscriber']")
    private WebElement clientAreaLabel;

    @FindBy(css = "#txtLoginSubscriber")
    private WebElement clientNameFld;

    @FindBy(css = "label[for='txtPasswordSubscriber']")
    private WebElement clientPasswordLabel;
    @FindBy(css = "#txtPasswordSubscriber")
    private WebElement clientPasswordFld;

    @FindBy(css = "label[for='cboBase']")
    private WebElement clientDatabaseLabel;

    @FindBy(id = "cboBase")
    private WebElement yourDatabase;

    @FindBy(css = "label[for='txtUserLogin']")
    private WebElement usernameLabel;

    @FindBy(css = "#txtUserLogin")
    private WebElement usernameFld;

    @FindBy(css = "label[for='txtUserPassword']")
    private WebElement passwordLabel;

    @FindBy(css = "#txtUserPassword")
    private WebElement passwordFld;

    @FindBy(css = ".fieldAction")
    private WebElement forgotPassword;

    @FindBy(css = "div[onclick='authUser();']")
    private WebElement loginBtn;

    @FindBy(css = "#labelUserEr")
    private WebElement errorMessage;

    private By waitOverlay = By.cssSelector("#waiter");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public String getLogoLabelText() {
        return wait.until(ExpectedConditions.visibilityOf(logoLabel)).getText();
    }
    public String getClientAreaLabelText() {
        return wait.until(ExpectedConditions.visibilityOf(clientAreaLabel)).getText();
    }

    public String getClientPasswordLabelText() {
        return wait.until(ExpectedConditions.visibilityOf(clientPasswordLabel)).getText();
    }

    public String getClientDatabaseLabelText() {
        return wait.until(ExpectedConditions.visibilityOf(clientDatabaseLabel)).getText();
    }

    public String getUsernameLabelText() {
        return wait.until(ExpectedConditions.visibilityOf(usernameLabel)).getText();
    }

    public String getPasswordLabelText() {
        return wait.until(ExpectedConditions.visibilityOf(passwordLabel)).getText();
    }

    public String getForgotPasswordLabelText() {
        return wait.until(ExpectedConditions.visibilityOf(forgotPassword)).getText();
    }

    public String getLoginBtnText() {
        return wait.until(ExpectedConditions.visibilityOf(loginBtn)).getText();
    }

    public LoginPage enterClientAreaName(String clientName) {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(clientNameFld));
        element.clear();
        element.sendKeys(clientName);
        return this;
    }

    private void clickOnTab(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public LoginPage clickLanguageTab(String tabLanguageName) {
        switch (Language.valueOf(tabLanguageName)) {
            case FRENCH:
                clickOnTab(frenchTabLanguage);
                break;
            case ENGLISH:
                clickOnTab(englishTabLanguage);
                break;
            case GERMAN:
                clickOnTab(germanTabLanguage);
                break;
            case DUTCH:
                clickOnTab(dutchTabLanguage);
                break;
            case SPANISH:
                clickOnTab(spanishTabLanguage);
                break;
            case ITALIAN:
                clickOnTab(italianTabLanguage);
                break;
        }
        return this;
    }

    public LoginPage enterClientArea(String clientName) {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(clientNameFld));
        element.clear();
        element.sendKeys(clientName.toUpperCase());
        return this;
    }

    public LoginPage enterClientPassword(String clientPassword) {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(clientPasswordFld));
        element.clear();
        element.sendKeys(clientPassword);
        return this;
    }

    public LoginPage selectDatabase(String clientDatabase) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOf(yourDatabase)));
        wait.until(ExpectedConditions.elementToBeClickable(yourDatabase)).click();
        wait.until(ExpectedConditions.textToBePresentInElement(yourDatabase, clientDatabase));
        select.selectByVisibleText(clientDatabase);
        return this;
    }

    public LoginPage enterUsername(String username) {
        wait.until(ExpectedConditions.elementToBeClickable(usernameFld));
        WebElement element = wait.until(ExpectedConditions.visibilityOf(usernameFld));
        element.clear();
        element.sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordFld));
        WebElement element = wait.until(ExpectedConditions.visibilityOf(passwordFld));
        element.sendKeys(password);
        return this;
    }

    public HomePage clickLogInBtn() {
        wait.until(ExpectedConditions.visibilityOf(loginBtn));
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        waitForOverlaysToDisappear(waitOverlay);
        return new HomePage(driver);
    }


    public HomePage login(User user) {
        return enterClientArea(user.getClientArea())
                .enterClientPassword(user.getClientPassword())
                .selectDatabase(user.getDatabase())
                .enterUsername(user.getUsername())
                .enterPassword(user.getPassword())
                .clickLogInBtn();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
    }

}
