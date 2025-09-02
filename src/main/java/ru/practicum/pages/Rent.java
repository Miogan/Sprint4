package ru.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Rent {
    private final WebDriver driver;

    public Rent(WebDriver driver) {
        this.driver = driver;
    }

//    public void FillingOutTheRentForm(String dateRent, String rentalPeriod, String colorScooter, String commentRent){
//        driver.findElement(By.className(".Input_Input__1iN_Z Input_Responsible__1jDKN.Input_Filled__1rDxs react-datepicker-ignore-onclickoutside")).sendKeys(dateRent);
//        driver.findElement(By.className(".Dropdown-control")).sendKeys(rentalPeriod);
//        driver.findElement(By.className(".Order_Checkboxes__3lWSI.Order_FilledContainer__2MKAk")).sendKeys(colorScooter);
//        driver.findElement(By.className(".Input_Input__1iN_Z Input_Responsible__1jDKN")).sendKeys(commentRent);
//        driver.findElement(By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM")).click();
//    }
}
