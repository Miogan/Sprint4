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
    private final By locatorButtonCookiesWindow = By.className("App_CookieButton__3cvqF");
    private final By locatorButtonOrderUp = By.cssSelector(".Button_Button__ra12g");
    private final By locatorButtonOrderDown = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    private final String mainPageAddress = "https://qa-scooter.praktikum-services.ru";
    private final String orderPageAddress = "https://qa-scooter.praktikum-services.ru/order";
    // Вопросы
    public static final By locatorQuestionWhatIsThePrice = By.cssSelector(".accordion__button[aria-controls='accordion__panel-0']");
    public static final By locatorQuestionPossibilityOfSeveralScooters  = By.cssSelector(".accordion__button[aria-controls='accordion__panel-1']");
    public static final By locatorQuestionTiming  = By.cssSelector(".accordion__button[aria-controls='accordion__panel-2']");
    public static final By locatorQuestionScooterToday = By.cssSelector(".accordion__button[aria-controls='accordion__panel-3']");
    public static final By locatorQuestionExtendingOrReturningAScooter = By.cssSelector(".accordion__button[aria-controls='accordion__panel-4']");
    public static final By locatorQuestionChargerIncluded = By.cssSelector(".accordion__button[aria-controls='accordion__panel-5']");
    public static final By locatorQuestionGalyaCancellation = By.cssSelector(".accordion__button[aria-controls='accordion__panel-6']");
    public static final By locatorQuestionDeliveryOutsideTheMoscowRingRoad = By.cssSelector(".accordion__button[aria-controls='accordion__panel-7']");
    // Ответы
    public static final By locatorAnswerWhatIsThePrice = By.cssSelector(".accordion__panel[id='accordion__panel-0']");
    public static final By locatorAnswerPossibilityOfSeveralScooters = By.cssSelector(".accordion__panel[id='accordion__panel-1']");
    public static final By locatorAnswerTiming = By.cssSelector(".accordion__panel[id='accordion__panel-2']");
    public static final By locatorAnswerScooterToday = By.cssSelector(".accordion__panel[id='accordion__panel-3']");
    public static final By locatorAnswerExtendingOrReturningAScooter = By.cssSelector(".accordion__panel[id='accordion__panel-4']");
    public static final By locatorAnswerChargerIncluded = By.cssSelector(".accordion__panel[id='accordion__panel-5']");
    public static final By locatorAnswerGalyaCancellation = By.cssSelector(".accordion__panel[id='accordion__panel-6']");
    public static final By locatorAnswerDeliveryOutsideTheMoscowRingRoad = By.cssSelector(".accordion__panel[id='accordion__panel-7']");
    // Эталонный ответ
    public static final String locatorReferenceAnswerWhatIsThePrice = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    public static final String locatorReferenceAnswerPossibilityOfSeveralScooters = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    public static final String locatorReferenceAnswerTiming = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    public static final String locatorReferenceAnswerScooterToday = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    public static final String locatorReferenceAnswerExtendingOrReturningAScooter = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    public static final String locatorReferenceAnswerChargerIncluded = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    public static final String locatorReferenceAnswerGalyaCancellation = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    public static final String locatorReferenceAnswerDeliveryOutsideTheMoscowRingRoad = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Открываем страртовую страницу
    public void openMainPage() {
        driver.get(mainPageAddress);
    }

    // Находим и кликаем по заданному пункту FAQ
    public void clickOnButtonFAQ(By locatorQuestion) {
        WebElement element =  driver.findElement(locatorQuestion);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    // Проверяем, что предполагаемый текст совпадает с тем что находится в раскрытом пункте  FAQ
    public void checkErrorTextFAQ(By locatorAnswer, String textFAQ) {
        assertEquals("Текст не совпал", textFAQ, driver.findElement(locatorAnswer).getText());
    }

    // Проверяем работоспособность кнопки сверху
    public void clickOnButtonOrderUp() {
        WebElement element =  driver.findElement(locatorButtonOrderUp);
        element.click();
        String actualUrl = driver.getCurrentUrl();
        assertEquals("Кнопка заказа вверху ведет на ошибочный адрес", orderPageAddress, actualUrl);
    }

    // Проверяем работоспособность кнопки снизу
    public void clickOnButtonOrderDown() {
        WebElement element =  driver.findElement(locatorButtonOrderDown);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
        String actualUrl = driver.getCurrentUrl();

        assertEquals("Кнопка заказа снизу ведет на ошибочный адрес", orderPageAddress, actualUrl);
    }

    // Закрываем окно с куками
    public void closeCookes() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(locatorButtonCookiesWindow));
        WebElement element = driver.findElement(locatorButtonCookiesWindow);
        if (element.isDisplayed()) {
            element.click();
        }
    }
}
