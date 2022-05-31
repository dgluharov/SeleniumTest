package net.progress.POMs;

import net.progress.helper.Common;
import net.progress.helper.DataFormats;
import org.junit.Assert;
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
    private final String url = "http://practice.automationtesting.in/shop/";

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
            if (bookTitle.getText().equalsIgnoreCase(title)) {
                product.findElement(By.className("button")).click();
                price = DataFormats.reformatPrice(product.findElement(By.className("amount")));
                Thread.sleep(500);
                break;
            }
        }
        return price;
    }

    public void selectBookByName(String name) throws InterruptedException {
        WebElement result = null;
        for (WebElement product : products) {
            WebElement bookTitle = product.findElement(By.tagName("h3"));
            if (bookTitle.getText().equalsIgnoreCase(name)) {
                result = product.findElement(By.className("button"));
            }
        }
        Assert.assertNotNull(String.format("There is no item with name %s", name), result);
        result.click();
        Thread.sleep(200);
    }

    public void calculateProductsPrice() {

    }

    @Override
    public void load() {
        driver.get(this.url);
    }

    @Override
    public void isLoaded() throws Error {
        Common.checkUrl(driver, this.url);
    }

    public double getProductPrice(String bookName) {
        double result = 0d;
        for (WebElement product : products) {
            WebElement bookTitle = product.findElement(By.tagName("h3"));
            List<WebElement> bookPrices = product.findElements(By.className("amount"));
            if (bookTitle.getText().equalsIgnoreCase(bookName)) {
                if (bookPrices.size() > 1) {
                    result += DataFormats.reformatPrice(bookPrices.get(1));
                } else {
                    result += DataFormats.reformatPrice(bookPrices.get(0));
                }
            }
        }
        return result;
    }
}
