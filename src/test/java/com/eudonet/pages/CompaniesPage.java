package com.eudonet.pages;

import com.eudonet.base.BasePage;
import com.eudonet.objects.Company;
import com.eudonet.utils.FakerUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CompaniesPage extends BasePage {

    private Company company;

    @FindBy(css = "li[id='nvb300'] li[class='sbmA'] li:nth-child(2)")
    private WebElement listOfRecords;

    @FindBy(css = "a[onclick='sl(event);'][onmouseover=\"st(event, '<B>Company</B>');\"]")
    private WebElement companiesHeader;

    @FindBy(css = "li[id='nvb300'] li[class='sbmA'] li:nth-child(1)")
    private WebElement addNewRecord;

    @FindBy(xpath = "//*[@class='MainModal']/iframe")
    private WebElement frame1;

    @FindBy(xpath = "//div[@class='ToolbarModal ToolbarModalRight']/following::div/iframe")
    private WebElement frame2;

    @FindBy(css = "#eTxtSrch")
    private WebElement searchInput;

    @FindBy(css = "#spanAddFileFromPopup")
    private WebElement addBtn;

    @FindBy(css = "#COL_300_301_0_0_0")
    private WebElement companyName;

    @FindBy(css = "#COL_300_302_0_0_0")
    private WebElement street1Company;


    @FindBy(css = "#COL_300_305_0_0_0")
    private WebElement phoneCompany;

    @FindBy(css = "#COL_300_309_0_0_0")
    private WebElement zipCodeCompany;

    @FindBy(css = "#COL_300_310_0_0_0")
    private WebElement cityCompany;

    @FindBy(css = "#COL_300_303_0_0_0")
    private WebElement countryCompany;

    @FindBy(css = "#save-mid")
    private WebElement saveBtn;

    @FindBy(css = ".profile-username-title")
    private WebElement profileCompany;

    @FindBy(xpath = "//table[@id='eCatalogEditorSearchResults']")
    private WebElement searchTable;

    @FindBy(css = "a[onclick='sl(event);'][onmouseover=\"st(event, '<B>City</B>');\"]")
    private WebElement cityHeader;

    @FindBy(css = "a[onclick='sl(event);'][onmouseover=\"st(event, '<B>Country</B>');\"]")
    private WebElement countryHeader;

    @FindBy(css = "#IMG_FILTER_300_310")
    private WebElement cityFilter;

    @FindBy(css = "#IMG_FILTER_300_303")
    private WebElement countryFilter;

    @FindBy(css = "#srchFromExpressFilter")
    private WebElement searchFilter;

    @FindBy(xpath = "//*[@id='ePopupDiv']/ul[2]/li/span[contains(., 'begins')]")
    private WebElement firstRow;

    @FindBy(xpath = "//*[@id='ePopupDiv']/ul[2]")
    private WebElement filterTable;

    @FindBy(xpath = "//table[@id='mt_300']")
    private WebElement companiesTable;

    public CompaniesPage(WebDriver driver) {
        super(driver);
    }

    public CompaniesPage clickOnListOfRecords() {
        wait.until(ExpectedConditions.visibilityOf(listOfRecords)).click();
        return this;
    }

    public CompaniesPage enterCompanyName(String company) {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(companyName));
        element.clear();
        element.sendKeys(company);
        return this;
    }

    public CompaniesPage enterStreet1Company(String street1) {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(street1Company));
        element.clear();
        element.sendKeys(street1);
        return this;
    }

    public CompaniesPage enterPhoneCompany(long phone) {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(phoneCompany));
        element.clear();
        element.sendKeys(String.valueOf(phone));
        return this;
    }

    public CompaniesPage enterZipCodeCompany(String zip) {
        wait.until(ExpectedConditions.visibilityOf(zipCodeCompany)).click();
        WebElement element = getSelection(searchTable,zip);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        return this;
    }

    public CompaniesPage enterCityCompany(String city) {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(cityCompany));
        element.clear();
        element.sendKeys(city);
        return this;
    }

    public CompaniesPage enterCountryCompany(String country) {
        wait.until(ExpectedConditions.visibilityOf(countryCompany)).click();
        WebElement element = getSelection(searchTable,country);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        return this;
    }

    public void clickOnSaveBtn() {
        wait.until(ExpectedConditions.visibilityOf(saveBtn));
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
    }

    public String getCompaniesHeaderName() {
        return wait.until(ExpectedConditions.visibilityOf(companiesHeader)).getText();
    }

    public CompaniesPage clickOnAddNewRecord() {
        HomePage homePage = new HomePage(driver);
        homePage.menuHoverElement("Companies");
        wait.until(ExpectedConditions.visibilityOf(addNewRecord)).click();

        company = new Company("TESTING FACTORY" + new FakerUtils().generateRandomNumber(),
                "street" + new FakerUtils().generateRandomNumber(), new FakerUtils().generateRandomNumber(),
                "Timisoara", "Romania", "00151");

        handleAddModalWindow(company.getCompanyName());
        handleNewFileCompaniesWindow(company);
        wait.until(ExpectedConditions.visibilityOf(profileCompany));
        return this;
    }

    private void handleNewFileCompaniesWindow(Company company) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame2));
        enterCompanyName(company.getCompanyName());
        enterStreet1Company(company.getStreet1());
        enterPhoneCompany(company.getPhone());
        enterZipCodeCompany(company.getZipCode());
        enterCityCompany(company.getCity());
        enterCountryCompany(company.getCountry());
        driver.switchTo().defaultContent();
        clickOnSaveBtn();
    }

    private CompaniesPage handleAddModalWindow(String companyName) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame1));
        enterCompanyWhenSearch(companyName);
        clickOnAddButton();
        driver.switchTo().defaultContent();
        return this;
    }

    private void clickOnAddButton() {
        wait.until(ExpectedConditions.visibilityOf(addBtn));
        wait.until(ExpectedConditions.elementToBeClickable(addBtn)).click();
    }

    private CompaniesPage enterCompanyWhenSearch(String companyName) {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(searchInput));
        element.clear();
        element.sendKeys(companyName);
        return this;
    }

    public void checkNewEntryPresence() {
        assertEquals(profileCompany.getText(), company.getCompanyName());
    }

    public  WebElement getSelection(WebElement table, String entryName) {
        wait.until(ExpectedConditions.elementToBeClickable(table));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            WebElement td = row.findElement(By.tagName("td"));
            if (entryName.equals(td.getText())) {
                return td;
            }
        }
        return null;
    }

    public void filter(String columnHeader, String valueFilter) {
        switch (columnHeader) {
            case "City":
                hoverElement(cityHeader);
                clickOnElementFilter(cityFilter);
                break;
            case "Country":
                hoverElement(countryHeader);
                clickOnElementFilter(countryFilter);
                break;
        }
        filterOnTable(filterTable, valueFilter);
    }

    private CompaniesPage hoverElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        return this;
    }

    private void clickOnElementFilter(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    private void filterOnTable(WebElement table, String valueFilter) {
        wait.until(ExpectedConditions.elementToBeClickable(searchFilter)).sendKeys(valueFilter);
        wait.until(ExpectedConditions.visibilityOf(firstRow));
        List<WebElement> rows = table.findElements(By.tagName("li"));
        rows.get(0).click();
    }

    public  List<String> getFilteringResults(WebElement table, String columnHeader, String valueFilter) throws InterruptedException {
        List<String> searchedValues = new ArrayList<>();
        Thread.sleep(1000);
        WebElement tableBody = table.findElement(By.xpath("//table[@id='mt_300']/thead/following-sibling::tbody"));
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
        List<String> values = getFilteringResults(companiesTable, columnHeader, valueFilter);
        switch (columnHeader) {
            case "City":
            case "Country":
                for (String value : values) {
                    assertTrue(value.contains(valueFilter));
                }
                break;
        }
    }
}
