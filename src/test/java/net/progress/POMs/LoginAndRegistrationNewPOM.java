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

import java.time.Duration;

public class LoginAndRegistrationNewPOM extends LoadableComponent {
    private final WebDriver driver;

    @FindBy(how = How.ID, using = "email_create")
    private WebElement createAccountEmail;
    @FindBy(how = How.ID, using = "SubmitCreate")
    private WebElement createAccountButton;
    @FindBy(how = How.ID, using = "email")
    private WebElement loginEmail;
    @FindBy(how = How.ID, using = "passwd")
    private WebElement loginPassword;
    @FindBy(how = How.ID, using = "SubmitLogin")
    private WebElement loginButton;

    public LoginAndRegistrationNewPOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    public void load() {
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    }

    @Override
    public void isLoaded() throws Error {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(createAccountButton));
    }

    public void registerUser(String email) {
        createAccountEmail.sendKeys(email);
        createAccountButton.click();
    }
}
