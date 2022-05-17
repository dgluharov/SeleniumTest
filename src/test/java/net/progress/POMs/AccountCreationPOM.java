package net.progress.POMs;

import net.progress.helper.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class AccountCreationPOM extends LoadableComponent {
    private final WebDriver driver;

    @FindBy(how = How.ID, using = "id_gender1")
    private WebElement maleGender;
    @FindBy(how = How.ID, using = "id_gender2")
    private WebElement femaleGender;
    @FindBy(how = How.ID, using = "customer_firstname")
    private WebElement customerFirstName;
    @FindBy(how = How.ID, using = "customer_lastname")
    private WebElement customerLastName;
    @FindBy(how = How.ID, using = "passwd")
    private WebElement password;
    //    @FindBy(how = How.ID, using = "firstname")
//    private WebElement firstName;
//    @FindBy(how = How.ID, using = "lastname")
//    private WebElement lastName;
    @FindBy(how = How.ID, using = "address1")
    private WebElement address;
    @FindBy(how = How.ID, using = "city")
    private WebElement city;
    @FindBy(how = How.ID, using = "id_state")
    private WebElement state;
    @FindBy(how = How.ID, using = "postcode")
    private WebElement postcode;
    @FindBy(how = How.ID, using = "id_country")
    private WebElement country;
    @FindBy(how = How.ID, using = "phone_mobile")
    private WebElement mobilePhone;
    @FindBy(how = How.ID, using = "alias")
    private WebElement addressAlias;
    @FindBy(how = How.ID, using = "submitAccount")
    private WebElement registerButton;

    public AccountCreationPOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    public void load() {
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation");
    }

    @Override
    public void isLoaded() throws Error {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
    }

    public void populateUserData(User user) {
        if (user.getGender().equals("1")) {
            maleGender.click();
        } else {
            femaleGender.click();
        }
        customerFirstName.sendKeys(user.getFirstName());
        customerLastName.sendKeys(user.getLastName());
        password.sendKeys(user.getPassword());
        address.sendKeys(user.getAddress());
        city.sendKeys(user.getCity());
        Select select = new Select(state);
        WebElement option = select.getFirstSelectedOption();
        String defaultItem = option.getText();
        System.out.println(defaultItem);

    }
}
