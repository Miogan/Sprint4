package ru.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class MainPage {
    private final WebDriver driver;
    private By locatorButtonCookiesWindow = By.className("App_CookieButton__3cvqF");
    private By locatorButtonOrderUp = By.cssSelector(".Button_Button__ra12g");
    private By locatorButtonOrderDown = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    private By locatorQuestion;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Открываем страртовую страницу
    public void openMainPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    // Находим и кликаем по заданному пункту FAQ
    public void ClickOnButtonFAQ(By locatorQuestion) {
        WebElement element =  driver.findElement(locatorQuestion);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    // Проверяем, что предполагаемый текст совпадает с тем что находится в раскрытом пункте  FAQ
    public void checkErrorTextFAQ(By locatorAnswer, String textFAQ) {
        assertEquals("Текст не совпал", textFAQ, driver.findElement(locatorAnswer).getText());
    }

    // Order

    // Проверяем работоспособность кнопки сверху
    public void ClickOnButtonOrderUp() {
        WebElement element =  driver.findElement(locatorButtonOrderUp);
        element.click();
        String expectedUrl = "https://qa-scooter.praktikum-services.ru/order";
        String actualUrl = driver.getCurrentUrl();

        assertEquals("Кнопка заказа вверху ведет на ошибочный адрес", expectedUrl, actualUrl);
    }

    // Проверяем работоспособность кнопки снизу
    public void ClickOnButtonOrderDown() {
        WebElement element =  driver.findElement(locatorButtonOrderDown);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
        String expectedUrl = "https://qa-scooter.praktikum-services.ru/order";
        String actualUrl = driver.getCurrentUrl();

        assertEquals("Кнопка заказа снизу ведет на ошибочный адрес", expectedUrl, actualUrl);
    }

    // Закрываем окно с куками
    public void CloseCookes() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(locatorButtonCookiesWindow));
        WebElement element = driver.findElement(locatorButtonCookiesWindow);
        if (element.isDisplayed()) {
            element.click();
        }
    }
}
