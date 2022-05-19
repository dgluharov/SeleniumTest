package net.progress.POMs;

import net.progress.helper.DataFormats;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.util.List;

public class ShopPOM extends LoadableComponent {
    private final WebDriver driver;

    @FindBy(how = How.CLASS_NAME, using = "type-product")
    private List<WebElement> products;
   /* @FindBy(how = How.CSS, using = "a.woocommerce-LoopProduct-link > img")
    private List<WebElement> productImages;*/
    /*@FindBy(how = How.CLASS_NAME, using = "product_type_simple")
    private List<WebElement> addToCartButton;*/

    public ShopPOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public double selectItemByTitleAndReturnPrice(String title) throws InterruptedException {
        double price = 0d;
        for (WebElement product : products) {
            WebElement bookTitle = product.findElement(By.tagName("h3"));
            if (bookTitle.getText().toLowerCase().equalsIgnoreCase(title)) {
                product.findElement(By.className("button")).click();
                price = DataFormats.reformatPrice(product.findElement(By.className("amount")));
                Thread.sleep(500);
                break;
            }
        }
        return price;
    }

    @Override
    public void load() {
        driver.get("http://practice.automationtesting.in/shop/");
    }

    @Override
    public void isLoaded() throws Error {

    }
}
