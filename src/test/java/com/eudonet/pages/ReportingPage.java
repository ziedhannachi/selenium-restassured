package com.eudonet.pages;

import com.eudonet.base.BasePage;
import com.eudonet.objects.ChartDetails;
import com.eudonet.utils.FakerUtils;
import com.github.javafaker.Faker;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ReportingPage extends BasePage {

    String reportName;
    String chartName;


    @FindBy(xpath = "//span[contains(text(),'Export')]")
    public WebElement exportMenu;

    @FindBy(xpath = "//span[contains(text(),'Chart')]")
    public WebElement chartMenu;

    @FindBy(xpath = "//*[@class='MainModal']/iframe")
    private WebElement frame1;

    @FindBy(xpath = "//*[contains(text(),'Report Wizard')]/ancestor::div/div[2]/iframe")
    private WebElement frame2;

    @FindBy(css = ".icon-add")
    private WebElement addNewReportBtn;

    @FindBy(css = "#editor_field_selection")
    private WebElement availableFieldsTable;

    @FindBy(css = "#editor_DivButtonSelectUnit")
    private WebElement addArrow;

    @FindBy(css = "#next_btn-mid")
    private WebElement nextBtn;

    @FindBy(css = ".icon-excel")
    private WebElement excelFormat;

    @FindBy(css = "label[for='editor_format_label_3']")
    private WebElement wordFormat;

    @FindBy(css = "#editor_saveas")
    private WebElement fileNameInput;

    @FindBy(css = "#save_btn-mid")
    private WebElement saveBtn;

    @FindBy(css = "#mt_105000")
    private WebElement reportsTable;

    @FindBy(css = ".button-green-mid")
    private WebElement applyBtn;

    @FindBy(css = "label[for='ChartType1']")
    private WebElement singleSeriesChartTab;

    @FindBy(css = "label[for='ChartType2']")
    private WebElement multiSeriesChartTab;

    @FindBy(css = "#tbChrts_1")
    private WebElement chartsTable;

    @FindBy(css = "#chrt_1_1")
    private WebElement histogram_SS;

    @FindBy(css = "#chrt_1_2")
    private WebElement histogram3D_SS;

    @FindBy(css = "#chrt_2_1")
    private WebElement histogram_MS;

    @FindBy(css = "#chrt_2_2")
    private WebElement histogram3D_MS;


    @FindBy(css = "#EtiquettesFile")
    private WebElement label1_X;

    @FindBy(css = "#EtiquettesField_200")
    private WebElement label2_X;

    @FindBy(css = "#EtiquettesGroup")
    private WebElement label3_X;

    @FindBy(css = "#SeriesFile")
    private WebElement field1_X;

    @FindBy(css = "#SeriesField_200")
    private WebElement field2_X;

    @FindBy(css = "#ValuesFile")
    private WebElement value1_Y;

    @FindBy(css = "#ValuesOperation")
    private WebElement value2_Y;

    @FindBy(css = "#Title")
    private WebElement chartname;

    @FindBy(css = "#editor_saveas")
    private WebElement chartReportName;

    @FindBy(xpath = "//span[contains(@onmouseover,'st(event, 'Email campaign')')]")
    private WebElement emailCampaign;

    public ReportingPage(WebDriver driver) {
        super(driver);
    }

    public HomePage menuPinHover() {
        return new HomePage(driver).menuPinHover();
    }

    public void clickOnExportMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(exportMenu)).click();
    }

    public void clickOnChartMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(chartMenu)).click();
    }

    private void clickOnNextBtn() {
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(nextBtn)).click();
    }

    public void goToStep2() {
        clickOnNextBtn();
    }

    public void goToStep3(String format) {
        clickOnNextBtn();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame2));
        switch (format) {
            case "Microsoft Excel":
                wait.until(ExpectedConditions.elementToBeClickable(excelFormat)).click();
                break;
            case "Microsoft Word":
                wait.until(ExpectedConditions.elementToBeClickable(wordFormat)).click();
                break;
        }
    }

    public void goToStep4() {
        goToStep2();
    }

    public void goToStep5(String format) {
        goToStep2();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame2));
        if (format.equals("Microsoft Excel")) {
            reportName = "contacts_export_" + new FakerUtils().generateRandomNumber() + ".xls";
        } else {
            reportName = "contacts_export_" + new FakerUtils().generateRandomNumber() + ".doc";
        }
        wait.until(ExpectedConditions.elementToBeClickable(fileNameInput)).sendKeys(reportName);
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
    }

    public ReportingPage clickOnAddNewReportBtn() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame1));
        wait.until(ExpectedConditions.elementToBeClickable(addNewReportBtn)).click();
        driver.switchTo().defaultContent();
        return this;
    }

    public ReportingPage selectFields(List<String> fields) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame2));
        wait.until(ExpectedConditions.elementToBeClickable(availableFieldsTable));
        for (String field : fields) {
            WebElement fieldToAdd = getRowFromTable(availableFieldsTable, field);
            wait.until(ExpectedConditions.elementToBeClickable(fieldToAdd)).click();
            wait.until(ExpectedConditions.elementToBeClickable(addArrow)).click();
        }
        return this;
    }
    public void createNewReport(List<String> fields, String format) {
        menuPinHover();
        clickOnExportMenu();
        clickOnAddNewReportBtn();
        selectFields(fields);
        goToStep2();
        goToStep3(format);
        goToStep4();
        goToStep5(format);
    }

    public WebElement getReportNameFromTable(WebElement table, String reportName) {
        wait.until(ExpectedConditions.elementToBeClickable(table));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            List<WebElement> tds = row.findElements(By.tagName("td"));
            for (WebElement td : tds) {
                List<WebElement> effectiveRows = td.findElements(By.xpath("//*[contains(@id,'COL_105000_105001')]"));
                for (WebElement effectiveRow : effectiveRows) {
                    if (reportName.equals(effectiveRow.getText())) {
                        effectiveRow.click();
                        return effectiveRow;
                    }
                }
            }
        }
        return null;
    }

    private WebElement getRowFromTable(WebElement table, String rowText) {
        wait.until(ExpectedConditions.visibilityOf(table));
        wait.until(ExpectedConditions.elementToBeClickable(table));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            List<WebElement> tds = row.findElements(By.tagName("td"));
            for (WebElement td : tds) {
                List<WebElement> effectiveRows = td.findElements(By.xpath("//*[@id='editor_sourcelist']/span[@id='editor_field_200_200']/div"));
                for (WebElement effectiveRow : effectiveRows) {
                    if (rowText.equals(effectiveRow.getText())) {
                        return effectiveRow;
                    }
                }
            }
        }
        return null;
    }

    public void checkIfReportExists() {
        wait.until(ExpectedConditions.visibilityOf(applyBtn));
        wait.until(ExpectedConditions.elementToBeClickable(applyBtn));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame1));
        wait.until(ExpectedConditions.visibilityOf(reportsTable));
        wait.until(ExpectedConditions.elementToBeClickable(reportsTable));
        assertEquals(getReportNameFromTable(reportsTable, reportName).getText(), reportName);
        driver.switchTo().defaultContent();
    }

    public void createNewChart(String series, String format, ChartDetails chartDetails) {
        menuPinHover();
        clickOnChartMenu();
        clickOnAddNewReportBtn();
        selectSeries(series, format);
        goToStep2();
        selectChartFields(series, chartDetails);
        saveChart();
    }

    private void selectChartFields(String series, ChartDetails chartDetails) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame2));
        selectField(label1_X, chartDetails.getLabel1_X())
                .selectField(label2_X, chartDetails.getLabel2_X())
                .selectField(label3_X, chartDetails.getLabel3_X())
                .selectField(value1_Y, chartDetails.getValue1_Y())
                .selectField(value2_Y, chartDetails.getValue2_Y());
        if (series == "Multi-Series Chart") {
            selectField(field1_X, chartDetails.getField1_X())
                    .selectField(field2_X, chartDetails.getField2_X());
        }
    }

    public ReportingPage selectSeries(String series, String format) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame2));
        wait.until(ExpectedConditions.elementToBeClickable(chartsTable));
        switch (series) {
            case "Single Series Chart":
                wait.until(ExpectedConditions.elementToBeClickable(singleSeriesChartTab)).click();
                if (!format.contains("3D")) {
                    wait.until(ExpectedConditions.elementToBeClickable(histogram_SS)).click();
                } else {
                    wait.until(ExpectedConditions.elementToBeClickable(histogram3D_SS)).click();
                }
                break;
            case "Multi-Series Chart":
                wait.until(ExpectedConditions.elementToBeClickable(multiSeriesChartTab)).click();
                break;
        }
        return this;
    }

    public ReportingPage selectField(WebElement element, String elementName) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOf(element)));
        select.selectByVisibleText(elementName);
        return this;
    }

    private ReportingPage saveChart() {
        clickOnNextBtn();
        enterChartName();
        clickOnNextBtn();
        enterChartReportName();
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
        return this;
    }

    private void enterChartName() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame2));
        chartName = "chart_" + new FakerUtils().generateRandomNumber();
        wait.until(ExpectedConditions.elementToBeClickable(chartname)).sendKeys(chartName);
    }

    private void enterChartReportName() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame2));
        reportName = "chartReport_" + new FakerUtils().generateRandomNumber();
        wait.until(ExpectedConditions.elementToBeClickable(chartReportName)).sendKeys(reportName);
        reportName = chartName + reportName;
    }
}
