package net.progress.POMs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ProductPOM {
    private final WebDriver driver;

    public ProductPOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
