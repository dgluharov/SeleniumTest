package net.progress.tests;

import net.progress.helper.DriverHelper;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.ThreadLocalRandom;

public class LoginAndRegistration {
    private WebDriver driver;

    @Test
    public void sample() {
        driver = DriverHelper.getDriver();
        driver.get("http://practice.automationtesting.in/my-account/");

        WebElement loginTextBox = driver.findElement(By.id("reg_email"));

        loginTextBox.sendKeys("dgluharov");

        driver.close();
    }

    @Test
    public void sampleCSS() {
        driver = DriverHelper.getDriver();
        driver.get("http://practice.automationtesting.in/shop/");

        WebElement firstImage = driver.findElement(By.cssSelector(".post-170 > a:nth-child(1) > img:nth-child(1)"));

        firstImage.click();

        driver.close();
    }

    @Test
    public void registerUser() throws InterruptedException {
        driver = DriverHelper.getDriver();
        String username = "testMail" + ThreadLocalRandom.current().nextInt(1, 1000 + 1);
        String email = username + "@gmail.com";
        String password = "VeryStrongPassword123$#@";

        driver.get("http://practice.automationtesting.in/my-account/");
        WebElement loginTextBox = driver.findElement(By.id("reg_email"));
        WebElement registerButton = driver.findElement(By.name("register"));
        WebElement passwordTextBox = driver.findElement(By.id("reg_password"));
        WebElement asterisk = driver.findElement(By.cssSelector(".register > p:nth-child(1) > label:nth-child(1) > span:nth-child(1)"));


        loginTextBox.sendKeys(email);

        for (int i = 0; i < password.length(); i++) {
            passwordTextBox.sendKeys(password.charAt(i) + "");
            Thread.sleep(50);
        }
        registerButton.click();

        String confirmMessage = driver.findElement(By.cssSelector(".woocommerce-MyAccount-content")).getText();

        Assert.assertTrue(confirmMessage.contains(username));

        driver.close();
    }
}
