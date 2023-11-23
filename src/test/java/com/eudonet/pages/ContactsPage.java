package com.eudonet.pages;

import com.eudonet.base.BasePage;
import com.eudonet.objects.BusinessAddress;
import com.eudonet.objects.Contact;
import com.eudonet.utils.FakerUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ContactsPage extends BasePage {

    private Contact contact;
    private BusinessAddress businessAddress;

    private By waitOverlay = By.cssSelector("#waiter");

    @FindBy(css = "li[id='nvb200'] li[class='sbmA'] li:nth-child(1)")
    private WebElement addNewRecord;

    @FindBy(css = "li[id='nvb200'] li[class='sbmA'] li:nth-child(2)")
    private WebElement listOfRecords;

    @FindBy(css = "div[id='AdvFltMenu'] span")
    private WebElement advancedFilter;

    @FindBy(xpath = "//*[@class='MainModal']/iframe")
    private WebElement frame1;

    @FindBy(xpath = "//div[@class='ToolbarModal ToolbarModalRight']/following::div/iframe")
    private WebElement frame2;

    @FindBy(css = "#eTxtSrch")
    private WebElement searchInput;

    @FindBy(css = "#spanAddFileFromPopup")
    private WebElement addBtn;

    @FindBy(css = "a[title='New file Contacts']")
    private WebElement addBtn1;

    @FindBy(css = "#COL_200_202_0_0_0")
    private WebElement firstNameFld;

    @FindBy(css = "#COL_200_201_0_0_0")
    private WebElement lastNameFld;

    @FindBy(css = "#COL_200_204_0_0_0")
    private WebElement functionFld;

    @FindBy(xpath = "//table[@id='eCatalogEditorSearchResults']")
    private WebElement searchTable;


    @FindBy(xpath = "//table[@id='mt_300']")
    private WebElement companiesTable;

    //private By tableRow = By.xpath("//td[contains(@id,'CatValueResult_')]");

    @FindBy(css = "#COL_200_205_0_0_0")
    private WebElement civilityFld;

    @FindBy(css = "#COL_200_206_0_0_0")
    private WebElement phone;

    @FindBy(css = "#COL_200_208_0_0_0")
    private WebElement emailFld;

    @FindBy(css = "#COL_400_301_0_0_0")
    private WebElement businessAddressCompany;

    @FindBy(css = "#eCatalogEditorSearch")
    private WebElement searchCompany;

    @FindBy(css = "#COL_400_402_0_0_0")
    private WebElement street1Address;

    @FindBy(css = "#COL_400_405_0_0_0")
    private WebElement phoneAddress;

    @FindBy(css = "#COL_400_410_0_0_0")
    private WebElement cityAddress;
    @FindBy(css = "#COL_400_403_0_0_0")
    private WebElement countryAddress;

    @FindBy(css = "#COL_400_408_0_0_0")
    private WebElement emailAddress;

    @FindBy(xpath = "//div[@class='button-gray-mid'][normalize-space()='Submit']")
    private WebElement submitBtn;

    @FindBy(css = "#save-mid")
    private WebElement saveBtn;

    @FindBy(css = "#IMG_FILTER_200_208")
    private WebElement emailFilter;

    @FindBy(css = "#IMG_FILTER_200_201")
    private WebElement lastNameFilter;
    @FindBy(css = "")
    private WebElement searchEmailFilter;

    @FindBy(css = "#IMG_FILTER_200_202")
    private WebElement firstNameFilter;

    @FindBy(css = "#IMG_FILTER_200_204")
    private WebElement functionFilter;
    @FindBy(css = "#srchFromExpressFilter")
    private WebElement searchFilter;

    @FindBy(css = ".profile-username-title")
    private WebElement profileUser;

    @FindBy(css = "div[class='group-inner emptyField'] div[class='globalDivComponent d-flex full-width'] div[class='divInput ellipsDiv form-control input-line fname link-container'] a")
    private WebElement emailProfile;

    @FindBy(css = "a[onclick='sl(event);'][onmouseover=\"st(event, '<B>E-Mail</B> - Adresse email du contact');\"]")
    private WebElement emailHeader;

    @FindBy(css = "a[onclick='sl(event);'][onmouseover=\"st(event, '<B>First name</B> - Pr&#233;nom');\"]")
    private WebElement firstNameHeader;

    @FindBy(css = "a[onclick='sl(event);'][onmouseover=\"st(event, '<B>Last Name</B> - Nom');\"]")
    private WebElement lastNameHeader;

    @FindBy(css = "a[onclick='sl(event);'][onmouseover=\"st(event, '<B>Function</B> - Fonction du contact');\"]")
    private WebElement functionHeader;

    @FindBy(xpath = "//*[@id='ePopupDiv']/ul[2]")
    private WebElement filterTable;

    @FindBy(xpath = "//table[@id='mt_200']")
    private WebElement contactsTable;

    @FindBy(xpath = "//*[@id='ePopupDiv']/ul[2]/li/span[contains(., 'begins')]")
    private WebElement firstRow;

    public ContactsPage(WebDriver driver) {
        super(driver);
    }

    public ContactsPage enterFirstName(String firstName) {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(firstNameFld));
        element.clear();
        element.sendKeys(firstName);
        waitForOverlaysToDisappear(waitOverlay);
        return this;
    }

    public ContactsPage enterLastNameWhenSearch(String lastName) {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(searchInput));
        wait.until(ExpectedConditions.elementToBeClickable(searchInput));
        element.clear();
        element.sendKeys(lastName);
        return this;
    }

    public ContactsPage enterLastName(String lastName) {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(lastNameFld));
        element.clear();
        element.sendKeys(lastName);
        return this;
    }

    public ContactsPage enterFunction(String function) {
        wait.until(ExpectedConditions.visibilityOf(functionFld)).click();
        WebElement element = getSelection(searchTable, false, function);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        return this;
    }

    public ContactsPage enterCivility(String civility) {
        wait.until(ExpectedConditions.visibilityOf(civilityFld)).click();
        WebElement element = getSelection(searchTable, false, civility);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        return this;
    }

    public ContactsPage enterPhone(long phoneNumber) {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(phone));
        element.clear();;
        element.sendKeys(String.valueOf(phoneNumber));
        return this;
    }

    public ContactsPage enterMail(String email) {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(emailFld));
        element.clear();
        element.sendKeys(email);
        return this;
    }

    public ContactsPage enterCompany(String company) {
        wait.until(ExpectedConditions.elementToBeClickable(businessAddressCompany)).click();
        wait.until(ExpectedConditions.visibilityOf(searchCompany)).sendKeys(company);
        WebElement element = getSelection(companiesTable, true, company);
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        clickOnSubmitBtn();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame2));
        return this;
    }

    public void clickOnSubmitBtn() {
        waitForOverlaysToDisappear(waitOverlay);
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.visibilityOf(submitBtn));
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
    }

    public void clickOnSaveBtn() {
        wait.until(ExpectedConditions.visibilityOf(saveBtn));
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
    }

    public ContactsPage enterStreet1Address(String street) {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(street1Address));
        element.clear();
        element.sendKeys(street);
        return this;
    }

    public ContactsPage enterPhoneAddress(long phone) {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(phoneAddress));
        element.clear();
        element.sendKeys(String.valueOf(phone));
        return this;
    }

    public ContactsPage enterCityAddress(String city) {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(cityAddress));
        element.clear();
        element.sendKeys(city);
        return this;
    }

    public ContactsPage enterCountryAddress(String country) {
        wait.until(ExpectedConditions.visibilityOf(countryAddress)).click();
        WebElement element = getSelection(searchTable, false, country);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        return this;
    }

    public ContactsPage enterEmailAddress(String email) {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(emailAddress));
        element.clear();
        element.sendKeys(email);
        return this;
    }


    public  WebElement getSelection(WebElement table, boolean withHeader, String entryName) {
        wait.until(ExpectedConditions.visibilityOf(table));
        wait.until(ExpectedConditions.elementToBeClickable(table));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        if (withHeader) {
            tableBody = table.findElement(By.xpath("//table[@id='mt_300']/thead/following-sibling::tbody"));
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


    public ContactsPage handleAddModalWindow(String searchName) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame1));
        enterLastNameWhenSearch(searchName);
        clickOnAddButton();
        driver.switchTo().defaultContent();
        return this;
    }

    public ContactsPage handleNewFileContactsWindow(Contact contact) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame2));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(waitOverlay));
        enterFirstName(contact.getFirstName());
        enterLastName(contact.getLastName());
        enterFunction(contact.getFunction());
        enterCivility(contact.getCivility());
        enterPhone(contact.getPhone());
        enterMail(contact.getEmail());
        driver.switchTo().defaultContent();
        return this;
    }

    private ContactsPage handleBusinessAddressWindow(BusinessAddress businessAddress) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame2));
        enterStreet1Address(businessAddress.getStreet1());
        enterPhoneAddress(businessAddress.getPhone());
        enterCityAddress(businessAddress.getCity());
        enterCountryAddress(businessAddress.getCountry());
        enterEmailAddress(businessAddress.getEmail());
        enterCompany(businessAddress.getCompany());
        driver.switchTo().defaultContent();
        clickOnSaveBtn();
        return this;
    }

    public void clickOnAddButton() {
        waitForOverlaysToDisappear(waitOverlay);
        wait.until(ExpectedConditions.visibilityOf(addBtn));
        wait.until(ExpectedConditions.elementToBeClickable(addBtn)).click();
    }

    public ContactsPage clickOnAddNewRecord() {
        HomePage homePage = new HomePage(driver);
        homePage.menuHoverElement("Contacts");
        wait.until(ExpectedConditions.visibilityOf(addNewRecord)).click();

        contact = new Contact("Automation_" + new FakerUtils().generateFirstName(),
                "Automation_" + new FakerUtils().generateLastName(), "Tester", "Mr",
                new FakerUtils().generateRandomNumber(), "vichiritoiu@pentalog.com");

        businessAddress = new BusinessAddress("TESTING FACTORY S6",
                "street" + new FakerUtils().generateRandomNumber(), new FakerUtils().generateRandomNumber(),
                "Timisoara", "Romania", "vichiritoiu@pentalog.com");

        handleAddModalWindow(contact.getLastName());
        handleNewFileContactsWindow(contact);
        handleBusinessAddressWindow(businessAddress);
        wait.until(ExpectedConditions.visibilityOf(profileUser));
        return this;
    }

    public ContactsPage clickOnListOfRecords() {
        wait.until(ExpectedConditions.visibilityOf(listOfRecords));
        wait.until(ExpectedConditions.elementToBeClickable(listOfRecords)).click();
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
    public void filter(String columnHeader, String valueFilter) {
        switch (columnHeader) {
            case "First name":
                hoverElement(firstNameHeader);
                clickOnElementFilter(firstNameFilter);
                break;
            case "Last Name":
                hoverElement(lastNameHeader);
                clickOnElementFilter(lastNameFilter);
                break;
            case "Function":
                hoverElement(functionHeader);
                clickOnElementFilter(functionFilter);
                break;
        }
        filterOnTable(filterTable, valueFilter);
    }

    public  List<String> getFilteringResults(WebElement table, String columnHeader, String valueFilter) throws InterruptedException {
        List<String> searchedValues = new ArrayList<>();
        Thread.sleep(1000);
        WebElement tableBody = table.findElement(By.xpath("//table[@id='mt_200']/thead/following-sibling::tbody"));
        List<WebElement> rows = tableBody.findElements(By.xpath("//tr[contains(@class, 'line')]"));
        for (WebElement row : rows) {
            List<WebElement> tds = row.findElements(By.tagName("td"));
            for (WebElement td : tds) {
                switch (columnHeader) {
                    case "First name":
                    case "Last name":
                    case "Function":
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
        List<String> values = getFilteringResults(contactsTable, columnHeader, valueFilter);
        switch (columnHeader) {
            case "E-Mail":
                for (String value : values) {
                    assertTrue(value.equals(valueFilter));
                }
                break;
            case "First name":
            case "Last Name":
            case "Function":
                for (String value : values) {
                    assertTrue(value.contains(valueFilter));
                }
                break;
        }
    }

    public ContactsPage enterTextInSearchFilter(String searchedText) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(searchFilter));
        element.sendKeys(searchedText);
        return this;
    }

    public String getLastNameHeaderName() {
        return wait.until(ExpectedConditions.visibilityOf(lastNameHeader)).getText();
    }

    public void checkNewEntryPresence() {
        assertEquals(emailProfile.getText(), contact.getEmail());
        //assertEquals(profileUser.getText().trim(), contact.getFirstName() + contact.getLastName());
    }

    private ContactsPage hoverElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        return this;
    }
}
