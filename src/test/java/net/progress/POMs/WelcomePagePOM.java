package net.progress.POMs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WelcomePagePOM {
    private WebDriver driver;
    private WebElement message;

    public WelcomePagePOM(WebDriver driver) {
        this.driver = driver;
        message = driver.findElement(By.cssSelector())
    }
}
