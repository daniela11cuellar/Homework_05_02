package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SelectDatePage {

    WebDriver driver;

    By month = By.className("react-datepicker__month-select");

    By year = By.className("react-datepicker__year-select");

    By day = By.className("react-datepicker__day");


    public SelectDatePage(WebDriver driver) {

        this.driver = driver;

    }

    public String selectMonth(){
        Select select = new Select(driver.findElement(month));
        checkList(select.getOptions());
        int index = ThreadLocalRandom.current().nextInt(0, select.getOptions().size());
        WebElement monthOption = select.getOptions().get(index);
        monthOption.click();
        return monthOption.getAttribute("textContent");
    }

    public String selectYear(){
        Select select = new Select(driver.findElement(year));
        checkList(select.getOptions());
        int index = ThreadLocalRandom.current().nextInt(0, select.getOptions().size());
        WebElement yearOption = select.getOptions().get(index);
        yearOption.click();
        return yearOption.getAttribute("value");
    }

    public void selectDay(String numberDay){

        List<WebElement> days = driver.findElements(day);
        checkList(days);

        WebElement day = getDay(days, numberDay);
        day.click();
    }

    public WebElement getDay(List<WebElement> days, String day){
        WebElement returnElement = null;
        for(int i=0; i<days.size(); i++){
            checkAttribute(days.get(i),"textContent");
            if (days.get(i).getAttribute("textContent").contains(day)){
                returnElement = days.get(i);
                break;
            }
        }
        return returnElement;
    }

    public void checkList(List<WebElement> list) {
        if (list.size() == 0)
            throw new NullPointerException("list is empty");
    }

    public void checkAttribute(WebElement element, String attribute) {
        if (element.getAttribute(attribute).equals(null))
            throw new NullPointerException("the attribute is null: " + attribute);
    }
}
