package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestCasesTools {

    protected WebDriverWait wait;
    WebDriver driver;
    Home home;
    Forms forms;
    PracticeForm practiceForm;
    SelectDatePage selectDatePage;
    ModalPage modal;

    @BeforeTest
    public void setup() throws IOException {

        InputStream file = new FileInputStream("resources/config.properties");
        Properties properties = new Properties();
        properties.load(file);

        String browser = "FIREFOX";

        switch(browser) {
            case "CHROME":
                System.setProperty(properties.getProperty("PROPERTY_CHROME"), properties.getProperty("PATH_CHROME"));
                driver = new ChromeDriver();
                break;
            case "FIREFOX":
                System.setProperty(properties.getProperty("PROPERTY_FIRE"), properties.getProperty("PATH_FIRE"));
                driver = new FirefoxDriver();
                break;
            case "EDGE":
                System.setProperty(properties.getProperty("PROPERTY_EDGE"), properties.getProperty("PATH_EDGE"));
                driver = new EdgeDriver();
                break;
        }

        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://demoqa.com/");
        driver.manage().window().maximize();

    }

    @Test
    public void testHome() {
        home = new Home(driver);
        home.clickBtnForms();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.className("main-header")));
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/forms", "The urls aren't the same");
    }

    @Test
    public void testForms() {
        testHome();
        forms = new Forms(driver);
        forms.clickBtnPractice();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.className("practice-form-wrapper")));

        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/automation-practice-form", "The urls aren't the same");
    }

    @Test(dataProvider = "getData")
    public void testPracticeForm(String name, String lastName, String email,
                                 String mobile, String subject, String address, String state, String city) {

        testForms();

        practiceForm = new PracticeForm(driver);
        practiceForm.typeFirstName(name);
        practiceForm.typeLastName(lastName);
        practiceForm.typeEmail(email);
        practiceForm.typeMobile(mobile);
        practiceForm.typeSubject(subject);
        practiceForm.typeAddress(address);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(practiceForm.getFirstName(), name, "The name isn't the expected");
        softAssert.assertEquals(practiceForm.getLastName(), lastName, "The last name isn't the expected");
        softAssert.assertEquals(practiceForm.getEmail(), email, "The email isn't the expected");
        softAssert.assertEquals(practiceForm.getMobile(), mobile, "The mobile isn't the expected");
        softAssert.assertEquals(practiceForm.getSubject(), subject, "The subject isn't the expected");
        softAssert.assertEquals(practiceForm.getAddress(), address, "The address isn't the expected");
        softAssert.assertAll();

        String gender = practiceForm.clickBtnGender();
        practiceForm.clickBirth();

        selectDatePage = new SelectDatePage(driver);

        String month = selectDatePage.selectMonth();
        String year = selectDatePage.selectYear();

        String day = "20";
        selectDatePage.selectDay(day);

        practiceForm.assertDate(day, month, year);
        String hobby = practiceForm.clickBtnHobbies();

        String path = "C:\\Users\\user.DESKTOP-0I3ILBP\\Desktop\\";
        String file = "testing.jpg";

        practiceForm.uploadImage(path+file);

        practiceForm.selectState(state);
        practiceForm.selectCity(city);

        practiceForm.clickSubmit();

        modal = new ModalPage(driver);
        modal.assertModalIsPresent();
        modal.assertInformation(name, lastName, email, gender, mobile, day, month, year,
                subject, address, hobby, file, state, city);

    }


    @DataProvider
    public Object[][] getData() {
        return new Object[][]{
                {"Robot", "Executor", "robot-executor@hotmail.com", "3219790624", "Maths", "Kra 6 A este", "NCR", "Delhi"},
        };
    }


    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

