package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Forms {

    WebDriver driver;

    By btnPractice = By.xpath("//*[ contains (text(), 'Practice')]");


    public Forms(WebDriver driver) {

        this.driver = driver;

    }

    public void clickBtnPractice(){

        driver.findElement(btnPractice).click();

    }
}
