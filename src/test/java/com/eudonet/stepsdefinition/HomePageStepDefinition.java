package com.eudonet.stepsdefinition;

import com.eudonet.api.LoginApi;
import com.eudonet.constants.EndPoint;
import com.eudonet.context.TestContext;
import com.eudonet.objects.User;
import com.eudonet.pages.HomePage;
import com.eudonet.pages.LoginPage;
import com.eudonet.pages.PageFactoryManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;

import static org.testng.Assert.*;

public class HomePageStepDefinition {
    private final HomePage homePage;

    private final LoginPage loginPage;

    private final TestContext context;

    public HomePageStepDefinition(TestContext context) {
        this.context = context;
        this.homePage = PageFactoryManager.getHomePage(context.driver);
        this.loginPage = PageFactoryManager.getLoginPage(context.driver);
    }

    @Given("I am logged in")
    public void iAmLoggedIn() {
        LoginApi loginApi = new LoginApi(context.cookies.getCookies());
        loginApi.login();
        homePage.load(EndPoint.HOME.url);
    }

    @Then("the Home Page is loaded")
    public void theHomePageIsLoaded() {
        homePage.load(EndPoint.HOME.url);
    }

    @When("user Logout")
    public LoginPage userLogout() {
        homePage.menuPinHover().clickOnLogoutBtn();
        Alert alert = context.driver.switchTo().alert();
        alert.accept();
        return new LoginPage(context.driver);
    }

    @And("the next {string} translations are correct")
    public void theNextTranslationsAreCorrect(String logOutBtnText) {
        assertEquals(homePage.getLogoutBtnText(), logOutBtnText);
    }

    @Given("I am on the Home Page")
    public void iAmOnTheHomePage() {
        User user = new User("eudonet", "eudonet", "EUDO CLEAN",
                "ADMINISTRATEUR", "EudonetPentalog2023");
        loginPage.load(EndPoint.LOGIN.url);
        loginPage.clickLanguageTab("ENGLISH")
                .login(user);
    }

    @When("I hover over {string} from menu bar")
    public void iHoverOverFromMenuBar(String menuEntry) {
        homePage.menuHoverElement(menuEntry);
    }

    @Given("the {string} menu is {string} in menu bar")
    public void setupMenuInMenuBar(String menuEntry, String presence) {
        if (presence.equals("not present")) {
            homePage.removeEntryInMenuBar(menuEntry);
        } else {
            homePage.addNewEntryInMenuBar(menuEntry);
        }
    }

    @When("I {string} {string} menu in menu bar")
    public void addOrRemoveMenuInMenuBar(String operation, String menuEntry) {
        if (operation.equals("add")) {
            homePage.addNewEntryInMenuBar(menuEntry);
        } else {
            homePage.removeEntryInMenuBar(menuEntry);
        }
    }

    @Then("the {string} menu {string} displayed in menu bar")
    public void theMenuDisplayedMenuBar(String presence, String menuEntry) {
        if (presence.equals("is")) {
            assertTrue(homePage.entryIsPresentInMenuBar(menuEntry));
        } else {
            assertFalse(homePage.entryIsPresentInMenuBar(menuEntry));
        }
    }
}
