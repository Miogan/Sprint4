package ru.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class Order {

    private final WebDriver driver;
    private static final By locatorUserName = By.cssSelector(".Input_Input__1iN_Z[placeholder='* Имя']");
    private static final By locatorSurname = By.cssSelector(".Input_Input__1iN_Z[placeholder='* Фамилия']");
    private static final By locatorCity = By.cssSelector(".Input_Input__1iN_Z[placeholder='* Адрес: куда привезти заказ']");
    private static final By locatorMetroStation = By.cssSelector(".select-search__input");
    private static final By locatorSelectMetroStation = By.cssSelector(".select-search__select");
    private static final By locatorTelUser = By.cssSelector(".Input_Input__1iN_Z[placeholder='* Телефон: на него позвонит курьер']");
    private static final By locatorButtonNextStepOne = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    private static final By locatorDateRent = By.cssSelector(".Input_Input__1iN_Z[placeholder='* Когда привезти самокат']");
    private static final By locatorSelectedDeliveryDay = By.cssSelector(".react-datepicker__day--selected");
    private static final By locatorRentalPeriod = By.cssSelector(".Dropdown-placeholder");
    private static final By locatorCommentForTheCourier = By.cssSelector(".Input_Input__1iN_Z[placeholder='Комментарий для курьера']");
    private static final By OrderСonfirmationWindow = By.className("Order_Modal__YZ-d3");
    private static final String selectorButtonNextStepOROrder = "//button[@class='Button_Button__ra12g Button_Middle__1CSJM'";
    private static final String selectorRentalPeriodList = "//div[@class='Dropdown-option' and text()='";
    private static final String selectorCheckBoxColorScooter = "//label[text()='";
    private static final By locatorOrderConfirmationText = By.className("Order_ModalHeader__3FDaJ");
    private static final String OrderConfirmationText = "Заказ оформлен";
    private String textWinOrder;

    public Order(WebDriver driver) {
        this.driver = driver;
    }

    // Заполняем форму заказа
    public void fillingOutTheOrderFormName(String name) {
        driver.findElement(locatorUserName).sendKeys(name);
    }

    public void fillingOutTheOrderFormSurname(String surname) {
        driver.findElement(locatorSurname).sendKeys(surname);
    }

    public void fillingOutTheOrderFormCity(String city) {
        driver.findElement(locatorCity).sendKeys(city);
    }
    public void fillingOutTheOrderFormMetroStation(String metroStation) {
        driver.findElement(locatorMetroStation).click();
        driver.findElement(locatorMetroStation).sendKeys(metroStation);
        driver.findElement(locatorSelectMetroStation).click();
    }

    public void fillingOutTheOrderFormTelUser( String tel) {
        driver.findElement(locatorTelUser).sendKeys(tel);
    }

    //Нажимаем кнопку Далее, после 1 шаг
    public void clickButtonNext() {
        driver.findElement(locatorButtonNextStepOne).click();
    }

    // Заполняем форму на втором шаге
    public void fillingOutTheRentFormDateRent(String dateRent) {
        driver.findElement(locatorDateRent).sendKeys(dateRent);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(locatorSelectedDeliveryDay));
        driver.findElement(locatorSelectedDeliveryDay).click();
    }

    public void fillingOutTheRentFormRentalPeriod(String rentalPeriod) {
        // период
        driver.findElement(locatorRentalPeriod).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(selectorRentalPeriodList + rentalPeriod + "']")));
        driver.findElement(By.xpath(selectorRentalPeriodList + rentalPeriod + "']")).click();
    }

    public void fillingOutTheRentFormColorScooter(String colorScooter) {
        driver.findElement(By.xpath(selectorCheckBoxColorScooter + colorScooter + "']")).click();
    }

    public void fillingOutTheRentFormCommentForTheCourier(String commentRent) {
        driver.findElement(locatorCommentForTheCourier).sendKeys(commentRent);
    }

    public void clickButtonOrder() {
        driver.findElement(By.xpath(selectorButtonNextStepOROrder + " and text()='Заказать']")).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(OrderСonfirmationWindow));
        WebElement element = driver.findElement(OrderСonfirmationWindow);
        if (element.isDisplayed()) {
            driver.findElement(By.xpath(selectorButtonNextStepOROrder + "  and text()='Да']")).click();
        }
    }
    public void findWindowOrderConfirmation() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(locatorOrderConfirmationText));
        textWinOrder = driver.findElement(locatorOrderConfirmationText).getText();
        assertTrue("Не удалось завершить оформление заказа", textWinOrder.contains(OrderConfirmationText));
    }
}

