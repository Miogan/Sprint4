package ru.practicum;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.practicum.pages.MainPage;


@RunWith(Parameterized.class)
public class ListFaqParam {
    private By locatorQuestion;
    private By locatorAnswer;
    private final String textFAQ;

    @Rule
    public DriverFactory factory = new DriverFactory();

    public ListFaqParam (By locatorQuestion, By locatorAnswer, String textFAQ){
        this.locatorQuestion = locatorQuestion;
        this.locatorAnswer = locatorAnswer;
        this.textFAQ = textFAQ;
    }
    @Parameterized.Parameters
    public static Object[][] getAdress() {
        return new Object[][]{
                {By.cssSelector(".accordion__button[aria-controls='accordion__panel-0']"), By.cssSelector(".accordion__panel[id='accordion__panel-0']"), "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {By.cssSelector(".accordion__button[aria-controls='accordion__panel-1']"), By.cssSelector(".accordion__panel[id='accordion__panel-1']"), "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {By.cssSelector(".accordion__button[aria-controls='accordion__panel-2']"), By.cssSelector(".accordion__panel[id='accordion__panel-2']"), "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {By.cssSelector(".accordion__button[aria-controls='accordion__panel-3']"), By.cssSelector(".accordion__panel[id='accordion__panel-3']"), "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {By.cssSelector(".accordion__button[aria-controls='accordion__panel-4']"), By.cssSelector(".accordion__panel[id='accordion__panel-4']"), "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {By.cssSelector(".accordion__button[aria-controls='accordion__panel-5']"), By.cssSelector(".accordion__panel[id='accordion__panel-5']"), "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {By.cssSelector(".accordion__button[aria-controls='accordion__panel-6']"), By.cssSelector(".accordion__panel[id='accordion__panel-6']"), "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {By.cssSelector(".accordion__button[aria-controls='accordion__panel-7']"), By.cssSelector(".accordion__panel[id='accordion__panel-7']"), "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Test
    public void testNonFoundListItemTextParam() throws InstantiationException {
        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.ClickOnButtonFAQ(locatorQuestion);
        mainPage.checkErrorTextFAQ(locatorAnswer, textFAQ);
    }

}
