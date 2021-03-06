package net.progress.POMs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ProductPOM {
    private final WebDriver driver;
    @FindBy(how = How.CSS, using = ".single_add_to_cart_button")
    private WebElement addToBasketButton;

    public ProductPOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addProductInBasket() {
        addToBasketButton.click();
    }

}
