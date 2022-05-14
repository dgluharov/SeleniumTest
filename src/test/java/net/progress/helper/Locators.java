package net.progress.helper;

import org.openqa.selenium.WebElement;

public class Locators {
    private WebElement element;

    public Locators(WebElement element) {
        this.element = element;
    }

    public WebElement getElement() {
        return element;
    }

    public void setElement(WebElement element) {
        this.element = element;
    }
}
