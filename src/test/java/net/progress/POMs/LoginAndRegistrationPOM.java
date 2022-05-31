package net.progress.POMs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginAndRegistrationPOM extends LoadableComponent {
    private final WebDriver driver;

    @FindBy(how = How.ID, using = "reg_email")
    private WebElement registerUsername;

    @FindBy(how = How.ID, using = "reg_password")
    private WebElement registerPassword;
    private final String emailDomain = "@abv.bg";
    private final String password = "VeryStrongAutomation1234#$";
    @FindBy(how = How.NAME, using = "register")
    private WebElement registerButton;
    @FindBy(how = How.ID, using = "username")
    private WebElement loginUsername;
    @FindBy(how = How.ID, using = "password")
    private WebElement loginPassword;
    @FindBy(how = How.NAME, using = "login")
    private WebElement loginButton;

    public LoginAndRegistrationPOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getEmailDomain() {
        return emailDomain;
    }

    public String getPassword() {
        return password;
    }

    public void registerUser(String username) throws InterruptedException {
        registerUsername.sendKeys(username + emailDomain);
        for (int i = 0; i < password.length(); i++) {
            registerPassword.sendKeys(password.substring(i, i + 1));
            Thread.sleep(50);
        }
        registerButton.click();
    }

    public void loginUser(String username) {
        loginUsername.sendKeys(username + emailDomain);
        loginPassword.sendKeys(password);
        loginButton.click();
    }

    @Override
    public void load() {
        driver.get("http://practice.automationtesting.in/my-account/");
    }

    @Override
    public void isLoaded() throws Error {
        Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
    }
}
