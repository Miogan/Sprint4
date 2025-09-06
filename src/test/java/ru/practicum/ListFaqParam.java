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
    private final int questionIndex;

    @Rule
    public DriverFactory factory = new DriverFactory();

    public ListFaqParam(int questionIndex) {
        this.questionIndex = questionIndex;
    }

    @Parameterized.Parameters(name = "Локатор вопроса Локатор ответа Эталонный ответ{1}")
    public static Object[][] getAddress() {
        return new Object[][]{
                {0}, {1}, {2}, {3}, {4}, {5}, {6}, {7},
        };
    }

    @Test
    public void testNonFoundListItemTextParam() throws InstantiationException {
        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);
        mainPage.openMainPage();
        By questionLocator = mainPage.getQuestionLocator(questionIndex);
        By answerLocator = mainPage.getAnswerLocator(questionIndex);
        String expectedText = mainPage.getExpectedAnswer(questionIndex);
        mainPage.clickOnButtonFAQ(questionLocator);
        mainPage.checkErrorTextFAQ(answerLocator, expectedText);
    }

}
