package com.eudonet.stepsdefinition;

import com.eudonet.context.TestContext;
import com.eudonet.objects.ChartDetails;
import com.eudonet.pages.ContactsPage;
import com.eudonet.pages.HomePage;
import com.eudonet.pages.PageFactoryManager;
import com.eudonet.pages.ReportingPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import java.util.List;

public class ReportingPageStepDefinition {

    private ReportingPage reportingPage;
    private HomePage homePage;

    private final TestContext context;

    public ReportingPageStepDefinition(TestContext context) {
        this.context = context;
        this.reportingPage = PageFactoryManager.getReportingPage(context.driver);
        this.homePage = PageFactoryManager.getHomePage(context.driver);
    }

    @And("user selects {string} format for report")
    public void userSelectsFormatForReport(String format) {
        this.reportingPage.goToStep3(format);
    }

    @And("user creates a new report which contains parameters using {string}")
    public void userCreatesANewReportWhichContainsParametersUsing(String format, List<String> fields) {
        this.reportingPage.createNewReport(fields, format);
    }

    @Then("the report file with is created")
    public void theReportFileWithIsCreated() {
        this.reportingPage.checkIfReportExists();
    }

    @And("user creates a new chart which contains parameters using {string} and {string}")
    public void userCreatesANewChartWhichContainsParametersUsingAnd(String series, String format, ChartDetails chartDetails) {
        context.chartDetails = chartDetails;
        this.reportingPage.createNewChart(series, format, context.chartDetails);
    }

    @Then("the chart file with is created")
    public void theChartFileWithIsCreated() {
        this.reportingPage.checkIfReportExists();
    }
}
