package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home {

    WebDriver driver;

    By btnForms = By.xpath("//*[ contains (text(), 'Forms')]");


    public Home(WebDriver driver) {

        this.driver = driver;

    }

    public void clickBtnForms(){

        driver.findElement(btnForms).click();

    }
}
