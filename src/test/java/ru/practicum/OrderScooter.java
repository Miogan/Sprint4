package ru.practicum;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.practicum.pages.MainPage;
import ru.practicum.pages.OrderPage;

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

    @Parameterized.Parameters(name = "Имя: {0}, Фамилия {1}, Город: {2}, Станция метро: {3}, Номер телефона: {4}, Дата аренды: {5}, Период аренды {6}, Цвет самоката:{7}, Комментарий курьеру: {8}")
    public static Object[][] getOrderDetails() {
        return new Object[][]{
                {"Арсений", "Троицкий", "г.Москва", "Комсомольская", "+7111111111", "01.09.2025", "двое суток", "серая безысходность", "оплата наличными"},
                {"Макар", "Иванов", "г.Саратов", "Лубянка", "+7111111111", "31.12.2025", "семеро суток", "чёрный жемчуг", "-"},
        };
    }

    @Test
    public void testNonFoundAFieldsOrderForm() throws InstantiationException {
        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);
        var order = new OrderPage(driver);
        mainPage.openMainPage();
        mainPage.closeCookes(); // окно с куками, если есть
        mainPage.clickOnButtonOrderDown(); // проверяем нижнюю кнопку заказа и дальнейший тест пойдет через нее
        //заполняем первую форму
        order.fillingOutTheOrderFormName(name);
        order.fillingOutTheOrderFormSurname(surname);
        order.fillingOutTheOrderFormCity(city);
        order.fillingOutTheOrderFormMetroStation(metroStation);
        order.fillingOutTheOrderFormTelUser(tel);
        order.clickButtonNext();
        // заполняем вторую форму
        order.fillingOutTheRentFormDateRent(dateRent);
        order.fillingOutTheRentFormRentalPeriod(rentalPeriod);
        order.fillingOutTheRentFormColorScooter(colorScooter);
        order.fillingOutTheRentFormCommentForTheCourier(commentRent);
        order.clickButtonOrder();
        order.findWindowOrderConfirmation();
    }

    @Test
    public void testNonFoundPageAddressUpButton() throws InstantiationException {
        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickOnButtonOrderUp(); // проверяем что верхняя кнопка заказать ведет на страницу заказа
    }

}
