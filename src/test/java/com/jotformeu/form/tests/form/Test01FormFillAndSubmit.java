package com.jotformeu.form.tests.form;

import com.jotformeu.form.pages.form.FormPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.sound.midi.Instrument;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test01FormFillAndSubmit {

    private ChromeDriver driver;
    private FormPage form;

    @BeforeMethod
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "/home/reus/Documents/work/Java/AndreyPolyakovFormTest/src/main/resources/drivers/linux/chromedriver");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        form = new FormPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test001() {

        driver.get("https://form.jotformeu.com/92251830338354");
        form
                .assertItHasTitle("Music School Registration")
                .fillFirstNameField("Иван")
                .fillLastNameField("Иванов")
                .selectBirthDateMonth("January")
                .selectBirthDateDay("1")
                .selectBirthDateYear("1980")
                .selectInstrument("Bass")
                .selectDaysForClasses(new ArrayList<Integer>((Arrays.asList(1, 2))))
                .selectClassesStartDate("10", "21", "2020")
                .selectClassesStartTime("8", "00", true)
                .fillComment("No comments")
                .clickSubmitBtn()
                .assertItHasText("Form over quota")
        ;
    }
}
