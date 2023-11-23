package com.eudonet.pages;

import com.eudonet.base.BasePage;
import com.eudonet.objects.Business;
import com.eudonet.utils.FakerUtils;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BusinessPage extends BasePage {

    private Business business;

    private By waitOverlay = By.cssSelector("#waiter");
    @FindBy(css = "li[id='nvb100'] li[class='sbmA'] li:nth-child(2)")
    private WebElement listOfRecords;

    @FindBy(css = "a[onclick='sl(event);'][onmouseover=\"st(event, '<B>Business</B>');\"]")
    private WebElement businessHeader;

    @FindBy(css = "#IMG_FILTER_100_101")
    private WebElement businessFilter;

    @FindBy(css = "a[onclick='sl(event);'][onmouseover=\"st(event, '<B>Decision</B> - Facteurs de d&#233;cision');\"]")
    private WebElement decisionHeader;

    @FindBy(css = "#IMG_FILTER_100_110")
    private WebElement decisionFilter;

    @FindBy(css = "a[onclick='sl(event);'][onmouseover=\"st(event, '<B>Source</B>');\"]")
    private WebElement sourceHeader;

    @FindBy(css = "#IMG_FILTER_100_111")
    private WebElement sourceFilter;

    @FindBy(css = "a[onclick='sl(event);'][onmouseover=\"st(event, '<B>Type</B>');\"]")
    private WebElement typeHeader;

    @FindBy(css = "#IMG_FILTER_100_108")
    private WebElement typeFilter;

    @FindBy(css = "li[id='nvb100'] li[class='sbmA'] li:nth-child(3)")
    private WebElement advancedSearch;

    @FindBy(xpath = "//*[@class='MainModal']/iframe")
    private WebElement frame1;

    @FindBy(xpath = "//div[@class='ToolbarModal ToolbarModalRight']/following::div/iframe")
    private WebElement frame2;

    @FindBy(xpath = "//*[contains(text(),'Catalogue :')]/ancestor::div/div[2]/iframe")
    private WebElement frame3;

    @FindBy(css = "a[title='New file Business']")
    private WebElement addBtn;

    @FindBy(xpath = "//*[@class='eTxtSrch']")
    private WebElement textSearch;

    @FindBy(css = "#COL_100_101_0_0_0")
    private WebElement businessName;

    @FindBy(css = "#COL_100_105_0_0_0")
    private WebElement inputProduct;

    @FindBy(css = "#COL_100_108_0_0_0")
    private WebElement inputType;

    @FindBy(css = "#COL_100_111_0_0_0")
    private WebElement inputSource;

    @FindBy(css = "#COL_100_110_0_0_0")
    private WebElement inputDecision;

    @FindBy(css = "#eCatalogEditorSearch")
    private WebElement searchInput;

    @FindBy(xpath = "//table[@id='eCatalogEditorSearchResults']")
    private WebElement searchTable;

    @FindBy(xpath = "//div[@id='eCEDValues']")
    private WebElement typesTable;

    @FindBy(css = "#ok-mid")
    private WebElement submitBtn;

    @FindBy(css = "#save-mid")
    private WebElement finalSubmitBtn;

    @FindBy(xpath = "//table[@id='mt_100']")
    private WebElement businessesTable;

    @FindBy(css = "#srchFromExpressFilter")
    private WebElement searchFilter;

    @FindBy(xpath = "//*[@id='ePopupDiv']/ul[2]/li/span[contains(., 'begins')]")
    private WebElement firstRow;

    @FindBy(xpath = "//*[@id='ePopupDiv']/ul[2]")
    private WebElement filterTable;

    @FindBy(css = "#BtnSelect")
    private WebElement addArrowBtn;

    @FindBy(css = ".eCatalogEditorMenuItemAdvSpan")
    private WebElement fullListOfTypes;

    @FindBy(xpath = "//input[contains(@id,'COL_100_101_')]")
    private WebElement newAddedBusinessName;

    @FindBy(xpath = "(//table[@id='mt_100']/tbody/tr[contains(@class,'line')])[1]")
    private WebElement firstRowBusinessTable;

    @FindBy(css = "div[id='AdvFltMenu'] span")
    private WebElement advancedFiltersBtn;

    public BusinessPage(WebDriver driver) {
        super(driver);
    }

    public BusinessPage clickOnListOfRecords() {
        wait.until(ExpectedConditions.visibilityOf(listOfRecords));
        wait.until(ExpectedConditions.elementToBeClickable(listOfRecords)).click();
        return this;
    }

    public String getBusinessHeaderName() {
        return wait.until(ExpectedConditions.visibilityOf(businessHeader)).getText();
    }

    public BusinessPage clickOnAdvancedSearchMenu() {
        LocalDateTime myDate = LocalDateTime.now();
        DateTimeFormatter myFormatData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = myDate.format(myFormatData);

        business = new Business("BUSINESS_" + formattedDate + "_" + new FakerUtils().generateRandomNumber(),
                "Vidéo-conférence", "New Biz", "Mailing", "Price");

        HomePage homePage = new HomePage(driver);
        homePage.menuHoverElement("Business");
        wait.until(ExpectedConditions.visibilityOf(advancedSearch)).click();
        waitForOverlaysToDisappear(waitOverlay);
        handleAddModalWindow();
        handleNewFileBusinesssWindow(business);
        return this;
    }

    private BusinessPage handleAddModalWindow() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame1));
        clickOnAddButton();
        driver.switchTo().defaultContent();
        return this;
    }

    private BusinessPage handleNewFileBusinesssWindow(Business business) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(waitOverlay));
        enterBusinessName(business.getName());
        selectProduct(business.getProduct());
        selectType(business.getType());
        selectSource(business.getSource());
        selectDecision(business.getDecision());
        clickOnLastSubmitBtn();
        return this;
    }

    private void selectDecision(String decision) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame2));
        wait.until(ExpectedConditions.visibilityOf(inputDecision));
        wait.until(ExpectedConditions.elementToBeClickable(inputDecision)).click();
        WebElement element = getOtherSelection(typesTable, decision);
        wait.until(ExpectedConditions.visibilityOf(element)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addArrowBtn)).click();
        clickOnSubmitBtn();
    }

    private void selectSource(String source) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame2));
        wait.until(ExpectedConditions.visibilityOf(inputSource)).click();
        WebElement element = getOtherSelection(typesTable, source);
        wait.until(ExpectedConditions.visibilityOf(element)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addArrowBtn)).click();
        clickOnSubmitBtn();
    }

    private void selectType(String type) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame2));
        wait.until(ExpectedConditions.visibilityOf(inputType)).click();
        wait.until(ExpectedConditions.elementToBeClickable(fullListOfTypes)).click();
        WebElement element = getOtherSelection(typesTable, type);
        wait.until(ExpectedConditions.visibilityOf(element)).click();
        clickOnSubmitBtn();
    }

    private void selectProduct(String product) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame2));
        wait.until(ExpectedConditions.elementToBeClickable(inputProduct)).click();
        wait.until(ExpectedConditions.visibilityOf(searchInput)).sendKeys(product);
        WebElement element = getProductSelection(searchTable, false, product);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        driver.switchTo().defaultContent();
    }

    private BusinessPage enterBusinessName(String name) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame2));
        WebElement element = wait.until(ExpectedConditions.visibilityOf(businessName));
        element.clear();
        element.sendKeys(name);
        driver.switchTo().defaultContent();
        return this;
    }

    private void clickOnAddButton() {
        waitForOverlaysToDisappear(waitOverlay);
        wait.until(ExpectedConditions.visibilityOf(addBtn));
        wait.until(ExpectedConditions.elementToBeClickable(addBtn)).click();
    }

    public  WebElement getProductSelection(WebElement table, boolean withHeader, String entryName) {
        wait.until(ExpectedConditions.visibilityOf(table));
        wait.until(ExpectedConditions.elementToBeClickable(table));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        if (withHeader) {
            tableBody = table.findElement(By.xpath("//table[@id='eCatalogEditorSearchResults']/thead/following-sibling::tbody"));
        }
        List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            WebElement td = row.findElement(By.tagName("td"));
            if (entryName.equals(td.getText())) {
                return td;
            }
        }
        return null;
    }

    public  WebElement getOtherSelection(WebElement table, String entryName) {
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.visibilityOf(submitBtn));
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame3));
        wait.until(ExpectedConditions.visibilityOf(table));
        wait.until(ExpectedConditions.elementToBeClickable(table));
        WebElement tableBody = table.findElement(By.tagName("ul"));
        WebElement row = tableBody.findElement(By.xpath("//li[contains(text(),'" + entryName + "')]"));
        return row;
    }

    private void clickOnSubmitBtn() {
        waitForOverlaysToDisappear(waitOverlay);
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.visibilityOf(submitBtn));
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
    }

    private void clickOnLastSubmitBtn() {
        waitForOverlaysToDisappear(waitOverlay);
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.visibilityOf(finalSubmitBtn));
        wait.until(ExpectedConditions.elementToBeClickable(finalSubmitBtn)).click();
    }

    public void checkDataFiltered(String columnHeader, String valueFilter) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(businessHeader));
        wait.until(ExpectedConditions.visibilityOf(typeHeader));
        wait.until(ExpectedConditions.visibilityOf(sourceHeader));
        wait.until(ExpectedConditions.visibilityOf(decisionHeader));
        wait.until(ExpectedConditions.elementToBeClickable(firstRowBusinessTable));
        waitForOverlaysToDisappear(waitOverlay);
        WebElement element = firstRowBusinessTable.findElement(By.xpath("//td[contains(text(),'" + valueFilter + "')]"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        wait.until(ExpectedConditions.textToBePresentInElement(element, valueFilter));
        List<String> values = getFilteringResults(businessesTable, columnHeader, valueFilter);
        switch (columnHeader) {
            case "Decision":
            case "Type":
            case "Source":
                for (String value : values) {
                    assertTrue(value.equals(valueFilter));
                }
                break;
            case "Business":
                for (String value : values) {
                    assertTrue(value.contains(valueFilter));
                }
                break;
        }
    }

    public  List<String> getFilteringResults(WebElement table, String columnHeader, String valueFilter) throws InterruptedException {
        List<String> searchedValues = new ArrayList<>();
        Thread.sleep(1000);
        WebElement tableBody = table.findElement(By.xpath("//table[@id='mt_100']/thead/following-sibling::tbody"));
        List<WebElement> rows = tableBody.findElements(By.xpath("//tr[contains(@class, 'line')]"));
        for (WebElement row : rows) {
            List<WebElement> tds = row.findElements(By.tagName("td"));
            for (WebElement td : tds) {
                switch (columnHeader) {
                    case "Business":
                    case "Decision":
                    case "Type":
                    case "Source":
                        if (td.getText().contains(valueFilter)) {
                            searchedValues.add(td.getText());
                        }
                        break;
                }
            }
        }
        return searchedValues;
    }

    public void filter(String columnHeader, String valueFilter) {
        switch (columnHeader) {
            case "Business":
                hoverElement(businessHeader);
                clickOnElementFilter(businessFilter);
                break;
            case "Decision":
                hoverElement(decisionHeader);
                clickOnElementFilter(decisionFilter);
                break;
            case "Source":
                hoverElement(sourceHeader);
                clickOnElementFilter(sourceFilter);
                break;
            case "Type":
                hoverElement(typeHeader);
                clickOnElementFilter(typeFilter);
                break;
        }
        filterOnTable(filterTable, valueFilter);
    }

    private BusinessPage hoverElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        return this;
    }

    public void clickOnElementFilter(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    private void filterOnTable(WebElement table, String valueFilter) {
        wait.until(ExpectedConditions.visibilityOf(searchFilter));
        wait.until(ExpectedConditions.elementToBeClickable(searchFilter)).sendKeys(valueFilter);
        wait.until(ExpectedConditions.visibilityOf(firstRow));
        List<WebElement> rows = table.findElements(By.tagName("li"));
        rows.get(0).click();
    }

    public void checkNewEntryPresence() {
        wait.until(ExpectedConditions.visibilityOf(newAddedBusinessName));
        assertEquals(newAddedBusinessName.getDomAttribute("value"), business.getName());
    }
}
