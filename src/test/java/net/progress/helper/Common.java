package net.progress.helper;

import org.openqa.selenium.WebDriver;

import java.util.Random;

public class Common {
    public static String getUniqueUser() {
        String result = "User_";
        Random random = new Random();
        result += random.nextInt(10000);
        return result;
    }

    public static boolean checkUrl(WebDriver driver, String expectedUrl, int timeoutInSeconds) {
        String currentUrl = "";
        boolean isLoaded = false;

        for (int i = 0; i < timeoutInSeconds * 2; i++) {
            currentUrl = driver.getCurrentUrl();
            if (currentUrl.equals(expectedUrl)) {
                isLoaded = true;
                break;
            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return isLoaded;
    }

    public static boolean checkUrl(WebDriver driver, String expectedUrl) {
        return checkUrl(driver, expectedUrl, 10);
    }
}
