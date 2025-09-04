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
    @Parameterized.Parameters(name = "Локатор вопроса: {0}, Локатор ответа: {1}, Эталонный текст ответа: {2}")
    public static Object[][] getAddress() {
        return new Object[][]{
                {MainPage.locatorQuestionWhatIsThePrice, MainPage.locatorAnswerWhatIsThePrice, MainPage.locatorReferenceAnswerWhatIsThePrice},
                {MainPage.locatorQuestionPossibilityOfSeveralScooters, MainPage.locatorAnswerPossibilityOfSeveralScooters, MainPage.locatorReferenceAnswerPossibilityOfSeveralScooters},
                {MainPage.locatorQuestionTiming, MainPage.locatorAnswerTiming, MainPage.locatorReferenceAnswerTiming},
                {MainPage.locatorQuestionScooterToday, MainPage.locatorAnswerScooterToday, MainPage.locatorReferenceAnswerScooterToday},
                {MainPage.locatorQuestionExtendingOrReturningAScooter, MainPage.locatorAnswerExtendingOrReturningAScooter, MainPage.locatorReferenceAnswerExtendingOrReturningAScooter},
                {MainPage.locatorQuestionChargerIncluded, MainPage.locatorAnswerChargerIncluded, MainPage.locatorReferenceAnswerChargerIncluded},
                {MainPage.locatorQuestionGalyaCancellation, MainPage.locatorAnswerGalyaCancellation, MainPage.locatorReferenceAnswerGalyaCancellation},
                {MainPage.locatorQuestionDeliveryOutsideTheMoscowRingRoad, MainPage.locatorAnswerDeliveryOutsideTheMoscowRingRoad, MainPage.locatorReferenceAnswerDeliveryOutsideTheMoscowRingRoad},
        };
    }

    @Test
    public void testNonFoundListItemTextParam() throws InstantiationException {
        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickOnButtonFAQ(locatorQuestion);
        mainPage.checkErrorTextFAQ(locatorAnswer, textFAQ);
    }
}
