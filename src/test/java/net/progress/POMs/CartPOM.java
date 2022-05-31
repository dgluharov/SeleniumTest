package net.progress.POMs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPOM {
    private final WebDriver driver;
    private final String url = "http://practice.automationtesting.in/basket/";

    @FindBy(how = How.CSS, using = "td.product-name > a")
    private List<WebElement> cartItems;

    @FindBy(how = How.CSS, using = "td[data-title=\"Subtotal\"]")
    private WebElement subtotal;

    @FindBy(how = How.CSS, using = ".qty")
    private List<WebElement> quantityFields;

    public CartPOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean doesItemExist(String bookTitle) {
        boolean result = false;
        for (WebElement webElement : cartItems) {
            if (webElement.getText().equalsIgnoreCase(bookTitle)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public int getTotalQuantityOfItems() {
        int result = 0;
        for (WebElement quantityField : quantityFields) {
            result += Integer.parseInt(quantityField.getAttribute("value"));
        }
        return result;
    }

    public String getUrl() {
        return this.url;
    }

    public WebElement getSubtotal() {
        return subtotal;
    }
}
