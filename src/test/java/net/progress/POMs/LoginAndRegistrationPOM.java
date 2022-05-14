package net.progress.POMs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginAndRegistrationPOM extends LoadableComponent {
    private final WebDriver driver;
    @FindBy(how = How.ID, using = "reg_email")
    private WebElement registerUsername;
    @FindBy(how = How.ID, using = "reg_password")
    private WebElement registerPassword;
    @FindBy(how = How.ID, using = "register")
    private WebElement registerButton;

    public LoginAndRegistrationPOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void registerUser(String username, String password) throws InterruptedException {

        WebElement loginTextBox = driver.findElement(By.id("reg_email"));
        loginTextBox.sendKeys(username + "@abv.bg");

        WebElement passwordTextBox = driver.findElement(By.id("reg_password"));
        for (int i = 0; i < password.length(); i++) {
            passwordTextBox.sendKeys(password.substring(i, i + 1));
            Thread.sleep(50);
        }

        driver.findElement(By.name("register")).click();
    }

    @Override
    protected void load() {
        driver.get("http://practice.automationtesting.in/my-account/");
    }

    @Override
    protected void isLoaded() throws Error {
        Wait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
    }
}
