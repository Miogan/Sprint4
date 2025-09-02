package ru.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class Order {

    private final WebDriver driver;
    private By locatorUserName = By.cssSelector(".Input_Input__1iN_Z[placeholder='* Имя']");
    private By locatorSurname = By.cssSelector(".Input_Input__1iN_Z[placeholder='* Фамилия']");
    private By locatorCity = By.cssSelector(".Input_Input__1iN_Z[placeholder='* Адрес: куда привезти заказ']");
    private By locatorMetroStation = By.cssSelector(".select-search__input");
    private By locatorSelectMetroStation = By.cssSelector(".select-search__select");
    private By locatorTelUser = By.cssSelector(".Input_Input__1iN_Z[placeholder='* Телефон: на него позвонит курьер']");
    private By locatorButtonNextStepOne = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    private By locatorDateRent = By.cssSelector(".Input_Input__1iN_Z[placeholder='* Когда привезти самокат']");
    private By locatorSelectedDeliveryDay = By.cssSelector(".react-datepicker__day--selected");
    private By locatorRentalPeriod = By.cssSelector(".Dropdown-placeholder");
    private By locatorCommentForTheCourier = By.cssSelector(".Input_Input__1iN_Z[placeholder='Комментарий для курьера']");
    private By OrderСonfirmationWindow = By.className("Order_Modal__YZ-d3");
    private String selectorButtonNextStepOROrder = "//button[@class='Button_Button__ra12g Button_Middle__1CSJM'";
    private String selectorRentalPeriodList = "//div[@class='Dropdown-option' and text()='";
    private String selectorCheckBoxColorScooter = "//label[text()='";

    public Order(WebDriver driver) {
        this.driver = driver;
    }

    // Заполняем форму заказа 1 шаг
    public void FillingOutTheOrderForm(String name, String surname, String city, String metroStation, String tel) {
        driver.findElement(locatorUserName).sendKeys(name);
        driver.findElement(locatorSurname).sendKeys(surname);
        driver.findElement(locatorCity).sendKeys(city);
        driver.findElement(locatorMetroStation).click();
        driver.findElement(locatorMetroStation).sendKeys(metroStation);
        driver.findElement(locatorSelectMetroStation).click();
        driver.findElement(locatorTelUser).sendKeys(tel);
    }

    //Нажимаем кнопку Далее, после 1 шаг
    public void ClickButtonNext() {
        driver.findElement(locatorButtonNextStepOne).click();
    }

    public void FillingOutTheRentForm(String dateRent, String rentalPeriod, String colorScooter, String commentRent) {
        driver.findElement(locatorDateRent).sendKeys(dateRent);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(locatorSelectedDeliveryDay));
        driver.findElement(locatorSelectedDeliveryDay).click();
        // период
        driver.findElement(locatorRentalPeriod).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(selectorRentalPeriodList + rentalPeriod + "']")));
        driver.findElement(By.xpath(selectorRentalPeriodList + rentalPeriod + "']")).click();
        driver.findElement(By.xpath(selectorCheckBoxColorScooter + colorScooter + "']")).click();
        driver.findElement(locatorCommentForTheCourier).sendKeys(commentRent);
    }

    public void ClickButtonOrder() {
        driver.findElement(By.xpath(selectorButtonNextStepOROrder + " and text()='Заказать']")).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(OrderСonfirmationWindow));
        WebElement element = driver.findElement(OrderСonfirmationWindow);
        if (element.isDisplayed()) {
            driver.findElement(By.xpath(selectorButtonNextStepOROrder + "  and text()='Да']")).click();
        }

    }
}

