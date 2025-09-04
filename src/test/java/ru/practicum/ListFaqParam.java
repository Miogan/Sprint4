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
                {MainPage.LocatorQuestionWhatIsThePrice, MainPage.LocatorAnswerWhatIsThePrice, MainPage.LocatorReferenceAnswerWhatIsThePrice},
                {MainPage.LocatorQuestionPossibilityOfSeveralScooters, MainPage.LocatorAnswerPossibilityOfSeveralScooters, MainPage.LocatorReferenceAnswerPossibilityOfSeveralScooters},
                {MainPage.LocatorQuestionTiming, MainPage.LocatorAnswerTiming, MainPage.LocatorReferenceAnswerTiming},
                {MainPage.LocatorQuestionScooterToday, MainPage.LocatorAnswerScooterToday, MainPage.LocatorReferenceAnswerScooterToday},
                {MainPage.LocatorQuestionExtendingOrReturningAScooter, MainPage.LocatorAnswerExtendingOrReturningAScooter, MainPage.LocatorReferenceAnswerExtendingOrReturningAScooter},
                {MainPage.LocatorQuestionChargerIncluded, MainPage.LocatorAnswerChargerIncluded, MainPage.LocatorReferenceAnswerChargerIncluded},
                {MainPage.LocatorQuestionGalyaCancellation, MainPage.LocatorAnswerGalyaCancellation, MainPage.LocatorReferenceAnswerGalyaCancellation},
                {MainPage.LocatorQuestionDeliveryOutsideTheMoscowRingRoad, MainPage.LocatorAnswerDeliveryOutsideTheMoscowRingRoad, MainPage.LocatorReferenceAnswerDeliveryOutsideTheMoscowRingRoad},
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
