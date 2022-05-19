package net.progress.tests;

import net.progress.POMs.CartPOM;
import net.progress.POMs.HeaderPOM;
import net.progress.POMs.HomePagePOM;
import net.progress.POMs.ShopPOM;
import net.progress.helper.DataFormats;
import net.progress.helper.DriverHelper;
import net.progress.helper.ElementHelper;
import net.progress.helper.SelectorTypes;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class MakeOrder {
    private WebDriver driver;

    @Test
    public void addItemToBasket() throws InterruptedException {
        driver = DriverHelper.getDriver();
        HeaderPOM headerPOM = new HeaderPOM(driver);
        HomePagePOM homePagePOM = new HomePagePOM(driver);
        ShopPOM shopPOM = new ShopPOM(driver);
        CartPOM cartPOM = new CartPOM(driver);
        double totalPrice = 0d;


        List<String> books = new ArrayList<>();
        books.add("HTML5 WebApp Develpment");
        books.add("Functional Programming in JS");

        homePagePOM.load();
        headerPOM.navigateToShop();
        for (String book : books) {
            totalPrice += shopPOM.selectItemByTitleAndReturnPrice(book);
        }
        headerPOM.navigateToCart();

        Assert.assertEquals("The total price is not the expected one",
                totalPrice,
                DataFormats.reformatPrice(cartPOM.getSubtotal()), 0.1);
        for (String book : books) {
            Assert.assertTrue("The book is not added to the cart - " + book,
                    ElementHelper.doesElementExist(driver, SelectorTypes.XPath, String.format("//a[text()='%s']", book)));
        }
    }
}
