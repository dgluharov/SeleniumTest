package net.progress.POMs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CartPOM {
    private final WebDriver driver;

    @FindBy(how = How.CSS, using = "td[data-title=\"Subtotal\"]")
    private WebElement subtotal;

    public CartPOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getSubtotal() {
        return subtotal;
    }
}
