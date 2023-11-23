package com.eudonet.pages;

import com.eudonet.base.BasePage;
import com.eudonet.utils.FakerUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class EmailCampaignPage extends BasePage {

    @FindBy(xpath = "//span[contains(text(),'Email campaign')]")
    private WebElement emailCampaignMenu;

    @FindBy(css = "label[for='rdo_208']")
    private WebElement conctactEmailRadioBtn;

    @FindBy(css = "#tpl_21")
    private WebElement welcomeTemplate;

    @FindBy(css = "#next_btn-mid")
    private WebElement nextBtn;

    @FindBy(xpath = "(//div[contains(text(),'Submit')])")
    private WebElement submitBtn;

    @FindBy(xpath = "//td[contains(text(),'Email template')]")
    private WebElement emailTemplateModalWnd;

    @FindBy(xpath = "//td[contains(text(),'Customize the subject of the email')]")
    private WebElement subjectEmailWnd;

    @FindBy(css = ".value-container")
    private WebElement subject;

    @FindBy(xpath = "//*[@class='MainModal']/iframe")
    private WebElement frame1;

    @FindBy(xpath = "//*[contains(text(),'Email Wizard')]/ancestor::div/div[2]/iframe")
    private WebElement frame2;

    @FindBy(xpath = "(//*[@class='MainModal']/iframe)[last()]")
    private WebElement frame3;

    @FindBy(xpath = "//div[@id='cke_1_contents']/span[@id='cke_17']/following-sibling::iframe")
    private WebElement frame4;

    @FindBy(css = "#savecampaign_btn-mid")
    private WebElement saveCampaignBtn;

    @FindBy(css = "#save_btn-mid")
    private WebElement sendBtn;

    @FindBy(xpath = "//td[contains(text(),'Information')]")
    private WebElement infoModalWnd;

    @FindBy(css = "#cancel-mid")
    private WebElement closeBtn;

    @FindBy(xpath = "//td[contains(text(),'Sending')]")
    private WebElement sendingModalWnd;

    @FindBy(css = "li[id='nvb106000'] li[class='navAction']")
    private WebElement listOfRecords;

    @FindBy(xpath = "//a[contains(text(),'Campaign')]")
    private WebElement campaignHeader;

    @FindBy(css = "#IMG_FILTER_106000_106001")
    private WebElement campaignFilter;

    @FindBy(xpath = "//*[@id='ePopupDiv']/ul[2]")
    private WebElement filterTable;

    @FindBy(css = "#srchFromExpressFilter")
    private WebElement searchFilter;

    @FindBy(xpath = "//*[@id='ePopupDiv']/ul[2]/li/span[contains(., 'begins')]")
    private WebElement firstRow;

    @FindBy(xpath = "//table[@id='mt_106000']")
    private WebElement emailCampaignsTable;
    private By waitOverlay = By.cssSelector("#waiter");


    @FindBy(css = "body")
    private WebElement inputSubject;

    public EmailCampaignPage(WebDriver driver) {
        super(driver);
    }

    public HomePage menuPinHover() {
        return new HomePage(driver).menuPinHover();
    }

    public void createNewEmailCampaign() {
        menuPinHover();
        clickOnEmailCampaignMenu();
        recipientsStep();
        modelSelectionStep();
        designStep();
        contentsAndLinksStep();
        campaignInfo();
        checksBeforeSending();
        sendingOptions();
    }

    private void sendingOptions() {
        clickOnSaveCampaignBtn();
        wait.until(ExpectedConditions.visibilityOf(infoModalWnd));
        wait.until(ExpectedConditions.elementToBeClickable(closeBtn)).click();
        clickOnSendBtn();
        wait.until(ExpectedConditions.visibilityOf(sendingModalWnd));
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
        wait.until(ExpectedConditions.visibilityOf(infoModalWnd));
        wait.until(ExpectedConditions.elementToBeClickable(closeBtn)).click();
    }

    private void clickOnSendBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(sendBtn)).click();
    }

    private void clickOnSaveCampaignBtn() {
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(saveCampaignBtn)).click();
    }

    private void checksBeforeSending() {
        clickOnNextBtn();
    }

    private void campaignInfo() {
        clickOnNextBtn();
    }

    private void contentsAndLinksStep() {
        clickOnNextBtn();
    }

    private void designStep() {
        wait.until(ExpectedConditions.visibilityOf(emailTemplateModalWnd));
        clickOnSubmitBtn();
        fillInSubject();
    }

    private void fillInSubject() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame2));
        wait.until(ExpectedConditions.elementToBeClickable(subject)).click();
        String emailSubject = "subject" + new FakerUtils().generateRandomNumber();
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.visibilityOf(subjectEmailWnd));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame3));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame4));
        wait.until(ExpectedConditions.visibilityOf(inputSubject));
        wait.until(ExpectedConditions.elementToBeClickable(inputSubject)).sendKeys(emailSubject);
        wait.until(ExpectedConditions.textToBePresentInElement(inputSubject, emailSubject));
        clickOnSubmitBtn();
        clickOnNextBtn();
    }

    private void clickOnSubmitBtn() {
        driver.switchTo().defaultContent();
        wait.until((ExpectedConditions.visibilityOf(submitBtn)));
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
    }

    private void modelSelectionStep() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame2));
        wait.until(ExpectedConditions.elementToBeClickable(welcomeTemplate)).click();
        clickOnNextBtn();
    }

    private void clickOnEmailCampaignMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(emailCampaignMenu)).click();
    }

    private void clickOnNextBtn() {
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(nextBtn)).click();
    }

    private void recipientsStep() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame2));
        waitForOverlaysToDisappear(waitOverlay);
        wait.until(ExpectedConditions.visibilityOf(conctactEmailRadioBtn));
        wait.until(ExpectedConditions.elementToBeClickable(conctactEmailRadioBtn)).click();
        clickOnNextBtn();
    }

    public void findEmailCampaign() {
        displayEmailCampaigns();
        LocalDateTime myDate = LocalDateTime.now();
        DateTimeFormatter myFormatData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = myDate.format(myFormatData);
        filter("Campaign " + formattedDate);
    }

    private void displayEmailCampaigns() {
        HomePage homePage = new HomePage(driver);
        homePage.addNewEntryInMenuBar("Mail campaigns");
        homePage.menuHoverElement("Mail campaigns");
        clickOnListOfRecords();
        wait.until(ExpectedConditions.visibilityOf(campaignHeader));
    }

    public EmailCampaignPage clickOnListOfRecords() {
        wait.until(ExpectedConditions.visibilityOf(listOfRecords));
        wait.until(ExpectedConditions.elementToBeClickable(listOfRecords)).click();
        return this;
    }

    private void filterOnTable(WebElement table, String valueFilter) {
        wait.until(ExpectedConditions.visibilityOf(searchFilter));
        wait.until(ExpectedConditions.elementToBeClickable(searchFilter)).sendKeys(valueFilter);
        wait.until(ExpectedConditions.visibilityOf(firstRow));
        List<WebElement> rows = table.findElements(By.tagName("li"));
        rows.get(0).click();
    }

    public void filter(String valueFilter) {
        hoverElement(campaignHeader);
        clickOnElementFilter(campaignFilter);
        filterOnTable(filterTable, valueFilter);
    }

    private EmailCampaignPage hoverElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        return this;
    }

    private void clickOnElementFilter(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void verifyEmailCampaignExistence() throws InterruptedException {
        LocalDateTime myDate = LocalDateTime.now();
        DateTimeFormatter myFormatData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = myDate.format(myFormatData);
        checkDataFiltered("Campaign " + formattedDate);
    }

    public List<String> getFilteringResults(WebElement table, String valueFilter) throws InterruptedException{
        List<String> searchedValues = new ArrayList<>();
        Thread.sleep(1000);
        WebElement tableBody = table.findElement(By.xpath("//table[@id='mt_106000']/thead/following-sibling::tbody"));
        List<WebElement> rows = tableBody.findElements(By.xpath("//tr[contains(@class, 'line')]"));
        for (WebElement row : rows) {
            List<WebElement> tds = row.findElements(By.tagName("td"));
            for (WebElement td : tds) {
                if (td.getText().contains(valueFilter)) {
                    searchedValues.add(td.getText());
                }
            }
        }
        return searchedValues;
    }

    public void checkDataFiltered(String valueFilter) throws InterruptedException {
        List<String> values = getFilteringResults(emailCampaignsTable, valueFilter);
        for (String value : values) {
            assertTrue(value.contains(valueFilter));
        }
    }
}
