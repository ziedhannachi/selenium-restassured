package com.eudonet.stepsdefinition;

import com.eudonet.constants.EndPoint;
import com.eudonet.context.TestContext;
import com.eudonet.objects.User;
import com.eudonet.pages.*;
import io.cucumber.java.en.Given;

public class MyEudonetPageStepDefinition {

    private MyEudonetPage myEudonetPage;
    private LoginPage loginPage;

    private final TestContext context;

    public MyEudonetPageStepDefinition(TestContext context) {
        this.context = context;
        this.myEudonetPage = PageFactoryManager.getMyEudonetPage(context.driver);
        this.loginPage = PageFactoryManager.getLoginPage(context.driver);
    }

    @Given("I am on My Eudonet page")
    public void iAmOnMyEudonetPage() {
        User user = new User("eudonet", "eudonet", "EUDO CLEAN",
                "ADMINISTRATEUR", "EudonetPentalog2023");
        loginPage.load(EndPoint.LOGIN.url);
        loginPage.clickLanguageTab("ENGLISH")
                .login(user)
                .menuPinHover()
                .clickOnMyEudonetMenu();
    }

    @Given("I am on Choose language page")
    public ChooseLanguagePage iAmOnChooseLanguagePage() {
        return this.myEudonetPage.clickOnChooseLanguageLink();
    }

    @Given("I am on Export report page")
    public ExportReportPage iAmOnExportReportPage() {
        return this.myEudonetPage.clickOnExportReportLink();
    }

}
