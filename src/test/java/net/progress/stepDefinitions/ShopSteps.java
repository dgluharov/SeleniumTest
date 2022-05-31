package net.progress.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.progress.POMs.CartPOM;
import net.progress.POMs.HeaderPOM;
import net.progress.POMs.ShopPOM;
import net.progress.helper.Context;
import net.progress.helper.DriverHelper;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class ShopSteps {
    private WebDriver driver;
    private Context context;

    public ShopSteps() {
        this.context = new Context();
        driver = DriverHelper.getDriver();
    }

    @Given("a user is on the shop page")
    public void aUserIsOnTheShopPage() {
        ShopPOM shopPOM = new ShopPOM(driver);
        shopPOM.load();
        shopPOM.isLoaded();
        context.shopPOM = shopPOM;
    }

    @Given("the user adds the following items to the cart:")
    public void theUserAddsTheFollowingItemsToTheCart(DataTable dataTable) throws InterruptedException {
        ShopPOM shopPOM = new ShopPOM(driver);
        Map<String, String> items = dataTable.asMaps().get(0);
        for (String key : items.keySet()) {
            String bookName = items.get(key);
            if (bookName != null && !bookName.trim().equals("")) {
                context.shopPOM.selectBookByName(bookName);
                context.quantityAddedProducts += 1;
                context.totalPriceAddedProducts += shopPOM.getProductPrice(bookName);
            }
        }
        context.items = items;
    }

    @When("the user navigates to the cart")
    public void theUserNavigatesToTheCart() {
        HeaderPOM headerPOM = new HeaderPOM(driver);
        headerPOM.navigateToCart();
    }

    @Then("all items are successfully added")
    public void allItemsAreSuccessfullyAdded() {
        CartPOM cartPOM = new CartPOM(driver);
        Map<String, String> itemsExpected = context.items;
        for (String key : itemsExpected.keySet()) {
            String bookTitle = itemsExpected.get(key);
            if (bookTitle != null && !bookTitle.equals("")) {
                Assert.assertTrue(String.format("Book with %s is not added to the cart", bookTitle),
                        cartPOM.doesItemExist(bookTitle));
            }
        }
        Assert.assertEquals("The quantity of added products in not as the quantity in cart page",
                context.quantityAddedProducts, cartPOM.getTotalQuantityOfItems());
    }

    @Then("the cart link is properly updated")
    public void validateTheCartLink() {
        HeaderPOM headerPOM = new HeaderPOM(driver);
        Assert.assertEquals("The quantity of added products in not as the quantity in cart page",
                context.quantityAddedProducts, headerPOM.getCartItemsQuantity());
        Assert.assertEquals("The total price is not as the expected one",
                context.totalPriceAddedProducts, headerPOM.getTotalPrice(), 0.1);
    }

}
