package com.jotformeu.form.pages.form;

import com.jotformeu.form.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class FormPage extends BasePage {

    private static final String FIRSTNAME_FIELD_ID = "first_3";
    private static final String SUBMIT_BTN_ID = "input_2";
    private static final String TITLE_ID = "header_1";
    private static final String LASTNAME_FIELD_ID = "last_3";
    private static final String BIRTHDAY_MONTH_SELECT_ID = "input_4_month";
    private static final String BIRTHDAY_DAY_SELECT_ID = "input_4_day";
    private static final String BIRTHDAY_YEAR_SELECT_ID = "input_4_year";
    private static final String INSTRUMENT_SELECT_ID = "input_5";
    private static final String START_CLASSES_MONTH_FIELD_ID = "month_7";
    private static final String START_CLASSES_DAY_SELECT_ID = "day_7";
    private static final String START_CLASSES_YEAR_SELECT_ID = "year_7";
    private static final String START_CLASSES_HOURS_SELECT_ID = "hour_7";
    private static final String START_CLASSES_MINUTES_SELECT_ID = "min_7";
    private static final String START_CLASSES_AMPM_SELECT_ID = "ampm_7";
    private static final String COMMENT_FIELD_ID = "input_8";
    private static final String OVER_QUOTA_MESSAGE_XPATH = "/html/body/table/tbody/tr[2]/td/table/tbody/tr[2]/td/div/div[1]/h1";

    public FormPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = TITLE_ID)
    private WebElement elTitle;

    @FindBy(id = FIRSTNAME_FIELD_ID)
    private WebElement elfirstNameField;

    @FindBy(id = LASTNAME_FIELD_ID)
    private WebElement elLastNameField;

    @FindBy(id = BIRTHDAY_MONTH_SELECT_ID)
    private WebElement elBirthDateMonthSelect;

    @FindBy(id = BIRTHDAY_DAY_SELECT_ID)
    private WebElement elBirthDateDaySelect;

    @FindBy(id = BIRTHDAY_YEAR_SELECT_ID)
    private WebElement elBirthDateYearSelect;

    @FindBy(id = INSTRUMENT_SELECT_ID)
    private WebElement elInstrumentSelect;

    @FindBy(id = START_CLASSES_MONTH_FIELD_ID)
    private WebElement elStartClassesMonthField;

    @FindBy(id = START_CLASSES_DAY_SELECT_ID)
    private WebElement elStartClassesDayField;

    @FindBy(id = START_CLASSES_YEAR_SELECT_ID)
    private WebElement elStartClassesYearField;

    @FindBy(id = START_CLASSES_HOURS_SELECT_ID)
    private WebElement elStartClassesHoursSelect;

    @FindBy(id = START_CLASSES_MINUTES_SELECT_ID)
    private WebElement elStartClassesMinutesSelect;

    @FindBy(id = START_CLASSES_AMPM_SELECT_ID)
    private WebElement elStartClassesAmPmSelect;

    @FindBy(id = COMMENT_FIELD_ID)
    private WebElement elCommentField;

    @FindBy(id = SUBMIT_BTN_ID)
    private WebElement elSubmitBtn;

    @FindBy(id = OVER_QUOTA_MESSAGE_XPATH)
    private WebElement elOverQuotaMessage;

    public FormPage fillFirstNameField(String firstName) {
        clearAndFillElement(elfirstNameField, firstName);
        return this;
    }

    //
    public FormPage fillLastNameField(String lastName) {
        clearAndFillElement(elLastNameField, lastName);
        return this;
    }

    //
    public FormPage selectBirthDateMonth(String month) {
        (waitElementVisible(elBirthDateMonthSelect, 20)).click();
        selectOptionByIndex(BIRTHDAY_MONTH_SELECT_ID, 1);
        return this;
    }

    public FormPage selectBirthDateDay(String day) {
        (waitElementVisible(elBirthDateDaySelect, 20)).click();
        selectOptionByIndex(BIRTHDAY_DAY_SELECT_ID, 1);
        return this;
    }

    public FormPage selectBirthDateYear(String year) {
        (waitElementVisible(elBirthDateYearSelect, 20)).click();
//        selectOptionByText(BIRTHDAY_YEAR_FIELD_ID, year);
        selectOptionByIndex(BIRTHDAY_YEAR_SELECT_ID, 40);
        return this;
    }

    public FormPage selectInstrument(String instrument) {
        (waitElementVisible(elInstrumentSelect, 20)).click();
        selectOptionByIndex(INSTRUMENT_SELECT_ID, 2);
        return this;
    }

    public FormPage selectDaysForClasses(List<Integer> days) {
        for (int i = 0; i < days.size(); i++) {
            String checkBoxXpath = "//*[@id='label_input_6_" + (days.get(i) - 1) + "']";
            srollToElementByXpth(checkBoxXpath);
            (waitElementVisibleByXpath(checkBoxXpath, 20)).click();
            sleep(1000);
        }
        return this;
    }

    //
    public FormPage selectClassesStartDate(String month, String day, String year) {
        clearAndFillElement(elStartClassesMonthField, month);
        sleep(1000);
        clearAndFillElement(elStartClassesDayField, day);
        sleep(1000);
        clearAndFillElement(elStartClassesYearField, year);
        sleep(1000);

        return this;
    }

    //
    public FormPage selectClassesStartTime(String hours, String minutes, Boolean pm) {
        (waitElementVisible(elStartClassesHoursSelect, 20)).click();
        selectOptionByIndex(START_CLASSES_HOURS_SELECT_ID, 8);
        sleep(1000);

        (waitElementVisible(elStartClassesMinutesSelect, 20)).click();
        selectOptionByIndex(START_CLASSES_MINUTES_SELECT_ID, 1);
        sleep(1000);

        (waitElementVisible(elStartClassesAmPmSelect, 20)).click();
        selectOptionByIndex(START_CLASSES_AMPM_SELECT_ID, pm ? 1 : 0);
        sleep(1000);

        return this;
    }


    public FormPage fillComment(String comment) {
        clearAndFillElement(elCommentField, comment);
        return this;
    }

    public FormPage clickSubmitBtn() {
        waitElementVisible(elSubmitBtn, 20).click();
        return this;
    }

    public FormPage assertItHasTitle(String tittle) {
        Assert.assertTrue(waitElementVisible(elTitle, 20).getText().toLowerCase().contains(tittle.toLowerCase()));
        return this;
    }

    public FormPage assertItHasText(String text) {
        sleep(1000);
        Assert.assertTrue(SourceContainsText(text));
//        Assert.assertTrue(waitElementVisible(elOverQuotaMessage, 20).isDisplayed());
//        System.out.println("over quota text : "+(waitElementVisible(elOverQuotaMessage, 20).getText().toLowerCase()));
//        Assert.assertTrue(waitElementVisible(elOverQuotaMessage, 20).getText().toLowerCase().contains(text.toLowerCase()));
        return this;
    }
//
//    public FormPage removeEntry(String entryTitle) {
//        for (int i = 0; i < ElAdminEntries.size(); i++) {
//            sleep(500);
//            WebElement entry = ElAdminEntries.get(i);
//            if (entry.getText().toLowerCase().contains(entryTitle.toLowerCase())) {
//                elAdminEntryCheckboxes.get(i).click();
//            }
//        }
//        elActionsDropDownBtn.click();
//        selectOptionByIndex("action", 1);//"Удалить выбранные Entries");
//        elActionConfirmBtn.click();
//        elAConfirmDeleteEntryBtn.click();
//        return this;
//    }
}
