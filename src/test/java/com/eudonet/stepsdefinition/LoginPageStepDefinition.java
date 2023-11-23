package com.eudonet.stepsdefinition;

import com.eudonet.constants.EndPoint;
import com.eudonet.context.TestContext;
import com.eudonet.constants.Language;
import com.eudonet.objects.LoginDetails;
import com.eudonet.objects.User;
import com.eudonet.pages.LoginPage;
import com.eudonet.pages.PageFactoryManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginPageStepDefinition {
    private final LoginPage loginPage;
    private final TestContext context;

    public LoginPageStepDefinition(TestContext context) {
        this.context = context;
        this.loginPage = PageFactoryManager.getLoginPage(context.driver);
    }

    @Given("the Login Page is loaded")
    public void theLoginPageIsLoaded() {
        loginPage.load(EndPoint.LOGIN.url);
    }

    @When("I click on any {language} tab")
    public void iClickOnAnyTab(Language language) {
        loginPage.clickLanguageTab(language.name());
    }

    @Then("the following data are translated correct")
    public void theFollowingDataAreTranslatedCorrect(LoginDetails loginDetails) {
        context.loginDetails = loginDetails;
        assertEquals(loginDetails.getLogoLabel(), loginPage.getLogoLabelText());
        assertEquals(loginDetails.getClientAreaLabel(),loginPage.getClientAreaLabelText());
        assertEquals(loginDetails.getClientPasswordLabel(), loginPage.getClientPasswordLabelText());
        assertEquals(loginDetails.getYourDataBaseLabel(), loginPage.getClientDatabaseLabelText());
        assertEquals(loginDetails.getYourUsernameLabel(), loginPage.getUsernameLabelText());
        assertEquals(loginDetails.getPasswordLabel(), loginPage.getPasswordLabelText());
        assertEquals(loginDetails.getForgotYourPasswordLink(), loginPage.getForgotPasswordLabelText());
        assertEquals(loginDetails.getLoginBtnText(), loginPage.getLoginBtnText());
    }

    @When("user enters invalid credentials login will be unsuccessful")
    public void enterInvalidCredentials(User user) {
        context.user = user;
        loginPage.clickLanguageTab("ENGLISH");
        loginPage.login(context.user);
    }

    @Then("an {string} is displayed")
    public void anErrorMessageIsDisplayed(String errorMessage) {
        assertTrue(loginPage.getErrorMessage().equals(errorMessage));
    }

    @When("user enter login credentials login is successful")
    public void enterValidCredentials(User user) {
        context.user = user;
        loginPage.clickLanguageTab("ENGLISH");
        loginPage.login(context.user);
    }
}
