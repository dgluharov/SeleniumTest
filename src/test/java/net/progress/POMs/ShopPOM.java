package net.progress.POMs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ShopPOM {
    private final WebDriver driver;


    @FindBy(how = How.CSS, using = ".post-169")
    private WebElement firstProduct;
    @FindBy(how = How.CSS, using = ".post-170")
    private WebElement secondProduct;


    public ShopPOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
