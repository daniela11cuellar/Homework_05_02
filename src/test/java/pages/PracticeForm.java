package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PracticeForm {
    WebDriver driver;

    By firstName = By.id("firstName");

    By lastName = By.id("lastName");

    By email = By.id("userEmail");

    By mobile = By.id("userNumber");

    By subject = By.id("subjectsInput");

    By address = By.id("currentAddress");

    By subjectOption = By.id("react-select-2-option-0");

    By subjectSelected = By.className("subjects-auto-complete__multi-value__label");

    By birth = By.id("dateOfBirthInput");

    By upload = By.id("uploadPicture");

    By inputState = By.id("react-select-3-input");

    By optionState = By.id("react-select-3-option-0");

    By inputCity = By.id("react-select-4-input");

    By optionCity = By.cssSelector("#fixedban div:nth-child(4)");

    By submit = By.id("submit");

    public PracticeForm(WebDriver driver) {
        this.driver = driver;
    }

    public void typeFirstName(String name){
        driver.findElement(firstName).sendKeys(name);
    }

    public void typeLastName(String last){
        driver.findElement(lastName).sendKeys(last);
    }

    public void typeEmail(String mail){
        driver.findElement(email).sendKeys(mail);
    }

    public void typeMobile(String number){
        driver.findElement(mobile).sendKeys(number);
    }

    public void typeSubject(String sub){
        driver.findElement(subject).sendKeys(sub);
        driver.findElement(subjectOption).click();
    }

    public void typeAddress(String addr){
        driver.findElement(address).sendKeys(addr);
    }

    public String getFirstName(){
      return driver.findElement(firstName).getAttribute("value");
    }

    public String getLastName(){
        return driver.findElement(lastName).getAttribute("value");
    }

    public String getEmail(){
        return driver.findElement(email).getAttribute("value");
    }

    public String getMobile(){
        return driver.findElement(mobile).getAttribute("value");
    }

    public String getSubject(){
        return driver.findElement(subjectSelected).getAttribute("textContent");
    }

    public String getAddress(){
        return driver.findElement(address).getAttribute("value");
    }


    public void clickBirth(){
        driver.findElement(birth).click();
    }

    public String clickBtnGender(){
        String[] genderArray = new String[]{ "Male", "Female", "Other"};
        int index = ThreadLocalRandom.current().nextInt(0, genderArray.length);
        WebElement option = driver.findElement(By.xpath("//*[contains(text(),'"+genderArray[index]+"')]"));
        option.click();
        return genderArray[index];
    }

    public void assertDate(String expectedDay, String  expectedMonth, String expectedYear){

        String valueBirth = driver.findElement(birth).getAttribute("value");
        String[] date = valueBirth.split(" ");

        Assert.assertEquals(date.length, 3, "The format date is not the expected");

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(date[0], expectedDay, "The day isn't the expected");
        softAssert.assertTrue(expectedMonth.contains(date[1]), "The month isn't the expected");
        softAssert.assertEquals(date[2], expectedYear, "The year isn't the expected");

        softAssert.assertAll();

    }

    public String clickBtnHobbies(){
        String[] hobbiesArray = new String[]{ "Sports", "Reading", "Music"};
        int index = ThreadLocalRandom.current().nextInt(0, hobbiesArray.length);
        WebElement option = driver.findElement(By.xpath("//*[contains(text(),'"+hobbiesArray[index]+"')]"));
        option.click();
        return hobbiesArray[index];
    }


    public void uploadImage(String path) {
        driver.findElement(upload).sendKeys(path);
    }

    public void selectState(String state) {
        driver.findElement(inputState).sendKeys(state);
        driver.findElement(optionState).click();
    }

    public void selectCity(String state) {
        driver.findElement(inputCity).sendKeys(state);
        driver.findElement(optionCity).click();
    }

    public void clickSubmit() {
        driver.findElement(submit).click();
    }
}
