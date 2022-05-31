package net.progress.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.progress.POMs.LoginAndRegistrationPOM;
import net.progress.POMs.WelcomePagePOM;
import net.progress.helper.Common;
import net.progress.helper.Context;
import net.progress.helper.DriverHelper;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class LoginAndRegistration {
    private WebDriver driver;
    private Context context;

    public LoginAndRegistration() {
        this.context = new Context();
    }

    @Given("there is a new user")
    public void thereIsAUser() {
        String user = Common.getUniqueUser();
        context.user = user;
    }

    @When("the user fills the registration form with appropriate data")
    public void userFillsCredentials() throws InterruptedException {
        driver = DriverHelper.getDriver();
        LoginAndRegistrationPOM loginAndRegistrationPOM = new LoginAndRegistrationPOM(driver);


        loginAndRegistrationPOM.load();
        loginAndRegistrationPOM.isLoaded();
        loginAndRegistrationPOM.registerUser(context.user);
    }

    @Then("the user is successfully registered")
    public void theUserIsSuccessfullyRegistered() {
        WelcomePagePOM welcomePagePOM = new WelcomePagePOM(driver);
        String confirmMessage = welcomePagePOM.getWelcomeMessage().getText();

        Assert.assertEquals("Hello message is not as expected one",
                String.format("Hello %s (not %s? Sign out)", context.user, context.user), confirmMessage);

    }
}
