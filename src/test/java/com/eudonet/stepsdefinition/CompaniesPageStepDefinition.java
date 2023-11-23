package com.eudonet.stepsdefinition;

import com.eudonet.context.TestContext;
import com.eudonet.pages.CompaniesPage;
import com.eudonet.pages.HomePage;
import com.eudonet.pages.PageFactoryManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.Assert.assertEquals;

public class CompaniesPageStepDefinition {

    private CompaniesPage companiesPage;
    private final TestContext context;

    private HomePage homePage;

    public CompaniesPageStepDefinition(TestContext context) {
        this.context = context;
        this.companiesPage = PageFactoryManager.getCompaniesPage(context.driver);
        this.homePage = PageFactoryManager.getHomePage(context.driver);
    }

    @And("I click on List Of Records submenu on Companies menu")
    public CompaniesPage iClickOnListOfRecordsSubmenuOnCompaniesMenu() {
        return this.companiesPage.clickOnListOfRecords();
    }

    @Then("the list of companies table is displayed")
    public void theListOfCompaniesTableIsDisplayed() {
        assertEquals(companiesPage.getCompaniesHeaderName(), "Company");
    }

    @When("I add a new company from New submenu")
    public CompaniesPage iAddANewCompanyFromNewSubmenu() {
        return this.companiesPage.clickOnAddNewRecord();
    }


    @Then("the new added company is displayed in companies table")
    public void theNewAddedCompanyIsDisplayedInCompaniesTable() {
        this.companiesPage.checkNewEntryPresence();
    }

    @And("the table with all companies is displayed")
    public void theTableWithAllCompaniesIsDisplayed() {
        this.homePage.addNewEntryInMenuBar("Companies");
        this.homePage.menuHoverElement("Companies");
        this.companiesPage.clickOnListOfRecords();
        theListOfCompaniesTableIsDisplayed();
    }

    @When("user filters companies using {string} with {string}")
    public void userFiltersCompaniesUsingWith(String columnHeader, String searchValue) {
        this.companiesPage.filter(columnHeader, searchValue);
    }

    @Then("the selected companies {string} and {string} are displayed")
    public void theSelectedCompaniesAndAreDisplayed(String columnHeader, String searchValue) throws InterruptedException {
        this.companiesPage.checkDataFiltered(columnHeader, searchValue);
    }
}
