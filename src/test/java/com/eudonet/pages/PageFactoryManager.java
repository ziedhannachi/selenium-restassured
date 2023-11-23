package com.eudonet.pages;


import org.openqa.selenium.WebDriver;

public class PageFactoryManager {
    public static LoginPage loginPage;
    public static HomePage homePage;
    public static MyEudonetPage myEudonetPage;
    public static ChooseLanguagePage chooseLanguagePage;
    public static ExportReportPage exportReportPage;
    public static ContactsPage contactsPage;
    public static AddressPage addressPage;
    public static CompaniesPage companiesPage;
    public static ReportingPage reportingPage;
    public static EmailCampaignPage emailCampaignPage;
    public static BusinessPage businessPage;


    public static LoginPage getLoginPage(WebDriver driver) {
        return loginPage == null ? new LoginPage(driver) : loginPage;
    }

    public static HomePage getHomePage(WebDriver driver) {
        return homePage == null ? new HomePage(driver) : homePage;
    }

    public static MyEudonetPage getMyEudonetPage(WebDriver driver) {
        return myEudonetPage == null ? new MyEudonetPage(driver) : myEudonetPage;
    }

    public static ChooseLanguagePage getChooseLanguagePage(WebDriver driver) {
        return chooseLanguagePage == null ? new ChooseLanguagePage(driver) : chooseLanguagePage;
    }

    public static ExportReportPage getExportReportPage(WebDriver driver) {
        return exportReportPage == null ? new ExportReportPage(driver) : exportReportPage;
    }

    public static ContactsPage getContactsPage(WebDriver driver) {
        return contactsPage == null ? new ContactsPage(driver) : contactsPage;
    }

    public static AddressPage getAddressPage(WebDriver driver) {
        return addressPage == null ? new AddressPage(driver) : addressPage;
    }

    public static CompaniesPage getCompaniesPage(WebDriver driver) {
        return companiesPage == null ? new CompaniesPage(driver) : companiesPage;
    }

    public static ReportingPage getReportingPage(WebDriver driver) {
        return reportingPage == null ? new ReportingPage(driver) : reportingPage;
    }

    public static EmailCampaignPage getEmailCampaignPage(WebDriver driver) {
        return emailCampaignPage == null ? new EmailCampaignPage(driver) : emailCampaignPage;
    }

    public static BusinessPage getBusinessPage(WebDriver driver) {
        return businessPage == null ? new BusinessPage(driver) : businessPage;
    }
}
