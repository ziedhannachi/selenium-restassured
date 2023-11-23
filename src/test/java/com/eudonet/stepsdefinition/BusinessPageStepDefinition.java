package com.eudonet.stepsdefinition;

import com.eudonet.context.TestContext;
import com.eudonet.pages.BusinessPage;
import com.eudonet.pages.HomePage;
import com.eudonet.pages.PageFactoryManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.Assert.assertEquals;

public class BusinessPageStepDefinition {

    private final TestContext context;
    private BusinessPage businessPage;
    private HomePage homePage;

    public BusinessPageStepDefinition(TestContext context) {
        this.context = context;
        this.businessPage = PageFactoryManager.getBusinessPage(context.driver);
        this.homePage = PageFactoryManager.getHomePage(context.driver);
    }

    @And("I click on List Of Records submenu on Business menu")
    public BusinessPage iClickOnListOfRecordsSubmenuOnBusinessMenu() {
        return this.businessPage.clickOnListOfRecords();
    }

    @Then("the list of businesses table is displayed")
    public void theListOfBusinessesTableIsDisplayed() {
        assertEquals(businessPage.getBusinessHeaderName(), "Business");
    }

    @When("I add a new record from Advanced search submenu")
    public BusinessPage iAddANewRecordFromAdvancedSearchSubmenu() {
        return this.businessPage.clickOnAdvancedSearchMenu();
    }

    @And("the table with all businesses is displayed")
    public void theTableWithAllBusinessesIsDisplayed() {
        this.homePage.addNewEntryInMenuBar("Business");
        this.homePage.menuHoverElement("Business");
        this.businessPage.clickOnListOfRecords();
        theListOfBusinessesTableIsDisplayed();
    }

    @When("user filters businesses using {string} with {string}")
    public void userFiltersBusinessesUsingWith(String columnHeader, String searchValue) {
        this.businessPage.filter(columnHeader, searchValue);
    }

    @Then("the selected businesses {string} and {string} are displayed")
    public void theSelectedBusinessesAndAreDisplayed(String columnHeader, String searchValue) throws InterruptedException {
        this.businessPage.checkDataFiltered(columnHeader, searchValue);
    }

    @Then("the new added business is displayed in businesses table")
    public void theNewAddedBusinessIsDisplayedInBusinessesTable() {
        this.businessPage.checkNewEntryPresence();
    }
}
