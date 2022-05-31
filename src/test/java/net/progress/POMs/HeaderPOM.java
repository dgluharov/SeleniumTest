package net.progress.POMs;

import net.progress.helper.Common;
import net.progress.helper.DataFormats;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HeaderPOM {
    private final WebDriver driver;
    private CartPOM cartPOM;


    @FindBy(how = How.ID, using = "menu-item-40")
    private WebElement shopButton;
    @FindBy(how = How.ID, using = "menu-item-50")
    private WebElement myAccountButton;
    @FindBy(how = How.ID, using = "menu-item-224")
    private WebElement testCasesButton;
    @FindBy(how = How.ID, using = "wpmenucartli")
    private WebElement cartButton;
    @FindBy(how = How.CSS, using = ".cartcontents")
    private WebElement cartItemsQuantity;
    @FindBy(how = How.CSS, using = "a.wpmenucart-contents span.amount")
    private WebElement totalPrice;

    public HeaderPOM(WebDriver driver) {
        this.driver = driver;
        this.cartPOM = new CartPOM(driver);
        PageFactory.initElements(driver, this);
    }

    public int getCartItemsQuantity() {
        return Integer.parseInt(cartItemsQuantity.getText().substring(0, 1).trim());
    }

    public double getTotalPrice() {
        return DataFormats.reformatPrice(totalPrice);
    }


    public void navigateToShop() {
        shopButton.click();
    }

    public void navigateToCart() {
        cartButton.click();
        Assert.assertTrue("The user is not redirected to Cart page after 10 seconds", Common.checkUrl(driver, cartPOM.getUrl()));
    }
}
