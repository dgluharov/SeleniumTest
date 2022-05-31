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

public class WelcomePagePOM extends LoadableComponent {
    private WebDriver driver;
    @FindBy(how = How.CSS, using = ".woocommerce-MyAccount-content > p:nth-child(1)")
    private WebElement welcomeMessage;
    @FindBy(how = How.CSS, using = ".woocommerce-MyAccount-content > p:nth-child(1) > a:nth-child(2)")
    private WebElement signOutLink;

    public WelcomePagePOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getWelcomeMessage() {
        return welcomeMessage;
    }

    @Override
    public void load() {
        driver.get("http://practice.automationtesting.in/my-account/");
    }

    @Override
    public void isLoaded() throws Error {
        Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(signOutLink));
    }
}
