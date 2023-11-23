package com.eudonet.stepsdefinition;

import com.eudonet.context.TestContext;
import com.eudonet.pages.AddressPage;
import com.eudonet.pages.ContactsPage;
import com.eudonet.pages.HomePage;
import com.eudonet.pages.PageFactoryManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.Assert.assertEquals;

public class AddressPageStepDefinition {

    private AddressPage addressPage;

    private final TestContext context;

    private HomePage homePage;

    public AddressPageStepDefinition(TestContext context) {
        this.context = context;
        this.addressPage = PageFactoryManager.getAddressPage(context.driver);
        this.homePage = PageFactoryManager.getHomePage(context.driver);
    }

    @And("I click on List Of Records submenu on Address menu")
    public AddressPage iClickOnListOfRecordsSubmenuOnAddressMenu() {
        return this.addressPage.clickOnListOfRecords();
    }

    @Then("the list of addresses table is displayed")
    public void theListOfAddressesTableIsDisplayed() {
        assertEquals(addressPage.getAddressHeaderName(), "Address");
    }

    @And("the table with all addresses is displayed")
    public void theTableWithAllAddressesIsDisplayed() {
        this.homePage.addNewEntryInMenuBar("Address");
        this.homePage.menuHoverElement("Address");
        this.addressPage.clickOnListOfRecords();
        theListOfAdressesTableIsDisplayed();
    }

    @Then("the list of  table is displayed")
    public void theListOfAdressesTableIsDisplayed() {
        assertEquals(addressPage.getAddressHeaderName(), "Address");
    }

    @When("user filters addresses using {string} with {string}")
    public void userFiltersAddressesUsingWith(String columnHeader, String searchValue) {
        this.addressPage.filter(columnHeader, searchValue);
    }

    @Then("the selected addresses {string} and {string} are displayed")
    public void theSelectedAddressesAndAreDisplayed(String columnHeader, String searchValue) throws InterruptedException {
        this.addressPage.checkDataFiltered(columnHeader, searchValue);
    }
}
