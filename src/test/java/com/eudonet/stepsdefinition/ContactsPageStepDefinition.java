package com.eudonet.stepsdefinition;

import com.eudonet.constants.EndPoint;
import com.eudonet.context.TestContext;
import com.eudonet.objects.User;
import com.eudonet.pages.ContactsPage;
import com.eudonet.pages.HomePage;
import com.eudonet.pages.LoginPage;
import com.eudonet.pages.PageFactoryManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.assertEquals;

public class ContactsPageStepDefinition {

    private ContactsPage contactsPage;
    private HomePage homePage;

    private final TestContext context;

    public ContactsPageStepDefinition(TestContext context) {
        this.context = context;
        this.contactsPage = PageFactoryManager.getContactsPage(context.driver);
        this.homePage = PageFactoryManager.getHomePage(context.driver);
    }

    @When("I click on List Of Records submenu")
    public ContactsPage iClickOnListOfRecordsSubmenu() {
        return this.contactsPage.clickOnListOfRecords();
    }

    @Then("the list of contacts table is displayed")
    public void theListOfContactsTableIsDisplayed() {
        assertEquals(contactsPage.getLastNameHeaderName(), "Last Name");
    }

    @When("I add a new record from New submenu")
    public ContactsPage iAddANewRecordFromNewSubmenu() {
       return this.contactsPage.clickOnAddNewRecord();
    }

    @Then("the new added contact is displayed in contacts table")
    public void theNewAddedContactIsDisplayedInContactsTable() {
        this.contactsPage.checkNewEntryPresence();
    }

    @And("the table with all contacts is displayed")
    public void theTableWithAllContactsIsDisplayed() {
        this.homePage.addNewEntryInMenuBar("Contacts");
        this.homePage.menuHoverElement("Contacts");
        this.contactsPage.clickOnListOfRecords();
        theListOfContactsTableIsDisplayed();
    }

    @When("user filters contacts using {string} with {string}")
    public void userFiltersContactsUsingWith(String columnHeader, String searchValue) {
        this.contactsPage.filter(columnHeader, searchValue);
    }


    @Then("the selected contacts {string} and {string} are displayed")
    public void theSelectedContactsAndAreDisplayed(String columnHeader, String searchValue) throws InterruptedException {
        this.contactsPage.checkDataFiltered(columnHeader, searchValue);
    }
}
