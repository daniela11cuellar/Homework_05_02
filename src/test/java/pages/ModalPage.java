package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ModalPage {

    WebDriver driver;

    By modal = By.className("modal-content");

    By names = By.xpath("//*[contains(text(),'Student Name')]/following-sibling::td");

    By email = By.xpath("//*[contains(text(),'Student Email')]/following-sibling::td");

    By gender = By.xpath("//*[contains(text(),'Gender')]/following-sibling::td");

    By mobile = By.xpath("//*[contains(text(),'Mobile')]/following-sibling::td");

    By dateOfBirth = By.xpath("//*[contains(text(),'Date of Birth')]/following-sibling::td");

    By subjects = By.xpath("//*[contains(text(),'Subjects')]/following-sibling::td");

    By hobbies = By.xpath("//*[contains(text(),'Hobbies')]/following-sibling::td");

    By picture = By.xpath("//*[contains(text(),'Picture')]/following-sibling::td");

    By address = By.xpath("//*[contains(text(),'Address')]/following-sibling::td");

    By stateAndCity = By.xpath("//*[contains(text(),'State and City')]/following-sibling::td");

    public ModalPage(WebDriver driver) {

        this.driver = driver;

    }

    public void assertModalIsPresent() {
        try{
            driver.findElement(modal);
        }catch (Exception e){
            throw new ElementNotVisibleException("the modal is not present");
        }
    }

    public void assertInformation(String expectedName, String expectedLastName, String expectedEmail, String expectedGender,
                                  String expectedMobile, String day, String month, String year,
                                  String expectedSubject, String expectedAddress, String expectedHobbies,
                                  String expectedPicture, String expectedState, String expectedCity) {

        String modalNames = driver.findElement(names).getText();
        String modalEmail = driver.findElement(email).getText();
        String modalGender = driver.findElement(gender).getText();
        String modalMobile = driver.findElement(mobile).getText();
        String modalDate = driver.findElement(dateOfBirth).getText();

        String modalSub = driver.findElement(subjects).getText();
        String modalAddress = driver.findElement(address).getText();
        String modalHobbies = driver.findElement(hobbies).getText();
        String modalPicture = driver.findElement(picture).getText();

        String modalStateAndCity = driver.findElement(stateAndCity).getText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(modalNames, expectedName + " " + expectedLastName, "The name aren't the expected");
        softAssert.assertEquals(modalEmail, expectedEmail, "The email isn't the expected");
        softAssert.assertEquals(modalGender, expectedGender, "The gender isn't the expected");
        softAssert.assertEquals(modalMobile, expectedMobile, "The mobile isn't the expected");
        softAssert.assertEquals(modalDate, day + " " + month + "," + year, "The date isn't the expected");
        softAssert.assertEquals(modalSub, expectedSubject, "The subject isn't the expected");
        softAssert.assertEquals(modalAddress, expectedAddress, "The address isn't the expected");
        softAssert.assertEquals(modalHobbies, expectedHobbies, "The hobby isn't the expected");
        softAssert.assertEquals(modalHobbies, expectedHobbies, "The hobby isn't the expected");
        softAssert.assertEquals(modalPicture, expectedPicture, "The picture isn't the expected");
        softAssert.assertEquals(modalStateAndCity, expectedState + " " + expectedCity, "The state or city aren't the expected");
        softAssert.assertAll();

    }
}
