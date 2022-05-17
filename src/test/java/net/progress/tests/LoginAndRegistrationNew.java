package net.progress.tests;

import net.progress.POMs.AccountCreationPOM;
import net.progress.POMs.LoginAndRegistrationNewPOM;
import net.progress.helper.DriverHelper;
import net.progress.helper.User;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.Random;

public class LoginAndRegistrationNew {
    private WebDriver driver;

    @Test
    public void registerUser() {
        driver = DriverHelper.getDriver();
        LoginAndRegistrationNewPOM loginAndRegistration = new LoginAndRegistrationNewPOM(driver);
        AccountCreationPOM accountCreation = new AccountCreationPOM(driver);
        String email = generateEmail();
        User user = new User("Niko",
                "Kirov",
                "1",
                "VeryStrongAutomation1234#$",
                "Rakovska 5",
                "Sofia",
                "Alaska",
                "1000",
                "United States",
                "0888888888",
                "test");

        loginAndRegistration.load();
        loginAndRegistration.isLoaded();
        loginAndRegistration.registerUser(email);

        accountCreation.isLoaded();
        accountCreation.populateUserData(user);

    }


    private String generateEmail() {
        Random random = new Random();
        return "user" + random.nextInt(10000) + "@abv.bg";
    }
}
