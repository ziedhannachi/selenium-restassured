package com.eudonet.stepsdefinition;

import com.eudonet.context.TestContext;
import com.eudonet.pages.EmailCampaignPage;
import com.eudonet.pages.PageFactoryManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class EmailCampaignPageStepDefinition {

    private EmailCampaignPage emailCampaignPage;
    private TestContext context;

    public EmailCampaignPageStepDefinition(TestContext context) {
        this.context = context;
        this.emailCampaignPage = PageFactoryManager.getEmailCampaignPage(context.driver);
    }

    @And("user creates a new email campaign")
    public void userCreatesANewEmailCampaign() {
        this.emailCampaignPage.createNewEmailCampaign();
    }

    @Then("the email campaign with is created")
    public void theEmailCampaignWithIsCreated() {
        this.emailCampaignPage.findEmailCampaign();
    }

    @And("the email campaign is found in email campaigns list of records")
    public void theEmailCampaignIsFoundInEmailCampaignsListOfRecords() throws InterruptedException {
        this.emailCampaignPage.verifyEmailCampaignExistence();
    }
}
