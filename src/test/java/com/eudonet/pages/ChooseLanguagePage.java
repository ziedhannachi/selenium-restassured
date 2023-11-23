package com.eudonet.pages;

import com.eudonet.base.BasePage;
import com.eudonet.constants.Language;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ChooseLanguagePage extends BasePage {

    @FindBy(css = "#lguser")
    private WebElement languageDropDown;

    @FindBy(css = ".button-green-mid")
    private WebElement submitBtn;

    private final By closeBtn = By.cssSelector("#cancel-mid");

    public ChooseLanguagePage(WebDriver driver) {
        super(driver);
    }

    public ChooseLanguagePage selectLanguage(String language) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOf(languageDropDown)));
        select.selectByVisibleText(language);
        return this;
    }

    public ChooseLanguagePage clickOnSubmitBtn() {
        wait.until(ExpectedConditions.visibilityOf(submitBtn)).click();
        return this;

    }

    public ChooseLanguagePage selectLanguageFromDropdown(String languageName) {
        switch (Language.valueOf(languageName)) {
            case FRENCH:
                selectLanguage("Français");
                break;
            case ENGLISH:
                selectLanguage("English");
                break;
            case GERMAN:
                selectLanguage("Deutsch");
                break;
            case DUTCH:
                selectLanguage("Nederlands");
                break;
            case SPANISH:
                selectLanguage("Español");
                break;
            case ITALIAN:
                selectLanguage("Italiano");
                break;
        }
        return this;
    }

    public ChooseLanguagePage clickOnCloseBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(closeBtn)).click();
        return this;
    }
}
