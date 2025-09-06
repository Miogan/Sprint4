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
    public static final By LOCATOR_BUTTON_COOKIE_SWINDOW = By.className("App_CookieButton__3cvqF");
    public static final By LOCATOR_BUTTON_ORDER_UP = By.cssSelector(".Button_Button__ra12g");
    public static final By LOCATOR_BUTTON_ORDER_DOWN = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    public static final String MAIN_PAGE_ADDRESS = "https://qa-scooter.praktikum-services.ru";
    public static final String ORDER_PAGE_ADDRESS = "https://qa-scooter.praktikum-services.ru/order";
    // Вопросы
    public static final By LOCATOR_QUESTION_WHAT_IS_THE_PRICE = By.cssSelector(".accordion__button[aria-controls='accordion__panel-0']");
    public static final By LOCATOR_QUESTION_POSSIBILITY_OF_SEVERAL_SCOOTERS = By.cssSelector(".accordion__button[aria-controls='accordion__panel-1']");
    public static final By LOCATOR_QUESTION_TIMING = By.cssSelector(".accordion__button[aria-controls='accordion__panel-2']");
    public static final By LOCATOR_QUESTION_SCOOTER_TODAY = By.cssSelector(".accordion__button[aria-controls='accordion__panel-3']");
    public static final By LOCATOR_QUESTION_EXTENDING_OR_RETURNING_A_SCOOTER = By.cssSelector(".accordion__button[aria-controls='accordion__panel-4']");
    public static final By LOCATOR_QUESTION_CHARGER_INCLUDED = By.cssSelector(".accordion__button[aria-controls='accordion__panel-5']");
    public static final By LOCATOR_QUESTION_CANCELLATION = By.cssSelector(".accordion__button[aria-controls='accordion__panel-6']");
    public static final By LOCATOR_QUESTION_DELIVERY_OUTSIDE_THE_MKAD = By.cssSelector(".accordion__button[aria-controls='accordion__panel-7']");
    // Ответы
    public static final By LOCATOR_ANSWER_WHAT_IS_THE_PRICE = By.cssSelector(".accordion__panel[id='accordion__panel-0']");
    public static final By LOCATOR_ANSWER_POSSIBILITY_OF_SEVERAL_SCOOTERS = By.cssSelector(".accordion__panel[id='accordion__panel-1']");
    public static final By LOCATOR_ANSWER_TIMING = By.cssSelector(".accordion__panel[id='accordion__panel-2']");
    public static final By LOCATOR_ANSWER_SCOOTER_TODAY = By.cssSelector(".accordion__panel[id='accordion__panel-3']");
    public static final By LOCATOR_ANSWER_EXTENDING_OR_RETURNING_A_SCOOTER = By.cssSelector(".accordion__panel[id='accordion__panel-4']");
    public static final By LOCATOR_ANSWER_CHARGER_INCLUDED = By.cssSelector(".accordion__panel[id='accordion__panel-5']");
    public static final By LOCATOR_ANSWER_CANCELLATION = By.cssSelector(".accordion__panel[id='accordion__panel-6']");
    public static final By LOCATOR_ANSWER_DELIVERY_OUTSIDE_THE_MKAD = By.cssSelector(".accordion__panel[id='accordion__panel-7']");
    // Эталонный ответ
    public static final String REFERENCE_ANSWER_WHAT_IS_THE_PRICE = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    public static final String REFERENCE_ANSWER_POSSIBILITY_OF_SEVERAL_SCOOTERS = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    public static final String REFERENCE_ANSWER_TIMING = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    public static final String REFERENCE_ANSWER_SCOOTER_TODAY = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    public static final String REFERENCE_ANSWER_EXTENDING_OR_RETURNING_A_SCOOTER = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    public static final String REFERENCE_ANSWER_CHARGER_INCLUDED = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    public static final String REFERENCE_ANSWER_CANCELLATION = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    public static final String REFERENCE_ANSWER_DELIVERY_OUTSIDE_THE_MKAD = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Открываем страртовую страницу
    public void openMainPage() {
        driver.get(MAIN_PAGE_ADDRESS);
    }

    // Находим и кликаем по заданному пункту FAQ
    public void clickOnButtonFAQ(By locatorQuestion) {
        WebElement element = driver.findElement(locatorQuestion);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    // Проверяем, что предполагаемый текст совпадает с тем что находится в раскрытом пункте  FAQ
    public void checkErrorTextFAQ(By locatorAnswer, String textFAQ) {
        assertEquals("Текст не совпал", textFAQ, driver.findElement(locatorAnswer).getText());
    }

    // Проверяем работоспособность кнопки сверху
    public void clickOnButtonOrderUp() {
        WebElement element = driver.findElement(LOCATOR_BUTTON_ORDER_UP);
        element.click();
        String actualUrl = driver.getCurrentUrl();
        assertEquals("Кнопка заказа вверху ведет на ошибочный адрес", ORDER_PAGE_ADDRESS, actualUrl);
    }

    // Проверяем работоспособность кнопки снизу
    public void clickOnButtonOrderDown() {
        WebElement element = driver.findElement(LOCATOR_BUTTON_ORDER_DOWN);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
        String actualUrl = driver.getCurrentUrl();

        assertEquals("Кнопка заказа снизу ведет на ошибочный адрес", ORDER_PAGE_ADDRESS, actualUrl);
    }

    // Закрываем окно с куками
    public void closeCookes() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(LOCATOR_BUTTON_COOKIE_SWINDOW));
        WebElement element = driver.findElement(LOCATOR_BUTTON_COOKIE_SWINDOW);
        if (element.isDisplayed()) {
            element.click();
        }
    }

    private static final By[] QUESTION_LOCATORS = {
            LOCATOR_QUESTION_WHAT_IS_THE_PRICE,
            LOCATOR_QUESTION_POSSIBILITY_OF_SEVERAL_SCOOTERS,
            LOCATOR_QUESTION_TIMING,
            LOCATOR_QUESTION_SCOOTER_TODAY,
            LOCATOR_QUESTION_EXTENDING_OR_RETURNING_A_SCOOTER,
            LOCATOR_QUESTION_CHARGER_INCLUDED,
            LOCATOR_QUESTION_CANCELLATION,
            LOCATOR_QUESTION_DELIVERY_OUTSIDE_THE_MKAD,
    };

    private static final By[] ANSWER_LOCATORS = {
            LOCATOR_ANSWER_WHAT_IS_THE_PRICE,
            LOCATOR_ANSWER_POSSIBILITY_OF_SEVERAL_SCOOTERS,
            LOCATOR_ANSWER_TIMING,
            LOCATOR_ANSWER_SCOOTER_TODAY,
            LOCATOR_ANSWER_EXTENDING_OR_RETURNING_A_SCOOTER,
            LOCATOR_ANSWER_CHARGER_INCLUDED,
            LOCATOR_ANSWER_CANCELLATION,
            LOCATOR_ANSWER_DELIVERY_OUTSIDE_THE_MKAD,
    };

    private static final String[] REFERENCE_ANSWER = {
            REFERENCE_ANSWER_WHAT_IS_THE_PRICE,
            REFERENCE_ANSWER_POSSIBILITY_OF_SEVERAL_SCOOTERS,
            REFERENCE_ANSWER_TIMING,
            REFERENCE_ANSWER_SCOOTER_TODAY,
            REFERENCE_ANSWER_EXTENDING_OR_RETURNING_A_SCOOTER,
            REFERENCE_ANSWER_CHARGER_INCLUDED,
            REFERENCE_ANSWER_CANCELLATION,
            REFERENCE_ANSWER_DELIVERY_OUTSIDE_THE_MKAD,
    };

    public By getQuestionLocator(int index) {
        return QUESTION_LOCATORS[index];
    }

    public By getAnswerLocator(int index) {
        return ANSWER_LOCATORS[index];
    }

    public String getExpectedAnswer(int index) {
        return REFERENCE_ANSWER[index];
    }

}
