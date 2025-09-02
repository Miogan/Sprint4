package ru.practicum;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.practicum.pages.MainPage;
import ru.practicum.pages.Order;

@RunWith(Parameterized.class)
public class OrderScooter {

    private String name;
    private String surname;
    private String city;
    private String metroStation;
    private String tel;
    private String dateRent;
    private String rentalPeriod;
    private String colorScooter;
    private String commentRent;

    @Rule
    public DriverFactory factory = new DriverFactory();

    public OrderScooter(String name, String surname, String city, String metroStation, String tel, String dateRent, String rentalPeriod, String colorScooter, String commentRent) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.metroStation = metroStation;
        this.tel = tel;
        this.dateRent = dateRent;
        this.rentalPeriod = rentalPeriod;
        this.colorScooter = colorScooter;
        this.commentRent = commentRent;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderDetails() {
        return new Object[][]{
                {"Арсений", "Троицкий", "г.Москва", "Комсомольская", "+7111111111", "01.09.2025", "двое суток", "серая безысходность", "оплата наличными"},
         //       {"Макар", "Иванов", "г.Саратов", "Лубянка", "+7111111111", "31.12.2025", "семеро суток", "чёрный жемчуг", "-"},
        };
    }

    @Test
    public void testNonFoundАFieldsOrderForm() throws InstantiationException {
            WebDriver driver = factory.getDriver();
            var mainPage = new MainPage(driver);
            var order = new Order(driver);
            mainPage.openMainPage();
            mainPage.CloseCookes(); // окно с куками, если есть
            mainPage.ClickOnButtonOrderUp(); // проверяем что верхняя кнопка заказать ведет на страницу заказа
            mainPage.openMainPage(); // возвращаемся на стартовую страницу
            mainPage.ClickOnButtonOrderDown(); // проверяем нижнюю кнопку заказа и дальнейший тест пойдет через нее
            order.FillingOutTheOrderForm(name, surname, city, metroStation, tel);
            order.ClickButtonNext();
            order.FillingOutTheRentForm(dateRent, rentalPeriod, colorScooter, commentRent);
            order.ClickButtonOrder();
    }

}
