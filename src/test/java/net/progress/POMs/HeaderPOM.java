package net.progress.POMs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HeaderPOM {
    private final WebDriver driver;


    @FindBy(how = How.ID, using = "menu-item-40")
    private WebElement shopButton;
    @FindBy(how = How.ID, using = "menu-item-50")
    private WebElement myAccountButton;
    @FindBy(how = How.ID, using = "menu-item-224")
    private WebElement testCasesButton;
    @FindBy(how = How.ID, using = "wpmenucartli")
    private WebElement cartButton;

    public HeaderPOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToShop() {
        shopButton.click();
    }

    public void navigateToCart() {
        cartButton.click();
    }
}
