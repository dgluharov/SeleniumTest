package net.progress.tests;

import net.progress.helper.DriverHelper;
import net.progress.helper.SelectorTypes;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        String username = getUniqueUser();
        String password = "VeryStrongAutomation1234#$";

        registerUser(driver, username, password);


        String confirmMessage = driver.findElement(By.cssSelector(".woocommerce-MyAccount-content > p:nth-child(1)")).getText();

        Assert.assertEquals("Hello message is not as expected one", String.format("Hello %s (not %s? Sign out)", username, username), confirmMessage);

        driver.close();
    }


    @Test
    public void loginUser() throws InterruptedException {
        driver = DriverHelper.getDriver();
        String username = getUniqueUser();
        String password = "VeryStrongAutomation1234#$";
        registerUser(driver, username, password);
        driver.close();


        driver = DriverHelper.getDriver();
        loginUser(driver, username, password);

        String elementValue = ".woocommerce-MyAccount-content > p:nth-child(1)";
        String confirmMessage = driver.findElement(By.cssSelector(elementValue)).getText();
        Assert.assertTrue("Message element does not exist", doesElementExist(driver, SelectorTypes.CssSelector, elementValue));
        Assert.assertEquals("Hello message is not as expected one", String.format("Hello %s (not %s? Sign out)", username, username), confirmMessage);

        driver.close();
    }


    @Test
    public void loginWithNonExistUser() {
        driver = DriverHelper.getDriver();
        driver.get("http://practice.automationtesting.in/my-account/");
        String username = getUniqueUser();
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement buttonLogin = driver.findElement(By.name("login"));
        usernameField.sendKeys(username + "@abv.bg");
        String password = "VeryStrongAutomation1234#$";
        passwordField.sendKeys(password);
        buttonLogin.click();

        String elementValue = ".woocommerce-error > li:nth-child(1)";
        String confirmMessage = driver.findElement(By.cssSelector(elementValue)).getText();
        Assert.assertTrue("Message element does not exist", doesElementExist(driver, SelectorTypes.CssSelector, elementValue));
        Assert.assertEquals("Hello message is not as expected one", "Error: A user could not be found with this email address.", confirmMessage);

        driver.close();
    }

    private boolean doesElementExist(WebDriver driver, SelectorTypes findBy, String value) {
        List<WebElement> elements = new ArrayList<>();
        switch (findBy) {
            case ID:
                elements = driver.findElements(By.id(value));
                break;
            case Name:
                elements = driver.findElements(By.name(value));
                break;
            case XPath:
                elements = driver.findElements(By.xpath(value));
                break;
            case CssSelector:
                elements = driver.findElements(By.cssSelector(value));
                break;
            case ClassName:
                elements = driver.findElements(By.className(value));
                break;
            default:
                Assert.fail("There is no definition for selector of type " + findBy);
                break;
        }
        return elements.size() > 0;
    }

    private void registerUser(WebDriver driver, String username, String password) throws InterruptedException {
        driver.get("http://practice.automationtesting.in/my-account/");

        WebElement loginTextBox = driver.findElement(By.id("reg_email"));
        loginTextBox.sendKeys(username + "@abv.bg");

        WebElement passwordTextBox = driver.findElement(By.id("reg_password"));
        for (int i = 0; i < password.length(); i++) {
            passwordTextBox.sendKeys(password.substring(i, i + 1));
            Thread.sleep(50);
        }

        driver.findElement(By.name("register")).click();
    }

    private void registerUserWithStaticUsername(WebDriver driver, String username, String password) throws InterruptedException {
        driver.get("http://practice.automationtesting.in/my-account/");

        WebElement loginTextBox = driver.findElement(By.id("reg_email"));
        loginTextBox.sendKeys(username);

        WebElement passwordTextBox = driver.findElement(By.id("reg_password"));
        for (int i = 0; i < password.length(); i++) {
            passwordTextBox.sendKeys(password.substring(i, i + 1));
            Thread.sleep(50);
        }

        driver.findElement(By.name("register")).click();
    }

    private void loginUser(WebDriver driver, String username, String password) {
        driver.get("http://practice.automationtesting.in/my-account/");
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement buttonLogin = driver.findElement(By.name("login"));

        usernameField.sendKeys(username + "@abv.bg");
        passwordField.sendKeys(password);
        buttonLogin.click();
    }

    private void loginWithoutUsername(WebDriver driver, String password) {
        driver.get("http://practice.automationtesting.in/my-account/");
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement buttonLogin = driver.findElement(By.name("login"));

        passwordField.sendKeys(password);
        buttonLogin.click();
    }

    private String getUniqueUser() {
        String result = "User_";

        Random random = new Random();
        result += random.nextInt(10000);

        return result;
    }
    /*Homework*/

    @Test
    public void loginWithoutPassword() throws InterruptedException {
        driver = DriverHelper.getDriver();
        driver.get("http://practice.automationtesting.in/my-account/");

        String username = getUniqueUser();
        String password = "VeryStrongAutomation1234#$";
        registerUser(driver, username, password);
        driver.close();

        driver = DriverHelper.getDriver();
        loginUser(driver, username, "");

        String elementValue = ".woocommerce-error > li:nth-child(1)";
        String alertMessage = driver.findElement(By.cssSelector(elementValue)).getText();
        Assert.assertTrue("Message element does not exist", doesElementExist(driver, SelectorTypes.CssSelector, elementValue));
        Assert.assertEquals("Alert message is not as expected one", "Error: Password is required.", alertMessage);

        driver.close();
    }

    @Test
    public void loginWithoutInvalidUsername() throws InterruptedException {
        driver = DriverHelper.getDriver();
        driver.get("http://practice.automationtesting.in/my-account/");

        String username = getUniqueUser();
        String password = "VeryStrongAutomation1234#$";
        registerUser(driver, username, password);
        driver.close();

        driver = DriverHelper.getDriver();
        loginUser(driver, "", password);

        String elementValue = ".woocommerce-error > li:nth-child(1)";
        String alertMessage = driver.findElement(By.cssSelector(elementValue)).getText();
        Assert.assertTrue("Message element does not exist", doesElementExist(driver, SelectorTypes.CssSelector, elementValue));
        Assert.assertEquals("Alert message is not as expected one", "ERROR: Invalid username. Lost your password?", alertMessage);

        driver.close();
    }

    @Test
    public void loginWithoutUsername() throws InterruptedException {
        driver = DriverHelper.getDriver();
        driver.get("http://practice.automationtesting.in/my-account/");

        String username = getUniqueUser();
        String password = "VeryStrongAutomation1234#$";
        registerUser(driver, username, password);
        driver.close();

        driver = DriverHelper.getDriver();
        loginWithoutUsername(driver, password);

        String elementValue = ".woocommerce-error > li:nth-child(1)";
        String alertMessage = driver.findElement(By.cssSelector(elementValue)).getText();
        Assert.assertTrue("Message element does not exist", doesElementExist(driver, SelectorTypes.CssSelector, elementValue));
        Assert.assertEquals("Alert message is not as expected one", "Error: Username is required.", alertMessage);
        driver.close();
    }

    @Test
    public void loginWithoutUsernameAndPassword() {
        driver = DriverHelper.getDriver();
        driver.get("http://practice.automationtesting.in/my-account/");

        WebElement buttonLogin = driver.findElement(By.name("login"));
        buttonLogin.click();
        String elementValue = ".woocommerce-error > li:nth-child(1)";
        String alertMessage = driver.findElement(By.cssSelector(elementValue)).getText();
        Assert.assertTrue("Message element does not exist", doesElementExist(driver, SelectorTypes.CssSelector, elementValue));
        Assert.assertEquals("Alert message is not as expected one", "Error: Username is required.", alertMessage);
        driver.close();
    }

    @Test
    public void loginWithRandomPassword() throws InterruptedException {
        driver = DriverHelper.getDriver();
        driver.get("http://practice.automationtesting.in/my-account/");

        String username = getUniqueUser();
        String password = "VeryStrongAutomation1234#$";
        registerUser(driver, username, password);
        driver.close();

        driver = DriverHelper.getDriver();
        loginUser(driver, username, "password");

        String elementValue = ".woocommerce-error > li:nth-child(1)";
        String alertMessage = driver.findElement(By.cssSelector(elementValue)).getText();
        Assert.assertTrue("Message element does not exist", doesElementExist(driver, SelectorTypes.CssSelector, elementValue));
        Assert.assertEquals("Alert message is not as expected one", String.format("ERROR: The password you " +
                "entered for the username %s is incorrect. Lost your password?", username + "@abv.bg"), alertMessage);
        driver.close();
    }

    @Test
    public void registerUserWithoutUsername() throws InterruptedException {
        driver = DriverHelper.getDriver();
        driver.get("http://practice.automationtesting.in/my-account/");
        String password = "VeryStrongAutomation1234#$";
        registerUserWithStaticUsername(driver, "", password);

        String elementValue = ".woocommerce-error > li:nth-child(1)";
        String alertMessage = driver.findElement(By.cssSelector(elementValue)).getText();
        Assert.assertTrue("Message element does not exist", doesElementExist(driver, SelectorTypes.CssSelector, elementValue));
        Assert.assertEquals("Alert message is not as expected one", "Error: Please provide a valid email address.", alertMessage);

        driver.close();
    }

    @Test
    public void registerUserWithoutPassword() throws InterruptedException {
        driver = DriverHelper.getDriver();
        driver.get("http://practice.automationtesting.in/my-account/");

        String username = getUniqueUser();
        String password = "";
        registerUser(driver, username, password);

        String elementValue = ".woocommerce-error > li:nth-child(1)";
        String alertMessage = driver.findElement(By.cssSelector(elementValue)).getText();
        Assert.assertTrue("Message element does not exist", doesElementExist(driver, SelectorTypes.CssSelector, elementValue));
        Assert.assertEquals("Alert message is not as expected one", "Error: Please enter an account password.", alertMessage);

        driver.close();
    }

    @Test
    public void registerExistingUser() throws InterruptedException {
        driver = DriverHelper.getDriver();
        driver.get("http://practice.automationtesting.in/my-account/");

        String username = getUniqueUser();
        String password = "VeryStrongAutomation1234#$";
        registerUser(driver, username, password);
        driver.close();

        driver = DriverHelper.getDriver();
        registerUser(driver, username, password);

        String elementValue = ".woocommerce-error > li:nth-child(1)";
        String alertMessage = driver.findElement(By.cssSelector(elementValue)).getText();
        Assert.assertTrue("Message element does not exist", doesElementExist(driver, SelectorTypes.CssSelector, elementValue));
        Assert.assertEquals("Alert message is not as expected one", "Error: An account is already " +
                "registered with your email address. Please login.", alertMessage);
    }

    /*@Test
    public void registerUserWithInvalidUsername() throws InterruptedException {
        driver = DriverHelper.getDriver();
        driver.get("http://practice.automationtesting.in/my-account/");

        String password = "VeryStrongAutomation1234#$";
        registerUserWithStaticUsername(driver, "test", password);

        String elementValue = ".woocommerce-error > li:nth-child(1)";
        String alertMessage = driver.findElement(By.cssSelector(elementValue)).getText();
        Assert.assertTrue("Message element does not exist", doesElementExist(driver, SelectorTypes.CssSelector, elementValue));
        Assert.assertEquals("Alert message is not as expected one", "Error: Please provide a valid email address.", alertMessage);
        //driver.close();
    }*/
    @Test
    public void registerUserWithWeekPassword() throws InterruptedException {
        driver = DriverHelper.getDriver();
        driver.get("http://practice.automationtesting.in/my-account/");

        String username = getUniqueUser();
        String password = "sdsdsdsd";
        registerUser(driver, username, password);
        WebElement passwordTextBox = driver.findElement(By.id("reg_password"));
        passwordTextBox.sendKeys("aaa");

        String elementValue = ".woocommerce-password-strength";
        String alertMessage = driver.findElement(By.cssSelector(elementValue)).getText();
        Assert.assertTrue("Message element does not exist", doesElementExist(driver, SelectorTypes.CssSelector, elementValue));
        Assert.assertEquals("Alert message is not as expected one", "Weak - Please enter a stronger password.", alertMessage);

        driver.close();
    }


    @Test
    public void runAllTest() throws InterruptedException {
        loginWithoutUsername();
        loginWithoutPassword();
        loginWithNonExistUser();
        loginWithoutInvalidUsername();
        loginWithoutUsernameAndPassword();
        loginWithRandomPassword();
    }
}
