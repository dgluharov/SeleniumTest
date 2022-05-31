package net.progress.POMs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePagePOM extends LoadableComponent {
    private final WebDriver driver;

    public HomePagePOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    public void load() {
        driver.get("http://practice.automationtesting.in/");
    }

    @Override
    public void isLoaded() throws Error {
        Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("n2-ss-6-align")));
    }
}
