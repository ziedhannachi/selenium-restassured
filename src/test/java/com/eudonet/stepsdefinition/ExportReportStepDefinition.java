package com.eudonet.stepsdefinition;

import com.eudonet.context.TestContext;
import com.eudonet.pages.ExportReportPage;
import com.eudonet.pages.PageFactoryManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ExportReportStepDefinition {

    private ExportReportPage exportReportPage;

    private final TestContext context;

    public ExportReportStepDefinition(TestContext context) {
        this.context = context;
        this.exportReportPage = PageFactoryManager.getExportReportPage(context.driver);
    }

    @When("I select an {string} version and an {string} mode")
    public void iSelectVersionAndMode(String officeVersion, String exportMode) {
        exportReportPage.chooseOfficeVersion(officeVersion, exportMode);
    }


    @Then("the {string} version and {string} mode are set correctly")
    public void theVersionAndModeAreSetCorrectly(String officeVersion, String exportMode) {
        exportReportPage.checkSelectionCorectness(officeVersion, exportMode);
    }
}

