package com.eudonet.stepsdefinition;

import com.eudonet.constants.Language;
import com.eudonet.context.TestContext;
import com.eudonet.pages.ChooseLanguagePage;
import com.eudonet.pages.LoginPage;
import com.eudonet.pages.MyEudonetPage;
import com.eudonet.pages.PageFactoryManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;

public class ChooseLanguageStepDefinition {

    private ChooseLanguagePage chooseLanguagePage;

    private final TestContext context;

    public ChooseLanguageStepDefinition(TestContext context) {
        this.context = context;
        this.chooseLanguagePage = PageFactoryManager.getChooseLanguagePage(context.driver);
    }

    @When("I select a {language} from the drop down list")
    public void iSelectAFromTheDropDownList(Language language) {
        chooseLanguagePage.selectLanguageFromDropdown(language.name()).clickOnSubmitBtn();
        chooseLanguagePage.clickOnCloseBtn();
        Alert alert = context.driver.switchTo().alert();
        alert.accept();
    }
}
