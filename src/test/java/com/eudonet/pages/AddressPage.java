package com.eudonet.pages;

import com.eudonet.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class AddressPage extends BasePage {

    @FindBy(css = "li[id='nvb400'] li[class='sbmA'] li:nth-child(2)")
    private WebElement listOfRecords;

    @FindBy(css = "a[onclick='sl(event);'][onmouseover=\"st(event, '<B>Address</B>');\"]")
    private WebElement addressHeader;

    @FindBy(css = "a[onclick='sl(event);'][onmouseover=\"st(event, '<B>Company</B>');\"]")
    private WebElement companyHeader;

    @FindBy(css = "#IMG_FILTER_400_301")
    private WebElement companyFilter;

    @FindBy(css = "a[onclick='sl(event);'][onmouseover=\"st(event, '<B>Last Name</B> - Nom');\"]")
    private WebElement lastNameHeader;

    @FindBy(css = "#IMG_FILTER_400_201")
    private WebElement lastNameFilter;

    @FindBy(xpath = "//*[@id='ePopupDiv']/ul[2]")
    private WebElement filterTable;

    @FindBy(css = "#srchFromExpressFilter")
    private WebElement searchFilter;

    @FindBy(xpath = "//*[@id='ePopupDiv']/ul[2]/li/span[contains(., 'begins')]")
    private WebElement firstRow;

    @FindBy(xpath = "//table[@id='mt_400']")
    private WebElement addressTable;

    public AddressPage(WebDriver driver) {
        super(driver);
    }

    public AddressPage clickOnListOfRecords() {
        wait.until(ExpectedConditions.visibilityOf(listOfRecords)).click();
        return this;
    }

    public String getAddressHeaderName() {
        return wait.until(ExpectedConditions.visibilityOf(addressHeader)).getText();
    }

    private void clickOnElementFilter(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void filter(String columnHeader, String valueFilter) {
        switch (columnHeader) {
            case "Company":
                hoverElement(companyHeader);
                clickOnElementFilter(companyFilter);
                break;
            case "Last Name":
                hoverElement(lastNameHeader);
                clickOnElementFilter(lastNameFilter);
                break;
        }
        filterOnTable(filterTable, valueFilter);
    }

    private void filterOnTable(WebElement table, String valueFilter) {
        wait.until(ExpectedConditions.elementToBeClickable(searchFilter)).sendKeys(valueFilter);
        wait.until(ExpectedConditions.visibilityOf(firstRow));
        List<WebElement> rows = table.findElements(By.tagName("li"));
        rows.get(0).click();
    }

    private AddressPage hoverElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        return this;
    }

    public  List<String> getFilteringResults(WebElement table, String columnHeader, String valueFilter) throws InterruptedException {
        List<String> searchedValues = new ArrayList<>();
        Thread.sleep(1000);
        WebElement tableBody = table.findElement(By.xpath("//table[@id='mt_400']/thead/following-sibling::tbody"));
        List<WebElement> rows = tableBody.findElements(By.xpath("//tr[contains(@class, 'line')]"));
        for (WebElement row : rows) {
            List<WebElement> tds = row.findElements(By.tagName("td"));
            for (WebElement td : tds) {
                switch (columnHeader) {
                    case "City":
                    case "Country":
                        if (td.getText().contains(valueFilter)) {
                            searchedValues.add(td.getText());
                        }
                        break;
                }
            }
        }
        return searchedValues;
    }

    public void checkDataFiltered(String columnHeader, String valueFilter) throws InterruptedException {
        List<String> values = getFilteringResults(addressTable, columnHeader, valueFilter);
        switch (columnHeader) {
            case "Last Name":
            case "Company":
                for (String value : values) {
                    assertTrue(value.contains(valueFilter));
                }
                break;
        }
    }
}
